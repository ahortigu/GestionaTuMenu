package com.aihg.gestionatumenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment implements View.OnClickListener {
    Button bIngredientes, bListaCompra, bDespenda, bRecetas, bPlanificador, bMenu;
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Botones
        bIngredientes = view.findViewById(R.id.bIngredientes);
        bListaCompra = view.findViewById(R.id.bListaCompra);
        bDespenda = view.findViewById(R.id.bDespensa);
        bRecetas = view.findViewById(R.id.bRecetas);
        bPlanificador = view.findViewById(R.id.bPlanificador);
        bMenu = view.findViewById(R.id.bMenu);

        // Added listener
        bIngredientes.setOnClickListener(this);
        bListaCompra.setOnClickListener(this);
        bDespenda.setOnClickListener(this);
        bRecetas.setOnClickListener(this);
        bPlanificador.setOnClickListener(this);
        bMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        NavDirections action;
        switch (view.getId()) {
            case R.id.bIngredientes:
                action = HomeFragmentDirections.actionHomeFragmentToIngredientesFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.bListaCompra:
                action = HomeFragmentDirections.actionHomeFragmentToListaCompraFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.bDespensa:
                action = HomeFragmentDirections.actionHomeFragmentToDespensaFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.bRecetas:
                action = HomeFragmentDirections.actionHomeFragmentToRecetasFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.bPlanificador:
                action = HomeFragmentDirections.actionHomeFragmentToPlanificadorFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.bMenu:
                 action = HomeFragmentDirections.actionHomeFragmentToMenuFragment();
                Navigation.findNavController(view).navigate(action);
                break;
        }
    }
}