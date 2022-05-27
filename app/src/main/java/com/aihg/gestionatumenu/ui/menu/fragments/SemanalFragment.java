package com.aihg.gestionatumenu.ui.menu.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.entities.Semanal;

import java.util.List;
import java.util.stream.Collectors;

public class SemanalFragment extends AbstractMenuFragment {

    public SemanalFragment() {
        super(true, false);
    }

    @Override
    protected void setObservers() {
        getViewModel()
            .getSemanal()
            .observe(getViewLifecycleOwner(), new Observer<List<Semanal>>() {
                @Override
                public void onChanged(List<Semanal> semanal) {
                    getAdapter().setMenu(
                        semanal.stream()
                            .map(item -> (MenuInterface) item)
                            .collect(Collectors.toList())
                    );
                }
            });
    }
}