package com.aihg.gestionatumenu.ui.recetas.fragments;

import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_CREAR_INGREDIENTE;
import static com.aihg.gestionatumenu.ui.shared.util.GestionaTuMenuConstants.TOAST_CREAR_INGREDIENTE_YA_EXISTE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aihg.gestionatumenu.R;


public class RecetasCreateFragment extends Fragment {
    private View view;

    public RecetasCreateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.recetas__edit_create_fragment, container, false);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.nav_save).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.nav_save ){
            Toast.makeText(
                    view.getContext(), "TODO", Toast.LENGTH_SHORT
            ).show();
        }
        return super.onOptionsItemSelected(item);
    }
}