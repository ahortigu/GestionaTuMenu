package com.aihg.gestionatumenu.ui.recetas.listener;

import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Utiliza;

public interface RecetaListener {
    public void toDeleteUtiliza(Utiliza ingredienteBorrar, int positionABorrar);
    public void toUpdateUtiliza(Utiliza ingredienteActualizar);

    public void toDeleteCatalogo(Cataloga categoriaDondeBorrar);
    public void toAddCatalogo(CategoriaReceta categoriaAnadir);
}
