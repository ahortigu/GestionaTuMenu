package com.aihg.gestionatumenu.ui.ingredientes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.SpinnerCategoriasAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.viewmodel.IngredientesViewModel;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class IngredienteEditFragment extends Fragment {
    private View view;
    private IngredientesViewModel viewModel;
    private List<CategoriaIngrediente> categorias;

    private SpinnerCategoriasAdapter categoriasAdapter;
    private Spinner categoriasSpinner;

    public IngredienteEditFragment() {
        this.categorias = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Log.i("LOADING", "Creating Observables on Ingrediente Edit Screen");
        viewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);
        viewModel
                .getCategorias()
                .observe(this, new Observer<List<CategoriaIngrediente>>() {
                    @Override
                    public void onChanged(List<CategoriaIngrediente> categoriasOv) {
                        categoriasOv.toArray();
                        categorias = categoriasOv;
                        Log.i("LOADING", "Las categorias son:" + categorias);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.ingredientes__edit_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ingrediente
        Ingrediente ingrediente = IngredienteDetailsFragmentArgs.fromBundle(getArguments()).getIngrediente();
        TextView txt_nombre = view.findViewById(R.id.txt_ie_ingrediente);
        txt_nombre.setHint(ingrediente.getNombre());

        // Categoria
        categoriasAdapter = new SpinnerCategoriasAdapter(
                view.getContext(),
                android.R.layout.simple_spinner_item,
                categorias);
        categoriasSpinner = (Spinner) view.findViewById(R.id.sp_ie_categoria);
        categoriasSpinner.setAdapter(categoriasAdapter);
        Log.i("SPINNER", "Adaptador seteado");
        categoriasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CategoriaIngrediente categoriaIngrediente = categoriasAdapter.getItem(position);
                Log.i("SPINNER", "Se ha elegido la categoria:" + categoriaIngrediente.getNombre());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }
}