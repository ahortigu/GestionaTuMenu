package com.aihg.gestionatumenu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;

import java.util.List;

public class IngredientesNestedAdapter extends RecyclerView.Adapter<IngredientesNestedAdapter.NestedViewHolder> {
    private List<String> ingredientesList;
    public IngredientesNestedAdapter(List<String> ingredientesList) {
        this.ingredientesList = ingredientesList;
    }

    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingrediente_cardview, parent, false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        holder.txtIngrediente.setText(ingredientesList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientesList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {
        private TextView txtIngrediente;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIngrediente = itemView.findViewById(R.id.txtIngrediente);
        }
    }
}