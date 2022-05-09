package com.aihg.gestionatumenu.ui.listacompra.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class ListaCompraViewModel extends AndroidViewModel {
    private final LiveData<List<ListaCompra>> ingredientes;
    private GestionaTuMenuRepository repository;

    public ListaCompraViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);
        ingredientes = repository.getALLListaCompra();
    }
    public LiveData<List<ListaCompra>> getAllDespensa() {
        return ingredientes;
    }

    public void insertIngrediente(@NonNull ListaCompra toInsert) {
        Log.i("VM-LISTA COMPRA", "Inserting " + toInsert.toString());
        repository.insert(toInsert);
    }

    public void updateIngrediente(@NonNull ListaCompra toUpdate) {
        Log.i("VM-LISTA COMPRA", "Updating " + toUpdate.toString());
        repository.update(toUpdate);
    }

    public void deleteIngrediente(@NonNull ListaCompra toDelete) {
        Log.i("VM-LISTA COMPRA", "Deleting " + toDelete.toString());
        repository.delete(toDelete);
    }

}
