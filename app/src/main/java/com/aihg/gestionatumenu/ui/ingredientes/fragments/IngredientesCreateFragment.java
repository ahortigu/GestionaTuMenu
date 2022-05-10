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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.SpinnerCategoriasAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.adaptors.SpinnerMedicionAdapter;
import com.aihg.gestionatumenu.ui.ingredientes.viewmodel.IngredientesViewModel;

import java.util.List;
import java.util.stream.IntStream;

public class IngredientesCreateFragment extends Fragment {
    private View view;
    private IngredientesViewModel viewModel;

    private Ingrediente toCreate;

    private Spinner categoriasSpinner;
    private Spinner medicionesSpinner;
    private TextView txt_nombre;
    private Button bGuardar;


    public IngredientesCreateFragment() {
        toCreate = new Ingrediente("", null, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i("LOADING", "Creating Observables on Ingrediente Edit Screen");
        viewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);

        viewModel.getIngredientes().observe(this, new Observer<List<Ingrediente>>() {
            @Override
            public void onChanged(List<Ingrediente> ingredientesOv) {
                ingredientesOv.add(toCreate);
            }
        });

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

                        categoriasSpinner = (Spinner) view.findViewById(R.id.sp_ic_categoria);
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
                                toCreate.setCategoriaIngrediente(seleccionado);
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

                        medicionesSpinner = (Spinner) view.findViewById(R.id.sp_ic_medicion);
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
                                toCreate.setMedicion(seleccionado);
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
        this.view = inflater.inflate(R.layout.ingredientes_create_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_nombre = view.findViewById(R.id.txt_ic_ingrediente);
        txt_nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                // TODO anadir validaciones aqui
                toCreate.setNombre(s.toString());
            }
        });

        bGuardar = view.findViewById(R.id.b_ic_guardar);
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Ingrediente seteado", toCreate.toString());
                if(!toCreate.getNombre().isEmpty() && toCreate.getCategoriaIngrediente() != null && toCreate.getMedicion() != null)
                {
                    // TODO anadir validaciones aqui
                    viewModel.insertIngrediente(toCreate);
                    Toast.makeText(view.getContext(), "Ingrediente creado con éxito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(view.getContext(), "Por favor, no deje ningún campo vacío", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
    }

    private int getPosicionCategoria(List<CategoriaIngrediente> dondeBuscar) {
        return IntStream.range(0, dondeBuscar.size())
                .filter(i -> dondeBuscar.get(i).equals(toCreate.getCategoriaIngrediente()))
                .findFirst()
                .orElse(-1);
    }

    private int getPosicionMedicion(List<Medicion> dondeBuscar) {
        return IntStream.range(0, dondeBuscar.size())
                .filter(i -> dondeBuscar.get(i).equals(toCreate.getMedicion()))
                .findFirst()
                .orElse(-1);
    }

}