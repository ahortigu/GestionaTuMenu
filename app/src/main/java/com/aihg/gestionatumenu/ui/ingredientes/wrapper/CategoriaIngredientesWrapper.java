package com.aihg.gestionatumenu.ui.ingredientes.wrapper;

import java.util.List;

public class CategoriaIngredientesWrapper {

    private List<String> nestedIngredientesList;
    private String txtCategoria;
    private boolean isExpandable;

    public CategoriaIngredientesWrapper(List<String> nestedIngredientesList , String txtCategoria) {
        this.nestedIngredientesList = nestedIngredientesList ;
        this.txtCategoria = txtCategoria;
        isExpandable = false;
    }

    public List<String> getNestedIngredientesList() {
        return nestedIngredientesList;
    }

    public String getTxtCategoria() {
        return txtCategoria;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
