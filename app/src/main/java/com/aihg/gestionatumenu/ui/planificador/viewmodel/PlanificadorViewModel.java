package com.aihg.gestionatumenu.ui.planificador.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.repository.GestionaTuMenuRepository;

import java.util.List;

public class PlanificadorViewModel extends AndroidViewModel {

    private GestionaTuMenuRepository repository;

    private final LiveData<List<Dia>> dias;
    private final LiveData<List<MomentoComida>> momentos;
    private final LiveData<List<Planificador>> planificadorList;

    public PlanificadorViewModel(@NonNull Application application) {
        super(application);
        repository = new GestionaTuMenuRepository(application);

        dias = repository.getAllDias();
        momentos = repository.getAllMomentosComida();
        planificadorList = repository.getAllPlanificador();
    }

    public LiveData<List<Dia>> getDias() {
        return dias;
    }

    public LiveData<List<MomentoComida>> getMomentos() {
        return momentos;
    }

    public LiveData<List<Planificador>> getPlanificadorList() {
        return planificadorList;
    }

    public void insertRecetaPlanificador(Planificador receta) {
        repository.insert(receta);
    }

    public void updateRecetaPlanificador(Planificador receta) {
        repository.update(receta);
    }

    public void deleteRecetaPlanificador(Planificador receta) {
        repository.delete(receta);
    }

}
