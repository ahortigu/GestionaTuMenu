package com.aihg.gestionatumenu.ui.shared.fragments.buscaringrediente;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class BuscarIngredienteAdapter extends RecyclerView.Adapter<BuscarIngredienteAdapter.BuscarIngredienteViewHolder> {
    private List<Ingrediente> ingredientes;

    public BuscarIngredienteAdapter() {
        this.ingredientes = new ArrayList<>();
    }

    @NonNull
    @Override
    public BuscarIngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__ingrediente_item, parent, false);
        return new BuscarIngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscarIngredienteViewHolder holder, int position) {
        Ingrediente ingrediente = ingredientes.get(position);
        holder.txt_nombre.setText(ingrediente.getNombre());
    }

    @Override
    public int getItemCount() {
        if (ingredientes == null) {
            return 0;
        } else
            return ingredientes.size();
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
        notifyDataSetChanged();
    }

    public class  BuscarIngredienteViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;

        public BuscarIngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_shared_nombre_ingrediente);
        }
    }
}
