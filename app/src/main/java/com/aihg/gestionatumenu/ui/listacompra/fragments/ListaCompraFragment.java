package com.aihg.gestionatumenu.ui.listacompra.fragments;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_BORRAR_LISTA_COMPRA;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_NO_EXISTE_INGREDIENTE_LISTA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.ui.listacompra.adapters.ListaCompraAdapter;
import com.aihg.gestionatumenu.ui.listacompra.viewmodel.ListaCompraViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaCompraFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ListaCompraAdapter adapter;
    private List<ListaCompra> dondeBuscar;
    private ListaCompraViewModel viewModel;
    private ListaCompra  aInsertar;

    public ListaCompraFragment() {
        dondeBuscar = new ArrayList<>();
        aInsertar = new ListaCompra();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.listacompra__fragment, container, false);
        setViewModelAndObserver();
        saveArguments(savedInstanceState);
        setBuscador();
        setRecyclerView();

        return view;
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
                NavDirections action = ListaCompraFragmentDirections.actionListaCompraFragmentToBuscarIngredienteFragment();
                Navigation.findNavController(view).navigate(action);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setViewModelAndObserver(){
        viewModel = new ViewModelProvider(this).get(ListaCompraViewModel.class);
        viewModel.
                getAllDespensa()
                .observe(requireActivity(), new Observer<List<ListaCompra>>() {
            @Override
            public void onChanged(List<ListaCompra> ingredientesOb) {
                adapter.setIngredientes(ingredientesOb);
                dondeBuscar = ingredientesOb;
            }
        });
    }

    public void saveArguments(Bundle savedInstanceState) {
        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;
        if (bundle != null) {
            ListaCompraFragmentArgs args = ListaCompraFragmentArgs.fromBundle(bundle);
            this.aInsertar = args.getListacomprabuscar();
            Log.i("RECIBIENDO INGREDIENTE", "El ingrediente a anadir es "+ aInsertar);
            if (aInsertar != null) {
                Log.i("ANADIENDO INGREDIENTE", "El ingrediente a anadir es "+ aInsertar.getIngrediente().getNombre());
                viewModel.insertIngrediente(aInsertar);
            }
        }
    }

    public void setRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_lc_ingredientes);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new ListaCompraAdapter();
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (!dondeBuscar.isEmpty()) {
                    ListaCompra toDelete = dondeBuscar.get(position);
                    viewModel.deleteIngrediente(toDelete);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(
                        view.getContext(), TOAST_BORRAR_LISTA_COMPRA, Toast.LENGTH_SHORT
                    ).show();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void setBuscador(){
        SearchView buscador = view.findViewById(R.id.sv_lc_buscador_ingrediente);
        buscador.clearFocus();
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<ListaCompra> listaCompraFiltrada = dondeBuscar.stream()
                        .filter(listaCompraItem ->
                                listaCompraItem.getIngrediente().getNombre().toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());

                if (listaCompraFiltrada.isEmpty()) {
                    Toast.makeText(view.getContext(), TOAST_NO_EXISTE_INGREDIENTE_LISTA, Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setIngredientesFiltrados(listaCompraFiltrada);
                }
                return true;
            }
        });
    }

}