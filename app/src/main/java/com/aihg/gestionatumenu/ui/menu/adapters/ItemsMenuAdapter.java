package com.aihg.gestionatumenu.ui.menu.adapters;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.ui.listacompra.fragments.ListaCompraFragmentDirections;
import com.aihg.gestionatumenu.ui.menu.wrapper.MenuWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemsMenuAdapter extends RecyclerView.Adapter<ItemsMenuAdapter.ItemsCategoriaPlanificadorViewHolder> {

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
    public ItemsCategoriaPlanificadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.menu__item, parent, false);
        return new ItemsCategoriaPlanificadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsCategoriaPlanificadorViewHolder holder, int position) {
        MenuWrapper wrapper = wrappers.get(position);
        Dia dia = wrapper.getDia();

        holder.txt_dia.setText(dia.getNombre());
        setConfigOnTextView(holder.txt_momento_desayuno, dia, wrapper.getDesayuno());
        setConfigOnTextView(holder.txt_momento_comida, dia, wrapper.getComida());
        setConfigOnTextView(holder.txt_momento_cena, dia, wrapper.getCena());

        boolean isExpandable = wrapper.isExpandido();
        holder.l_m_expandable_momentos.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        if (isExpandable) {
            holder.iv_arrow.setImageResource(R.drawable.ic_arrow_up);
        } else {
            holder.iv_arrow.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    private void setConfigOnTextView(TextView momento, Dia dia, @NonNull MenuInterface menu) {
        if (menu.hasReceta()) {
            momento.setText(menu.getId_receta().getNombre());
            momento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickSemanalConReceta(dia, menu);
                    setOnClickPlanificadorConReceta(dia, menu);
                }
            });
        } else {
            setConfigTextViewSemanalSinReceta(momento, dia, menu);
            setConfigTextViewPlanificadorSinReceta(momento, dia, menu);
        }
    }

    private void setOnClickSemanalConReceta(Dia dia, MenuInterface menu) {
        if(isSemanal) {

        }
    }

    private void setOnClickPlanificadorConReceta(Dia dia, MenuInterface menu) {
        if(isPlanificador) {

        }
    }

    private void setConfigTextViewSemanalSinReceta(TextView momento, Dia dia, MenuInterface menu) {
        if(isSemanal) {
            momento.setText("Sin receta planificada");
        }
    }

    private void setConfigTextViewPlanificadorSinReceta(TextView momento, Dia dia, MenuInterface menu) {
        if(isPlanificador) {
            momento.setHint("Elegir " + menu.getId_momento_comida().getNombre().toLowerCase());
            // TODO anadir subrayado en el HINT
            momento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

    public class ItemsCategoriaPlanificadorViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_dia;
        private TextView txt_momento_desayuno;
        private TextView txt_momento_comida;
        private TextView txt_momento_cena;
        private ConstraintLayout l_botones;
        private RelativeLayout l_m_expandable_momentos;
        private ImageView iv_arrow;

        public ItemsCategoriaPlanificadorViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_dia = itemView.findViewById(R.id.txt_m_dia);
            txt_momento_desayuno = itemView.findViewById(R.id.txt_m_momento_desayuno);
            txt_momento_comida = itemView.findViewById(R.id.txt_m_momento_comida);
            txt_momento_cena= itemView.findViewById(R.id.txt_m_momento_cena);
            l_botones = itemView.findViewById(R.id.l_m_botones);
            l_m_expandable_momentos = itemView.findViewById(R.id.l_m_expandable_momentos);
            iv_arrow = itemView.findViewById(R.id.iv_m_arrow);
        }
    }
}
