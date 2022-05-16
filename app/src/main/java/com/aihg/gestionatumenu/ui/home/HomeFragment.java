package com.aihg.gestionatumenu.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aihg.gestionatumenu.R;


public class HomeFragment extends Fragment implements View.OnClickListener {
    Button bIngredientes, bListaCompra, bDespenda, bRecetas, bPlanificador, bMenu;
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home__fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Botones
        bIngredientes = view.findViewById(R.id.b_Ingredientes);
        bListaCompra = view.findViewById(R.id.b_lista_compra);
        bDespenda = view.findViewById(R.id.b_despensa);
        bRecetas = view.findViewById(R.id.b_recetas);
        bPlanificador = view.findViewById(R.id.b_planificador);
        bMenu = view.findViewById(R.id.b_menu);

        // Added listener
        bIngredientes.setOnClickListener(this);
        bListaCompra.setOnClickListener(this);
        bDespenda.setOnClickListener(this);
        bRecetas.setOnClickListener(this);
        bPlanificador.setOnClickListener(this);
        bMenu.setOnClickListener(this);

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_add).setVisible(false);
        menu.findItem(R.id.nav_buscar).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }

    @Override
    public void onClick(View view) {
        NavDirections action;
        switch (view.getId()) {
            case R.id.b_Ingredientes:
                action = HomeFragmentDirections.actionHomeFragmentToIngredientesFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.b_lista_compra:
                action = HomeFragmentDirections.actionHomeFragmentToListaCompraFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.b_despensa:
                action = HomeFragmentDirections.actionHomeFragmentToDespensaFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.b_recetas:
                action = HomeFragmentDirections.actionHomeFragmentToRecetasFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.b_planificador:
                action = HomeFragmentDirections.actionHomeFragmentToPlanificadorFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.b_menu:
                 action = HomeFragmentDirections.actionHomeFragmentToMenuFragment();
                Navigation.findNavController(view).navigate(action);
                break;
        }
    }
}