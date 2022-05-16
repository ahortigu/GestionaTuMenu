package com.aihg.gestionatumenu.ui.recetas.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;

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

    private boolean isInstruccionesExpandido;
    private ConstraintLayout l_rd_expandable_instrucciones_parent;
    private ConstraintLayout l_rd_expandable_instrucciones;
    private ImageView iv_rd_instrucciones;
    private TextView txt_instrucciones;

    public RecetaDetailsFragment() {
        this.isIngredienteExpandido = false;
        this.isInstruccionesExpandido = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        receta = RecetaDetailsFragmentArgs.fromBundle(getArguments()).getReceta();

        viewModel = new ViewModelProvider(this).get(RecetasViewModel.class);
        setObservers();
    }

    private void setObservers() {
        viewModel
            .getUtilizaByReceta(receta)
            .observe(this, new Observer<List<Utiliza>>() {
                @Override
                public void onChanged(List<Utiliza> utilizas) {

                }
            });
        viewModel
            .getCatalogaByReceta(receta)
            .observe(this, new Observer<List<Cataloga>>() {
                @Override
                public void onChanged(List<Cataloga> cataloga) {

                }
            });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__details_fragment, container, false);

        TextView txt_nombre = view.findViewById(R.id.txt_rd_receta_nombre);
        txt_nombre.setText(receta.getNombre());

        loadInstrucciones();


        //TextView txt_categoria = view.findViewById(R.id.txt_rd_categoria);
       // txt_categoria.setText(cataloga.getId_categoria_receta().getNombre());

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_rd_ingredientes);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new IngredientesDeRecetaAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadInstrucciones() {
        this.l_rd_expandable_instrucciones_parent = view.findViewById(R.id.l_rd_instrucciones);
        this.l_rd_expandable_instrucciones = view.findViewById(R.id.l_rd_expandable_instrucciones);
        this.iv_rd_instrucciones = view.findViewById(R.id.iv_rd_instrucciones);
        this.txt_instrucciones = view.findViewById(R.id.txt_rd_instrucciones);

        txt_instrucciones.setText(receta.getInstrucciones());
        l_rd_expandable_instrucciones.setVisibility(View.VISIBLE);
        iv_rd_instrucciones.setImageResource(R.drawable.ic_arrow_up);

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