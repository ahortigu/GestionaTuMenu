package com.aihg.gestionatumenu.ui.recetas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class RecetasViewModel extends AndroidViewModel {
    private GestionaTuMenuRepository repository;

    private final LiveData<List<CategoriaReceta>> categorias;
    private final LiveData<List<Cataloga>> catalogo;

    public RecetasViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);

        catalogo = repository.getALLCataloga();
        categorias = repository.getALLCategoriasReceta();
    }

    public LiveData<List<Cataloga>> getCatalogo() {
        return catalogo;
    }

    public LiveData<List<CategoriaReceta>> getCategorias() {
        return categorias;
    }

    //public LiveData<List<Utiliza>> getIngredientes(Receta receta) {
    //    return repository.getUtilizaByReceta(receta);
    //}

    public void insertIngredienteReceta(Utiliza ingrediente) {
        repository.insert(ingrediente);
    }

    public void updateIngredienteReceta(Utiliza ingrediente) {
        repository.update(ingrediente);
    }

    public void deleteIngredienteReceta(Utiliza categoria) {
        repository.delete(categoria);
    }

    public void insertCategoriaReceta(Cataloga categoria) {
        repository.insert(categoria);
    }

    public void deleteCategoriaReceta(Cataloga categoria) {
        repository.delete(categoria);
    }

    public void updateReceta(Receta receta) {
        repository.update(receta);
    }

    public void insertReceta(Receta receta) {
        repository.insert(receta);
    }
}
