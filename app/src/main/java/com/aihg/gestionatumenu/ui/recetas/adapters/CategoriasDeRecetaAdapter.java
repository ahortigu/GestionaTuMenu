package com.aihg.gestionatumenu.ui.recetas.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.ui.util.AbstractSpinner;

import java.util.List;

public class CategoriasDeRecetaAdapter extends AbstractSpinner<CategoriaReceta> {

    public CategoriasDeRecetaAdapter(@NonNull Context context, int resource, List<CategoriaReceta> elementos) {
        super(context, resource, elementos);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public String getNombreItem(CategoriaReceta item) {
        return item.getNombre();
    }
}
