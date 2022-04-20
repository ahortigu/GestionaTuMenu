package com.aihg.gestionatumenu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;

import java.util.List;

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.ViewHolder> {
    private List<String> data;
    private OnNoteListener onNoteListener;

    public IngredientesAdapter(List<String> data, OnNoteListener onNoteListener) {
        this.data = data;
        this.onNoteListener = onNoteListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            textView = (TextView) view.findViewById(R.id.txtNombre);
            view.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition()
            );
        }
    }

    @Override
    public IngredientesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Se carga el card individual
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingrediente_cardview, parent, false);
        return new ViewHolder(view, this.onNoteListener);
    }

    @Override
    public void onBindViewHolder(IngredientesAdapter.ViewHolder holder, int position) {
        holder.textView.setText(this.data.get(position));

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
