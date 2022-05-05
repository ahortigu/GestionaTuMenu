package com.aihg.gestionatumenu.ui.recetas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aihg.gestionatumenu.R;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.ui.ingredientes.fragments.IngredienteDetailsFragmentArgs;
import com.aihg.gestionatumenu.ui.ingredientes.fragments.IngredienteDetailsFragmentDirections;

public class RecetaDetailsFragment extends Fragment {
    private View view;

    public RecetaDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas_details_fragment, container, false);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.more).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavDirections action;
        if(id == R.id.nav_editar) {
            action = RecetaDetailsFragmentDirections.actionRecetaDetailsFragmentToRecetaEditFragment();
            Navigation.findNavController(view).navigate(action);
        }
        return super.onOptionsItemSelected(item);
    }
}