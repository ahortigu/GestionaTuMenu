package com.aihg.gestionatumenu.ui.menu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.ui.menu.fragments.PlanificadorFragmentDirections;
import com.aihg.gestionatumenu.ui.menu.fragments.SemanalFragmentDirections;
import com.aihg.gestionatumenu.ui.menu.wrapper.MenuWrapper;

public class SubitemsMenuAdapter extends RecyclerView.Adapter<SubitemsMenuAdapter.SubItemMenuViewHolder> {
    private MenuWrapper wrapper;

    private Boolean isSemanal;
    private Boolean isPlanificador;

    private View view;

    public SubitemsMenuAdapter(MenuWrapper wrapper, boolean isSemanal, boolean isPlanificador) {
        this.wrapper = wrapper;
        if (isSemanal == isPlanificador) throw new IllegalStateException(
            "No puede ser un adaptador para Semanal y Planificador al mismo. Uno de los valores debe ser true."
        );

        this.isPlanificador = isSemanal;
        this.isSemanal = isPlanificador;
    }

    @NonNull
    @Override
    public SubItemMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.menu__subitem, parent, false);
        return new SubItemMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemMenuViewHolder holder, int position) {
        MenuInterface menu = wrapper.getMenuDelDia().get(position);
        setConfigOnTextView(holder, menu);
    }

    @Override
    public int getItemCount() {
        return this.wrapper.getMenuDelDia().size();
    }

    private void setConfigOnTextView(@NonNull SubItemMenuViewHolder holder, MenuInterface menu) {
        holder.lb_momento.setText(menu.getNombreMomentoDia());
        if (menu.hasReceta()) {
            holder.txt_receta.setText(menu.getNombreReceta());
            holder.v_subitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickSemanalConReceta(menu);
                    setOnClickPlanificadorConReceta(menu);
                }
            });
        } else {
            setConfigTextViewSemanalSinReceta(holder, menu);
            setConfigTextViewPlanificadorSinReceta(holder, menu);
        }
    }

    private void setOnClickSemanalConReceta(MenuInterface menu) {
        if(isSemanal && menu.getId_receta() != null) {
            NavDirections action = SemanalFragmentDirections
                    .actionMenuFragmentToRecetaDetailsFragment(menu.getId_receta());
            Navigation.findNavController(view).navigate(action);
        }
    }

    private void setOnClickPlanificadorConReceta(MenuInterface menu) {
        if(isPlanificador) {
            PlanificadorFragmentDirections.ActionPlanificadorFragmentToBuscarRecetaFragment
                    action =  PlanificadorFragmentDirections
                    .actionPlanificadorFragmentToBuscarRecetaFragment();
            action.setBuscarPlanificador((Planificador) menu);
            Navigation.findNavController(view).navigate(action);
        }
    }

    private void setConfigTextViewSemanalSinReceta(
        @NonNull SubItemMenuViewHolder holder, MenuInterface menu
    ) {
        if(isSemanal) holder.txt_receta.setText("Sin receta planificada");
    }

    private void setConfigTextViewPlanificadorSinReceta(
        @NonNull SubItemMenuViewHolder holder, MenuInterface menu
    ) {
        if(isPlanificador) {
            holder.txt_receta.setText("");
            holder.txt_receta.setHint(
                "Elegir " + menu.getId_momento_comida().getNombre().toLowerCase() + "..."
            );
            // TODO anadir subrayado en el HINT
            holder.v_subitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlanificadorFragmentDirections.ActionPlanificadorFragmentToBuscarRecetaFragment
                            action =  PlanificadorFragmentDirections
                            .actionPlanificadorFragmentToBuscarRecetaFragment();
                    action.setBuscarPlanificador((Planificador) menu);
                    Navigation.findNavController(view).navigate(action);
                }
            });
        }
    }

    public class SubItemMenuViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;

        private TextView lb_momento;
        private TextView txt_receta;

        public SubItemMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            lb_momento = itemView.findViewById(R.id.lb_m_momento);
            txt_receta = itemView.findViewById(R.id.txt_m_receta);
        }
    }
}
