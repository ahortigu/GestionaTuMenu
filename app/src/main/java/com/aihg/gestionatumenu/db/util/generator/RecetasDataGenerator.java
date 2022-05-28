package com.aihg.gestionatumenu.db.util.generator;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ACEITE;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.AJO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ALCAPARRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ANCHOAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ARROZ;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.AVENA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.BACALAO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CALABACIN;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CALAMAR;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CEBOLLA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CHORIZO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ESPAGUETIS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ESPARRAGOS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.GAMBA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.GARBANZOS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.GUINDILLAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.GUISANTES;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.HARINA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.HUEVOS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.JAMON_YORK;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.LAMINA_LASAGNA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.LATA_ATUN;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.LECHE;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.LECHUGA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.LENTEJAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.MERMELADA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.MOZZARELA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.OLIVAS_NEGRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PAN;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PATATA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PEREJIL;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PIMIENTA_NEGRA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PLATANO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.SAL;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.SALMON;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.TERNERA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.TOMATE;

import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.util.Arrays;
import java.util.List;

public class RecetasDataGenerator {

    // CATEGORIA (RECETAS)
    private static final CategoriaReceta CR_CARNES = new CategoriaReceta(1, "CARNES");
    private static final CategoriaReceta CR_DESAYUNO = new CategoriaReceta(2, "DESAYUNO");
    private static final CategoriaReceta CR_ENSALADAS = new CategoriaReceta(3, "ENSALADAS");
    private static final CategoriaReceta CR_POTAJES = new CategoriaReceta(4, "POTAJES");
    private static final CategoriaReceta CR_EXOTICOS = new CategoriaReceta(5, "EXÓTICOS");
    private static final CategoriaReceta CR_OTROS = new CategoriaReceta(6, "OTROS");
    private static final CategoriaReceta CR_PESCADOS_Y_MARISCOS = new CategoriaReceta(7, "PESCADOS y MARISCOS");
    private static final CategoriaReceta CR_PIZZAS_Y_PASTAS = new CategoriaReceta(8, "PIZZAS y PASTAS");
    private static final CategoriaReceta CR_POSTRES = new CategoriaReceta(9, "POSTRES");
    private static final CategoriaReceta CR_VEGETARIANO = new CategoriaReceta(10, "VEGETARIANO");

    // RECETAS

    public static final Receta LASAGNA = new Receta(5, "Lasaña", "Instrucciones lasaña");

    public static final Receta LECHE_CON_AVENA = new Receta(6, "Leche con avena", "Instruciones leche con avena");
    public static final Receta TOSTADA_MERMELADA = new Receta(7, "Tostadas con mermelada", "Instrucciones tostadas con mermelada");
    public static final Receta TOSTADA_CON_TOMATE = new Receta(8, "Tostadas con tomate", "Instrucciones tostadas con tomate");

    public static final Receta ENSALADA_ATUN = new Receta(9, "Ensalada atún", "Instrucciones ensalada atún.");

    public static final Receta LENTEJAS_CHORIZO = new Receta(10, "Lentejas con chorizo", "Instrucciones lentejas con chorizo");
    public static final Receta POTAJE_BACALAO = new Receta(12, "Potaje bacalao", "Instrucciones potaje bacalao");

    public static final Receta PIZZA_JAMON = new Receta(11, "Pizza con jamón", "Pizza con jamón");
    public static final Receta PASTA_PUTANESCA = new Receta(4, "Pasta a la Putanesca", "Instrucciones pasta");

    public static final Receta GAMBAS_SALSA_VERDE = new Receta(13, "Gambas con salsa verde", "Instrucciones gambas con salsa verde");
    public static final Receta CALAMARES_SALASA_VERDE = new Receta(14, "Calamares en salsa verde", "Instrucciones calamares en salsa verde");
    public static final Receta SALMON_ESPARRAGOS = new Receta(2, "Salmón con trigueros", "Instrucciones salmón con esparragos triguetos");

    public static final Receta GUISANTES_CON_PATATAS = new Receta(15, "Guisantes con patatas", "Instrucciones guisantes con patata");
    public static final Receta ARROZ_CUBANA = new Receta(16, "Arroz a la cubana", "Instrucciones arroz a la cubana");
    public static final Receta TORTILLA_PATATA = new Receta(1, "Tortilla de patata", "Instrucciones tortilla de patata");
    public static final Receta ZARANGOLLO = new Receta(3, "Zarangollo", "Instrucciones zarangollo");

    public static List<CategoriaReceta> getDefaultCategoriasRecetas() {
        return Arrays.asList(
                CR_CARNES,
                CR_DESAYUNO,
                CR_ENSALADAS,
                CR_EXOTICOS,
                CR_OTROS,
                CR_PESCADOS_Y_MARISCOS,
                CR_PIZZAS_Y_PASTAS,
                CR_POTAJES,
                CR_POSTRES,
                CR_VEGETARIANO
        );
    }

    public static List<Receta> getDefaultRecetas() {
        return Arrays.asList(
                LASAGNA,
                LECHE_CON_AVENA,
                TOSTADA_MERMELADA,
                TOSTADA_CON_TOMATE,
                ENSALADA_ATUN,
                LENTEJAS_CHORIZO,
                POTAJE_BACALAO,
                PASTA_PUTANESCA,
                SALMON_ESPARRAGOS,
                GAMBAS_SALSA_VERDE,
                CALAMARES_SALASA_VERDE,
                PIZZA_JAMON,
                ARROZ_CUBANA,
                GUISANTES_CON_PATATAS,
                TORTILLA_PATATA,
                ZARANGOLLO
        );
    }

    public static List<Cataloga> getCatalogacionRecetas() {
        return Arrays.asList(
                new Cataloga(LASAGNA, CR_CARNES),

                new Cataloga(LECHE_CON_AVENA, CR_DESAYUNO),
                new Cataloga(TOSTADA_CON_TOMATE, CR_DESAYUNO),
                new Cataloga(TOSTADA_MERMELADA, CR_DESAYUNO),

                new Cataloga(ENSALADA_ATUN, CR_ENSALADAS),

                new Cataloga(LENTEJAS_CHORIZO, CR_POTAJES),
                new Cataloga(POTAJE_BACALAO, CR_POTAJES),

                new Cataloga(PASTA_PUTANESCA, CR_PESCADOS_Y_MARISCOS),
                new Cataloga(SALMON_ESPARRAGOS, CR_PESCADOS_Y_MARISCOS),
                new Cataloga(GAMBAS_SALSA_VERDE, CR_PESCADOS_Y_MARISCOS),
                new Cataloga(CALAMARES_SALASA_VERDE, CR_PESCADOS_Y_MARISCOS),

                new Cataloga(PASTA_PUTANESCA, CR_PIZZAS_Y_PASTAS),
                new Cataloga(PIZZA_JAMON, CR_PIZZAS_Y_PASTAS),
                new Cataloga(ARROZ_CUBANA, CR_VEGETARIANO),
                new Cataloga(GUISANTES_CON_PATATAS, CR_VEGETARIANO),
                new Cataloga(TORTILLA_PATATA, CR_VEGETARIANO),
                new Cataloga(ZARANGOLLO, CR_VEGETARIANO)

        );
    }

    public static List<Utiliza> getAsignacionIngredientesReceta() {
        return Arrays.asList(
                new Utiliza(LASAGNA, AJO, 3),
                new Utiliza(LASAGNA, TOMATE, 5),
                new Utiliza(LASAGNA, TERNERA, 500),
                new Utiliza(LASAGNA, LAMINA_LASAGNA, 3),

                new Utiliza(LECHE_CON_AVENA, LECHE, 200),
                new Utiliza(LECHE_CON_AVENA, AVENA, 200),

                new Utiliza(TOSTADA_MERMELADA, PAN, 200),
                new Utiliza(TOSTADA_MERMELADA, MERMELADA, 0),

                new Utiliza(TOSTADA_CON_TOMATE, PAN, 200),
                new Utiliza(TOSTADA_CON_TOMATE, TOMATE, 1),

                new Utiliza(ENSALADA_ATUN, LECHUGA, 1),
                new Utiliza(ENSALADA_ATUN, LATA_ATUN, 1),

                new Utiliza(LENTEJAS_CHORIZO, PATATA, 1),
                new Utiliza(LENTEJAS_CHORIZO, CHORIZO, 20),
                new Utiliza(LENTEJAS_CHORIZO, LENTEJAS, 400),

                new Utiliza(POTAJE_BACALAO, BACALAO, 500),
                new Utiliza(POTAJE_BACALAO, PATATA, 3),
                new Utiliza(POTAJE_BACALAO, SAL, 0),
                new Utiliza(POTAJE_BACALAO, GARBANZOS, 500),

                new Utiliza(PASTA_PUTANESCA, AJO, 3),
                new Utiliza(PASTA_PUTANESCA, ESPAGUETIS, 250),
                new Utiliza(PASTA_PUTANESCA, ALCAPARRAS, 10),
                new Utiliza(PASTA_PUTANESCA, ANCHOAS, 20),
                new Utiliza(PASTA_PUTANESCA, OLIVAS_NEGRAS, 10),
                new Utiliza(PASTA_PUTANESCA, TOMATE, 400),
                new Utiliza(PASTA_PUTANESCA, GUINDILLAS, 4),
                new Utiliza(PASTA_PUTANESCA, SAL, 0),
                new Utiliza(PASTA_PUTANESCA, PIMIENTA_NEGRA, 0),
                new Utiliza(PASTA_PUTANESCA, ACEITE, 20),

                new Utiliza(SALMON_ESPARRAGOS, SALMON, 500),
                new Utiliza(SALMON_ESPARRAGOS, PIMIENTA_NEGRA, 0),
                new Utiliza(SALMON_ESPARRAGOS, ESPARRAGOS, 200),
                new Utiliza(SALMON_ESPARRAGOS, AJO, 60),

                new Utiliza(GAMBAS_SALSA_VERDE, GAMBA, 20),
                new Utiliza(GAMBAS_SALSA_VERDE, AJO, 3),
                new Utiliza(GAMBAS_SALSA_VERDE, PEREJIL, 0),

                new Utiliza(CALAMARES_SALASA_VERDE, CALAMAR, 500),
                new Utiliza(CALAMARES_SALASA_VERDE, AJO, 3),
                new Utiliza(CALAMARES_SALASA_VERDE, PEREJIL, 0),

                new Utiliza(PIZZA_JAMON, TOMATE, 4),
                new Utiliza(PIZZA_JAMON, HUEVOS, 1),
                new Utiliza(PIZZA_JAMON, JAMON_YORK, 300),
                new Utiliza(PIZZA_JAMON, MOZZARELA, 2),
                new Utiliza(PIZZA_JAMON, LECHE, 200),
                new Utiliza(PIZZA_JAMON, HARINA, 200),

                new Utiliza(ARROZ_CUBANA, ARROZ, 400),
                new Utiliza(ARROZ_CUBANA, TOMATE, 2),
                new Utiliza(ARROZ_CUBANA, PLATANO, 1),
                new Utiliza(ARROZ_CUBANA, HUEVOS, 2),

                new Utiliza(GUISANTES_CON_PATATAS, PATATA, 3),
                new Utiliza(GUISANTES_CON_PATATAS, CEBOLLA, 2),
                new Utiliza(GUISANTES_CON_PATATAS, GUISANTES, 400),
                new Utiliza(GUISANTES_CON_PATATAS, ACEITE, 20),

                new Utiliza(TORTILLA_PATATA, ACEITE, 150),
                new Utiliza(TORTILLA_PATATA, SAL, 0),
                new Utiliza(TORTILLA_PATATA, HUEVOS, 5),
                new Utiliza(TORTILLA_PATATA, PATATA, 2),
                new Utiliza(TORTILLA_PATATA, CEBOLLA, 3),

                new Utiliza(ZARANGOLLO, PATATA, 3),
                new Utiliza(ZARANGOLLO, CEBOLLA, 2),
                new Utiliza(ZARANGOLLO, CALABACIN, 2),
                new Utiliza(ZARANGOLLO, SAL, 0),
                new Utiliza(ZARANGOLLO, ACEITE, 20)
        );
    }
}
