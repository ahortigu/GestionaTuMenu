package com.aihg.gestionatumenu.ui.recetas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.viewmodel.IngredientesViewModel;
import com.aihg.gestionatumenu.ui.listacompra.adapters.ListaCompraAdapter;
import com.aihg.gestionatumenu.ui.recetas.adapters.AddExistingIngredienteAdapter;

import java.util.List;

public class AddExistingIngredienteFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private AddExistingIngredienteAdapter adapter;
    private IngredientesViewModel viewModel;

    public AddExistingIngredienteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);
        viewModel.getIngredientes().observe(this, new Observer<List<Ingrediente>>() {
            @Override
            public void onChanged(List<Ingrediente> ingredientes) {
                adapter.setIngredientes(ingredientes);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.shared__add_existing_ingrediente_fragment, container, false);;

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_ia_ingredientes);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new AddExistingIngredienteAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }

}