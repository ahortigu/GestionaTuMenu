package com.aihg.gestionatumenu.ui.recetas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.SubItemsIngredientesAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.fragments.IngredientesFragmentDirections;
import com.aihg.gestionatumenu.ui.recetas.fragments.RecetasFragmentDirections;
import com.aihg.gestionatumenu.ui.recetas.wrapper.CategoriaRecetaWrapper;

import java.util.List;

public class SubItemsRecetasAdapter extends RecyclerView.Adapter<SubItemsRecetasAdapter.SubItemsRecetasViewHolder> {
    private List<Cataloga> recetas;

    public SubItemsRecetasAdapter (CategoriaRecetaWrapper wrapper) {
        this.recetas = wrapper.getRecetas();
    }

    @NonNull
    @Override
    public SubItemsRecetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recetas__subitem, parent, false);
        return new SubItemsRecetasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemsRecetasViewHolder holder, int position) {
        Cataloga receta = recetas.get(position);
        holder.txt_nombre.setText(receta.getId_receta().getNombre());

        holder.v_subitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO elemento a recuperar para mandarlo como argumento/parametro
               // Receta receta = recetas.get(holder.getAdapterPosition());
                NavDirections action = RecetasFragmentDirections.actionRecetasFragmentToRecetaDetailsFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class SubItemsRecetasViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;

        public SubItemsRecetasViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_rs_nombre);
        }
    }
}
