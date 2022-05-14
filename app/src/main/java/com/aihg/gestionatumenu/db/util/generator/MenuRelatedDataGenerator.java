package com.aihg.gestionatumenu.db.util.generator;

import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.Semanal;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;

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

    public static List<Semanal> getDefaultSemanal() {
        return Arrays.asList(
            new Semanal(null, DESAYUNO, LUNES),
            new Semanal(null, COMIDA, LUNES),
            new Semanal(null, CENA, LUNES),

            new Semanal(null, DESAYUNO, MARTES),
            new Semanal(null, COMIDA, MARTES),
            new Semanal(null, CENA, MARTES),

            new Semanal(null, DESAYUNO, MIERCOLES),
            new Semanal(null, COMIDA, MIERCOLES),
            new Semanal(null, CENA, MIERCOLES),

            new Semanal(null, DESAYUNO, JUEVES),
            new Semanal(null, COMIDA, JUEVES),
            new Semanal(null, CENA, JUEVES),

            new Semanal(null, DESAYUNO, VIERNES),
            new Semanal(null, COMIDA, VIERNES),
            new Semanal(null, CENA, VIERNES),

            new Semanal(null, DESAYUNO, SABADO),
            new Semanal(null, COMIDA, SABADO),
            new Semanal(null, CENA, SABADO),

            new Semanal(null, DESAYUNO, DOMINGO),
            new Semanal(null, COMIDA, DOMINGO),
            new Semanal(null, CENA, DOMINGO)
        );
    }

    public static List<Planificador> getDefaultPlanificador() {
        return Arrays.asList(
            new Planificador(null, DESAYUNO, LUNES),
            new Planificador(null, COMIDA, LUNES),
            new Planificador(null, CENA, LUNES),

            new Planificador(null, DESAYUNO, MARTES),
            new Planificador(null, COMIDA, MARTES),
            new Planificador(null, CENA, MARTES),

            new Planificador(null, DESAYUNO, MIERCOLES),
            new Planificador(null, COMIDA, MIERCOLES),
            new Planificador(null, CENA, MIERCOLES),

            new Planificador(null, DESAYUNO, JUEVES),
            new Planificador(null, COMIDA, JUEVES),
            new Planificador(null, CENA, JUEVES),

            new Planificador(null, DESAYUNO, VIERNES),
            new Planificador(null, COMIDA, VIERNES),
            new Planificador(null, CENA, VIERNES),

            new Planificador(null, DESAYUNO, SABADO),
            new Planificador(null, COMIDA, SABADO),
            new Planificador(null, CENA, SABADO),

            new Planificador(null, DESAYUNO, DOMINGO),
            new Planificador(null, COMIDA, DOMINGO),
            new Planificador(null, CENA, DOMINGO)
        );
    }
}
