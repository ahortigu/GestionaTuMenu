package com.aihg.gestionatumenu.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_Ingredientes:
                NavDirections toIngredientes = HomeFragmentDirections.actionHomeFragmentToIngredientesFragment();
                Navigation.findNavController(view).navigate(toIngredientes);
                break;
            case R.id.b_lista_compra:
                NavDirections toListaCompra = HomeFragmentDirections.actionHomeFragmentToListaCompraFragment();
                Navigation.findNavController(view).navigate(toListaCompra);
                break;
            case R.id.b_despensa:
                NavDirections toDespensa = HomeFragmentDirections.actionHomeFragmentToDespensaFragment();
                Navigation.findNavController(view).navigate(toDespensa);
                break;
            case R.id.b_recetas:
                NavDirections toRecetas = HomeFragmentDirections.actionHomeFragmentToRecetasFragment();
                Navigation.findNavController(view).navigate(toRecetas);
                break;
            case R.id.b_planificador:
                NavDirections toPlanificador = HomeFragmentDirections.actionHomeFragmentToPlanificadorFragment();
                Navigation.findNavController(view).navigate(toPlanificador);
                break;
            case R.id.b_menu:
                NavDirections toMenu = HomeFragmentDirections.actionHomeFragmentToMenuFragment();
                Navigation.findNavController(view).navigate(toMenu);
                break;
        }
    }
}