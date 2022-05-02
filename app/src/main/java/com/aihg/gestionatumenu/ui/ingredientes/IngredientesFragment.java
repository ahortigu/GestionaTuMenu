package com.aihg.gestionatumenu.ui.ingredientes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.ItemsCategoriasAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.wrapper.CategoriaIngredientesWrapper;

import java.util.ArrayList;
import java.util.List;


public class IngredientesFragment extends Fragment {
    private List<CategoriaIngredientesWrapper> listaIngredientes;
    private RecyclerView recyclerView;
    private ItemsCategoriasAdapter adapter;

    public IngredientesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicialización de datos
        fetchData();

        // adapter = new ItemsCategoriasAdapter(listaIngredientes);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.ingredientes__fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_categorias);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ItemsCategoriasAdapter(listaIngredientes);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recyclerView.setAdapter(adapter);
    }

    public void fetchData() {
        //Mock data
        listaIngredientes = new ArrayList<>();
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

        Log.i("FRAGMENT", "CREANDO COSAS");
        listaIngredientes.add(new CategoriaIngredientesWrapper(carnes, "CARNES"));
        listaIngredientes.add(new CategoriaIngredientesWrapper(cerealesYFrutosSecos, "CEREALES Y FRUTOS SECOS"));
        listaIngredientes.add(new CategoriaIngredientesWrapper(condimentos, "CONDIMENTOS"));
        listaIngredientes.add(new CategoriaIngredientesWrapper(fruta, "FRUTA"));
        listaIngredientes.add(new CategoriaIngredientesWrapper(verduras, "VERDURAS"));
    }
}