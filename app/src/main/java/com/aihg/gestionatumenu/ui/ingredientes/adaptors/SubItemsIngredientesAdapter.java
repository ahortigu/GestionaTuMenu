package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.IngredientesFragmentDirections;
import com.aihg.gestionatumenu.ui.ingredientes.wrapper.CategoriaWrapper;

import java.util.List;

public class SubItemsIngredientesAdapter extends RecyclerView.Adapter<SubItemsIngredientesAdapter.SubItemIngredienteViewHolder> {
    private List<Ingrediente> ingredientes;

    public SubItemsIngredientesAdapter(CategoriaWrapper wrapper) {
        this.ingredientes = wrapper.getIngredientes();
    }

    @NonNull
    @Override
    public SubItemIngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.ingredientes__subitem_ingrediente, parent, false);
        return new SubItemIngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemIngredienteViewHolder holder, int position) {
        Ingrediente ingrediente = ingredientes.get(position);
        Log.i("INGREDIENTE", "" + ingrediente.getNombre());
        holder.txt_nombre_ingrediente.setText(ingrediente.getNombre());
        holder.txt_valor_medicion.setText(ingrediente.getMedicion().getNombre());

        holder.v_subItemIngrediente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                           // TODO elemento a recuperar para mandarlo como argumento/parametro
                    Ingrediente ingrediente = ingredientes.get(holder.getAdapterPosition());
                    NavDirections action = IngredientesFragmentDirections.actionIngredientesFragmentToIngredienteDetailsFragment(ingrediente);
                    Navigation.findNavController(view).navigate(action);
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public class SubItemIngredienteViewHolder extends RecyclerView.ViewHolder {
        private View v_subItemIngrediente;

        private TextView txt_nombre_ingrediente;
        private TextView txt_valor_medicion;

        public SubItemIngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subItemIngrediente = itemView;
            txt_nombre_ingrediente = itemView.findViewById(R.id.txt_nombre_ingrediente);
            txt_valor_medicion = itemView.findViewById(R.id.txt_valor_medicion);
        }
    }
}