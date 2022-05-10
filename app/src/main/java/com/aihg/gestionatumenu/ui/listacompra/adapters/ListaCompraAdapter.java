package com.aihg.gestionatumenu.ui.listacompra.adapters;

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

import java.util.ArrayList;
import java.util.List;

public class ListaCompraAdapter extends RecyclerView.Adapter<ListaCompraAdapter.ListaCompraViewHolder> {
    private List<ListaCompra> ingredientes;

    public ListaCompraAdapter() {
        this.ingredientes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListaCompraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.listacompra__item, parent, false);
        return new ListaCompraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCompraViewHolder holder, int position) {
        ListaCompra ingrediente = ingredientes.get(position);
        holder.txt_nombre.setText(ingrediente.getIngrediente().getNombre());

        if (!NO_CUANTIFICABLE.equals(ingrediente.getIngrediente().getMedicion())) {
            holder.txt_medicion.setText(ingrediente.getIngrediente().getMedicion().getNombre());
            holder.et_cantidad.setText(ingrediente.getCantidad() + "");
        }
        else{
            holder.txt_medicion.setVisibility(View.INVISIBLE);
            holder.et_cantidad.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (ingredientes == null) {
            return 0;
        } else
            return ingredientes.size();
    }


    public void setIngredientes(List<ListaCompra> ingredientes) {
        this.ingredientes = ingredientes;
        notifyDataSetChanged();
    }


    public class ListaCompraViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;
        private TextView txt_medicion;
        private EditText et_cantidad;

        public ListaCompraViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_lci_ingrediente);
            txt_medicion = itemView.findViewById(R.id.txt_lci_medicion);
            et_cantidad = itemView.findViewById(R.id.et_lci_cantidad);
        }
    }
}