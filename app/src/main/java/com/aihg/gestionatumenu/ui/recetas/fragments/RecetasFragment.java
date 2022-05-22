package com.aihg.gestionatumenu.ui.recetas.fragments;

import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.TOAST_BORRAR_RECETA;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.ui.recetas.adapters.ItemsCategoriaRecetaAdapter;
import com.aihg.gestionatumenu.ui.recetas.listener.RecetaListener;
import com.aihg.gestionatumenu.ui.recetas.viewmodel.RecetasViewModel;

import java.util.List;

public class RecetasFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsCategoriaRecetaAdapter adapter;
    private View view;
    private RecetasViewModel viewModel;

    private RecetaListener listener;

    public RecetasFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this).get(RecetasViewModel .class);

        viewModel
            .getCategorias()
            .observe(this, new Observer<List<CategoriaReceta>>() {
                @Override
                public void onChanged(List<CategoriaReceta> categoriasOb) {
                    adapter.setCategorias(categoriasOb);
                }
            });

        viewModel
            .getCatalogo()
            .observe(this, new Observer<List<Cataloga>>() {
                @Override
                public void onChanged(List<Cataloga> catalogoOb) {
                    adapter.setCatalogo(catalogoOb);
                }
        });

        viewModel
            .getRecetasUtilizadasMenuPlanificador()
            .observe(this, new Observer<List<Receta>>() {
                @Override
                public void onChanged(List<Receta> recetas) {
                    adapter.setNoBorrar(recetas);
                }
            });

        this.listener = new RecetaListener() {
            @Override
            public void toDeleteUtiliza(Utiliza ingredienteBorrar, int positionABorrar) {}

            @Override
            public void toUpdateUtiliza(Utiliza ingredienteActualizar) {}

            @Override
            public void toDeleteCatalogo(CategoriaReceta categoriaBorrar) {}

            @Override
            public void toAddCatalogo(CategoriaReceta categoriaAnadir) {}

            @Override
            public void toDeteleReceta(Receta borrar) {
                borrarCatalogo(borrar);
            }

            private void borrarCatalogo(Receta borrar) {
                viewModel.deleteCategoriasReceta(borrar);
                viewModel
                    .getCatalogaByReceta(borrar)
                    .observe(getViewLifecycleOwner(), new Observer<List<Cataloga>>() {
                        @Override
                        public void onChanged(List<Cataloga> catalogas) {
                            borrarIngredientes(borrar);
                        }
                    });
            }

            private void borrarIngredientes(Receta borrar) {
                viewModel.deleteIngredientesReceta(borrar);
                viewModel
                    .getUtilizaByReceta(borrar)
                    .observe(getViewLifecycleOwner(), new Observer<List<Utiliza>>() {
                        @Override
                        public void onChanged(List<Utiliza> catalogas) {
                            borrarReceta(borrar);
                        }
                    });
            }

            private void borrarReceta(Receta borrar) {
                viewModel.deleteReceta(borrar);
                viewModel
                    .getRecetaByNombre(borrar.getNombre())
                    .observe(getViewLifecycleOwner(), new Observer<Receta>() {
                        @Override
                        public void onChanged(Receta receta) {
                            Toast.makeText(
                               view.getContext(), TOAST_BORRAR_RECETA, Toast.LENGTH_SHORT
                            ).show();
                            adapter.notifyDataSetChanged();
                        }
                    });
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_r_recetas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter == null) adapter = new ItemsCategoriaRecetaAdapter(listener);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_add).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            case R.id.nav_add:
                NavDirections action = RecetasFragmentDirections.actionRecetasFragmentToRecetasCreateFragment();
                Navigation.findNavController(view).navigate(action);
                break;
            case R.id.nav_buscar:
                NavDirections action2 = RecetasFragmentDirections.actionRecetasFragmentToBuscarRecetaFragment();
                Navigation.findNavController(view).navigate(action2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}