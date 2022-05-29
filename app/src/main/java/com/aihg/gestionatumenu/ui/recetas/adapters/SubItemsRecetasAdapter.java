package com.aihg.gestionatumenu.ui.recetas.adapters;

import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_RECETA;

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
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.ui.recetas.fragments.RecetasFragmentDirections;
import com.aihg.gestionatumenu.ui.recetas.wrapper.CategoriaRecetaWrapper;

import java.util.List;

public class SubItemsRecetasAdapter extends RecyclerView.Adapter<SubItemsRecetasAdapter.SubItemsRecetasViewHolder> {
    private List<Cataloga> recetas;

    public SubItemsRecetasAdapter (CategoriaRecetaWrapper wrapper) {
        this.recetas = wrapper.getRecetas();
        if (this.recetas.isEmpty()) {
            this.recetas.add(new Cataloga(
                new Receta(NO_RECETA, ""), wrapper.getCategoria()
            ));
        }
    }

    @NonNull
    @Override
    public SubItemsRecetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__subitem_nombre, parent, false);
        return new SubItemsRecetasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemsRecetasViewHolder holder, int position) {
        Cataloga cataloga = recetas.get(position);

        if(NO_RECETA.equals(cataloga.getId_receta().getNombre())){
            holder.iv_imagen.setVisibility(View.GONE);
        }

        holder.txt_nombre.setText(cataloga.getId_receta().getNombre());

        if (!NO_RECETA.equals(cataloga.getId_receta().getNombre())) {
            holder.v_subitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cataloga catalogaOC = recetas.get(holder.getAdapterPosition());
                    NavDirections action = RecetasFragmentDirections
                            .actionRecetasFragmentToRecetaDetailsFragment(catalogaOC.getId_receta());
                    Navigation.findNavController(view).navigate(action);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class SubItemsRecetasViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;
        private ImageView iv_imagen;

        public SubItemsRecetasViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            iv_imagen = itemView.findViewById(R.id.iv_shared_n);
            txt_nombre = itemView.findViewById(R.id.txt_shared_n_nombre_item);
        }
    }
}
