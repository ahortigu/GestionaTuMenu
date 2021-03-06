package com.aihg.gestionatumenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements AppBarConfiguration.OnNavigateUpListener {
    private NavController navController;
    private NavHostFragment navHostFragment;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NavController
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
            .findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = navHostFragment.getNavController();

        // Toolbar y bottonNavBar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Propagación de acciones
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        menu.findItem(R.id.nav_add).setVisible(false);
        menu.findItem(R.id.nav_save).setVisible(false);
        menu.findItem(R.id.nav_editar).setVisible(false);
        menu.findItem(R.id.nav_buscar).setVisible(false);
        menu.findItem(R.id.nav_clear).setVisible(false);
        menu.findItem(R.id.nav_done).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }
}