package com.aihg.gestionatumenu.ui.shared.fragments.buscarreceta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarRecetaFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private BuscarRecetaAdapter adapter;

    private RecetasViewModel listaCompraViewModel;

    private List<Receta> dondeBuscar;


    public BuscarRecetaFragment() {
        dondeBuscar = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.shared__buscar_receta_fragment, container, false);
        setAdapterWithCorrectViewModel();
        setRecyclerView();
        setBuscador();
        return view;
    }

    public void setAdapterWithCorrectViewModel(){
        int idDestinoAnterior =  NavHostFragment.findNavController(this).getPreviousBackStackEntry().getDestination().getId();

        switch(idDestinoAnterior) {
            case R.id.recetasFragment:
                listaCompraViewModel = new ViewModelProvider(this).get(RecetasViewModel.class);
                listaCompraViewModel.getRecetas().observe(requireActivity(), new Observer<List<Receta>>() {
                    @Override
                    public void onChanged(List<Receta> recetasOb) {
                        adapter.setRecetas(recetasOb);
                        dondeBuscar = recetasOb;
                    }
                });
                break;
        }
    }

    public void setBuscador(){
        SearchView buscador = view.findViewById(R.id.sv_shared_br_buscador_receta);
        buscador.clearFocus();
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override

            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Receta> recetasFiltradas = dondeBuscar.stream()
                        .filter(receta ->
                                receta.getNombre().toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());

                if (recetasFiltradas.isEmpty()) {
                    Toast.makeText(view.getContext(), "La receta no existe", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setRecetasFiltradas(recetasFiltradas);
                }
                return true;
            }
        });
    }

    public void setRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_shared_br_recetas);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new BuscarRecetaAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }
}