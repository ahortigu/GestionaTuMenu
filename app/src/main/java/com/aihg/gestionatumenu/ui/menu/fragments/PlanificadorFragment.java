package com.aihg.gestionatumenu.ui.menu.fragments;

import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_PLANIFICADOR_APLICADO;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_PLANIFICADOR_LIMPIO;
import static java.util.stream.Collectors.toList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.entities.Semanal;

import java.util.List;

public class PlanificadorFragment extends AbstractMenuFragment {

    public PlanificadorFragment() {
        super(false, true);
    }

    @Override
    protected void setObservers() {
        getViewModel()
                .getPlanificador()
                .observe(getViewLifecycleOwner(), new Observer<List<Planificador>>() {
                    @Override
                    public void onChanged(List<Planificador> planificadors) {
                        getAdapter().setMenu(
                                planificadors.stream()
                                        .map(item -> (MenuInterface) item)
                                        .collect(toList())
                        );
                    }
                });
        getViewModel()
                .getSemanal()
                .observe(getViewLifecycleOwner(), new Observer<List<Semanal>>() {
                    @Override
                    public void onChanged(List<Semanal> semanal) {
                    }
                });
    }

    @Override
    public void saveArguments(Bundle savedInstanceState) {
        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;
        if (bundle != null) {
            PlanificadorFragmentArgs args = PlanificadorFragmentArgs.fromBundle(bundle);
            Planificador aActualizar = args.getUpdatePlanificador();
            if (aActualizar != null) {
                getViewModel().updateRecetaPlanificador(aActualizar);
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_done).setVisible(true);
        menu.findItem(R.id.nav_clear).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        switch (item.getItemId()) {
            case R.id.nav_clear:
                getViewModel()
                        .getPlanificador()
                        .getValue()
                        .stream()
                        .map(plan -> {
                            plan.setId_receta(null);
                            return plan;
                        })
                        .collect(toList())
                        .forEach(plan -> getViewModel().updateRecetaPlanificador(plan));

                Toast.makeText(
                        getView().getContext(), TOAST_PLANIFICADOR_LIMPIO, Toast.LENGTH_SHORT
                ).show();
                getAdapter().forceNotifyDataSetChanged();
                break;
            case R.id.nav_done:
                List<Planificador> planificador = getViewModel()
                        .getPlanificador()
                        .getValue();

                List<Semanal> semanal = getViewModel()
                        .getSemanal()
                        .getValue();

                semanal.stream()
                        .map(momentoComida -> {
                            Planificador plan = planificador.stream()
                                    .filter(comida ->
                                            comida.getId_dia().equals(momentoComida.getId_dia())
                                                    && comida.getId_momento_comida().equals(momentoComida.getId_momento_comida())
                                    )
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalStateException(
                                            "Debe existir los mismos Momentos tanto en Semanal como en Planificador"
                                    ));

                            momentoComida.setId_receta(plan.getId_receta());
                            return momentoComida;
                        })
                        .collect(toList())
                        .forEach(
                                comida -> getViewModel().updateRecetaSemanal(comida)
                        );

                Toast.makeText(
                        getView().getContext(), TOAST_PLANIFICADOR_APLICADO, Toast.LENGTH_SHORT
                ).show();
                NavDirections toMenu = PlanificadorFragmentDirections.actionPlanificadorFragmentToMenuFragment();
                Navigation.findNavController(getView()).navigate(toMenu);
                getAdapter().forceNotifyDataSetChanged();
                break;
            default:
                new IllegalStateException(
                    "Problema en Planificador");
        }
        return super.onOptionsItemSelected(item);
    }
}