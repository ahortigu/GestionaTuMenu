package com.aihg.gestionatumenu.db.util.generator;

import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.Menu;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;

import static com.aihg.gestionatumenu.db.util.generator.RecetasDataGenerator.*;

import java.util.Arrays;
import java.util.List;

public class MenuRelatedDataGenerator {
    // DIAS
    public static final Dia LUNES = new Dia(1, "LUNES");
    public static final Dia MARTES = new Dia(2, "MARTES");
    public static final Dia MIERCOLES = new Dia(3, "MIERCOLES");
    public static final Dia JUEVES = new Dia(4, "JUEVES");
    public static final Dia VIERNES = new Dia(5, "VIERNES");
    public static final Dia SABADO = new Dia(6, "SABADO");
    public static final Dia DOMINGO = new Dia(7, "DOMINGO");

    // MOMENTOS
    public static final MomentoComida DESAYUNO = new MomentoComida(1, "DESAYUNO");
    public static final MomentoComida COMIDA = new MomentoComida(2, "COMIDA");
    public static final MomentoComida CENA = new MomentoComida(3, "CENA");


    // MENUS
    public static final Menu MLC  = new Menu(TORTILLA_PATATA, COMIDA ,LUNES);
    public static final Menu MMC  = new Menu(SALMON_ESPARRAGOS, COMIDA ,MARTES);
    public static final Menu MXC  = new Menu(ZARANGOLLO, COMIDA, MIERCOLES);
    public static final Menu MJC  = new Menu(PASTA_PUTANESCA, COMIDA ,JUEVES);

    // PLANIFICADOR
    public static final Planificador PVC  = new Planificador(ZARANGOLLO, COMIDA,VIERNES);
    public static final Planificador PSC  = new Planificador(PASTA_PUTANESCA, COMIDA,SABADO);

    public static List<Dia> getDefaultDias() {
        return Arrays.asList(
                LUNES,
                MARTES,
                MIERCOLES,
                JUEVES,
                VIERNES,
                SABADO,
                DOMINGO
        );
    }

    public static List<MomentoComida> getDefaultMomentoComida() {
        return Arrays.asList(
                DESAYUNO,
                COMIDA,
                CENA
        );
    }

    public static List<Menu> getDefaultMenus() {
        return Arrays.asList(
                MLC,
                MMC,
                MXC,
                MJC
        );
    }

    public static List<Planificador> getDefaultPlanificador() {
        return Arrays.asList(
                PVC,
                PSC
        );
    }
}
