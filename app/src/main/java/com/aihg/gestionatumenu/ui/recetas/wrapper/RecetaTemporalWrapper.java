package com.aihg.gestionatumenu.ui.recetas.wrapper;

import static java.util.stream.Collectors.toList;

import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Receta;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecetaTemporalWrapper implements Serializable {

    private Receta receta;
    private Set<CategoriaReceta> categorias;
    private Set<Ingrediente> ingredientes;

    public RecetaTemporalWrapper() {
        this.receta = new Receta("", "");
        this.categorias = new HashSet<>();
        this.ingredientes = new HashSet<>();
    }

    public Receta getReceta() {
        return receta;
    }

    public List<CategoriaReceta> getCategorias() {
        return categorias.stream().collect(toList());
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes.stream().collect(toList());
    }

    public void setNombre(String nuevoNombre) {
        this.receta.setNombre(nuevoNombre);
    }

    public void setInstrucciones(String nuevasInstrucciones) {
        this.receta.setInstrucciones(nuevasInstrucciones);
    }

    public void anadirIngrediente(Ingrediente anadir) {
        this.ingredientes.add(anadir);
    }

    public void deleteIngrediente(Ingrediente borrar) {
        this.ingredientes.remove(borrar);
    }

    public void anadirCategoria(CategoriaReceta anadir) {
        this.categorias.add(anadir);
    }

    public void deleteCategoria(CategoriaReceta borrar) {
        this.categorias.remove(borrar);
    }
}
