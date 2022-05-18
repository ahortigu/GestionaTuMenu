package com.aihg.gestionatumenu.ui.ingredientes.listener;

import com.aihg.gestionatumenu.db.entities.Ingrediente;

public interface IngredientesListener {
    public void onDeleteItem(Ingrediente aBorrar, int posicion);
}
