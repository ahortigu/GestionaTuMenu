package com.aihg.gestionatumenu.db.database.util;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;

import java.util.Arrays;
import java.util.List;

public class DataGenerator {

    // CATEGORIAS
    private static final CategoriaIngrediente CARNES = new CategoriaIngrediente(1, "CARNES");
    private static final CategoriaIngrediente CEREALES_Y_FRUTOS_SECOS = new CategoriaIngrediente(2, "CEREALES Y FRUTOS SECOS");
    private static final CategoriaIngrediente CONDIMENTOS = new CategoriaIngrediente(3, "CONDIMENTOS");
    private static final CategoriaIngrediente FRUTA = new CategoriaIngrediente(4, "FRUTA");
    private static final CategoriaIngrediente HUEVOS_Y_LACTEOS = new CategoriaIngrediente(5, "HUEVOS y  LÁCTEOS");
    private static final CategoriaIngrediente OTROS = new CategoriaIngrediente(6, "OTROS");
    private static final CategoriaIngrediente PESCADOS = new CategoriaIngrediente(7, "PESCADOS");
    private static final CategoriaIngrediente VERDURAS = new CategoriaIngrediente(8, "VERDURAS");

    // MEDICIONES
    private static final Medicion GRAMOS = new Medicion(1, "Gr");
    private static final Medicion KILOS = new Medicion(2, "Kg");
    private static final Medicion LITROS = new Medicion(3, "L");
    private static final Medicion MILILITROS = new Medicion(4, "ml");
    private static final Medicion NO_CUANTIFICABLE = new Medicion(5, "NO CUANTIFICABLE");
    private static final Medicion UNIDAD = new Medicion(6, "Unidad");

    private static Ingrediente nuevoIngrediente(
            String nombre, CategoriaIngrediente categoria, Medicion medicion
    ) {
        Ingrediente nuevo = new Ingrediente();
        nuevo.setNombre(nombre);
        nuevo.setCategoriaIngrediente(categoria);
        nuevo.setMedicion(medicion);
        return nuevo;
    }

    public static List<CategoriaIngrediente> getDefaultCategoriasIngrediente() {
        return Arrays.asList(
            CARNES,
            CEREALES_Y_FRUTOS_SECOS,
            CONDIMENTOS,
            FRUTA,
            HUEVOS_Y_LACTEOS,
            OTROS,
            PESCADOS,
            VERDURAS
        );
    }

    public static List<Medicion> getDefaultMediciones() {
        return Arrays.asList(
            GRAMOS,
            KILOS,
            LITROS,
            MILILITROS,
            NO_CUANTIFICABLE,
            UNIDAD
        );
    }

    public static List<Ingrediente> getDefaultIngredientes() {
        return Arrays.asList(
            nuevoIngrediente("Conejo", CARNES, KILOS),
            nuevoIngrediente("Pollo", CARNES, KILOS),
            nuevoIngrediente("Cordero", CARNES, KILOS),
            nuevoIngrediente("Avena", CEREALES_Y_FRUTOS_SECOS, GRAMOS),
            nuevoIngrediente("Arroz", CEREALES_Y_FRUTOS_SECOS, GRAMOS),
            nuevoIngrediente("Aceite", CONDIMENTOS, MILILITROS)
        );
    }
}
