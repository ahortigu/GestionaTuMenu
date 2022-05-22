package com.aihg.gestionatumenu.ui.recetas.fragments;

import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.RECETA_CREAR_HINT_INSTRUCCIONES;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.RECETA_CREAR_HINT_NOMBRE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.ui.recetas.listener.RecetaListener;
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;
import com.aihg.gestionatumenu.ui.recetas.wrapper.RecetaTemporalWrapper;

import java.util.List;

public class RecetasCreateFragment extends Fragment {
    private View view;
    private RecetasViewModel viewModel;

    private RecetaTemporalWrapper receta;

    private EditText et_rce_nombre_receta;

    private boolean isInstruccionesExpandido;
    private ConstraintLayout l_rce_expandable_instrucciones_parent;
    private ConstraintLayout l_rce_expandable_instrucciones;
    private EditText et_rce_instrucciones;
    private ImageView iv_rce_instrucciones;

    public RecetasCreateFragment() {
        this.isInstruccionesExpandido = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(this).get(RecetasViewModel.class);

        loadRecetaEnCreacion();
        loadObservers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__edit_create_fragment, container, false);

        loadNombreReceta();
        loadInstrucciones();

        return view;
    }

    private void loadNombreReceta() {
        this.et_rce_nombre_receta = view.findViewById(R.id.et_rce_nombre_receta);
        this.et_rce_nombre_receta.setText(receta.getNombre());
        this.et_rce_nombre_receta.setHint(RECETA_CREAR_HINT_NOMBRE);
        this.et_rce_nombre_receta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                receta.setNombre(editable.toString());
            }
        });
    }

    private void loadInstrucciones() {
        this.l_rce_expandable_instrucciones_parent = view.findViewById(R.id.l_rce_instrucciones);
        this.l_rce_expandable_instrucciones = view.findViewById(R.id.l_rce_expandable_instrucciones);
        this.iv_rce_instrucciones = view.findViewById(R.id.iv_rce_instrucciones);
        this.et_rce_instrucciones = view.findViewById(R.id.et_rce_instrucciones);
        this.et_rce_instrucciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                receta.setInstrucciones(editable.toString());
            }
        });

        this.et_rce_instrucciones.setText(receta.getInstrucciones());
        this.et_rce_instrucciones.setHint(RECETA_CREAR_HINT_INSTRUCCIONES);
        this.l_rce_expandable_instrucciones.setVisibility(View.VISIBLE);
        this.iv_rce_instrucciones.setImageResource(R.drawable.ic_arrow_up);

        this.l_rce_expandable_instrucciones_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isInstruccionesExpandido = !isInstruccionesExpandido;
                l_rce_expandable_instrucciones.setVisibility(
                    isInstruccionesExpandido ? View.VISIBLE : View.GONE
                );
                iv_rce_instrucciones.setImageResource(
                    isInstruccionesExpandido ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
                );
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_save).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.nav_save ){
            Toast.makeText(
                    view.getContext(), "TODO", Toast.LENGTH_SHORT
            ).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadObservers() {
        viewModel
            .getCategorias()
            .observe(this, new Observer<List<CategoriaReceta>>() {
                @Override
                public void onChanged(List<CategoriaReceta> categoriaRecetas) {}
            });
    }

    private void loadRecetaEnCreacion() {
        receta = RecetasCreateFragmentArgs.fromBundle(getArguments()).getOnCreation();
        if (receta == null) {
            receta = new RecetaTemporalWrapper();
        }
    }
}