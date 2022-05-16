package com.aihg.gestionatumenu.ui.despensa.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;

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
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.ui.despensa.fragments.DespensaFragmentDirections;
import com.aihg.gestionatumenu.ui.despensa.wrapper.CategoriaWrapper;

import java.util.List;

public class SubItemsDespensaAdapter extends RecyclerView.Adapter<SubItemsDespensaAdapter.SubItemDespensaViewHolder> {

    private List<Despensa> despensa;

    public SubItemsDespensaAdapter(CategoriaWrapper wrapper) {
        this.despensa = wrapper.getDespensa();
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

        Despensa despensaItem = this.despensa.get(position);
        holder.txt_nombre.setText(despensaItem.getIngrediente().getNombre());

        if(!NO_CUANTIFICABLE.equals(despensaItem.getIngrediente().getMedicion())){
            holder.et_cantidad.setText(despensaItem.getCantidad() + "");
            holder.txt_medicion.setText(despensaItem.getIngrediente().getMedicion().getNombre());
        } else {
            holder.et_cantidad.setVisibility(View.INVISIBLE);
            holder.txt_medicion.setVisibility(View.INVISIBLE);
        }

        holder.v_subitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Pasar ingrediente como parámetro
                //Despensa ingrediente = ingredientes.get(holder.getAdapterPosition());
                NavDirections action = DespensaFragmentDirections.actionDespensaFragmentToBuscarIngredienteFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
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
