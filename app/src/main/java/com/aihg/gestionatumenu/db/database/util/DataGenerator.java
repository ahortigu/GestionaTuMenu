package com.aihg.gestionatumenu.db.database.util;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

import java.util.Arrays;
import java.util.List;

public class DataGenerator {

    private static CategoriaIngrediente nuevoCategoriaIngrediente(String nombre) {
        CategoriaIngrediente nuevo = new CategoriaIngrediente();
        nuevo.setNombre(nombre);
        return nuevo;
    }

    private static Ingrediente nuevoIngrediente(String nombre, int categoriaId) {
        Ingrediente nuevo = new Ingrediente();
        nuevo.setNombre(nombre);
        nuevo.setIdCategoria(categoriaId);
        return nuevo;
    }

    public static List<CategoriaIngrediente> getDefaultCategoriasIngrediente() {
        return Arrays.asList(
                nuevoCategoriaIngrediente("CARNES"),
                nuevoCategoriaIngrediente("CEREALES Y FRUTOS SECOS"),
                nuevoCategoriaIngrediente("CONDIMENTOS"),
                nuevoCategoriaIngrediente("FRUTA"),
                nuevoCategoriaIngrediente("HUEVOS y  LÁCTEOS"),
                nuevoCategoriaIngrediente("OTROS"),
                nuevoCategoriaIngrediente("PESCADOS"),
                nuevoCategoriaIngrediente("VERDURAS")
        );
    }

    public static List<Ingrediente> getDefaultIngredientes() {
        return Arrays.asList(
                nuevoIngrediente("Conejo", 1),
                nuevoIngrediente("Pollo",1),
                nuevoIngrediente("Cordero",1),
                nuevoIngrediente("Avena",2),
                nuevoIngrediente("Arroz",2),
                nuevoIngrediente("Aceite",3)
        );
    }
}
