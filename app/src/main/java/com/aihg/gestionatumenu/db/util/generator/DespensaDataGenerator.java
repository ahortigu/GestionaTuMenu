package com.aihg.gestionatumenu.db.util.generator;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ACEITE;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.AJO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ALCAPARRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ANCHOAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.CALAMAR;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ESPAGUETIS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.LECHE;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.MANZANA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.OLIVAS_NEGRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PAN;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PATATA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PIMIENTA_NEGRA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PLATANO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.POLLO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.SAL;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.SALMON;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.TOMATE;

import com.aihg.gestionatumenu.db.entities.Despensa;

import java.util.Arrays;
import java.util.List;

public class DespensaDataGenerator {

    public static List<Despensa> getDefaultDespensa() {
        return Arrays.asList(
                new Despensa(1, 1, AJO),
                new Despensa(2, 100, ESPAGUETIS),
                new Despensa(3, 3, ALCAPARRAS),
                new Despensa(5, 2, ANCHOAS),
                new Despensa(5, 50, OLIVAS_NEGRAS),
                new Despensa(6, 3, TOMATE),
                new Despensa(7, 0, SAL),
                new Despensa(8, 0, PIMIENTA_NEGRA),
                new Despensa(9, 400, CALAMAR),
                new Despensa(10, 300, ACEITE),
                new Despensa(11, 300, POLLO),
                new Despensa(12, 2, PAN),
                new Despensa(13, 300, PLATANO),
                new Despensa(14, 250, SALMON),
                new Despensa(15, 400, PATATA),
                new Despensa(16, 1, LECHE),
                new Despensa(17, 1, MANZANA)
        );
    }


}
