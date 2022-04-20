package com.aihg.gestionatumenu.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.aihg.gestionatumenu.HomeFragmentDirections;
import com.aihg.gestionatumenu.R;

import java.util.ArrayList;
import java.util.List;


public class IngredientesFragment extends Fragment implements IngredientesAdapter.OnNoteListener {
    private List<String> listaIngredientes;

    public IngredientesFragment() {
        super(R.layout.fragment_ingredientes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicialización de datos
        fetchData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Asinación del RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvIngredientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new IngredientesAdapter(this.listaIngredientes, this));
    }

    public void fetchData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Tomate");
        data.add("Cebolla");
        data.add("Salmon");
        data.add("Pasta");
        data.add("Arroz");
        data.add("Huevo");
        data.add("Leche");
        data.add("Aceite");
        data.add("Pepino");
        listaIngredientes = data;
    }

    @Override
    public void onNoteClick(int position) {
        NavDirections action = IngredientesFragmentDirections.actionIngredientesFragmentToCreateIngredienteFragment();
        Navigation.findNavController(getView()).navigate(action);
    }
}