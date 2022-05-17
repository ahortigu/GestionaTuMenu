package com.aihg.gestionatumenu.ui.despensa.adapters;

import android.view.View;

import com.aihg.gestionatumenu.db.entities.Despensa;

public interface DespensaListener {
    void onDeleteItem(Despensa despensa, int posicion);
    void onUpdateItem(Despensa despensa, int posicion);
}
