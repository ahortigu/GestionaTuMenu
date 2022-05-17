package com.aihg.gestionatumenu.ui.despensa.wrapper;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;

import java.util.ArrayList;
import java.util.List;

public class CategoriaWrapper {
    private CategoriaIngrediente categoriaIngrediente;
    private List<Despensa> despensa;
    private Boolean expandido;

    public CategoriaWrapper() {
        this(new CategoriaIngrediente(), new ArrayList<>(), false);
    }

    public CategoriaWrapper(CategoriaIngrediente categoriaIngrediente, List<Despensa> despensa) {
        this(categoriaIngrediente, despensa, false);
    }

    public CategoriaWrapper(
        CategoriaIngrediente categoriaIngrediente, List<Despensa> despensa, boolean isExpandido
    ) {
        this.categoriaIngrediente = categoriaIngrediente;
        this.despensa = despensa;
        this.expandido = isExpandido;
    }

    public CategoriaIngrediente getCategoriaIngrediente() {
        return categoriaIngrediente;
    }

    public void setCategoriaIngrediente(CategoriaIngrediente categoriaIngrediente) {
        this.categoriaIngrediente = categoriaIngrediente;
    }

    public List<Despensa> getDespensa() {
        return despensa;
    }

    public void setDespensa(List<Despensa> despensa) {
        this.despensa = despensa;
    }

    public Boolean isExpandido() {
        return expandido;
    }

    public void setExpandido(Boolean expandido) {
        this.expandido = expandido;
    }

    public String getNombreCategoria() {
        return this.categoriaIngrediente.getNombre();
    }
}
