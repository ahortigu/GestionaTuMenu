package com.aihg.gestionatumenu.ui.recetas.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;


public class RecetaEditFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;

    private RecetasViewModel viewModel;

    private Receta receta;

    private EditText et_rce_nombre_receta;

    private boolean isIngredienteExpandido;
    private ConstraintLayout l_rd_expandable_ingredientes_parent;
    private ConstraintLayout l_rd_expandable_ingredientes;
    private ImageView iv_rd_arrow_ingredientes;
    private ImageView iv_rce_plus;

    private boolean isInstruccionesExpandido;
    private ConstraintLayout l_rce_expandable_instrucciones_parent;
    private ConstraintLayout l_rce_expandable_instrucciones;
    private ImageView iv_rce_instrucciones;
    private EditText et_rce_instrucciones;

    private TextView txt_rd_categoria;

    public RecetaEditFragment() {
        this.isIngredienteExpandido = true;
        this.isInstruccionesExpandido = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receta = RecetaEditFragmentArgs.fromBundle(getArguments()).getAModificar();
        viewModel = new ViewModelProvider(this).get(RecetasViewModel.class);
        loadObservers();
    }

    private void loadObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__edit_create_fragment, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rv_rce_ingredientes);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadNombreReceta();
        loadInstrucciones();

        return view;
    }

    private void loadNombreReceta() {
        this.et_rce_nombre_receta = view.findViewById(R.id.et_rce_nombre_receta);
        this.et_rce_nombre_receta.setText(receta.getNombre());
        this.et_rce_nombre_receta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                receta.setNombre(editable.toString());
                viewModel.updateReceta(receta);
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
                viewModel.updateReceta(receta);
            }
        });

        this.et_rce_instrucciones.setText(receta.getInstrucciones());
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
}