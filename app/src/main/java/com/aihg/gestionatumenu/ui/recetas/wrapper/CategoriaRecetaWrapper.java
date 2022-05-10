package com.aihg.gestionatumenu.ui.recetas.wrapper;

import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;

import java.util.List;

public class CategoriaRecetaWrapper {
    private CategoriaReceta categoria;
    private List<Cataloga> recetas;

    private Boolean expandido;

    public CategoriaRecetaWrapper(CategoriaReceta categoria, List<Cataloga> recetas) {
        this.categoria = categoria;
        this.recetas = recetas;
        this.expandido = false;
    }

    public CategoriaReceta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaReceta categoria) {
        this.categoria = categoria;
    }

    public List<Cataloga> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Cataloga> recetas) {
        this.recetas = recetas;
    }

    public Boolean getExpandido() {
        return expandido;
    }

    public Boolean isExpandido() {
        return expandido;
    }

    public void setExpandido(Boolean expandido) {
        this.expandido = expandido;
    }

    public String getNombreCategoria() {
        return this.categoria.getNombre();
    }

    @Override
    public String toString() {
        return "CategoriaRecetaWrapper{" +
                "categoria=" + categoria +
                ", recetas=" + recetas +
                ", expandido=" + expandido +
                '}';
    }
}
