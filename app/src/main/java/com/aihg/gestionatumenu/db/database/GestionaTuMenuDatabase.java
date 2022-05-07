package com.aihg.gestionatumenu.db.database;

import static com.aihg.gestionatumenu.db.database.util.DataGenerator.getDefaultCategoriasIngrediente;
import static com.aihg.gestionatumenu.db.database.util.DataGenerator.getDefaultIngredientes;
import static com.aihg.gestionatumenu.db.database.util.DataGenerator.getDefaultMediciones;
import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.DATABASE_NAME;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.CategoriaRecetaDAO;
import com.aihg.gestionatumenu.db.daos.DespensaDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.ListaCompraDAO;
import com.aihg.gestionatumenu.db.daos.MedicionDAO;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Medicion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
                Ingrediente.class,
                CategoriaIngrediente.class,
                Medicion.class,
                ListaCompra.class,
                Despensa.class,
                CategoriaReceta.class,
        },
        version = 1
)
public abstract class GestionaTuMenuDatabase extends RoomDatabase {

    // Instancia
    private static volatile GestionaTuMenuDatabase INSTANCE;

    // DAO
    public abstract CategoriaIngredienteDAO categoriaIngredienteDAO();

    public abstract IngredienteDAO ingredienteDAO();

    public abstract MedicionDAO medicionDAO();

    public abstract ListaCompraDAO listaCompraDAO();

    public abstract DespensaDAO despensaDAO();

    public abstract CategoriaRecetaDAO categoriaRecetaDAO();

    private static Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // DAO
            CategoriaIngredienteDAO categoriaIngredienteDAO = INSTANCE.categoriaIngredienteDAO();
            IngredienteDAO ingredienteDAO = INSTANCE.ingredienteDAO();
            MedicionDAO medicionDAO = INSTANCE.medicionDAO();
            ListaCompraDAO listaCompraDAO = INSTANCE.listaCompraDAO();
            DespensaDAO despensaDAO = INSTANCE.despensaDAO();
            CategoriaRecetaDAO categoriaRecetaDAO = INSTANCE.categoriaRecetaDAO();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    getDefaultCategoriasIngrediente().stream().forEach(
                            categoriaIngredienteDAO::insert
                    );

                    getDefaultMediciones().stream().forEach(
                            medicionDAO::insert
                    );

                    getDefaultIngredientes().stream().forEach(
                            ingredienteDAO::insert
                    );
                }
            });
        }
    };

    public static GestionaTuMenuDatabase getDbInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (GestionaTuMenuDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GestionaTuMenuDatabase.class,
                            DATABASE_NAME
                    )
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}