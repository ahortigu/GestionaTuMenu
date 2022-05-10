package com.aihg.gestionatumenu.db.util.generator;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ACEITE;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.AJO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ALCAPARRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ANCHOAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ESPAGUETIS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.OLIVAS_NEGRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PIMIENTA_NEGRA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.SAL;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.TOMATE;

import com.aihg.gestionatumenu.db.entities.Despensa;

import java.util.Arrays;
import java.util.List;

public class DespensaDataGenerator {

    public static List<Despensa> getDefaultDespensa() {
        return Arrays.asList(
                new Despensa( 1, 1, AJO),
                new Despensa(2, 100, ESPAGUETIS),
                new Despensa(3, 3, ALCAPARRAS ),
                new Despensa(5,2, ANCHOAS ),
                new Despensa(5, 50, OLIVAS_NEGRAS),
                new Despensa(6, 3,TOMATE ),
                new Despensa(8, SAL ),
                new Despensa(40, PIMIENTA_NEGRA ),
                new Despensa(10, 300, ACEITE)
        );
    }


}
