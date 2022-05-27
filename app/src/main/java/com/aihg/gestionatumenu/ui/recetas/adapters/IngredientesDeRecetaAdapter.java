package com.aihg.gestionatumenu.ui.recetas.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CI_OTROS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.IS_NUMERIC;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_INGREDIENTE;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.ui.recetas.listener.RecetaListener;

import java.util.ArrayList;
import java.util.List;

public class IngredientesDeRecetaAdapter
        extends RecyclerView.Adapter<IngredientesDeRecetaAdapter.IngredientesDeRecetaViewHolder>  {
    private List<Utiliza> ingredientes;
    private boolean isEditable;
    private boolean isChanged;

    private RecetaListener listener;

    public IngredientesDeRecetaAdapter(boolean isEditable) {
        this(isEditable, null);
    }

    public IngredientesDeRecetaAdapter(boolean isEditable, RecetaListener listener) {
        this.ingredientes = new ArrayList<>();
        this.isEditable = isEditable;
        this.listener = listener;
        isChanged = true;
    }

    @NonNull
    @Override
    public IngredientesDeRecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__subitem_cantidad, parent, false);
        return new IngredientesDeRecetaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull IngredientesDeRecetaViewHolder holder, int position) {
        Utiliza ingrediente = ingredientes.get(position);
        holder.txt_nombre.setText(ingrediente.id_ingrediente.getNombre());

        if (!NO_CUANTIFICABLE.equals(ingrediente.id_ingrediente.getMedicion())) {
            holder.txt_medicion.setText(ingrediente.id_ingrediente.getMedicion().getNombre());
            holder.et_cantidad.setText(ingrediente.getCantidad() + "");
            holder.txt_medicion.setVisibility(View.VISIBLE);
            holder.et_cantidad.setVisibility(View.VISIBLE);
            holder.et_cantidad.setEnabled(isEditable);
        } else {
            holder.txt_medicion.setVisibility(View.GONE);
            holder.et_cantidad.setVisibility(View.GONE);
        }

        if (isEditable) {
            holder.et_cantidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) holder.et_cantidad.clearFocus();
                    if (!hasFocus && isChanged) {
                        EditText toUpdate = view.findViewById(R.id.et_shared_c_item_cantidad);
                        String newValue = toUpdate.getText().toString();
                        saveChange(newValue, ingrediente);
                        isChanged = false;
                    }
                }
            });
            holder.et_cantidad.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        holder.et_cantidad.clearFocus();
                        String newValue = textView.getText().toString();
                        saveChange(newValue, ingrediente);
                        isChanged = false;
                    }
                    return false;
                }
            });
            holder.et_cantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    isChanged = validateChange(editable.toString(), ingrediente);
                }
            });
        }
    }

    private void saveChange(String newValue, Utiliza toUpdate) {
        if (validateChange(newValue, toUpdate)) {
            toUpdate.setCantidad(Integer.parseInt(newValue));
            listener.toUpdateUtiliza(toUpdate);
        }
    }

    private boolean validateChange(String newValue, Utiliza toUpdate) {
        if (newValue.isEmpty()) return false;

        boolean isNumber = IS_NUMERIC.matcher(newValue).matches();
        if (!isNumber) return false;

        int newValueAsInt = Integer.parseInt(newValue);
        return newValueAsInt != toUpdate.getCantidad();
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public void setIngredientes(List<Utiliza> ingredientesValue) {
        this.ingredientes.clear();
        this.ingredientes.addAll(ingredientesValue);
        if (this.ingredientes.isEmpty()) {
            this.ingredientes.add(new Utiliza(
                new Receta(), new Ingrediente(NO_INGREDIENTE, CI_OTROS, NO_CUANTIFICABLE)
            ));
        }
        notifyDataSetChanged();
    }

    public class IngredientesDeRecetaViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView txt_nombre;
        private TextView txt_medicion;
        private EditText et_cantidad;

        public IngredientesDeRecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_rce_ingredientes);
            txt_nombre = itemView.findViewById(R.id.txt_shared_c_item_nombre);
            txt_medicion = itemView.findViewById(R.id.txt_shared_c_item_medicion);
            et_cantidad = itemView.findViewById(R.id.et_shared_c_item_cantidad);
        }
    }
}
