package com.aihg.gestionatumenu.ui.ingredientes;

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

import java.util.ArrayList;
import java.util.List;

public class CategoriaIngredientesAdapter extends RecyclerView.Adapter<CategoriaIngredientesAdapter.CategoriaIngredientesViewHolder> {

    private List<CategoriaIngredientesDataModel> categoriasList;
    private List<String> ingredientesList = new ArrayList<>();

    public CategoriaIngredientesAdapter(List<CategoriaIngredientesDataModel> categoriasList) {
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
        CategoriaIngredientesDataModel categoriaIngredientesDataModel = categoriasList.get(position);
        holder.txtCategoria.setText(categoriaIngredientesDataModel.getTxtCategoria());

        boolean isExpandable = categoriaIngredientesDataModel.isExpandable();
        holder.l_expandable_ingredientes.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable) {
            holder.iv_arrow.setImageResource(R.drawable.arrow_up);
        } else {
            holder.iv_arrow.setImageResource(R.drawable.arrow_down);
        }

        IngredientesNestedAdapter adapter = new IngredientesNestedAdapter(ingredientesList);
        holder.rv_child_ingredientes.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.rv_child_ingredientes.setAdapter(adapter);
        holder.l_parent_categoria_ingredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaIngredientesDataModel.setExpandable(!categoriaIngredientesDataModel.isExpandable());
                ingredientesList = categoriaIngredientesDataModel.getNestedIngredientesList();
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