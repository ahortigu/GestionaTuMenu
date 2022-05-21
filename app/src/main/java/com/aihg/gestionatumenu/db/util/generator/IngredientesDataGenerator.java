package com.aihg.gestionatumenu.db.util.generator;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;

import java.util.Arrays;
import java.util.List;

public class IngredientesDataGenerator {

    // CATEGORIAS (INGREDIENTES)
    public static final CategoriaIngrediente CI_CARNES = new CategoriaIngrediente(1, "CARNES");
    public static final CategoriaIngrediente CI_CEREALES_Y_FRUTOS_SECOS = new CategoriaIngrediente(2, "CEREALES Y FRUTOS SECOS");
    public static final CategoriaIngrediente CI_CONDIMENTOS = new CategoriaIngrediente(3, "CONDIMENTOS");
    public static final CategoriaIngrediente CI_FRUTA = new CategoriaIngrediente(4, "FRUTA");
    public static final CategoriaIngrediente CI_HUEVOS_Y_LACTEOS = new CategoriaIngrediente(5, "HUEVOS Y LÁCTEOS");
    public static final CategoriaIngrediente CI_OTROS = new CategoriaIngrediente(6, "OTROS");
    public static final CategoriaIngrediente CI_PESCADOS = new CategoriaIngrediente(7, "PESCADOS");
    public static final CategoriaIngrediente CI_VERDURAS = new CategoriaIngrediente(8, "VERDURAS");
    public static final CategoriaIngrediente CI_PASTA_Y_ARROZ = new CategoriaIngrediente(9, "PASTA Y ARROZ");
    public static final CategoriaIngrediente CI_LEGUMBRES = new CategoriaIngrediente(10, "LEGUMBRES");

    // MEDICIONES
    public static final Medicion GRAMOS = new Medicion(1, "Gr");
    public static final Medicion KILOS = new Medicion(2, "Kg");
    public static final Medicion LITROS = new Medicion(3, "L");
    public static final Medicion MILILITROS = new Medicion(4, "ml");
    public static final Medicion NO_CUANTIFICABLE = new Medicion(5, "");
    public static final Medicion UNIDAD = new Medicion(6, "Unidad");

    // INGREDIENTES
    public static final Ingrediente CONEJO = new Ingrediente(6, "Conejo", KILOS, CI_CARNES);
    public static final Ingrediente POLLO = new Ingrediente(7, "Pollo", KILOS, CI_CARNES);
    public static final Ingrediente CORDERO = new Ingrediente(8, "Cordero", KILOS, CI_CARNES);
    public static final Ingrediente TERNERA = new Ingrediente(24, "Ternera", GRAMOS, CI_CARNES);
    public static final Ingrediente CHORIZO = new Ingrediente(52, "Chorizo", GRAMOS, CI_CARNES);
    public static final Ingrediente JAMON_YORK = new Ingrediente(55, "Jamón york", GRAMOS, CI_CARNES);

    public static final Ingrediente AVENA = new Ingrediente(10, "Avena", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);
    public static final Ingrediente PISTACHOS = new Ingrediente(25, "Pistachos", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);
    public static final Ingrediente ALMENDRAS = new Ingrediente(26, "Almendras", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);
    public static final Ingrediente PAN = new Ingrediente(27, "Pan", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);
    public static final Ingrediente HARINA = new Ingrediente(27, "Harina", GRAMOS, CI_CEREALES_Y_FRUTOS_SECOS);

    public static final Ingrediente SAL = new Ingrediente(15, "Sal", NO_CUANTIFICABLE, CI_CONDIMENTOS);
    public static final Ingrediente AZUCAR = new Ingrediente(16, "Azúcar", GRAMOS, CI_CONDIMENTOS);
    public static final Ingrediente GUINDILLAS = new Ingrediente(23, "Guindillas", UNIDAD, CI_CONDIMENTOS);
    public static final Ingrediente ACEITE = new Ingrediente(12, "Aceite", MILILITROS, CI_CONDIMENTOS);
    public static final Ingrediente VINAGRE = new Ingrediente(13, "Vinagre", MILILITROS, CI_CONDIMENTOS);
    public static final Ingrediente PIMIENTA_NEGRA = new Ingrediente(14, "Pimienta Negra", NO_CUANTIFICABLE, CI_CONDIMENTOS);
    public static final Ingrediente PEREJIL = new Ingrediente(28, "Perejil", NO_CUANTIFICABLE, CI_CONDIMENTOS);

    public static final Ingrediente PLATANO = new Ingrediente(20, "Platano", UNIDAD, CI_FRUTA);
    public static final Ingrediente FRESA = new Ingrediente(30, "Fresa", UNIDAD, CI_FRUTA);
    public static final Ingrediente MANZANA = new Ingrediente(31, "Manzana", UNIDAD, CI_FRUTA);
    public static final Ingrediente UVAS = new Ingrediente(32, "Uvas", GRAMOS, CI_FRUTA);
    public static final Ingrediente KIWI = new Ingrediente(33, "Kiwi", UNIDAD, CI_FRUTA);
    public static final Ingrediente MANDARINA = new Ingrediente(34, "Mandarina", UNIDAD, CI_FRUTA);
    public static final Ingrediente NARANJA = new Ingrediente(35, "Naranja", UNIDAD, CI_FRUTA);

    public static final Ingrediente HUEVOS = new Ingrediente(5, "Huevos", UNIDAD, CI_HUEVOS_Y_LACTEOS);
    public static final Ingrediente LECHE = new Ingrediente(37, "Leche", LITROS, CI_HUEVOS_Y_LACTEOS);
    public static final Ingrediente PARMESANO = new Ingrediente(37, "Parmesano", UNIDAD, CI_HUEVOS_Y_LACTEOS);
    public static final Ingrediente CHEDAR = new Ingrediente(38, "Chedar", GRAMOS, CI_HUEVOS_Y_LACTEOS);
    public static final Ingrediente YOGOURT = new Ingrediente(39, "Yogourt", UNIDAD, CI_HUEVOS_Y_LACTEOS);
    public static final Ingrediente MOZZARELA = new Ingrediente(56, "Mozzarela", UNIDAD, CI_HUEVOS_Y_LACTEOS);

    public static final Ingrediente MERMELADA = new Ingrediente(51, "Mermelada", NO_CUANTIFICABLE, CI_OTROS);

    public static final Ingrediente ESPAGUETIS = new Ingrediente(18, "Espaguetis", GRAMOS, CI_PASTA_Y_ARROZ);
    public static final Ingrediente ARROZ = new Ingrediente(11, "Arroz", GRAMOS, CI_PASTA_Y_ARROZ);
    public static final Ingrediente MACARRONES = new Ingrediente(40, "Macarrones", GRAMOS, CI_PASTA_Y_ARROZ);
    public static final Ingrediente TAGLIATELE = new Ingrediente(41, "Tagliatele", GRAMOS, CI_PASTA_Y_ARROZ);
    public static final Ingrediente LAMINA_LASAGNA = new Ingrediente(50, "Lamina de Lasana", UNIDAD, CI_PASTA_Y_ARROZ);

    public static final Ingrediente SALMON = new Ingrediente(9, "Salmón", GRAMOS, CI_PESCADOS);
    public static final Ingrediente ANCHOAS = new Ingrediente(20, "Anchoas", GRAMOS, CI_PESCADOS);
    public static final Ingrediente BACALAO = new Ingrediente(42, "Bacalao", GRAMOS, CI_PESCADOS);
    public static final Ingrediente CALAMAR = new Ingrediente(43, "Calamar", GRAMOS, CI_PESCADOS);
    public static final Ingrediente DORADA = new Ingrediente(44, "Dorada", GRAMOS, CI_PESCADOS);
    public static final Ingrediente GAMBA = new Ingrediente(45, "Gamba", GRAMOS, CI_PESCADOS);
    public static final Ingrediente LATA_ATUN = new Ingrediente(45, "Lata atún", UNIDAD, CI_PESCADOS);

    public static final Ingrediente PATATA = new Ingrediente(1, "Patata", UNIDAD, CI_VERDURAS);
    public static final Ingrediente CEBOLLA = new Ingrediente(2, "Cebolla", UNIDAD, CI_VERDURAS);
    public static final Ingrediente ESPARRAGOS = new Ingrediente(3, "Espárragos", GRAMOS, CI_VERDURAS);
    public static final Ingrediente CALABACIN = new Ingrediente(4, "Calabacín", UNIDAD, CI_VERDURAS);
    public static final Ingrediente TOMATE = new Ingrediente(22, "Tomate", GRAMOS, CI_VERDURAS);
    public static final Ingrediente AJO = new Ingrediente(17, "Ajo", UNIDAD, CI_VERDURAS);
    public static final Ingrediente ALCAPARRAS = new Ingrediente(19, "Alcaparras", GRAMOS, CI_VERDURAS);
    public static final Ingrediente OLIVAS_NEGRAS = new Ingrediente(21, "Olivas Negras", GRAMOS, CI_VERDURAS);
    public static final Ingrediente PIMIENTO = new Ingrediente(46, "Pimiento", UNIDAD, CI_VERDURAS);
    public static final Ingrediente LECHUGA = new Ingrediente(47, "Lechuga", UNIDAD, CI_VERDURAS);
    public static final Ingrediente ZANAHORIA = new Ingrediente(48, "Zanahoria", UNIDAD, CI_VERDURAS);
    public static final Ingrediente SETAS = new Ingrediente(49, "Setas", UNIDAD, CI_VERDURAS);
    public static final Ingrediente GUISANTES = new Ingrediente(57, "Guisantes", GRAMOS, CI_VERDURAS);

    public static final Ingrediente LENTEJAS = new Ingrediente(53, "LENTEJAS", GRAMOS, CI_LEGUMBRES);
    public static final Ingrediente GARBANZOS = new Ingrediente(54, "GARBANZOS", GRAMOS, CI_LEGUMBRES);


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
                ANCHOAS,
                TERNERA,
                PISTACHOS,
                ALMENDRAS,
                PAN,
                PEREJIL,
                PLATANO,
                FRESA,
                MANZANA,
                UVAS,
                KIWI,
                MANDARINA,
                NARANJA,
                LECHE,
                PARMESANO,
                CHEDAR,
                YOGOURT,
                MACARRONES,
                TAGLIATELE,
                BACALAO,
                CALAMAR,
                DORADA,
                GAMBA,
                PIMIENTO,
                LECHUGA,
                ZANAHORIA,
                SETAS,
                LAMINA_LASAGNA,
                MERMELADA,
                LATA_ATUN,
                CHORIZO,
                LENTEJAS,
                GARBANZOS,
                MOZZARELA,
                HARINA,
                GUISANTES,
                JAMON_YORK
        );
    }
}
