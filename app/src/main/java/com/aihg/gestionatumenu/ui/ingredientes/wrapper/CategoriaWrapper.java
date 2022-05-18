package com.aihg.gestionatumenu.ui.ingredientes.wrapper;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class CategoriaWrapper {
    private CategoriaIngrediente categoriaIngrediente;
    private List<Ingrediente> ingredientes;
    private Boolean expandido;

    public CategoriaWrapper() {
        this(new CategoriaIngrediente(), new ArrayList<>(), false);
    }

    public CategoriaWrapper(CategoriaIngrediente categoriaIngrediente, List<Ingrediente> ingredientes) {
        this(categoriaIngrediente, ingredientes, false);
    }

    public CategoriaWrapper(
        CategoriaIngrediente categoriaIngrediente, List<Ingrediente> ingredientes, boolean isExpandido
    ) {
        this.categoriaIngrediente = categoriaIngrediente;
        this.ingredientes = ingredientes;
        this.expandido = isExpandido;
    }

    public CategoriaIngrediente getCategoriaIngrediente() {
        return categoriaIngrediente;
    }

    public void setCategoriaIngrediente(CategoriaIngrediente categoriaIngrediente) {
        this.categoriaIngrediente = categoriaIngrediente;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
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
