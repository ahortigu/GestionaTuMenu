package com.aihg.gestionatumenu.ui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.aihg.gestionatumenu.R;

import java.util.List;

public abstract class AbstractSpinner<T> extends ArrayAdapter<T>  {
    private List<T> elementos;

    public AbstractSpinner(@NonNull Context context, int resource, List<T> elementos) {
        super(context, resource);
        this.elementos = elementos;
    }

    public abstract long getItemId(int position);

    public abstract String getNombreItem(T item);

    @Override
    public int getCount(){
        return elementos.size();
    }

    @Override
    public T getItem(int position){
        return elementos.get(position);
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textoSeleccionado = (TextView) super.getView(position, convertView, parent);
        textoSeleccionado.setTextColor(R.color.gris_oscuro);
        textoSeleccionado.setText(getNombreItem(getItem(position)));
        return textoSeleccionado;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textoDropdown = (TextView) super.getDropDownView(position, convertView, parent);
        textoDropdown.setTextColor(R.color.gris_oscuro);
        textoDropdown.setText(getNombreItem(getItem(position)));
        return textoDropdown;
    }
}
