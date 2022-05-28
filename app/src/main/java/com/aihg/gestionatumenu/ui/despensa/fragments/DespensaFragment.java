package com.aihg.gestionatumenu.ui.despensa.fragments;

import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_BORRAR_DESPENSA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_UPDATE_DESPENSA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.despensa.listener.DespensaListener;
import com.aihg.gestionatumenu.ui.despensa.adapters.ItemsCatDespensaAdapter;
import com.aihg.gestionatumenu.ui.despensa.viewmodel.DespensaViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DespensaFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsCatDespensaAdapter adapter;
    private View view;
    private Despensa  aInsertar;
    private List<Despensa> despensa;
    private DespensaViewModel viewModel;

    private DespensaListener onItemClickListener;

    private List<Ingrediente> toUpdate;
    private boolean actualizado;

    public DespensaFragment() {
        aInsertar = new Despensa();
        actualizado = true;
        despensa = new ArrayList<>();
        toUpdate = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this).get(DespensaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.despensa__fragment, container, false);

        setObservers();
        saveArguments(savedInstanceState);
        setRecyclerView();

        return view;
    }

    private void actualizarDespensa() {
        if (!despensa.isEmpty() && !toUpdate.isEmpty()) {
            toUpdate.forEach(ingrediente -> {
                Despensa toUpdate = despensa.stream()
                    .filter(item -> item.getIngrediente().getId() == ingrediente.getId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("El ingrediente deberia estar relacionado"));

                toUpdate.setIngrediente(ingrediente);
                viewModel.updateDespensa(toUpdate);
                Log.i("Update Data Despensa", "Updated " + toUpdate);
            });
            toUpdate.clear();
            adapter.notifyDataSetChanged();
            actualizado = true;
        } else Log.i("Update Data Despensa", "Sin Despensa que actualizar");
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_add).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            case R.id.nav_add:
                NavDirections action = DespensaFragmentDirections.actionDespensaFragmentToBuscarIngredienteFragment();
                Navigation.findNavController(view).navigate(action);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setObservers(){
        viewModel
            .getCategorias()
            .observe(getViewLifecycleOwner(), new Observer<List<CategoriaIngrediente>>() {
                @Override
                public void onChanged(List<CategoriaIngrediente> categoriasOb) {
                    adapter.setCategorias(categoriasOb);
                }
            });
        viewModel
            .getDespensa()
            .observe(getViewLifecycleOwner(), new Observer<List<Despensa>>() {
                @Override
                public void onChanged(List<Despensa> despensaOb) {
                    adapter.setDespensaItems(despensaOb);
                    despensa = despensaOb;
                    if (aInsertar != null && !despensa.contains(aInsertar)) {
                        boolean existe = despensa.stream()
                            .map(item -> item.getIngrediente())
                            .collect(Collectors.toList())
                            .contains(aInsertar.getIngrediente());
                        if (!existe) viewModel.insertDespensa(aInsertar);
                        aInsertar = null;
                    }
                    actualizarDespensa();
                }
            });
        viewModel
            .getDespensasToUpdate()
            .observe(getViewLifecycleOwner(), new Observer<List<Ingrediente>>() {
                @Override
                public void onChanged(List<Ingrediente> ingredientes) {
                    if (!ingredientes.isEmpty() && actualizado){
                        toUpdate = ingredientes;
                        actualizado = false;
                    }
                }
            });
        onItemClickListener = new DespensaListener() {
            @Override
            public void onDeleteItem(Despensa despensa, int posicion) {
                viewModel.deleteDespensa(despensa);
                adapter.notifyItemRemoved(posicion);
                Toast.makeText(
                    view.getContext(), TOAST_BORRAR_DESPENSA, Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public void onUpdateItem(Despensa toUpdate) {
                viewModel.updateDespensa(toUpdate);
                Toast.makeText(
                    view.getContext(), TOAST_UPDATE_DESPENSA, Toast.LENGTH_SHORT
                ).show();
            }
        };
    }

    public void  setRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_d_categorias);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new ItemsCatDespensaAdapter(onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    public void saveArguments(Bundle savedInstanceState) {
        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;
        if (bundle != null) {
            DespensaFragmentArgs args = DespensaFragmentArgs .fromBundle(bundle);
            this.aInsertar = args.getDespensabuscar();
            //if (aInsertar != null) {
            //    viewModel.insertDespensa(aInsertar);
            //}
        }
    }
}