package com.aihg.gestionatumenu.ui.ingredientes.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class IngredientesViewModel extends AndroidViewModel {
    private final LiveData<List<CategoriaIngrediente>> categoriasIngrediente;
    private final LiveData<List<Ingrediente>> ingredientes;
    private final LiveData<List<Medicion>> mediciones;

    private GestionaTuMenuRepository repository;

    public IngredientesViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);

        categoriasIngrediente = repository.getAllCategoriasIngrediente();
        ingredientes = repository.getAllIngredientes();
        mediciones = repository.getAllMediciones();
    }

    public LiveData<List<CategoriaIngrediente>> getCategorias() {
        return categoriasIngrediente;
    }

    public LiveData<List<Medicion>> getMediciones() {
        return mediciones;
    }

    public LiveData<List<Ingrediente>> getIngredientes() {
        return ingredientes;
    }

    public void insertIngrediente(@NonNull Ingrediente toInsert) {
        Log.i("VM-INGREDIENTES", "Inserting " + toInsert.toString());
        repository.insert(toInsert);
    }

    public void updateIngrediente(@NonNull Ingrediente toUpdate) {
        Log.i("VM-INGREDIENTES", "Updating " + toUpdate.toString());
        repository.update(toUpdate);
    }

    public void deleteIngrediente(@NonNull Ingrediente toDelete) {
        Log.i("VM-INGREDIENTES", "Deleting " + toDelete.toString());
        repository.delete(toDelete);
    }
}
