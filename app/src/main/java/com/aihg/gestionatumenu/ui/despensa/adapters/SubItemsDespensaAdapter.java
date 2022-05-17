package com.aihg.gestionatumenu.ui.despensa.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.NO_DESPENSA;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.NO_INGREDIENTE;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.NO_RECETA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.ui.despensa.fragments.DespensaFragmentDirections;
import com.aihg.gestionatumenu.ui.despensa.wrapper.CategoriaWrapper;

import java.util.List;

public class SubItemsDespensaAdapter
    extends RecyclerView.Adapter<SubItemsDespensaAdapter.SubItemDespensaViewHolder> {

    private List<Despensa> despensa;

    public SubItemsDespensaAdapter(CategoriaWrapper wrapper) {
        this.despensa = wrapper.getDespensa();
        if (this.despensa.isEmpty()) {
            this.despensa.add(new Despensa(
                0,
                new Ingrediente(NO_DESPENSA, wrapper.getCategoriaIngrediente(), NO_CUANTIFICABLE)
            ));
        }
    }

    @NonNull
    @Override
    public SubItemDespensaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.despensa__subitem, parent, false);
        return new SubItemDespensaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemDespensaViewHolder holder, int position) {
        Despensa ingrediente = this.despensa.get(position);
        holder.txt_nombre.setText(ingrediente.getIngrediente().getNombre());

        if (!NO_CUANTIFICABLE.equals(ingrediente.getIngrediente().getMedicion())) {
            holder.txt_medicion.setText(ingrediente.getIngrediente().getMedicion().getNombre());
            holder.et_cantidad.setText(ingrediente.getCantidad() + "");
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
            txt_nombre = itemView.findViewById(R.id.txt_ds_ingrediente);
            txt_medicion = itemView.findViewById(R.id.txt_ds_medicion);
            et_cantidad = itemView.findViewById(R.id.et_ds_cantidad);
        }
    }
}
