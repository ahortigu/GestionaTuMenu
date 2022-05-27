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
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;

import java.util.List;
import java.util.stream.IntStream;

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

    public int getPositionItem(T item) {
        return IntStream.range(0, this.elementos.size())
            .filter(i -> this.elementos.get(i).equals(item))
            .findFirst()
            .orElse(-1);
    }

    public void setElementos(List<T> elementosNuevos) {
        this.elementos = elementosNuevos;
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        if (this.elementos.contains(item)) {
            this.elementos.add(item);
            notifyDataSetChanged();
        }
    }

    public void removeItem(T item) {
        if (this.elementos.contains(item)) {
            this.elementos.remove(item);
            notifyDataSetChanged();
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textoSeleccionado = (TextView) super.getView(position, convertView, parent);
        textoSeleccionado.setText(getNombreItem(getItem(position)));
        return textoSeleccionado;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textoDropdown = (TextView) super.getDropDownView(position, convertView, parent);
        textoDropdown.setText(getNombreItem(getItem(position)));
        return textoDropdown;
    }
}
