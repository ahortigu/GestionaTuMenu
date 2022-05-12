package com.aihg.gestionatumenu.ui.shared.fragments.buscaringrediente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.IngredienteInterface;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.ui.despensa.viewmodel.DespensaViewModel;
import com.aihg.gestionatumenu.ui.ingredientes.viewmodel.IngredientesViewModel;
import com.aihg.gestionatumenu.ui.listacompra.viewmodel.ListaCompraViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarIngredienteFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private BuscarIngredienteAdapter adapter;

    private IngredientesViewModel ingredientesViewModel;
    private DespensaViewModel despensaViewModel;
    private ListaCompraViewModel listaCompraViewModel;

    private List<IngredienteInterface> dondeBuscar;


    public BuscarIngredienteFragment() {
        dondeBuscar = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.shared__buscar_ingrediente_fragment, container, false);
        setAdapterWithCorrectViewModel();
        setRecyclerView();
        setBuscador();
        return view;
    }

    public void setAdapterWithCorrectViewModel(){
        int idDestinoAnterior =  NavHostFragment.findNavController(this).getPreviousBackStackEntry().getDestination().getId();

        switch(idDestinoAnterior) {
            case R.id.ingredientesFragment:
                ingredientesViewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);
                ingredientesViewModel.getIngredientes().observe(requireActivity(), new Observer<List<Ingrediente>>() {
                    @Override
                    public void onChanged(List<Ingrediente> ingredientesOb) {
                        List<IngredienteInterface> toInterface = ingredientesOb.stream()
                                .map(ingrediente -> (IngredienteInterface) ingrediente)
                                .collect(Collectors.toList());

                        adapter.setIngredientes(toInterface);
                        dondeBuscar = toInterface;
                    }
                });
                break;
            case R.id.despensaFragment:
                despensaViewModel = new ViewModelProvider(this).get(DespensaViewModel.class);
                despensaViewModel.getDespensa().observe(requireActivity(), new Observer<List<Despensa>>() {
                    @Override
                    public void onChanged(List<Despensa> despensaOb) {
                        List<IngredienteInterface> toInterface = despensaOb.stream()
                                .map(ingrediente -> (IngredienteInterface) ingrediente)
                                .collect(Collectors.toList());

                        adapter.setIngredientes(toInterface);
                        dondeBuscar = toInterface;
                    }
                });
                break;
            case R.id.listaCompraFragment:
                listaCompraViewModel = new ViewModelProvider(this).get(ListaCompraViewModel.class);
                listaCompraViewModel.getAllDespensa().observe(requireActivity(), new Observer<List<ListaCompra>>() {
                    @Override
                    public void onChanged(List<ListaCompra> listaCompraOb) {
                        List<IngredienteInterface> toInterface = listaCompraOb.stream()
                                .map(ingrediente -> (IngredienteInterface) ingrediente)
                                .collect(Collectors.toList());

                        adapter.setIngredientes(toInterface);
                        dondeBuscar = toInterface;
                    }
                });
                break;
        }
    }

    public void setRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_shared_bi_ingredientes);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new BuscarIngredienteAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void setBuscador(){
        SearchView buscador = view.findViewById(R.id.sv_shared_bi_buscador_ingrediente);
        buscador.clearFocus();
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override

            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<IngredienteInterface> ingredientesFiltrados = dondeBuscar.stream()
                        .filter(ingrediente ->
                                ingrediente.getIngrediente().getNombre().toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());

                if (ingredientesFiltrados.isEmpty()) {
                    Toast.makeText(view.getContext(), "El ingrediente no existe", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setIngredientesFiltrados(ingredientesFiltrados);
                }
                return true;
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