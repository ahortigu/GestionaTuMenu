package com.aihg.gestionatumenu.ui.despensa.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class DespensaViewModel extends AndroidViewModel {

    private final LiveData<List<CategoriaIngrediente>> categoriasIngrediente;
    private final LiveData<List<Despensa>> despensa;
    private GestionaTuMenuRepository repository;

    public DespensaViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);
        this.categoriasIngrediente = repository.getAllCategoriasIngrediente();
        this.despensa = repository.getALLDespensa();
    }

    public LiveData<List<CategoriaIngrediente>> getCategorias() {
        return categoriasIngrediente;
    }

    public LiveData<List<Despensa>> getDespensa() {
        return despensa;
    }

    public void insertDespensa(@NonNull Despensa toInsert) {
        Log.i("VM-DESPENSA", "Inserting " + toInsert.toString());
        repository.insert(toInsert);
    }

    public void updateDespensa(@NonNull Despensa toUpdate) {
        Log.i("VM-DFESPENSA", "Updating " + toUpdate.toString());
        repository.update(toUpdate);
    }

    public void deleteDespensa(@NonNull Despensa toDelete) {
        Log.i("VM-DESPENSA", "Deleting " + toDelete.toString());
        repository.delete(toDelete);
    }

    public LiveData<List<Ingrediente>> getDespensasToUpdate() {
        return repository.getDespensasToUpdate();
    }
}
