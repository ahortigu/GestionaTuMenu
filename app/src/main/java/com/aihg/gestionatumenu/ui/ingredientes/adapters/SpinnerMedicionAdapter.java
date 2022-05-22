package com.aihg.gestionatumenu.ui.ingredientes.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.ui.util.AbstractSpinner;

import java.util.List;

public class SpinnerMedicionAdapter extends AbstractSpinner<Medicion> {
    public SpinnerMedicionAdapter(@NonNull Context context, int resource, List<Medicion> elementos) {
        super(context, resource, elementos);
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getId();
    }

    @Override
    public String getNombreItem(Medicion item) {
        return item.getNombre().isEmpty() ? "No Cuantificable" : item.getNombre();
    }
}
