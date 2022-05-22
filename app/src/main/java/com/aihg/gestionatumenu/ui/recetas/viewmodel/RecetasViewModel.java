package com.aihg.gestionatumenu.ui.recetas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class RecetasViewModel extends AndroidViewModel {
    private GestionaTuMenuRepository repository;

    private final LiveData<List<CategoriaReceta>> categorias;
    private final LiveData<List<Cataloga>> catalogo;
    private final LiveData<List<Receta>> recetas;

    public RecetasViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);

        catalogo = repository.getALLCataloga();
        categorias = repository.getALLCategoriasReceta();
        recetas = repository.getALLRecetas();
    }

    public LiveData<List<Cataloga>> getCatalogo() {
        return catalogo;
    }

    public LiveData<List<CategoriaReceta>> getCategorias() {
        return categorias;
    }

    public LiveData<List<Receta>> getRecetas() {
        return recetas;
    }

    public LiveData<Receta> getRecetaByNombre(String nombre) {
        return repository.getRecetaByNombre(nombre);
    }

    public LiveData<List<Utiliza>> getUtilizaByReceta(Receta receta) {
        return repository.getUtilizaByReceta(receta);
    }

    public LiveData<List<Cataloga>> getCatalogaByReceta(Receta receta) {
        return repository.getCatalogaByReceta(receta);
    }

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

    public void insertReceta(Receta receta) {
        repository.insert(receta);
    }

    public void updateReceta(Receta receta) {
        repository.update(receta);
    }

    public void deleteReceta(Receta receta) {
        repository.delete(receta);
    }

    public LiveData<List<Receta>> getRecetasUtilizadasMenuPlanificador() {
        return repository.getRecetasUtilizadasMenuPlanificador();
    }

    public void deleteCategoriasReceta(Receta borrar) {
        repository.deleteCategoriasReceta(borrar);
    }

    public void deleteIngredientesReceta(Receta borrar) {
        repository.deleteIngredientesReceta(borrar);
    }
}
