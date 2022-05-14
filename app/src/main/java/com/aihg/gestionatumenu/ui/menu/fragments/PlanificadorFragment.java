package com.aihg.gestionatumenu.ui.menu.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.db.entities.Planificador;

import java.util.List;
import java.util.stream.Collectors;

public class PlanificadorFragment extends AbstractMenuFragment {

    public PlanificadorFragment() {
        super(false, true);
    }

    @Override
    protected void setObservers() {
        getViewModel()
            .getPlanificador()
            .observe(this, new Observer<List<Planificador>>() {
                @Override
                public void onChanged(List<Planificador> planificadors) {
                    getAdapter().setMenu(
                        planificadors.stream()
                            .map(item -> (MenuInterface) item)
                            .collect(Collectors.toList())
                    );
                }
            });
    }
}