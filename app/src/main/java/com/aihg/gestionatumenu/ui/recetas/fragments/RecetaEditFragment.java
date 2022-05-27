package com.aihg.gestionatumenu.ui.recetas.fragments;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.RECETA_CREAR_HINT_INSTRUCCIONES;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.RECETA_SELECT_CATEGORIA_SPINNER;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.SELECCIONA_CATEGORIA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_BORRAR_DESPENSA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_BORRAR_INGREDIENTE_RECETA;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_MIN_INGREDIENTES_RECETA_EDITAR;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_RECETA_EDIT_BORRAR_TODAS_CATEGORIAS;
import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_RECETA_EDIT_CATEGORIA_DUPLICADA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.ui.recetas.adapters.CategoriasDeRecetaAdapter;
import com.aihg.gestionatumenu.ui.recetas.adapters.IngredientesDeRecetaAdapter;
import com.aihg.gestionatumenu.ui.recetas.listener.RecetaListener;
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class RecetaEditFragment extends Fragment {
    private View view;
    private RecetasViewModel viewModel;
    private RecetaListener listener;

    private Receta receta;
    private EditText et_rce_nombre_receta;

    private boolean isCategoriaExpandido;
    private ConstraintLayout l_rce_expandable_categorias_parent;
    private ConstraintLayout l_rce_expandable_categorias;
    private ImageView iv_rce_arrow_categorias;

    private CategoriasDeRecetaAdapter categoriasCat1Adapter;
    private CategoriasDeRecetaAdapter categoriasCat2Adapter;

    private Spinner spCat1;
    private Spinner spCat2;
    private CategoriaReceta previoSpCat1;
    private CategoriaReceta previoSpCat2;
    private List<CategoriaReceta> categorias;
    private List<Cataloga> seleccionadas;

    private boolean isIngredienteExpandido;
    private ConstraintLayout l_rce_expandable_ingredientes_parent;
    private ConstraintLayout l_rce_expandable_ingredientes;
    private ImageView iv_rce_arrow_ingredientes;
    private ImageView iv_rce_plus;
    private IngredientesDeRecetaAdapter ingredienteAdapter;
    private RecyclerView rvIngrediente;
    private List<Utiliza> ingredientesReceta;

    private boolean isInstruccionesExpandido;
    private ConstraintLayout l_rce_expandable_instrucciones_parent;
    private ConstraintLayout l_rce_expandable_instrucciones;
    private EditText et_rce_instrucciones;
    private ImageView iv_rce_instrucciones;

    public RecetaEditFragment() {
        this.isIngredienteExpandido = true;
        this.isInstruccionesExpandido = true;
        this.isCategoriaExpandido = true;

        this.ingredientesReceta = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.seleccionadas = new ArrayList<>();

        this.previoSpCat1 = SELECCIONA_CATEGORIA;
        this.previoSpCat2 = SELECCIONA_CATEGORIA;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receta = RecetaEditFragmentArgs.fromBundle(getArguments()).getAModificar();
        viewModel = new ViewModelProvider(this).get(RecetasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__edit_create_fragment, container, false);

        Ingrediente aAnadir = RecetaEditFragmentArgs.fromBundle(getArguments()).getAAnadir();
        if (aAnadir != null) viewModel.insertIngredienteReceta(new Utiliza(receta, aAnadir));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadObservers();
        loadListener();

        loadCategoriasReceta();
        loadIngredientesReceta();
        loadNombreReceta();
        loadInstrucciones();

        this.iv_rce_plus = this.view.findViewById(R.id.iv_rce_plus);
        this.iv_rce_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecetaEditFragmentDirections.ActionRecetaEditFragmentToBuscarIngredienteFragment
                    actionToAnadir = RecetaEditFragmentDirections.actionRecetaEditFragmentToBuscarIngredienteFragment();
                actionToAnadir.setEditReceta(receta);
                Navigation.findNavController(view).navigate(actionToAnadir);
            }
        });
    }

    private void loadObservers() {
        viewModel
            .getUtilizaByReceta(receta)
            .observe(getViewLifecycleOwner(), new Observer<List<Utiliza>>() {
                @Override
                public void onChanged(List<Utiliza> ingredientesRecetaOb) {
                    ingredientesReceta = ingredientesRecetaOb;
                    ingredienteAdapter.setIngredientes(ingredientesRecetaOb);
                }
            });
        viewModel
            .getCategorias()
            .observe(getViewLifecycleOwner(), new Observer<List<CategoriaReceta>>() {
                @Override
                public void onChanged(List<CategoriaReceta> categoriaRecetasOb) {
                    categorias = categoriaRecetasOb;
                    categorias.add(0, SELECCIONA_CATEGORIA);
                    categoriasCat1Adapter.setElementos(categorias);
                    categoriasCat2Adapter.setElementos(categorias);
                }
            });
        viewModel
            .getCatalogaByReceta(receta)
            .observe(getViewLifecycleOwner(), new Observer<List<Cataloga>>() {
                @Override
                public void onChanged(List<Cataloga> catalogasOb) {
                    seleccionadas = catalogasOb;
                    if (categoriasCat1Adapter != null && categoriasCat2Adapter != null) {
                        loadValoresSpinner();
                        categoriasCat1Adapter.notifyDataSetChanged();
                        categoriasCat2Adapter.notifyDataSetChanged();
                    }
                }
            });
    }

    private void crearSpinner1(){
        if (categoriasCat1Adapter == null) categoriasCat1Adapter = new CategoriasDeRecetaAdapter(
            view.getContext(),
            android.R.layout.simple_spinner_item,
            categorias
        );
        else categoriasCat1Adapter.setElementos(categorias);
        categoriasCat1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCat1 = (Spinner) view.findViewById(R.id.sp_rec_cat1);
        spCat1.setAdapter(categoriasCat1Adapter);
        spCat1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                CategoriaReceta seleccionada = (CategoriaReceta) spCat1.getSelectedItem();
                if (!previoSpCat1.equals(seleccionada)) changeOnCategoria(seleccionada, 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }


    private void crearSpinner2(){
        if (categoriasCat2Adapter == null) categoriasCat2Adapter = new CategoriasDeRecetaAdapter(
            view.getContext(),
            android.R.layout.simple_spinner_item,
            categorias
        );
        else categoriasCat2Adapter.setElementos(categorias);
        categoriasCat2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCat2 = (Spinner) view.findViewById(R.id.sp_rec_cat2);
        spCat2.setAdapter(categoriasCat2Adapter);
        spCat2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                CategoriaReceta seleccionada = (CategoriaReceta) spCat2.getSelectedItem();
                if (!previoSpCat2.equals(seleccionada)) changeOnCategoria(seleccionada, 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void validarSpinnerDondeTrabajar(int spinner) {
        if (spinner < 1 && spinner > 2) throw new IllegalStateException("Spinner no contemplado");
    }

    private CategoriaReceta getPrevioSpinner(int spinner) {
        validarSpinnerDondeTrabajar(spinner);

        if (spinner == 1) return previoSpCat1;
        else return previoSpCat2;
    }

    private void setPrevioSpinner(CategoriaReceta nueva, int spinner) {
        validarSpinnerDondeTrabajar(spinner);

        if (spinner == 1) previoSpCat1 = nueva;
        else previoSpCat2 = nueva;
    }

    private void setDefaultSeleccion(int spinner) {
        validarSpinnerDondeTrabajar(spinner);
        if (spinner == 1) {
            categoriasCat1Adapter.setNotifyOnChange(false);
            int position1 = categoriasCat1Adapter.getPositionItem(SELECCIONA_CATEGORIA);
            previoSpCat1 = SELECCIONA_CATEGORIA;
            spCat1.setSelection(position1);
            categoriasCat1Adapter.setNotifyOnChange(true);
        }
        if (spinner == 2) {
            categoriasCat2Adapter.setNotifyOnChange(false);
            int position2 = categoriasCat2Adapter.getPositionItem(SELECCIONA_CATEGORIA);
            previoSpCat2 = SELECCIONA_CATEGORIA;
            spCat2.setSelection(position2);
            categoriasCat2Adapter.setNotifyOnChange(true);
        }
    }

    private void setPreviousSeleccion(int spinner) {
        validarSpinnerDondeTrabajar(spinner);
        if (spinner == 1) {
            categoriasCat1Adapter.setNotifyOnChange(false);
            int position1 = categoriasCat1Adapter.getPositionItem(previoSpCat1);
            spCat1.setSelection(position1);
            categoriasCat1Adapter.setNotifyOnChange(true);
        }
        if (spinner == 2) {
            categoriasCat2Adapter.setNotifyOnChange(false);
            int position2 = categoriasCat2Adapter.getPositionItem(previoSpCat2);
            spCat2.setSelection(position2);
            categoriasCat2Adapter.setNotifyOnChange(true);
        }
    }

    private void borrarCategoriaReceta(CategoriaReceta seleccionada) {
        Cataloga aBorrar = seleccionadas
            .stream()
            .filter(cataloga -> cataloga.getId_categoria_receta().equals(seleccionada))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("La categoria deberia existir " + seleccionada));
        viewModel.deleteCategoriaReceta(aBorrar);
    }

    private void changeOnCategoria(CategoriaReceta seleccionada, int spinner) {
            public void toDeteleReceta(Receta borrar) {}
        CategoriaReceta previa = getPrevioSpinner(spinner);

        if (!previa.equals(seleccionada)) {
            if (seleccionadas.contains(seleccionada)) {
                setDefaultSeleccion(spinner);
                Toast.makeText(
                    view.getContext(), TOAST_RECETA_EDIT_CATEGORIA_DUPLICADA, Toast.LENGTH_SHORT
                ).show();
            } else if (SELECCIONA_CATEGORIA.equals(seleccionada)) {
                if (seleccionadas.size() > 1 ) {
                    borrarCategoriaReceta(previa);
                    setPrevioSpinner(seleccionada, spinner);
                } else {
                    setPreviousSeleccion(spinner);
                    Toast.makeText(
                        view.getContext(), TOAST_RECETA_EDIT_BORRAR_TODAS_CATEGORIAS, Toast.LENGTH_SHORT
                    ).show();
                }
            } else {
                if (!SELECCIONA_CATEGORIA.equals(previa)) borrarCategoriaReceta(previa);
                viewModel.insertCategoriaReceta(new Cataloga(receta, seleccionada));
                setPrevioSpinner(seleccionada, spinner);
            }
        }
    }

    private void loadValoresSpinner() {
        this.rvCategoria = (RecyclerView) view.findViewById(R.id.rv_rce_categorias);
        if (!seleccionadas.isEmpty()) {
            categoriasCat1Adapter.setNotifyOnChange(false);
            categoriasCat2Adapter.setNotifyOnChange(false);

            CategoriaReceta first = seleccionadas.get(0).getId_categoria_receta();

            CategoriaReceta second = null;
            if (seleccionadas.size() > 1) second = seleccionadas.get(1).getId_categoria_receta();
            else second = SELECCIONA_CATEGORIA;

            int position1 = categoriasCat1Adapter.getPositionItem(first);
            previoSpCat1 = first;
            spCat1.setSelection(position1);

            int position2 = categoriasCat2Adapter.getPositionItem(second);
            previoSpCat2 = second;
            spCat2.setSelection(position2);

            categoriasCat1Adapter.setNotifyOnChange(true);
            categoriasCat2Adapter.setNotifyOnChange(true);
        }
    }

    private void loadCategoriasReceta() {
        this.l_rce_expandable_categorias_parent = view.findViewById(R.id.l_rce_categorias);
        this.l_rce_expandable_categorias = view.findViewById(R.id.l_rce_expandable_categorias);
        this.iv_rce_arrow_categorias = view.findViewById(R.id.iv_rce_arrow_categorias);
        this.iv_rce_arrow_categorias.setImageResource(R.drawable.ic_arrow_up);

        this.l_rce_expandable_categorias_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCategoriaExpandido = !isCategoriaExpandido;
                l_rce_expandable_categorias.setVisibility(
                    isCategoriaExpandido ? View.VISIBLE : View.GONE
                );
                iv_rce_arrow_categorias.setImageResource(
                    isCategoriaExpandido ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
                );
            }
        });

        crearSpinner1();
        crearSpinner2();
    }

    private void loadListener() {
        this.listener = new RecetaListener() {
            @Override
            public void toDeleteUtiliza(Utiliza ingredienteBorrar, int positionABorrar) {
                viewModel.deleteIngredienteReceta(ingredienteBorrar);
                Toast.makeText(
                    view.getContext(), TOAST_BORRAR_INGREDIENTE_RECETA, Toast.LENGTH_SHORT
                ).show();
                ingredienteAdapter.notifyItemRemoved(positionABorrar);
                ingredienteAdapter.notifyDataSetChanged();
            }

            @Override
            public void toUpdateUtiliza(Utiliza ingredienteActualizar) {
                viewModel.updateIngredienteReceta(ingredienteActualizar);
            }

            @Override
            public void toDeleteCatalogo(CategoriaReceta categoriaBorrar) {
                viewModel.deleteCategoriaReceta(new Cataloga(receta, categoriaBorrar));
            }

            @Override
            public void toAddCatalogo(CategoriaReceta categoriaAnadir) {
                viewModel.insertCategoriaReceta(new Cataloga(receta, categoriaAnadir));
            }

            @Override
            public void toDeteleReceta(Receta borrar) {}
        };
    }

    private void loadIngredientesReceta() {
        this.rvIngrediente = (RecyclerView) view.findViewById(R.id.rv_rce_ingredientes);
        this.rvIngrediente.setHasFixedSize(false);
        this.rvIngrediente.setLayoutManager(new LinearLayoutManager(getContext()));

        if (ingredienteAdapter == null) ingredienteAdapter = new IngredientesDeRecetaAdapter(true, listener);
        this.rvIngrediente.setAdapter(ingredienteAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (ingredientesReceta.size() < 2) {
                    Toast.makeText(
                        viewHolder.itemView.getContext(), TOAST_MIN_INGREDIENTES_RECETA_EDITAR, Toast.LENGTH_SHORT
                    ).show();
                    return 0;
                } else {
                    return super.getSwipeDirs(recyclerView, viewHolder);
                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TextView txtNombre = viewHolder.itemView.findViewById(R.id.txt_shared_c_item_nombre);
                int positionABorrar = IntStream.range(0, ingredientesReceta.size())
                    .filter(i -> txtNombre.getText().toString().equals(ingredientesReceta.get(i).getId_ingrediente().getNombre()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("El ingrediente " + txtNombre + " deberia existir."));
                Utiliza aBorrar = ingredientesReceta.get(positionABorrar);
                listener.toDeleteUtiliza(aBorrar, positionABorrar);
            }
        }).attachToRecyclerView(rvIngrediente);

        this.l_rce_expandable_ingredientes_parent = view.findViewById(R.id.l_rce_ingredientes);
        this.l_rce_expandable_ingredientes = view.findViewById(R.id.l_rce_expandable_ingredientes);
        this.iv_rce_arrow_ingredientes = view.findViewById(R.id.iv_rce_arrow_ingredientes);
        this.iv_rce_arrow_ingredientes.setImageResource(R.drawable.ic_arrow_up);

        this.l_rce_expandable_ingredientes_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isIngredienteExpandido = !isIngredienteExpandido;
                l_rce_expandable_ingredientes.setVisibility(
                    isIngredienteExpandido ? View.VISIBLE : View.GONE
                );
                iv_rce_arrow_ingredientes.setImageResource(
                    isIngredienteExpandido ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down
                );
            }
        });
    }

    private void loadNombreReceta() {
        this.et_rce_nombre_receta = view.findViewById(R.id.et_rce_nombre_receta);
        this.et_rce_nombre_receta.setText(receta.getNombre());
        this.et_rce_nombre_receta.setHint(receta.getNombre());
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
}