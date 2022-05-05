package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import android.util.Log;
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
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.wrapper.CategoriaWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsCategoriasAdapter extends RecyclerView.Adapter<ItemsCategoriasAdapter.ItemCategoriaViewHolder> {

    private List<CategoriaIngrediente> categorias;
    private List<Ingrediente> ingredientes;
    private List<CategoriaWrapper> wrappers;

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    public ItemsCategoriasAdapter() {
        this.categorias = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.wrappers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemCategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.ingredientes__item, parent, false);
        return new ItemCategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoriaViewHolder holder, int position) {
        CategoriaWrapper categoria = wrappers.get(position);

        Log.i("CATEGORIA", "categoria.getNombreCategoria() " + categoria.getNombreCategoria());
        Log.i("CATEGORIA", "categoria.getNestedIngredientesList().size() " + categoria.getIngredientes().size());
        // Log.i("CATEGORIA", "ingredientesList.size() " + ingredientesList.size());

        holder.txt_nombre_categoria.setText(categoria.getNombreCategoria());
        boolean isExpandable = categoria.isExpandido();
        //if (isExpandable) ingredientesList = categoria.getNestedIngredientesList();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
            holder.rv_child.getContext(), LinearLayoutManager.VERTICAL, false
        );
        layoutManager.setInitialPrefetchItemCount(
            categoria.getIngredientes().size()
        );

        SubItemsIngredientesAdapter adapter = new SubItemsIngredientesAdapter(categoria);
        holder.rv_child.setLayoutManager(layoutManager);
        holder.rv_child.setAdapter(adapter);
        holder.rv_child.setRecycledViewPool(recycledViewPool);

        holder.l_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoria.setExpandido(!categoria.isExpandido());
                //ingredientesList = categoria.getNestedIngredientesList();
                //holder.l_expandable_ingredientes.setVisibility(categoria.isExpandido() ? View.VISIBLE : View.GONE);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.l_expandable.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        Log.i("CATEGORIA", "isExpandable " + isExpandable);
        if (isExpandable) {
            holder.iv_arrow.setImageResource(R.drawable.ic_arrow_up);
        } else {
            holder.iv_arrow.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    @Override
    public int getItemCount() {
        if (wrappers == null) return 0;
        else return wrappers.size();
    }

    public void setCategorias(List<CategoriaIngrediente> categorias) {
        Log.i("MAPPING", "Actualizando Mapping Pantalla Ingredientes. Cambio Categoria");
        this.categorias = categorias;
        mapCategoriasConIngredientes();
        notifyDataSetChanged();
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        Log.i("MAPPING", "Actualizando Mapping Pantalla Ingredientes. Cambio Ingrediente");
        this.ingredientes = ingredientes;
        mapCategoriasConIngredientes();
        notifyDataSetChanged();
    }

    private void mapCategoriasConIngredientes() {
        this.wrappers = this.categorias.stream()
            .map(categoria -> new CategoriaWrapper(
                categoria,
                this.ingredientes.stream()
                    .filter(ingrediente -> ingrediente.getCategoriaIngrediente().equals(categoria))
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
    }

    public class ItemCategoriaViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout l_parent;
        private RelativeLayout l_expandable;
        private TextView txt_nombre_categoria;
        private ImageView iv_arrow;

        private RecyclerView rv_child;

        public ItemCategoriaViewHolder(@NonNull View itemView) {
            super(itemView);

            l_parent = itemView.findViewById(R.id.l_ii_ingredientes);
            l_expandable = itemView.findViewById(R.id.l_ii_expandable_ingredientes);
            txt_nombre_categoria = itemView.findViewById(R.id.txt_ii_categoria);
            iv_arrow = itemView.findViewById(R.id.iv_ii_arrow);
            rv_child = itemView.findViewById(R.id.rv_ii_ingredientes);
        }
    }
}