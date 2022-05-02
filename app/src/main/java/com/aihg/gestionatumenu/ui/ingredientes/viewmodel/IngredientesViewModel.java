package com.aihg.gestionatumenu.ui.ingredientes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class IngredientesViewModel extends AndroidViewModel {
    private final LiveData<List<CategoriaIngrediente>> categoriasIngrediente;
    private final LiveData<List<Ingrediente>> ingredientes;

    private GestionaTuMenuRepository repository;

    public IngredientesViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);

        categoriasIngrediente = repository.getAllCategoriasIngrediente();
        ingredientes = repository.getAllIngredientes();
    }

    public LiveData<List<CategoriaIngrediente>> getCategorias() {
        return categoriasIngrediente;
    }

    public LiveData<List<Ingrediente>> getIngredientes() {
        return ingredientes;
    }
}
