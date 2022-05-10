package com.aihg.gestionatumenu.ui.despensa.adapters;

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
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.ui.despensa.wrapper.CategoriaWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsCatDespensaAdapter extends  RecyclerView.Adapter<ItemsCatDespensaAdapter.ItemCatDespensaViewHolder>{

    private List<CategoriaIngrediente> categorias;
    private List<Despensa> despensa;
    private List<CategoriaWrapper> wrappers;
    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    public ItemsCatDespensaAdapter() {
        this.categorias = new ArrayList<>();
        this.despensa = new ArrayList<>();
        this.wrappers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemCatDespensaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.despensa__item_, parent, false);
        return new ItemCatDespensaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCatDespensaViewHolder holder, int position) {
        CategoriaWrapper categoria = wrappers.get(position);

        Log.i("CATEGORIA_DESPENSA", "categoria.getNombreCategoria() " + categoria.getNombreCategoria());
        Log.i("CATEGORIA_DESPENSA", "categoria.getDespensa().size() " + categoria.getDespensa().size());

        holder.txt_nombre_categoria.setText(categoria.getNombreCategoria());
        boolean isExpandable = categoria.isExpandido();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rv_child.getContext(), LinearLayoutManager.VERTICAL, false
        );
        layoutManager.setInitialPrefetchItemCount(
                categoria.getDespensa().size()
        );

        SubItemsDespensaAdapter adapter = new SubItemsDespensaAdapter(categoria);
        holder.rv_child.setLayoutManager(layoutManager);
        holder.rv_child.setAdapter(adapter);
        holder.rv_child.setRecycledViewPool(recycledViewPool);

        holder.l_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoria.setExpandido(!categoria.isExpandido());
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
        Log.i("MAPPING", "Actualizando Mapping Pantalla DESPENSA. Cambio Categoria");
        this.categorias = categorias;
        mapCategoriasConDespensaItems();
        notifyDataSetChanged();
    }

    public void setDespensaItems(List<Despensa> despensa) {
        Log.i("MAPPING", "Actualizando Mapping Pantalla DESPENSA. Cambio Despensa item");
        this.despensa = despensa;
        mapCategoriasConDespensaItems();
        notifyDataSetChanged();
    }

    private void mapCategoriasConDespensaItems() {
        this.wrappers = this.categorias.stream()
                .map(categoria -> new CategoriaWrapper(
                        categoria,
                        this.despensa.stream()
                                .filter(despensa -> categoria.equals(despensa.getIngrediente().getCategoriaIngrediente()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public class ItemCatDespensaViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout l_parent;
        private RelativeLayout l_expandable;
        private TextView txt_nombre_categoria;
        private ImageView iv_arrow;

        private RecyclerView rv_child;

        public ItemCatDespensaViewHolder(@NonNull View itemView) {
            super(itemView);
            l_parent = itemView.findViewById(R.id.l_id_despensa);
            l_expandable = itemView.findViewById(R.id.l_id_expandable_ingredientes);
            txt_nombre_categoria = itemView.findViewById(R.id.txt_id_categoria);
            iv_arrow = itemView.findViewById(R.id.iv_id_arrow);
            rv_child = itemView.findViewById(R.id.rv_id_ingredientes);
        }
    }
}
