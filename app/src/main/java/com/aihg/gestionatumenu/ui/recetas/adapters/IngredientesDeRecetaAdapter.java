package com.aihg.gestionatumenu.ui.recetas.adapters;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.IS_NUMERIC;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_MIN_INGREDIENTES_RECETA_EDITAR;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.ui.recetas.listener.RecetaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IngredientesDeRecetaAdapter
        extends RecyclerView.Adapter<IngredientesDeRecetaAdapter.IngredientesDeRecetaViewHolder>  {
    private List<Utiliza> ingredientes;
    private boolean isEditable;
    private boolean changeSaved;

    private RecetaListener listener;

    public IngredientesDeRecetaAdapter(boolean isEditable) {
        this(isEditable, null);
    }

    public IngredientesDeRecetaAdapter(boolean isEditable, RecetaListener listener) {
        this.ingredientes = new ArrayList<>();
        this.isEditable = isEditable;
        this.listener = listener;
        changeSaved = true;
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
                    if (!hasFocus && !changeSaved) {
                        // https://www.baeldung.com/java-check-string-number
                        EditText editText = view.findViewById(R.id.et_shared_c_item_cantidad);
                        String newValue = editText.getText().toString();
                        if (!newValue.isEmpty() && IS_NUMERIC.matcher(newValue).matches()) {
                            ingrediente.setCantidad(Integer.parseInt(newValue));
                            listener.toUpdateUtiliza(ingrediente);
                        }
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
