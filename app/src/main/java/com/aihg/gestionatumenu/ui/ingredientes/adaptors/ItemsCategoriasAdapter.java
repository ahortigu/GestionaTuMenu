package com.aihg.gestionatumenu.ui.ingredientes.adaptors;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.NO_INGREDIENTE;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_BORRAR_DESPENSA;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_BORRAR_DESPENSA_FALLADO;

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
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.listener.IngredientesListener;
import com.aihg.gestionatumenu.ui.ingredientes.wrapper.CategoriaWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ItemsCategoriasAdapter extends RecyclerView.Adapter<ItemsCategoriasAdapter.ItemCategoriaViewHolder> {

    private List<CategoriaIngrediente> categorias;
    private List<Ingrediente> ingredientes;
    private List<Ingrediente> puedenBorrar;
    private List<CategoriaWrapper> wrappers;

    private List<CategoriaIngrediente> expandidas;

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private IngredientesListener listener;

    public ItemsCategoriasAdapter(IngredientesListener listener) {
        this.categorias = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.wrappers = new ArrayList<>();
        this.puedenBorrar = new ArrayList<>();
        this.expandidas = new ArrayList<>();
        this.listener = listener;
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

        holder.txt_nombre_categoria.setText(categoria.getNombreCategoria());
        boolean isExpandable = categoria.isExpandido();

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Log.i("Hello", "there");
                return false;
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_m_item_nombre);
                if (NO_INGREDIENTE.equals(txtNombre.getText().toString())) {
                    return 0;
                }
                int positionToCheck = IntStream.range(0, ingredientes.size())
                        .filter(i -> txtNombre.getText().toString().equals(ingredientes.get(i).getIngrediente().getNombre()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("El ingrediente " + txtNombre + " deberia existir."));
                Ingrediente aBloquear = ingredientes.get(positionToCheck);
                if (puedenBorrar.contains(aBloquear)) {
                    return super.getSwipeDirs(recyclerView, viewHolder);
                } else {
                    return 0;
                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_m_item_nombre);
                int positionBorrar = IntStream.range(0, ingredientes.size())
                        .filter(i -> txtNombre.getText().toString().equals(ingredientes.get(i).getIngrediente().getNombre()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("El ingrediente " + txtNombre + " deberia existir."));
                Ingrediente aBorrar = ingredientes.get(positionBorrar);
                if (puedenBorrar.contains(aBorrar)) {
                    Toast.makeText(
                        holder.itemView.getContext(), TOAST_BORRAR_DESPENSA, Toast.LENGTH_SHORT
                    ).show();
                    listener.onDeleteItem(aBorrar, positionBorrar);
                } else {
                    Toast.makeText(
                        holder.itemView.getContext(), TOAST_BORRAR_DESPENSA_FALLADO, Toast.LENGTH_SHORT
                    ).show();
                }
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
        wrapperBuilder();
        notifyDataSetChanged();
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
        wrapperBuilder();
        notifyDataSetChanged();
    }

    public void setIngredientesPuedenBorrar(List<Ingrediente> ingredientesOb) {
        this.puedenBorrar = ingredientesOb;
        wrapperBuilder();
        notifyDataSetChanged();
    }

    private void wrapperBuilder() {
        this.wrappers = this.categorias.stream()
            .map(categoria -> {
                List<Ingrediente> ingrDeLaCategoria = this.ingredientes.stream()
                    .filter(ingrediente -> ingrediente.getCategoriaIngrediente().equals(categoria))
                    .collect(Collectors.toList());
                boolean isExpandida = expandidas.contains(categoria);
                return new CategoriaWrapper(
                    categoria,
                    ingrDeLaCategoria,
                    isExpandida
                );
            })
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