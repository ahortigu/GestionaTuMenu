package com.aihg.gestionatumenu.ui.listacompra.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.ui.listacompra.adapters.ListaCompraAdapter;
import com.aihg.gestionatumenu.ui.listacompra.viewmodel.ListaCompraViewModel;

import java.util.List;

public class ListaCompraFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ListaCompraAdapter adapter;
    private ListaCompraViewModel viewModel;

    public ListaCompraFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(this).get(ListaCompraViewModel.class);
        viewModel.getAllDespensa().observe(this, new Observer<List<ListaCompra>>() {
            @Override
            public void onChanged(List<ListaCompra> ingredientes) {
                adapter.setIngredientes(ingredientes);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.listacompra__fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_lc_ingredientes);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new ListaCompraAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.nav_add){
            NavDirections action = ListaCompraFragmentDirections.actionListaCompraFragmentToAddExistingIngredienteFragment();
            Navigation.findNavController(view).navigate(action);
        }
        return super.onOptionsItemSelected(item);
    }
}