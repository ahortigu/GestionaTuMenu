package com.aihg.gestionatumenu.ui.recetas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.util.List;

public class CategoriasDeRecetaAdapter extends  RecyclerView.Adapter<CategoriasDeRecetaAdapter.CategoriasDeRecetaViewHolder>{
    private List<Utiliza> ingredientes;
    private boolean isEditable;

    public CategoriasDeRecetaAdapter(List<Utiliza> ingredientes, boolean isEditable) {
        this.ingredientes = ingredientes;
        this.isEditable = isEditable;
    }

    @NonNull
    @Override
    public CategoriasDeRecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__subitem_spinner, parent, false);
        return new CategoriasDeRecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasDeRecetaViewHolder holder, int position) {
        Utiliza ingrediente = ingredientes.get(position);
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public class CategoriasDeRecetaViewHolder extends RecyclerView.ViewHolder{
        private Spinner spinner;
        public CategoriasDeRecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.sp_rec_shared_categoria);
        }
    }
}
