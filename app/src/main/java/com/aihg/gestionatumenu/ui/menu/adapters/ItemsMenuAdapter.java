package com.aihg.gestionatumenu.ui.menu.adapters;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.ui.menu.wrapper.MenuWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemsMenuAdapter extends RecyclerView.Adapter<ItemsMenuAdapter.ItemsMenuViewHolder> {

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    private List<MenuInterface> menu;
    private List<MenuWrapper> wrappers;

    private Boolean isSemanal;
    private Boolean isPlanificador;

    public ItemsMenuAdapter(boolean isSemanal, boolean isPlanificador) {
        this.menu = new ArrayList<>();
        this.wrappers = new ArrayList<>();

        if (isSemanal == isPlanificador) throw new IllegalStateException(
            "No puede ser un adaptador para Semanal y Planificador al mismo. Uno de los valores debe ser true."
        );

        this.isPlanificador = isSemanal;
        this.isSemanal = isPlanificador;
    }

    @Override
    public int getItemCount() {
        return wrappers.size();
    }

    public void setMenu(List<MenuInterface> menu) {
        this.menu = menu;
        buildWrapper();
        notifyDataSetChanged();
    }

    private void buildWrapper() {
        Map<Dia, List<MenuInterface>> byDia = this.menu.stream()
            .collect(groupingBy(MenuInterface::getId_dia));

        this.wrappers = byDia.entrySet()
            .stream()
            .map(dia -> new MenuWrapper(dia.getKey(), dia.getValue()))
            .sorted(comparingInt(MenuWrapper::getDiaId))
            .collect(toList());
    }

    @NonNull
    @Override
    public ItemsMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.menu__item, parent, false);
        return new ItemsMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsMenuViewHolder holder, int position) {
        MenuWrapper wrapper = wrappers.get(position);
        Dia dia = wrapper.getDia();

        holder.txt_dia.setText(dia.getNombre());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
            holder.rv_child.getContext(), LinearLayoutManager.VERTICAL, false
        );
        layoutManager.setInitialPrefetchItemCount(
            wrapper.getMenuDelDia().size()
        );

        SubitemsMenuAdapter adapter = new SubitemsMenuAdapter(wrapper, isSemanal, isPlanificador);
        holder.rv_child.setLayoutManager(layoutManager);
        holder.rv_child.setAdapter(adapter);
        holder.rv_child.setRecycledViewPool(recycledViewPool);

        holder.l_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrapper.setExpandido(!wrapper.isExpandido());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        boolean isExpandable = wrapper.isExpandido();
        holder.l_m_expandable_momentos.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        holder.iv_arrow.setImageResource(
            isExpandable ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
        );
    }

    public class ItemsMenuViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout l_parent;
        private RelativeLayout l_m_expandable_momentos;
        private TextView txt_dia;
        private RecyclerView rv_child;
        private ImageView iv_arrow;

        public ItemsMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            l_parent = itemView.findViewById(R.id.l_m_recetas);
            l_m_expandable_momentos = itemView.findViewById(R.id.l_m_expandable_momentos);
            txt_dia = itemView.findViewById(R.id.txt_m_dia);
            iv_arrow = itemView.findViewById(R.id.iv_m_arrow);
            rv_child = itemView.findViewById(R.id.rv_m_recetas);
        }
    }
}
