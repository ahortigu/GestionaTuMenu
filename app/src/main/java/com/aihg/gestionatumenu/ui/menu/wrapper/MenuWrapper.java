package com.aihg.gestionatumenu.ui.menu.wrapper;

import static com.aihg.gestionatumenu.db.util.generator.MenuRelatedDataGenerator.CENA;
import static com.aihg.gestionatumenu.db.util.generator.MenuRelatedDataGenerator.COMIDA;
import static com.aihg.gestionatumenu.db.util.generator.MenuRelatedDataGenerator.DESAYUNO;

import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.MenuInterface;
import com.aihg.gestionatumenu.db.entities.MomentoComida;

import java.util.List;

public class MenuWrapper {
    private Dia dia;
    private List<MenuInterface> menuDelDia;

    private Boolean expandido;

    public MenuWrapper(Dia dia, List<MenuInterface> menuDelDia) {
        this(dia, menuDelDia, false);
    }

    public MenuWrapper(Dia dia, List<MenuInterface> menuDelDia, boolean isExpandido) {
        this.dia = dia;
        this.menuDelDia = menuDelDia;
        this.expandido = isExpandido;
    }

    private MenuInterface menuDelMomento(MomentoComida momento) {
        return this.menuDelDia.stream()
            .filter(menu -> menu.getId_momento_comida().equals(momento))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("El dia deberia tener el momento."));
    }

    public MenuInterface getDesayuno() {
        return menuDelMomento(DESAYUNO);
    }

    public MenuInterface getComida() {
        return menuDelMomento(COMIDA);
    }

    public MenuInterface getCena() {
        return menuDelMomento(CENA);
    }

    public Dia getDia() {
        return dia;
    }

    public int getDiaId() {
        return dia.getId();
    }

    public List<MenuInterface> getMenuDelDia() {
        return menuDelDia;
    }

    public Boolean isExpandido() {
        return expandido;
    }

    public void setExpandido(Boolean expandido) {
        this.expandido = expandido;
    }
}


