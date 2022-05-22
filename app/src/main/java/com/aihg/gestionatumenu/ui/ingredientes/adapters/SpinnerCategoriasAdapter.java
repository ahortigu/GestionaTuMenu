package com.aihg.gestionatumenu.ui.ingredientes.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.ui.util.AbstractSpinner;

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
