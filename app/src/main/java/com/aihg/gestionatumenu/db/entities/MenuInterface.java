package com.aihg.gestionatumenu.db.entities;

import androidx.annotation.NonNull;

public interface MenuInterface {
    public MomentoComida getId_momento_comida();
    public Dia getId_dia();

    public Receta getId_receta();
    public void setId_receta(@NonNull Receta id_receta);

    public default Boolean hasReceta() {
        return this.getId_receta() != null;
    }
}
