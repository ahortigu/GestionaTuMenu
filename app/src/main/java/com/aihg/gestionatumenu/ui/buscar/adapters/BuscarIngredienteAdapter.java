package com.aihg.gestionatumenu.ui.buscar.adapters;

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
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.ui.buscar.fragments.BuscarIngredienteFragmentDirections;
import com.aihg.gestionatumenu.ui.recetas.wrapper.RecetaTemporalWrapper;

import java.util.ArrayList;
import java.util.List;

public class BuscarIngredienteAdapter extends RecyclerView.Adapter<BuscarIngredienteAdapter.BuscarIngredienteViewHolder> {
    private List<IngredienteInterface> ingredientes;

    private Receta toEdit;
    private RecetaTemporalWrapper onCreation;

    public BuscarIngredienteAdapter() {
        this.ingredientes = new ArrayList<>();
    }

    @NonNull
    @Override
    public BuscarIngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__subitem_nombre, parent, false);
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
                    case R.id.despensaFragment:
                        Despensa aAnadirDespensa = new Despensa(0, (Ingrediente) ingrediente);
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToDespensaFragment
                                toDespensa =
                                    BuscarIngredienteFragmentDirections
                                        .actionBuscarIngredienteFragmentToDespensaFragment();
                        toDespensa.setDespensabuscar((Despensa) aAnadirDespensa);
                        Navigation.findNavController(view).navigate(toDespensa);
                        break;
                    case R.id.listaCompraFragment:
                        ListaCompra aAnadirListaCompra = new ListaCompra(0, (Ingrediente) ingrediente);
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToListaCompraFragment
                                toListaCompra =
                                    BuscarIngredienteFragmentDirections
                                        .actionBuscarIngredienteFragmentToListaCompraFragment();
                        toListaCompra.setListacomprabuscar(aAnadirListaCompra);
                        Navigation.findNavController(view).navigate(toListaCompra);
                        break;
                    case R.id.recetasCreateFragment:
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToRecetasCreateFragment
                            toRecetaCreate =
                                BuscarIngredienteFragmentDirections
                                    .actionBuscarIngredienteFragmentToRecetasCreateFragment();
                        onCreation.anadirIngrediente((Ingrediente) ingrediente);
                        toRecetaCreate.setOnCreation(onCreation);
                        Navigation.findNavController(view).navigate(toRecetaCreate);
                        break;
                    case R.id.recetaEditFragment:
                        BuscarIngredienteFragmentDirections.ActionBuscarIngredienteFragmentToRecetaEditFragment
                            toRecetaEdit =
                                BuscarIngredienteFragmentDirections
                                    .actionBuscarIngredienteFragmentToRecetaEditFragment(toEdit);
                        toRecetaEdit.setAAnadir((Ingrediente) ingrediente);
                        Navigation.findNavController(view).navigate(toRecetaEdit);
                        break;
                    default:
                        throw new IllegalStateException("Se debe de tener una pantalla donde navegar.");
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

    public void setToEdit(Receta toEdit) {
        this.toEdit = toEdit;
    }

    public void setOnCreation(RecetaTemporalWrapper onCreation) {
        this.onCreation = onCreation;
    }

    public class BuscarIngredienteViewHolder extends RecyclerView.ViewHolder {
        private View v_subitem;
        private TextView txt_nombre;
        private ConstraintLayout l_shared_buscar_ingrediente;

        public BuscarIngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            v_subitem = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_shared_n_nombre_item);
            l_shared_buscar_ingrediente = itemView.findViewById(R.id.l_shared_nombre);
        }
    }
}
