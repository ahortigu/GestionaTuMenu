package com.aihg.gestionatumenu.ui.shared.fragments.buscar.ingrediente;

import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_NO_EXISTE_INGREDIENTE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.IngredienteInterface;
import com.aihg.gestionatumenu.db.entities.Receta;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.shared__buscar_fragments, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapterWithCorrectViewModel();
        setRecyclerView();
        setBuscador();
    }

    public void setAdapterWithCorrectViewModel() {
        ingredientesViewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);

        NavDestination destinoAnterior = Navigation
                .findNavController(view)
                .getPreviousBackStackEntry()
                .getDestination();

        switch (destinoAnterior.getId()) {
            case R.id.despensaFragment:
                ingredientesViewModel
                    .getIngredientesParaBuscarDespensa() // ingredientes que no esten en la despensa
                    .observe(getViewLifecycleOwner(), new Observer<List<Ingrediente>>() {
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
            case R.id.listaCompraFragment:
                Log.i("LISTA COMPRA", destinoAnterior.getDisplayName());
                ingredientesViewModel
                    .getIngredientesBuscarListaCompra() // ingredientes que no esten en la lista de la compra
                    .observe(getViewLifecycleOwner(), new Observer<List<Ingrediente>>() {
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
            case R.id.recetasFragment:
                ingredientesViewModel
                    .getIngredienteBuscarReceta(new Receta()) // ingredientes que no esten en la receta
                    .observe(getViewLifecycleOwner(), new Observer<List<Ingrediente>>() {
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
            default:
                throw new IllegalStateException("Se deberia de conocer la procedencia");
        }
    }

    public void setRecyclerView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_shared_b_items);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new BuscarIngredienteAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void setBuscador() {
        SearchView buscador = view.findViewById(R.id.sv_shared_b);
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
                    Toast.makeText(view.getContext(), TOAST_NO_EXISTE_INGREDIENTE, Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setIngredientesFiltrados(ingredientesFiltrados);
                }
                return true;
            }
        });
    }

}