package com.aihg.gestionatumenu.ui.menu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.ui.menu.adapters.ItemsMenuAdapter;
import com.aihg.gestionatumenu.ui.menu.viewmodel.MenuViewModel;


public abstract class AbstractMenuFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;

    private ItemsMenuAdapter adapter;
    private MenuViewModel viewModel;

    private Boolean isSemanal;
    private Boolean isPlanificador;

    public AbstractMenuFragment(boolean isSemanal, boolean isPlanificador) {
        if (isSemanal == isPlanificador) throw new IllegalStateException(
            "No puede ser un adaptador para Semanal y Planificador al mismo. Uno de los valores debe ser true."
        );
        this.isSemanal = isSemanal;
        this.isPlanificador = isPlanificador;
    }

    protected abstract void setObservers();
    protected void saveArguments(Bundle savedInstanceState) {}
    protected void setearLogicaBotones() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        setObservers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.menu__fragment, container, false);

        setearLogicaBotones();

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rv_m_dias);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) this.adapter = new ItemsMenuAdapter(isSemanal, isPlanificador);
        this.recyclerView.setAdapter(this.adapter);

        saveArguments(savedInstanceState);

        return this.view;
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_done).setVisible(false);
        menu.findItem(R.id.nav_clear).setVisible(false);
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public ItemsMenuAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ItemsMenuAdapter adapter) {
        this.adapter = adapter;
    }

    public MenuViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MenuViewModel viewModel) {
        this.viewModel = viewModel;
    }

}
