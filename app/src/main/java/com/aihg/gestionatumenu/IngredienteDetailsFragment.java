package com.aihg.gestionatumenu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aihg.gestionatumenu.db.entities.Ingrediente;


public class IngredienteDetailsFragment extends Fragment {
    public IngredienteDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredientes__view_detail, container, false);
        Ingrediente ingrediente = IngredienteDetailsFragmentArgs.fromBundle(getArguments()).getIngrediente();
        TextView txt_nombre = view.findViewById(R.id.txt_nombre);
        TextView txt_categoria = view.findViewById(R.id.txt_categoria);
        TextView txt_medicion = view.findViewById(R.id.txt_medicion);
        txt_nombre.setText(ingrediente.getNombre());
        txt_categoria.setText(ingrediente.getCategoriaIngrediente().getNombre());
        txt_medicion.setText(ingrediente.getMedicion().getNombre());
        return view;
    }
}