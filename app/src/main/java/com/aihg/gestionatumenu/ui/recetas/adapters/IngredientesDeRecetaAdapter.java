package com.aihg.gestionatumenu.ui.recetas.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.util.ArrayList;
import java.util.List;

public class IngredientesDeRecetaAdapter
        extends RecyclerView.Adapter<IngredientesDeRecetaAdapter.IngredientesDeRecetaViewHolder>  {
    private List<Utiliza> ingredientes;

    public IngredientesDeRecetaAdapter() {
        this.ingredientes = new ArrayList<>();
    }

    @NonNull
    @Override
    public IngredientesDeRecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.listacompra__item, parent, false);
        return new IngredientesDeRecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientesDeRecetaViewHolder holder, int position) {
        Utiliza ingrediente = ingredientes.get(position);
        holder.txt_nombre.setText(ingrediente.id_ingrediente.getNombre());

        if (!NO_CUANTIFICABLE.equals(ingrediente.id_ingrediente.getMedicion())) {
            holder.txt_medicion.setText(ingrediente.id_ingrediente.getNombre());
            holder.et_cantidad.setText(ingrediente.getCantidad() + "");
            holder.txt_medicion.setVisibility(View.VISIBLE);
            holder.et_cantidad.setVisibility(View.VISIBLE);
        }
        else{
            holder.txt_medicion.setVisibility(View.GONE);
            holder.et_cantidad.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public void setIngredientes(List<Utiliza> ingredientes) {
        this.ingredientes = ingredientes;
        notifyDataSetChanged();
    }

    public class IngredientesDeRecetaViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_nombre;
        private TextView txt_medicion;
        private EditText et_cantidad;

        public IngredientesDeRecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombre = itemView.findViewById(R.id.txt_lci_ingrediente);
            txt_medicion = itemView.findViewById(R.id.txt_lci_medicion);
            et_cantidad = itemView.findViewById(R.id.et_lci_cantidad);
        }
    }
}
