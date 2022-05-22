package com.aihg.gestionatumenu.ui.recetas.wrapper;

import static com.aihg.gestionatumenu.ui.util.GestionaTuMenuConstants.NO_INGREDIENTE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class RecetaTemporalWrapper implements Serializable {

    private Receta receta;
    private Set<CategoriaReceta> categorias;
    private List<Utiliza> utiliza;

    public RecetaTemporalWrapper() {
        this.receta = new Receta("", "");
        this.categorias = new HashSet<>();
        this.utiliza = new ArrayList<>();
    }

    public Receta getReceta() {
        return receta;
    }

    public List<CategoriaReceta> getCategorias() {
        return categorias.stream().collect(toList());
    }

    public List<Ingrediente> getIngredientes() {
        return this.utiliza.stream().map(Utiliza::getId_ingrediente).collect(toList());
    }

    public List<Utiliza> getUtiliza() {
        return this.utiliza;
    }

    public String getNombre() {
        return this.receta.getNombre();
    }

    public void setNombre(String nuevoNombre) {
        this.receta.setNombre(nuevoNombre);
    }

    public void setInstrucciones(String nuevasInstrucciones) {
        this.receta.setInstrucciones(nuevasInstrucciones);
    }

    public String getInstrucciones() {
        return this.receta.getInstrucciones();
    }

    public void anadirIngrediente(Ingrediente anadir) {
        if (!anadir.getNombre().equals(NO_INGREDIENTE)) {
            this.utiliza.add(new Utiliza(this.getReceta(), anadir));
        }
    }

    public void deleteIngrediente(Ingrediente borrar) {
        int positionBorrar = IntStream.range(0, this.utiliza.size())
            .filter(i -> borrar.getNombre().equals(this.utiliza.get(i).getId_ingrediente().getNombre()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("El ingrediente " + borrar.getNombre() + " deberia existir."));
        this.utiliza.remove(positionBorrar);
    }

    public void actualizarUtiliza(Utiliza toUpdate) {
        this.deleteIngrediente(toUpdate.getId_ingrediente());
        this.utiliza.add(toUpdate);
    }

    public void anadirCategoria(CategoriaReceta anadir) {
        this.categorias.add(anadir);
    }

    public void deleteCategoria(CategoriaReceta borrar) {
        this.categorias.remove(borrar);
    }
}
