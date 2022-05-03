package com.aihg.gestionatumenu.ui.ingredientes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aihg.gestionatumenu.IngredienteDetailsFragmentDirections;
import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.ItemsCategoriasAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.viewmodel.IngredientesViewModel;

import java.util.List;


public class IngredientesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsCategoriasAdapter adapter;
    private View view;

    private IngredientesViewModel viewModel;

    public IngredientesFragment() {
        Log.i("FRAGMENT-INGREDIENTE", "constructor");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("FRAGMENT-INGREDIENTE", "onCreate");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);

        Log.i("LOADING", "Creating Observables on Ingredientes Screen");
        viewModel
            .getCategorias()
            .observe(this, new Observer<List<CategoriaIngrediente>>() {
                @Override
                public void onChanged(List<CategoriaIngrediente> categorias) {
                    adapter.setCategorias(categorias);
                }
            });
        viewModel
            .getIngredientes()
            .observe(this, new Observer<List<Ingrediente>>() {
                @Override
                public void onChanged(List<Ingrediente> ingredientes) {
                    adapter.setIngredientes(ingredientes);
                }
            });
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.i("FRAGMENT-INGREDIENTE", "onCreateView");
        this.view = inflater.inflate(R.layout.ingredientes__fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_categorias);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new ItemsCategoriasAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("FRAGMENT-INGREDIENTE", "onViewCreated");
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }
}