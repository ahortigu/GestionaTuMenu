package com.aihg.gestionatumenu.ui.despensa.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.ui.despensa.adapters.ItemsCatDespensaAdapter;
import com.aihg.gestionatumenu.ui.despensa.viewmodel.DespensaViewModel;
import com.aihg.gestionatumenu.ui.listacompra.fragments.ListaCompraFragmentArgs;

import java.util.List;

public class DespensaFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsCatDespensaAdapter adapter;
    private View view;
    private Despensa  aInsertar;
    private DespensaViewModel viewModel;

    public DespensaFragment() {
        aInsertar = new Despensa();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.despensa__fragment, container, false);

        setViewModelsAndObservers();
        saveArguments(savedInstanceState);
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
                NavDirections action = DespensaFragmentDirections.actionDespensaFragmentToBuscarIngredienteFragment();
                Navigation.findNavController(view).navigate(action);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    public void setViewModelsAndObservers(){
        viewModel = new ViewModelProvider(this).get(DespensaViewModel.class);
        viewModel
                .getCategorias()
                .observe(requireActivity(), new Observer<List<CategoriaIngrediente>>() {
                    @Override
                    public void onChanged(List<CategoriaIngrediente> categoriasOb) {
                        adapter.setCategorias(categoriasOb);
                    }
                });
        viewModel
                .getDespensa()
                .observe(requireActivity(), new Observer<List<Despensa>>() {
                    @Override
                    public void onChanged(List<Despensa> despensaOb) {
                        adapter.setDespensaItems(despensaOb);
                    }
                });

    }

    public void  setRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_d_categorias);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new ItemsCatDespensaAdapter();
        recyclerView.setAdapter(adapter);

    }

    public void saveArguments(Bundle savedInstanceState) {
        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;
        if (bundle != null) {
            DespensaFragmentArgs args = DespensaFragmentArgs .fromBundle(bundle);
            this.aInsertar = args.getDespensabuscar();
            if (aInsertar != null) {
                viewModel.insertDespensa(aInsertar);
            }
        }
    }
}