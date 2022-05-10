package com.aihg.gestionatumenu.ui.recetas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
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
}
