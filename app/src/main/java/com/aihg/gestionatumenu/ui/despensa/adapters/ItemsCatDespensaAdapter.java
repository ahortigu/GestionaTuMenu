package com.aihg.gestionatumenu.ui.despensa.adapters;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_DESPENSA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_BORRAR_DESPENSA;

import static java.util.stream.Collectors.toList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.ui.despensa.listener.DespensaListener;
import com.aihg.gestionatumenu.ui.despensa.wrapper.CategoriaWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ItemsCatDespensaAdapter extends  RecyclerView.Adapter<ItemsCatDespensaAdapter.ItemCatDespensaViewHolder>{

    private List<CategoriaIngrediente> categorias;
    private List<Despensa> despensa;
    private List<CategoriaWrapper> wrappers;

    private List<CategoriaIngrediente> expandidas;

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    private DespensaListener listener;

    public ItemsCatDespensaAdapter(DespensaListener listener) {
        this.categorias = new ArrayList<>();
        this.despensa = new ArrayList<>();
        this.wrappers = new ArrayList<>();
        this.expandidas = new ArrayList<>();
        this.listener = listener;
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

        holder.txt_nombre_categoria.setText(categoria.getNombreCategoria());
        boolean isExpandable = categoria.isExpandido();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rv_child.getContext(), LinearLayoutManager.VERTICAL, false
        );
        layoutManager.setInitialPrefetchItemCount(
                categoria.getDespensa().size()
        );

        SubItemsDespensaAdapter adapter = new SubItemsDespensaAdapter(categoria, listener);
        holder.rv_child.setLayoutManager(layoutManager);
        holder.rv_child.setAdapter(adapter);
        holder.rv_child.setRecycledViewPool(recycledViewPool);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_c_item_nombre);
                if (txtNombre.getText().toString().equals(NO_DESPENSA)) {
                    return 0;
                } else {
                    return super.getSwipeDirs(recyclerView, viewHolder);
                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_c_item_nombre);
                int positionBorrar = IntStream.range(0, despensa.size())
                        .filter(i -> txtNombre.getText().toString().equals(despensa.get(i).getIngrediente().getNombre()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("El ingrediente " + txtNombre + " deberia existir."));
                listener.onDeleteItem(despensa.get(positionBorrar), positionBorrar);
                Toast.makeText(
                    holder.itemView.getContext(), TOAST_BORRAR_DESPENSA, Toast.LENGTH_SHORT
                ).show();
            }
        }).attachToRecyclerView(holder.rv_child);

        holder.l_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoria.setExpandido(!categoria.isExpandido());
                if (categoria.isExpandido()) {
                    expandidas.add(categoria.getCategoriaIngrediente());
                } else {
                    expandidas.remove(categoria.getCategoriaIngrediente());
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.l_expandable.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        holder.iv_arrow.setImageResource(
            isExpandable ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
        );
    }

    @Override
    public int getItemCount() {
        if (wrappers == null) return 0;
        else return wrappers.size();
    }

    public void setCategorias(List<CategoriaIngrediente> categorias) {
        this.categorias = categorias;
        mapCategoriasConDespensaItems();
        notifyDataSetChanged();
    }

    public void setDespensaItems(List<Despensa> despensa) {
        this.despensa = despensa;
        mapCategoriasConDespensaItems();
        notifyDataSetChanged();
    }

    private void mapCategoriasConDespensaItems() {
        this.wrappers = this.categorias.stream()
            .map(categoria -> {
                boolean isExpandida = expandidas.contains(categoria);
                return new CategoriaWrapper(
                    categoria,
                    this.despensa.stream()
                        .filter(despensa -> categoria.equals(despensa.getIngrediente().getCategoriaIngrediente()))
                        .collect(toList()),
                    isExpandida
                );
            })
            .collect(toList());
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
