package com.aihg.gestionatumenu.db.database.util.generator;

import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.ACEITE;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.AJO;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.ALCAPARRAS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.ANCHOAS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.CALABACIN;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.CEBOLLA;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.ESPAGUETIS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.ESPARRAGOS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.GUINDILLAS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.HUEVOS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.OLIVAS_NEGRAS;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.PATATA;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.PIMIENTA_NEGRA;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.SAL;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.SALMON;
import static com.aihg.gestionatumenu.db.database.util.generator.IngredientesDataGenerator.TOMATE;

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
    private static final CategoriaReceta CR_ESTOFADOS_Y_LEGUMBRES = new CategoriaReceta(4, "ESTOFADOS Y LEGUMBRES");
    private static final CategoriaReceta CR_EXOTICOS = new CategoriaReceta(5, "EXÓTICOS");
    private static final CategoriaReceta CR_OTROS = new CategoriaReceta(6, "OTROS");
    private static final CategoriaReceta CR_PESCADOS_Y_MARISCOS = new CategoriaReceta(7, "PESCADOS y MARISCOS");
    private static final CategoriaReceta CR_PIZZAS_Y_PASTAS = new CategoriaReceta(8, "PIZZAS y PASTAS");
    private static final CategoriaReceta CR_POSTRES = new CategoriaReceta(9, "POSTRES");
    private static final CategoriaReceta CR_VEGETARIANO = new CategoriaReceta(10, "VEGETARIANO");

    // RECETAS
    private static final Receta TORTILLA_PATATA = new Receta(1, "Tortilla de Patata", "instrucciones tortilla de patata");
    private static final Receta SALMON_ESPARRAGOS = new Receta(2, "Salmón con Esparragos trigueros", "instrucciones salmon esparragos");
    private static final Receta ZARANGOLLO = new Receta(3, "Zarangollo", "instrucciones zarangollo");
    private static final Receta PASTA_PUTANESCA = new Receta(4, "Pasta a la Putanesca", "instrucciones pasta");

    public static List<CategoriaReceta> getDefaultCategoriasRecetas() {
        return Arrays.asList(
            CR_CARNES,
            CR_DESAYUNO,
            CR_ENSALADAS,
            CR_ESTOFADOS_Y_LEGUMBRES,
            CR_EXOTICOS,
            CR_OTROS,
            CR_PESCADOS_Y_MARISCOS,
            CR_PIZZAS_Y_PASTAS,
            CR_POSTRES,
            CR_VEGETARIANO
        );
    }

    public static List<Receta> getDefaultRecetas() {
        return Arrays.asList(
            TORTILLA_PATATA,
            SALMON_ESPARRAGOS,
            ZARANGOLLO,
            PASTA_PUTANESCA
        );
    }

    public static List<Cataloga> getCatalogacionRecetas() {
        return Arrays.asList(
            new Cataloga(PASTA_PUTANESCA, CR_PESCADOS_Y_MARISCOS),
            new Cataloga(PASTA_PUTANESCA, CR_PIZZAS_Y_PASTAS),

            new Cataloga(SALMON_ESPARRAGOS, CR_PESCADOS_Y_MARISCOS),

            new Cataloga(TORTILLA_PATATA, CR_VEGETARIANO),
            new Cataloga(ZARANGOLLO, CR_VEGETARIANO)
        );
    }

    public static List<Utiliza> getAsignacionIngredientesReceta() {
        return Arrays.asList(
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

            new Utiliza(ZARANGOLLO, PATATA, 3),
            new Utiliza(ZARANGOLLO, CEBOLLA, 2),
            new Utiliza(ZARANGOLLO, CALABACIN, 2),
            new Utiliza(ZARANGOLLO, SAL, 0),
            new Utiliza(ZARANGOLLO, ACEITE, 20),

            new Utiliza(SALMON_ESPARRAGOS, SALMON, 500),
            new Utiliza(SALMON_ESPARRAGOS, PIMIENTA_NEGRA, 0),
            new Utiliza(SALMON_ESPARRAGOS, ESPARRAGOS, 200),
            new Utiliza(SALMON_ESPARRAGOS, AJO, 60),

            new Utiliza(TORTILLA_PATATA, ACEITE, 150),
            new Utiliza(TORTILLA_PATATA, SAL, 0),
            new Utiliza(TORTILLA_PATATA, HUEVOS, 5),
            new Utiliza(TORTILLA_PATATA, PATATA, 2),
            new Utiliza(TORTILLA_PATATA, CEBOLLA, 3)
        );
    }
}
