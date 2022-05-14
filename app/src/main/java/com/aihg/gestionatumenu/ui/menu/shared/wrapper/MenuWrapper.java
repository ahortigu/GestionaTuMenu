package com.aihg.gestionatumenu.ui.menu.shared.wrapper;

import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.MenuInterface;

import java.util.List;

public class MenuWrapper {
    private Dia dia;
    private List<MenuInterface> menu;

    private Boolean expandido;

    public MenuWrapper(Dia dia, List<MenuInterface> menu, Boolean expandido) {
        this.dia = dia;
        this.menu = menu;
        this.expandido = expandido;
    }

    public Dia getDia() {
        return dia;
    }

    public List<MenuInterface> getMenu() {
        return menu;
    }

    public Boolean getExpandido() {
        return expandido;
    }

    public void setExpandido(Boolean expandido) {
        this.expandido = expandido;
    }
}


