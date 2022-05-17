package com.aihg.gestionatumenu.ui.despensa.listener;

import com.aihg.gestionatumenu.db.entities.Despensa;

public interface DespensaListener {
    void onDeleteItem(Despensa despensa, int posicion);
    void onUpdateItem(Despensa despensa);
}
