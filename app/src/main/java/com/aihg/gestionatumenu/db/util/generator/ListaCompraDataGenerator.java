package com.aihg.gestionatumenu.db.util.generator;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ACEITE;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.AJO;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ALCAPARRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ANCHOAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.ESPAGUETIS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.GUINDILLAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.OLIVAS_NEGRAS;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.PIMIENTA_NEGRA;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.SAL;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.TOMATE;

import com.aihg.gestionatumenu.db.entities.ListaCompra;

import java.util.Arrays;
import java.util.List;

public class ListaCompraDataGenerator {

    public static List<ListaCompra> getDefaultListaCompra() {
        return Arrays.asList(
                new ListaCompra( 1, 3, AJO),
                new ListaCompra(2, 500, ESPAGUETIS),
                new ListaCompra(3, 4, ALCAPARRAS ),
                new ListaCompra(5,10, ANCHOAS ),
                new ListaCompra(5, 100, OLIVAS_NEGRAS),
                new ListaCompra(6, 300,TOMATE ),
                new ListaCompra(7, 300, GUINDILLAS ),
                new ListaCompra(8, 0, SAL ),
                new ListaCompra(9,0, PIMIENTA_NEGRA ),
                new ListaCompra(10, 400, ACEITE)

        );
    }

}
