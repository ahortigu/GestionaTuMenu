package com.aihg.gestionatumenu.ui.shared.fragments.buscar.ingrediente;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.IngredienteInterface;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Planificador;

import java.util.ArrayList;
import java.util.List;

public class BuscarIngredienteAdapter extends RecyclerView.Adapter<BuscarIngredienteAdapter.BuscarIngredienteViewHolder> {
    private List<IngredienteInterface> ingredientes;

    public BuscarIngredienteAdapter() {
        this.ingredientes = new ArrayList<>();
    }

    @NonNull
    @Override
    public BuscarIngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__ingrediente_item, parent, false);
        return new BuscarIngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscarIngredienteViewHolder holder, int position) {
        IngredienteInterface ingrediente = ingredientes.get(position);
        holder.txt_nombre.setText(ingrediente.getIngrediente().getNombre());

        holder.l_shared_buscar_ingrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDestination destinoAnterior = Navigation
                        .findNavController(view)
                        .getPreviousBackStackEntry()
                        .getDestination();

                switch (destinoAnterior.getId()) {
                    case R.id.ingredientesFragment:
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToIngredientesFragment
                                toIngredientes =
                            BuscarIngredienteFragmentDirections.actionBuscarIngredienteFragmentToIngredientesFragment();
                        toIngredientes.setIngredientebuscar((Ingrediente) ingrediente);
                        Navigation.findNavController(view).navigate(toIngredientes);
                        break;
                    case R.id.despensaFragment:
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToDespensaFragment
                                toDespensa =
                            BuscarIngredienteFragmentDirections.actionBuscarIngredienteFragmentToDespensaFragment();
                        toDespensa.setDespensabuscar((Despensa) ingrediente);
                        Navigation.findNavController(view).navigate(toDespensa);
                        break;
                    case R.id.listaCompraFragment:
                        ListaCompra aAnadir = new ListaCompra(0, (Ingrediente) ingrediente);
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToListaCompraFragment
                                toListaCompra =
                            BuscarIngredienteFragmentDirections.actionBuscarIngredienteFragmentToListaCompraFragment();
                        toListaCompra.setListacomprabuscar(aAnadir);
                        Log.i("ENVIANDO INGREDIENTE", "El ingrediente a anadir es "+ aAnadir);
                        Navigation.findNavController(view).navigate(toListaCompra);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public void setIngredientes(List<IngredienteInterface> ingredientes) {
        this.ingredientes = ingredientes;
        notifyDataSetChanged();
    }

    public void setIngredientesFiltrados(List<IngredienteInterface> ingredientes) {
        this.ingredientes = ingredientes;
        notifyDataSetChanged();
    }

    public class BuscarIngredienteViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;
        private ConstraintLayout l_shared_buscar_ingrediente;

        public BuscarIngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_shared_nombre_ingrediente);
            l_shared_buscar_ingrediente = itemView.findViewById(R.id.l_shared_ingrediente);
        }
    }
}
