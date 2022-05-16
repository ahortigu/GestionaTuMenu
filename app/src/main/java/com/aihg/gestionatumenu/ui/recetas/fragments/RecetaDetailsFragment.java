package com.aihg.gestionatumenu.ui.recetas.fragments;

import static java.util.stream.Collectors.joining;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.ui.recetas.adapters.IngredientesDeRecetaAdapter;
import com.aihg.gestionatumenu.ui.recetas.adapters.ItemsCategoriaRecetaAdapter;
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecetaDetailsFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;

    private RecetasViewModel viewModel;

    private IngredientesDeRecetaAdapter adapter;

    private Receta receta;

    private boolean isIngredienteExpandido;
    private ConstraintLayout l_rd_expandable_ingredientes_parent;
    private ConstraintLayout l_rd_expandable_ingredientes;
    private ImageView iv_rd_arrow_ingredientes;

    private boolean isInstruccionesExpandido;
    private ConstraintLayout l_rd_expandable_instrucciones_parent;
    private ConstraintLayout l_rd_expandable_instrucciones;
    private ImageView iv_rd_instrucciones;
    private TextView txt_instrucciones;

    private TextView txt_rd_categoria;

    public RecetaDetailsFragment() {
        this.isIngredienteExpandido = true;
        this.isInstruccionesExpandido = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        receta = RecetaDetailsFragmentArgs.fromBundle(getArguments()).getReceta();
        viewModel = new ViewModelProvider(this).get(RecetasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__details_fragment, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rv_rd_ingredientes);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadNombreReceta();
        loadCategorias();
        loadIngredientes();
        loadInstrucciones();

        return view;
    }

    private void loadNombreReceta() {
        TextView txt_nombre = view.findViewById(R.id.txt_rd_receta_nombre);
        txt_nombre.setText(receta.getNombre());
    }

    private void loadCategorias() {
        this.txt_rd_categoria = view.findViewById(R.id.txt_rd_categoria);
        this.viewModel
            .getCatalogaByReceta(receta)
            .observe(getViewLifecycleOwner(), catalogosOv -> {
                String categorias = "";
                if (!catalogosOv.isEmpty()) {
                    categorias = catalogosOv
                        .stream()
                        .map(catalogo -> catalogo.getId_categoria_receta().getNombre())
                        .collect(joining(", "));
                }
                txt_rd_categoria.setText(categorias);
            });
    }

    private void loadIngredientes() {
        if (adapter == null) adapter = new IngredientesDeRecetaAdapter(false);
        this.recyclerView.setAdapter(adapter);

        this.viewModel
            .getUtilizaByReceta(receta)
            .observe(getViewLifecycleOwner(), new Observer<List<Utiliza>>() {
                @Override
                public void onChanged(List<Utiliza> utilizas) {
                    adapter.setIngredientes(utilizas);
                }
            });

        this.l_rd_expandable_ingredientes_parent = view.findViewById(R.id.l_rd_ingredientes);
        this.l_rd_expandable_ingredientes = view.findViewById(R.id.l_rd_expandable_ingredientes);
        this.iv_rd_arrow_ingredientes = view.findViewById(R.id.iv_rd_arrow_ingredientes);

        this.l_rd_expandable_ingredientes_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isIngredienteExpandido = !isIngredienteExpandido;
                l_rd_expandable_ingredientes.setVisibility(
                    isIngredienteExpandido ? View.VISIBLE : View.GONE
                );
                iv_rd_arrow_ingredientes.setImageResource(
                    isIngredienteExpandido ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
                );
            }
        });
    }

    private void loadInstrucciones() {
        this.l_rd_expandable_instrucciones_parent = view.findViewById(R.id.l_rd_instrucciones);
        this.l_rd_expandable_instrucciones = view.findViewById(R.id.l_rd_expandable_instrucciones);
        this.iv_rd_instrucciones = view.findViewById(R.id.iv_rd_instrucciones);
        this.txt_instrucciones = view.findViewById(R.id.txt_rd_instrucciones);

        this.txt_instrucciones.setText(receta.getInstrucciones());
        this.l_rd_expandable_instrucciones.setVisibility(View.VISIBLE);
        this.iv_rd_instrucciones.setImageResource(R.drawable.ic_arrow_up);

        this.l_rd_expandable_instrucciones_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isInstruccionesExpandido = !isInstruccionesExpandido;
                l_rd_expandable_instrucciones.setVisibility(
                    isInstruccionesExpandido ? View.VISIBLE : View.GONE
                );
                iv_rd_instrucciones.setImageResource(
                    isInstruccionesExpandido ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
                );
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_buscar).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavDirections action;
        if(id == R.id.nav_editar) {
            action = RecetaDetailsFragmentDirections.actionRecetaDetailsFragmentToRecetaEditFragment();
            Navigation.findNavController(view).navigate(action);
        }
        return super.onOptionsItemSelected(item);
    }
}