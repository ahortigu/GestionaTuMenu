package com.aihg.gestionatumenu.db.database.util.generator;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO_Impl;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;

import java.util.Arrays;
import java.util.List;

public class IngredientesDataGenerator {

    // CATEGORIAS (INGREDIENTES)
    private static final CategoriaIngrediente CI_CARNES = new CategoriaIngrediente(1, "CARNES");
    private static final CategoriaIngrediente CI_CEREALES_Y_FRUTOS_SECOS = new CategoriaIngrediente(2, "CEREALES Y FRUTOS SECOS");
    private static final CategoriaIngrediente CI_CONDIMENTOS = new CategoriaIngrediente(3, "CONDIMENTOS");
    private static final CategoriaIngrediente CI_FRUTA = new CategoriaIngrediente(4, "FRUTA");
    private static final CategoriaIngrediente CI_HUEVOS_Y_LACTEOS = new CategoriaIngrediente(5, "HUEVOS Y LÁCTEOS");
    private static final CategoriaIngrediente CI_OTROS = new CategoriaIngrediente(6, "OTROS");
    private static final CategoriaIngrediente CI_PESCADOS = new CategoriaIngrediente(7, "PESCADOS");
    private static final CategoriaIngrediente CI_VERDURAS = new CategoriaIngrediente(8, "VERDURAS");
    private static final CategoriaIngrediente CI_PASTA_Y_ARROZ = new CategoriaIngrediente(9, "PASTA Y ARROZ");
    private static final CategoriaIngrediente CI_LEGUMBRES = new CategoriaIngrediente(10, "LEGUMBRES");

    // MEDICIONES
    private static final Medicion GRAMOS = new Medicion(1, "Gr");
    private static final Medicion KILOS = new Medicion(2, "Kg");
    private static final Medicion LITROS = new Medicion(3, "L");
    private static final Medicion MILILITROS = new Medicion(4, "ml");
    private static final Medicion NO_CUANTIFICABLE = new Medicion(5, "");
    private static final Medicion UNIDAD = new Medicion(6, "Unidad");

    // INGREDIENTES
    public static final Ingrediente PATATA = new Ingrediente(1, "Patata", UNIDAD, CI_VERDURAS);
    public static final Ingrediente CEBOLLA = new Ingrediente(2, "Cebolla", UNIDAD, CI_VERDURAS);
    public static final Ingrediente ESPARRAGOS = new Ingrediente(3, "Espárragos", GRAMOS, CI_VERDURAS);
    public static final Ingrediente CALABACIN = new Ingrediente(4, "Calabacín", UNIDAD, CI_VERDURAS);
    public static final Ingrediente HUEVOS = new Ingrediente(5, "Huevos", UNIDAD, CI_HUEVOS_Y_LACTEOS);
    public static final Ingrediente CONEJO = new Ingrediente(6, "Conejo", KILOS, CI_CARNES);
    public static final Ingrediente POLLO = new Ingrediente(7, "Pollo", KILOS, CI_CARNES);
    public static final Ingrediente CORDERO = new Ingrediente(8, "Cordero", KILOS, CI_CARNES);
    public static final Ingrediente SALMON = new Ingrediente(9, "Salmón", GRAMOS, CI_PESCADOS);
    public static final Ingrediente AVENA = new Ingrediente(10, "Avena", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);
    public static final Ingrediente ARROZ = new Ingrediente(11, "Arroz", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);
    public static final Ingrediente ACEITE = new Ingrediente(12, "Aceite", MILILITROS, CI_CONDIMENTOS);
    public static final Ingrediente VINAGRE = new Ingrediente(13, "Vinagre", MILILITROS, CI_CONDIMENTOS);
    public static final Ingrediente PIMIENTA_NEGRA = new Ingrediente(14, "Pimienta Negra", NO_CUANTIFICABLE, CI_CONDIMENTOS);
    public static final Ingrediente SAL = new Ingrediente(15, "Sal", NO_CUANTIFICABLE, CI_CONDIMENTOS);
    public static final Ingrediente AZUCAR = new Ingrediente(16, "Azúcar", GRAMOS, CI_CONDIMENTOS);
    public static final Ingrediente AJO = new Ingrediente(17, "Ajo", UNIDAD, CI_VERDURAS);
    public static final Ingrediente ESPAGUETIS = new Ingrediente(18, "Espaguetis", GRAMOS, CI_PASTA_Y_ARROZ);
    public static final Ingrediente ALCAPARRAS = new Ingrediente(19, "Alcaparras", GRAMOS, CI_VERDURAS);
    public static final Ingrediente ANCHOAS = new Ingrediente(20, "Anchoas", GRAMOS, CI_PESCADOS);
    public static final Ingrediente OLIVAS_NEGRAS = new Ingrediente(21, "Olivas Negras", GRAMOS, CI_VERDURAS);
    public static final Ingrediente TOMATE = new Ingrediente(22, "Tomate", GRAMOS, CI_VERDURAS);
    public static final Ingrediente GUINDILLAS = new Ingrediente(23, "Guindillas", UNIDAD, CI_CONDIMENTOS);

    public static List<CategoriaIngrediente> getDefaultCategoriasIngrediente() {
        return Arrays.asList(
            CI_CARNES,
            CI_CEREALES_Y_FRUTOS_SECOS,
            CI_CONDIMENTOS,
            CI_FRUTA,
            CI_HUEVOS_Y_LACTEOS,
            CI_OTROS,
            CI_PESCADOS,
            CI_VERDURAS,
            CI_PASTA_Y_ARROZ,
            CI_LEGUMBRES
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
            PATATA,
            CEBOLLA,
            ESPARRAGOS,
            CALABACIN,
            HUEVOS,
            CONEJO,
            POLLO,
            CORDERO,
            SALMON,
            AVENA,
            ARROZ,
            ACEITE,
            VINAGRE,
            PIMIENTA_NEGRA,
            SAL,
            AZUCAR,
            AJO,
            ESPAGUETIS,
            ALCAPARRAS,
            OLIVAS_NEGRAS,
            TOMATE,
            GUINDILLAS,
            ANCHOAS
        );
    }
}
