package com.aihg.gestionatumenu.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.aihg.gestionatumenu.R;

import java.util.ArrayList;
import java.util.List;


public class IngredientesFragment extends Fragment {
    private List<CategoriaIngredientesDataModel> listaIngredientes;
    private RecyclerView recyclerView;
    private CategoriaIngredientesAdapter adapter;

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
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvIngredientes);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new CategoriaIngredientesAdapter(this.listaIngredientes, this));

        recyclerView = (RecyclerView) view.findViewById(R.id.rvIngredientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CategoriaIngredientesAdapter(listaIngredientes);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    public void fetchData() {
        listaIngredientes = new ArrayList<>();

        //list1
        List<String> carnes = new ArrayList<>();
        carnes.add("Conejo");
        carnes.add("Pollo");
        carnes.add("Cordero");

        List<String> cerealesYFrutosSecos = new ArrayList<>();
        cerealesYFrutosSecos.add("Avena");
        cerealesYFrutosSecos.add("Arrow");

        List<String> condimentos = new ArrayList<>();
        condimentos.add("Aceite");
        condimentos.add("Vinagre");
        condimentos.add("Sal");
        condimentos.add("Pimienta");

        List<String> fruta = new ArrayList<>();
        fruta.add("Platano");
        fruta.add("Manzana");
        fruta.add("Cerezas");
        fruta.add("Melocoton");
        fruta.add("Piña");
        fruta.add("Pera");
        fruta.add("Kiwi");

        List<String> verduras = new ArrayList<>();
        verduras.add("Tomate");
        verduras.add("Coliflow");
        verduras.add("Lechuga");
        verduras.add("Pepino");

        listaIngredientes.add(new CategoriaIngredientesDataModel(carnes, "CARNES"));
        listaIngredientes.add(new CategoriaIngredientesDataModel(cerealesYFrutosSecos, "CEREALES Y FRUTOS SECOS"));
        listaIngredientes.add(new CategoriaIngredientesDataModel(condimentos, "CONDIMENTOS"));
        listaIngredientes.add(new CategoriaIngredientesDataModel(fruta, "FRUTA"));
        listaIngredientes.add(new CategoriaIngredientesDataModel(verduras, "VERDURAS"));
    }

//    @Override
//    public void onNoteClick(int position) {
//        NavDirections action = IngredientesFragmentDirections.actionIngredientesFragmentToCreateIngredienteFragment();
//        Navigation.findNavController(getView()).navigate(action);
//    }
}