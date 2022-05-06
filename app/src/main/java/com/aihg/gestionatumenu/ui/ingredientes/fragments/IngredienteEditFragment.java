package com.aihg.gestionatumenu.ui.ingredientes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

public class IngredienteEditFragment extends Fragment {
    private View view;

    public IngredienteEditFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.ingredientes__edit_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Ingrediente ingrediente = IngredienteDetailsFragmentArgs.fromBundle(getArguments()).getIngrediente();
        TextView txt_nombre = view.findViewById(R.id.txt_ie_ingrediente);
        txt_nombre.setHint(ingrediente.getNombre());
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }
}