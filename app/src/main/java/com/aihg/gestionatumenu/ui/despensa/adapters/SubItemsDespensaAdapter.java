package com.aihg.gestionatumenu.ui.despensa.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_DESPENSA;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.despensa.listener.DespensaListener;
import com.aihg.gestionatumenu.ui.despensa.wrapper.CategoriaWrapper;

import java.util.List;
import java.util.Timer;

public class SubItemsDespensaAdapter
    extends RecyclerView.Adapter<SubItemsDespensaAdapter.SubItemDespensaViewHolder> {

    private List<Despensa> despensa;

    private DespensaListener listener;

    private boolean changeSaved;

    private Timer timer = new Timer();

    public SubItemsDespensaAdapter(CategoriaWrapper wrapper, DespensaListener listener) {
        this.despensa = wrapper.getDespensa();
        if (this.despensa.isEmpty()) {
            this.despensa.add(new Despensa(
                0,
                new Ingrediente(NO_DESPENSA, wrapper.getCategoriaIngrediente(), NO_CUANTIFICABLE)
            ));
        }
        this.listener = listener;
        changeSaved = true;
    }

    @NonNull
    @Override
    public SubItemDespensaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.shared__subitem_cantidad, parent, false);
        return new SubItemDespensaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemDespensaViewHolder holder, int position) {
        Despensa ingrediente = this.despensa.get(position);
        holder.txt_nombre.setText(ingrediente.getIngrediente().getNombre());

        if (!NO_CUANTIFICABLE.equals(ingrediente.getIngrediente().getMedicion())) {
            holder.txt_medicion.setText(ingrediente.getIngrediente().getMedicion().getNombre());
            holder.et_cantidad.setText(ingrediente.getCantidad() + "");
            holder.et_cantidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus && !changeSaved) {
                        EditText toUpdate = view.findViewById(R.id.et_shared_c_item_cantidad);
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
            holder.txt_medicion.setVisibility(View.VISIBLE);
            holder.et_cantidad.setVisibility(View.VISIBLE);
        } else{
            holder.txt_medicion.setVisibility(View.GONE);
            holder.et_cantidad.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return despensa.size();
    }

    public class SubItemDespensaViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;

        private TextView txt_nombre;
        private TextView txt_medicion;
        private EditText et_cantidad;

        public SubItemDespensaViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_shared_c_item_nombre);
            txt_medicion = itemView.findViewById(R.id.txt_shared_c_item_medicion);
            et_cantidad = itemView.findViewById(R.id.et_shared_c_item_cantidad);
        }
    }
}
