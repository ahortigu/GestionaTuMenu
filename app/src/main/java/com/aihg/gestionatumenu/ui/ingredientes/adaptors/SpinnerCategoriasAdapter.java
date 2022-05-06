package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;

import java.util.List;

public class SpinnerCategoriasAdapter extends ArrayAdapter<CategoriaIngrediente> {

    private Context context;
    private List<CategoriaIngrediente> categorias;

    public SpinnerCategoriasAdapter(Context context, int textViewResourceId, List<CategoriaIngrediente> categorias) {
        super(context, textViewResourceId, categorias);
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public int getCount(){
        return categorias.size();
    }

    @Override
    public CategoriaIngrediente getItem(int position){
        return categorias.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nombreCategoria = (TextView) super.getView(position, convertView, parent);
        nombreCategoria.setTextColor(Color.BLACK);
        nombreCategoria.setText(categorias.get(position).getNombre());
        return nombreCategoria;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLUE);
        label.setText(categorias.get(position).getNombre());
        return label;
    }
}
