package com.aihg.gestionatumenu.ui.listacompra.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CI_OTROS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.NO_LISTA_COMPRA;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.ui.listacompra.listener.ListaCompraListener;
import com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants;

import java.util.ArrayList;
import java.util.List;

public class ListaCompraAdapter extends RecyclerView.Adapter<ListaCompraAdapter.ListaCompraViewHolder> {
    private List<ListaCompra> ingredientes;

    private ListaCompraListener listener;

    private boolean changeSaved;

    public ListaCompraAdapter(ListaCompraListener listener) {
        this.ingredientes = new ArrayList<>();
        this.listener = listener;
        this.changeSaved = true;
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
            holder.txt_medicion.setVisibility(View.VISIBLE);
            holder.txt_medicion.setText(ingrediente.getIngrediente().getMedicion().getNombre());
            holder.et_cantidad.setVisibility(View.VISIBLE);
            holder.et_cantidad.setText(ingrediente.getCantidad() + "");

            holder.et_cantidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus && !changeSaved) {
                        EditText toUpdate = view.findViewById(R.id.et_lci_cantidad);
                        toUpdate.setSelected(false);
                        ingrediente.setCantidad(Integer.parseInt(toUpdate.getText().toString()));
                        listener.onUpdateItem(ingrediente);
                        changeSaved = true;
                    }
                }
            });
            holder.et_cantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    changeSaved = false;
                }
            });
        }
        else{
            holder.txt_medicion.setVisibility(View.GONE);
            holder.et_cantidad.setVisibility(View.GONE);
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
        if (this.ingredientes.isEmpty()) {
            this.ingredientes.add(
                new ListaCompra(
                    0,
                    new Ingrediente(NO_LISTA_COMPRA, CI_OTROS, NO_CUANTIFICABLE)
                )
            );
        }
        notifyDataSetChanged();
    }

    public void setIngredientesFiltrados(List<ListaCompra> ingredientes) {
        this.ingredientes = ingredientes;
        notifyDataSetChanged();
    }


    public class ListaCompraViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_nombre;
        private TextView txt_medicion;
        private EditText et_cantidad;

        public ListaCompraViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombre = itemView.findViewById(R.id.txt_lci_ingrediente);
            txt_medicion = itemView.findViewById(R.id.txt_lci_medicion);
            et_cantidad = itemView.findViewById(R.id.et_lci_cantidad);
        }
    }
}
