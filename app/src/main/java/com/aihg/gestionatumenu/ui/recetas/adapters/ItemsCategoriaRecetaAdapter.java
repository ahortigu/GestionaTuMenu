package com.aihg.gestionatumenu.ui.recetas.adapters;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_DESPENSA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_RECETA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_BORRAR_DESPENSA;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

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
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.ui.recetas.wrapper.CategoriaRecetaWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ItemsCategoriaRecetaAdapter extends RecyclerView.Adapter<ItemsCategoriaRecetaAdapter.ItemsCategoriaRecetaViewHolder>{

    private List<CategoriaReceta> categorias;
    private List<Cataloga> catalogo;

    private List<CategoriaRecetaWrapper> wrapper;

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    public ItemsCategoriaRecetaAdapter() {
        this.categorias = new ArrayList<>();
        this.catalogo = new ArrayList<>();
        this.wrapper = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemsCategoriaRecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recetas__item, parent, false);
        return new ItemsCategoriaRecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsCategoriaRecetaViewHolder holder, int position) {
        CategoriaRecetaWrapper catalogaItem = this.wrapper.get(position);

        holder.txt_nombre_categoria.setText(catalogaItem.getNombreCategoria());

        boolean isExpandable = catalogaItem.isExpandido();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rv_child.getContext(), LinearLayoutManager.VERTICAL, false
        );

        layoutManager.setInitialPrefetchItemCount(
            catalogaItem.getRecetas().size()
        );

        SubItemsRecetasAdapter adapter = new SubItemsRecetasAdapter(catalogaItem);
        holder.rv_child.setLayoutManager(layoutManager);
        holder.rv_child.setAdapter(adapter);
        holder.rv_child.setRecycledViewPool(recycledViewPool);

        holder.l_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogaItem.setExpandido(!catalogaItem.isExpandido());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_n_nombre_item);
                if (txtNombre.getText().toString().equals(NO_RECETA)) {
                    return 0;
                } else {
                    return super.getSwipeDirs(recyclerView, viewHolder);
                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_n_nombre_item);
                //int positionBorrar = IntStream.range(0, wrapper.size())
                //    .filter(i -> txtNombre.getText().toString().equals(catalogaItem.get(i).getNombre()))
                //    .findFirst()
                //    .orElseThrow(() -> new IllegalStateException("El ingrediente " + txtNombre + " deberia existir."));
                //listener.onDeleteItem(wrapper.get(positionBorrar), positionBorrar);
                Toast.makeText(
                    holder.itemView.getContext(), TOAST_BORRAR_DESPENSA, Toast.LENGTH_SHORT
                ).show();
            }
        }).attachToRecyclerView(holder.rv_child);

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
        if (wrapper == null) return 0;
        else return wrapper.size();
    }

    public void setCategorias(List<CategoriaReceta> categorias) {
        this.categorias = categorias;
        wrapperBuilder();
        notifyDataSetChanged();
    }

    public void setCatalogo(List<Cataloga> catalogo) {
        this.catalogo = catalogo;
        wrapperBuilder();
        notifyDataSetChanged();
    }

    public void wrapperBuilder() {
        Map<CategoriaReceta, List<Cataloga>> mapCategorias = this.categorias
            .stream()
            .collect(toMap(
                categoria -> categoria,
                listaVacia -> new ArrayList<>()
            ));

        Map<CategoriaReceta, List<Cataloga>> mapCatalogo = this.catalogo.stream()
            .collect(groupingBy(Cataloga::getId_categoria_receta));

        // https://stackoverflow.com/questions/4299728/how-can-i-combine-two-hashmap-objects-containing-the-same-types
        // Note that, with this solution, if a key exists in both maps, the value in map2 will be preserved and the value in map1 lost.
        // Michael Scheper
        // Dec 16, 2013 at 1:06
        // En el primer mapa, siempre seran listas vacias cuyo objetivo es reemplazarlas.
        Map<CategoriaReceta, List<Cataloga>> combinado = new HashMap<>();
        combinado.putAll(mapCategorias);
        combinado.putAll(mapCatalogo);

        this.wrapper = combinado.keySet()
            .stream()
            .map(
                categoria -> new CategoriaRecetaWrapper(categoria, combinado.get(categoria))
            )
            .sorted(comparing(CategoriaRecetaWrapper::getNombreCategoria))
            .collect(Collectors.toList());
    }

    public class ItemsCategoriaRecetaViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout l_parent;
        private RelativeLayout l_expandable;
        private TextView txt_nombre_categoria;
        private ImageView iv_arrow;
        private RecyclerView rv_child;

        public ItemsCategoriaRecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            l_parent = itemView.findViewById(R.id.l_ri_recetas);
            l_expandable = itemView.findViewById(R.id.l_ri_expandable_recetas);
            txt_nombre_categoria = itemView.findViewById(R.id.txt_ri_categoria);
            iv_arrow = itemView.findViewById(R.id.iv_ri_arrow);
            rv_child = itemView.findViewById(R.id.rv_ri_recetas);
        }
    }
}
