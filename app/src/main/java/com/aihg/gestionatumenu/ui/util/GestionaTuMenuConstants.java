package com.aihg.gestionatumenu.ui.util;

import java.util.regex.Pattern;

public class GestionaTuMenuConstants {
    public static final String NO_INGREDIENTE = "No hay Ingredientes";
    public static final String NO_DESPENSA = "No hay Ingredientes";
    public static final String NO_LISTA_COMPRA = "No hay Ingredientes";
    public static final String NO_RECETA = "No hay Recetas";

    public static final String TOAST_BORRAR_LISTA_COMPRA = "¡Item borrado de la lista!";
    public static final String TOAST_BORRAR_DESPENSA = "¡Item borrado de la despensa!";
    public static final String TOAST_MIN_INGREDIENTES_RECETA_EDITAR = "¡Una receta debe contener almenos un ingrediente!";
    public static final String TOAST_MIN_INGREDIENTES_RECETA_CREAR = "¡Una receta debe contener almenos un ingrediente!";
    public static final String TOAST_BORRAR_INGREDIENTE_RECETA = "¡Ingrediente eliminado de la receta!";
    public static final String TOAST_BORRAR_INGREDIENTE = "¡Ingrediente borrado!";
    public static final String TOAST_BORRAR_INGREDIENTE_FALLADO = "No se puede borrar. ¡Está siendo utilizado!";
    public static final String TOAST_UPDATE_DESPENSA = "¡Ingrediente actualizado!";
    public static final String TOAST_UPDATE_LISTACOMPRA = "¡Lista actualizada!";
    public static final String TOAST_NO_EXISTE_RECETA = "La receta no existe";
    public static final String TOAST_NO_EXISTE_INGREDIENTE_LISTA = "El ingrediente no existe en la lista";
    public static final String TOAST_NO_EXISTE_INGREDIENTE = "El ingrediente no existe";
    public static final String TOAST_CREAR_INGREDIENTE = "Ingrediente creado con éxito";
    public static final String TOAST_CREAR_INGREDIENTE_YA_EXISTE = "¡El ingrediente ya existe!";
    public static final String TOAST_PLANIFICADOR_LIMPIO = "¡Planificador limpio!";
    public static final String TOAST_PLANIFICADOR_APLICADO = "¡Planificador aplicado!";

    public static final String TOAST_CAMPO_VACIO = "Por favor, no deje ningún campo vacío";

    public static final String RECETA_SELECT_CATEGORIA_SPINNER = "Selecciona una Categoría";
    public static final String TOAST_RECETA_EDIT_CATEGORIA_DUPLICADA = "¡Categoría duplicada!";
    public static final String TOAST_RECETA_EDIT_BORRAR_TODAS_CATEGORIAS = "¡Seleccione almenos una categoría!";

    public static final String RECETA_CREAR_HINT_INSTRUCCIONES = "Escriba las instrucciones aqui...";
    public static final String RECETA_CREAR_HINT_NOMBRE = "Nombre de la Receta";

    public static final int MAX_CATGORIAS_POR_RECETA = 2;

    public static final Pattern IS_NUMERIC = Pattern.compile("-?\\d+(\\.\\d+)?");
}
