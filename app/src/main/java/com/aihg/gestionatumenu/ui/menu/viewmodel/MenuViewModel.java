package com.aihg.gestionatumenu.ui.menu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.Semanal;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {

    private GestionaTuMenuRepository repository;

    private final LiveData<List<Planificador>> planificador;
    private final LiveData<List<Semanal>> semanal;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);

        planificador = repository.getAllPlanificador();
        semanal = repository.getAllSemanal();
    }

    public LiveData<List<Planificador>> getPlanificador() {
        return planificador;
    }

    public void updateRecetaPlanificador(Planificador receta) {
        repository.update(receta);
    }

    public void updateRecetaSemanal(Semanal semanal) {
        repository.update(semanal);
    }
}
