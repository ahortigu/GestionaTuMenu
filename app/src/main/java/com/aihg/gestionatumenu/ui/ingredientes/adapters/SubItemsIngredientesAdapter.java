package com.aihg.gestionatumenu.ui.ingredientes.adapters;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.NO_CUANTIFICABLE;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_INGREDIENTE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.fragments.IngredientesFragmentDirections;
import com.aihg.gestionatumenu.ui.ingredientes.wrapper.CategoriaWrapper;

import java.util.Arrays;
import java.util.List;

public class SubItemsIngredientesAdapter extends RecyclerView.Adapter<SubItemsIngredientesAdapter.SubItemIngredienteViewHolder> {
    private List<Ingrediente> ingredientes;
    private Ingrediente ingredienteVacio;


    public SubItemsIngredientesAdapter(CategoriaWrapper wrapper) {
        ingredienteVacio = new Ingrediente(NO_INGREDIENTE, wrapper.getCategoriaIngrediente(), NO_CUANTIFICABLE);

        if (wrapper.getIngredientes().isEmpty()) {
            this.ingredientes = Arrays.asList(ingredienteVacio);
        } else this.ingredientes = wrapper.getIngredientes();
    }

    @NonNull
    @Override
    public SubItemIngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.shared__subitem_medicion, parent, false);
        return new SubItemIngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemIngredienteViewHolder holder, int position) {
        Ingrediente ingrediente = ingredientes.get(position);
        holder.txt_nombre.setText(ingrediente.getNombre());

        if(ingredienteVacio.equals(ingrediente)){
            holder.iv_imagen.setVisibility(View.GONE);
            holder.txt_medicion.setVisibility(View.GONE);
        }

        if(!NO_CUANTIFICABLE.equals(ingrediente.getMedicion().getNombre())){
            holder.txt_medicion.setText(ingrediente.getMedicion().getNombre());
        } else {
            holder.txt_medicion.setVisibility(View.INVISIBLE);
        }

        if (!NO_INGREDIENTE.equals(ingrediente.getNombre())) {
            holder.v_subitem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // TODO elemento a recuperar para mandarlo como argumento/parametro
                        Ingrediente ingredienteOC = ingredientes.get(holder.getAdapterPosition());
                        NavDirections action = IngredientesFragmentDirections
                                .actionIngredientesFragmentToIngredienteDetailsFragment(ingredienteOC);
                        Navigation.findNavController(view).navigate(action);
                    }
                }
            );
        }
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public class SubItemIngredienteViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;

        private TextView txt_nombre;
        private TextView txt_medicion;
        private ImageView iv_imagen;

        public SubItemIngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_shared_m_item_nombre);
            txt_medicion = itemView.findViewById(R.id.txt_shared_m_item_medicion);
            iv_imagen = itemView.findViewById(R.id.iv_shared_m);
        }
    }
}