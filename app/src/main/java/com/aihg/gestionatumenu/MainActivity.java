package com.aihg.gestionatumenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements AppBarConfiguration.OnNavigateUpListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashScreem);
        setContentView(R.layout.activity_main);

        //NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();

        // Toolbar y bottonNavBar
        BottomNavigationView  bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Propagación de acciones
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
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