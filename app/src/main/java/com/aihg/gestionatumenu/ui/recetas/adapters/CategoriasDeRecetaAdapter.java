package com.aihg.gestionatumenu.ui.recetas.adapters;

import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.MAX_CATGORIAS_POR_RECETA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.RECETA_SELECT_CATEGORIA_SPINNER;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_RECETA_EDIT_BORRAR_TODAS_CATEGORIAS;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_RECETA_EDIT_CATEGORIA_DUPLICADA;
import static java.util.stream.Collectors.toList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.ui.recetas.listener.RecetaListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriasDeRecetaAdapter
    extends  RecyclerView.Adapter<CategoriasDeRecetaAdapter.CategoriasDeRecetaViewHolder> {

    private static final Cataloga EMPTY_CATALOGA = new Cataloga();
    private static final CategoriaReceta EMPTY_CATEGORIA = new CategoriaReceta();
    private static final CategoriaReceta SELECCIONA_CATEGORIA = new CategoriaReceta(RECETA_SELECT_CATEGORIA_SPINNER);

    private List<Cataloga> catalogas;
    private List<CategoriaReceta> categorias;

    private List<CategoriaReceta> seleccionadas;

    private RecetaListener listener;

    public CategoriasDeRecetaAdapter(RecetaListener listener) {
        this.catalogas = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.seleccionadas = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriasDeRecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shared__subitem_spinner, parent, false);
        return new CategoriasDeRecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasDeRecetaViewHolder holder, int position) {
        CategoriaReceta categoriaReceta = seleccionadas.get(position);
        if (holder.spinner.getAdapter() == null) {
            SpinnerCategoriasRecetaAdapter adapter = new SpinnerCategoriasRecetaAdapter(
                holder.itemView.getContext(),
                android.R.layout.simple_spinner_item,
                categorias
            );
            holder.spinner.setAdapter(adapter);
        }

        int posicion = -1;
        if (categoriaReceta.equals(EMPTY_CATEGORIA)) {
            posicion = categorias.indexOf(EMPTY_CATEGORIA);
        } else {
            posicion = categorias.indexOf(categoriaReceta);
        }
        holder.spinner.setSelection(posicion);

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private CategoriaReceta previous;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                CategoriaReceta seleccionada = (CategoriaReceta) adapterView.getSelectedItem();
                if (previous == null) previous = seleccionada;
                else if (!previous.equals(seleccionada)) {
                    if (seleccionada.equals(SELECCIONA_CATEGORIA)) {
                        if (getNumeroCategoriasSeleccionadas() > 1) {
                            listener.toDeleteCatalogo(previous);
                            notifyDataSetChanged();
                            previous = seleccionada;
                        } else {
                            Toast.makeText(
                                view.getContext(), TOAST_RECETA_EDIT_BORRAR_TODAS_CATEGORIAS, Toast.LENGTH_SHORT
                            ).show();
                            adapterView.setSelection(categorias.indexOf(previous));
                        }
                    } else {
                        if (seleccionadas.contains(seleccionada)) {
                            Toast.makeText(
                                view.getContext(), TOAST_RECETA_EDIT_CATEGORIA_DUPLICADA, Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            listener.toDeleteCatalogo(previous);
                            listener.toAddCatalogo(seleccionada);
                            notifyDataSetChanged();
                            previous = seleccionada;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("SPINNER", "Nada Seleccionado");
            }
        });
    }

    @Override
    public int getItemCount() {
        return seleccionadas.size();
    }

    private long getNumeroCategoriasSeleccionadas() {
        return this.seleccionadas
            .stream()
            .filter(categoriaReceta -> !categoriaReceta.equals(EMPTY_CATEGORIA))
            .count();
    }

    public void setCatalogas(List<Cataloga> catalogas) {
        this.catalogas = catalogas;
        this.seleccionadas = new ArrayList<>();
        this.seleccionadas.addAll(
            catalogas
                .stream()
                .map(Cataloga::getId_categoria_receta)
                .collect(toList())
        );
        while (this.seleccionadas.size() < MAX_CATGORIAS_POR_RECETA) {
            this.seleccionadas.add(EMPTY_CATEGORIA);
        }
        notifyDataSetChanged();
    }

    public void setCategorias(List<CategoriaReceta> categorias) {
        this.categorias = new ArrayList<>();
        this.categorias.add(SELECCIONA_CATEGORIA);
        this.categorias.addAll(categorias);
        notifyDataSetChanged();
    }

    public class CategoriasDeRecetaViewHolder extends RecyclerView.ViewHolder{
        private Spinner spinner;
        public CategoriasDeRecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.sp_rec_shared_categoria);
        }
    }
}
