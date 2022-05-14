package com.aihg.gestionatumenu.ui.menu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.ui.menu.wrapper.MenuWrapper;

import java.util.List;

public class ItemsMenuAdapter extends RecyclerView.Adapter<ItemsMenuAdapter.ItemsCategoriaPlanificadorViewHolder> {

    private List<Dia> dias;
    private List<MomentoComida> momentos;
    private List<Planificador> planificadorList;
    private List<MenuWrapper> wrappers;

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

        MenuWrapper categoriaDia = wrappers.get(position);

        holder.txt_dia.setText(categoriaDia.getDia().getNombre());

        //holder.txt_momento_desayuno.setText(categoriaDia.getMomento(categoriaDia.getMomentos(), 0));
        //holder.txt_momento_comida.setText(categoriaDia.getMomento(categoriaDia.getMomentos(), 1));
        //holder.txt_momento_cena.setText(categoriaDia.getMomento(categoriaDia.getMomentos(), 2));

        boolean isExpandable = categoriaDia.getExpandido();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemsCategoriaPlanificadorViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;

        private TextView txt_dia;
        private TextView txt_momento_desayuno;
        private TextView txt_momento_comida;
        private TextView txt_momento_cena;

        public ItemsCategoriaPlanificadorViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_dia = itemView.findViewById(R.id.txt_m_dia);
            txt_momento_desayuno = itemView.findViewById(R.id.txt_m_momento_desayuno);
            txt_momento_comida = itemView.findViewById(R.id.txt_m_momento_comida);
            txt_momento_cena= itemView.findViewById(R.id.txt_m_momento_cena);
        }
    }
}
