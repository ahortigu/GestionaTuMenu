package com.aihg.gestionatumenu.ui.ingredientes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.SpinnerCategoriasAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.SpinnerMedicionAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.viewmodel.IngredientesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class IngredienteEditFragment extends Fragment {
    private View view;
    private IngredientesViewModel viewModel;

    private Ingrediente toModify;

    private Spinner categoriasSpinner;
    private Spinner medicionesSpinner;

    public IngredienteEditFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Log.i("LOADING", "Creating Observables on Ingrediente Edit Screen");
        viewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);
        viewModel
            .getCategorias()
            .observe(this, new Observer<List<CategoriaIngrediente>>() {
                @Override
                public void onChanged(List<CategoriaIngrediente> categoriasOv) {
                    Log.d("LOADING", "Las categorias son:" + categoriasOv);
                    // Categoria
                    SpinnerCategoriasAdapter adapter = new SpinnerCategoriasAdapter(
                            view.getContext(),
                            android.R.layout.simple_spinner_item,
                            categoriasOv
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    categoriasSpinner = (Spinner) view.findViewById(R.id.sp_ie_categoria);
                    categoriasSpinner.setAdapter(adapter);

                    int posicionCategoria = getPosicionCategoria(categoriasOv);
                    if (posicionCategoria > 0) {
                        categoriasSpinner.setSelection(posicionCategoria);
                    }

                    Log.i("SPINNER", "Adaptador seteado");
                    categoriasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            CategoriaIngrediente seleccionado = (CategoriaIngrediente) adapterView.getSelectedItem();
                            Log.i("SPINNER", "Se ha elegido la categoria: " + seleccionado);
                            toModify.setCategoriaIngrediente(seleccionado);
                            viewModel.updateIngrediente(toModify);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapter) {
                            Log.i("SPINNER", "Nada Seleccionado");
                        }
                    });
                }
            });
        viewModel
            .getMediciones()
            .observe(this, new Observer<List<Medicion>>() {
                @Override
                public void onChanged(List<Medicion> medicionesOv) {
                    Log.d("LOADING", "Las mediciones son:" + medicionesOv);
                    // Categoria
                    SpinnerMedicionAdapter adapter = new SpinnerMedicionAdapter(
                            view.getContext(),
                            android.R.layout.simple_spinner_item,
                            medicionesOv
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    medicionesSpinner = (Spinner) view.findViewById(R.id.sp_ie_medicion);
                    medicionesSpinner.setAdapter(adapter);

                    int posicionMedicion = getPosicionMedicion(medicionesOv);
                    if (posicionMedicion > 0) {
                        medicionesSpinner.setSelection(posicionMedicion);
                    }

                    Log.i("SPINNER", "Adaptador seteado");
                    medicionesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            Medicion seleccionado = (Medicion) adapterView.getSelectedItem();
                            Log.i("SPINNER", "Se ha elegido la medicion: " + seleccionado);
                            toModify.setMedicion(seleccionado);
                            viewModel.updateIngrediente(toModify);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapter) {
                            Log.i("SPINNER", "Nada Seleccionado");
                        }
                    });
                }
            });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.ingredientes__edit_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toModify = IngredienteDetailsFragmentArgs.fromBundle(getArguments()).getIngrediente();
        TextView txt_nombre = view.findViewById(R.id.txt_ie_ingrediente);
        txt_nombre.setHint(toModify.getNombre());
        txt_nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                // TODO anadir validaciones aqui
                toModify.setNombre(s.toString());
                viewModel.updateIngrediente(toModify);
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_editar).setVisible(false);
        menu.findItem(R.id.nav_buscar).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);
    }



    private int getPosicionCategoria(List<CategoriaIngrediente> dondeBuscar) {
        return IntStream.range(0, dondeBuscar.size())
            .filter(i -> dondeBuscar.get(i).equals(toModify.getCategoriaIngrediente()))
            .findFirst()
            .orElse(-1);
    }

    private int getPosicionMedicion(List<Medicion> dondeBuscar) {
        return IntStream.range(0, dondeBuscar.size())
            .filter(i -> dondeBuscar.get(i).equals(toModify.getMedicion()))
            .findFirst()
            .orElse(-1);
    }
}