package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.ui.util.AbstractSpinner;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCategoriasAdapter extends AbstractSpinner<CategoriaIngrediente> {

    public SpinnerCategoriasAdapter(@NonNull Context context, int resource, List<CategoriaIngrediente> elementos) {
        super(context, resource, elementos);
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getId();
    }

    @Override
    public String getNombreItem(CategoriaIngrediente item) {
        return item.getNombre();
    }
}
