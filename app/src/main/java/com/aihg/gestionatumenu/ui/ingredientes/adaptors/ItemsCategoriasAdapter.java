package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.ui.ingredientes.wrapper.CategoriaIngredientesWrapper;

import java.util.ArrayList;
import java.util.List;

public class ItemsCategoriasAdapter extends RecyclerView.Adapter<ItemsCategoriasAdapter.CategoriaIngredientesViewHolder> {

    private List<CategoriaIngredientesWrapper> categoriasList;
    private List<String> ingredientesList = new ArrayList<>();

    public ItemsCategoriasAdapter(List<CategoriaIngredientesWrapper> categoriasList) {
        this.categoriasList = categoriasList;
    }

    @NonNull
    @Override
    public CategoriaIngredientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredientes__item_categoria, parent, false);
        return new CategoriaIngredientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaIngredientesViewHolder holder, int position) {
        CategoriaIngredientesWrapper categoriaIngredientesWrapper = categoriasList.get(position);
        holder.txtCategoria.setText(categoriaIngredientesWrapper.getTxtCategoria());

        boolean isExpandable = categoriaIngredientesWrapper.isExpandable();
        holder.l_expandable_ingredientes.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable) {
            holder.iv_arrow.setImageResource(R.drawable.arrow_up);
        } else {
            holder.iv_arrow.setImageResource(R.drawable.arrow_down);
        }

        SubItemsIngredientesAdapter adapter = new SubItemsIngredientesAdapter(ingredientesList);
        holder.rv_child_ingredientes.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.rv_child_ingredientes.setAdapter(adapter);
        holder.l_parent_categoria_ingredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaIngredientesWrapper.setExpandable(!categoriaIngredientesWrapper.isExpandable());
                ingredientesList = categoriaIngredientesWrapper.getNestedIngredientesList();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    public class CategoriaIngredientesViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout l_parent_categoria_ingredientes;
        private RelativeLayout l_expandable_ingredientes;
        private TextView txtCategoria;
        private ImageView iv_arrow;
        private RecyclerView rv_child_ingredientes;

        public CategoriaIngredientesViewHolder(@NonNull View itemView) {
            super(itemView);
            l_parent_categoria_ingredientes = itemView.findViewById(R.id.l_parent_categoria_ingredientes);
            l_expandable_ingredientes = itemView.findViewById(R.id.l_expandable_ingredientes);
            txtCategoria = itemView.findViewById(R.id.txt_nombre_categoria);
            iv_arrow = itemView.findViewById(R.id.iv_arrow);
            rv_child_ingredientes = itemView.findViewById(R.id.rv_subitems_ingrediente);
        }
    }
}