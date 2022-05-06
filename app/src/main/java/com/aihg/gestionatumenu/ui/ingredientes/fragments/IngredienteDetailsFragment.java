package com.aihg.gestionatumenu.ui.ingredientes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

public class IngredienteDetailsFragment extends Fragment {
    private View view;
    private Ingrediente ingrediente;

    public IngredienteDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.ingredientes__detail_fragment, container, false);
        ingrediente = IngredienteDetailsFragmentArgs.fromBundle(getArguments()).getIngrediente();
        TextView txt_nombre = view.findViewById(R.id.txt_id_ingrediente);
        TextView txt_categoria = view.findViewById(R.id.txt_id_categoria);
        TextView txt_medicion = view.findViewById(R.id.txt_id_medicion);
        txt_nombre.setText(ingrediente.getNombre());
        txt_categoria.setText(ingrediente.getCategoriaIngrediente().getNombre());
        txt_medicion.setText(ingrediente.getMedicion().getNombre());
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavDirections action;
        if(id == R.id.nav_editar) {
            action = IngredienteDetailsFragmentDirections.actionIngredienteDetailsFragmentToIngredienteEditFragment(ingrediente);
            Navigation.findNavController(view).navigate(action);

        }
        return super.onOptionsItemSelected(item);
    }
}