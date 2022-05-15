package com.aihg.gestionatumenu.ui.shared.fragments.buscarreceta;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.entities.Receta;

import java.util.ArrayList;
import java.util.List;

public class BuscarRecetaAdapter extends RecyclerView.Adapter<BuscarRecetaAdapter.BuscarRecetaViewHolder> {
    private List<Receta> recetas;
    private Planificador aRellenar;

    public BuscarRecetaAdapter() {
        this.recetas = new ArrayList<>();
    }

    @NonNull
    @Override
    public BuscarRecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__recetas__subitem, parent, false);
        return new BuscarRecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscarRecetaViewHolder holder, int position) {
        Receta receta = recetas.get(position);
        holder.txt_nombre.setText(receta.getNombre());
        holder.l_shared_buscar_receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).popBackStack();

                int idDestinoAnterior = Navigation.findNavController(view)
                        .getPreviousBackStackEntry()
                        .getDestination()
                        .getId();

                switch (idDestinoAnterior) {
                    case R.id.recetasFragment:
                        //TODO Pass receta
                        NavDirections toRecetas = BuscarRecetaFragmentDirections.actionBuscarRecetaFragmentToRecetasFragment();
                        Navigation.findNavController(view).navigate(toRecetas);
                        break;
                    case R.id.planificadorFragment:
                        aRellenar.setId_receta(receta);
                        BuscarRecetaFragmentDirections.ActionBuscarRecetaFragmentToPlanificadorFragment
                                action = BuscarRecetaFragmentDirections.actionBuscarRecetaFragmentToPlanificadorFragment();
                        action.setUpdatePlanificador(aRellenar);
                        Navigation.findNavController(view).navigate(action);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
        notifyDataSetChanged();
    }

    public void setPlanificadorRellenar(Planificador aRellenar) {
        Log.i("BUSCAR", "Buscando receta para el " + aRellenar.getNombreDia() + ", para " + aRellenar.getNombreMomentoDia());
        this.aRellenar = aRellenar;
    }

    public void setRecetasFiltradas(List<Receta> recetas) {
        this.recetas = recetas;
        notifyDataSetChanged();
    }

    public class BuscarRecetaViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;
        private ConstraintLayout l_shared_buscar_receta;

        public BuscarRecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_shared_receta_nombre);
            l_shared_buscar_receta = itemView.findViewById(R.id.l_shared_receta);
        }
    }
}
