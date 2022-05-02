package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.ui.ingredientes.IngredientesFragmentDirections;

import java.util.List;

public class SubItemsIngredientesAdapter extends RecyclerView.Adapter<SubItemsIngredientesAdapter.NestedViewHolder> {
    private List<String> ingredientesList;

    public SubItemsIngredientesAdapter(List<String> ingredientesList) {
        this.ingredientesList = ingredientesList;
    }

    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredientes__subitem_ingrediente, parent, false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder nestedViewHolder, int position) {
        nestedViewHolder.txt_nombre_ingrediente.setText(ingredientesList.get(position));
        nestedViewHolder.txt_valor_medicion.setText("Unidad");

        nestedViewHolder.ingredienteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingredientesList.get(nestedViewHolder.getAdapterPosition());
                    NavDirections action = IngredientesFragmentDirections.actionIngredientesFragmentToCreateIngredienteFragment();
                    Navigation.findNavController(view).navigate(action);
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return ingredientesList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {
        private View ingredienteView;

        private TextView txt_nombre_ingrediente;
        private TextView txt_valor_medicion;

        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredienteView = itemView;
            txt_nombre_ingrediente = itemView.findViewById(R.id.txt_nombre_ingrediente);
            txt_valor_medicion = itemView.findViewById(R.id.txt_valor_medicion);
        }
    }
}