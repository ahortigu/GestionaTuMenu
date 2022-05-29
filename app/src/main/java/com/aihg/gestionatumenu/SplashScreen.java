package com.aihg.gestionatumenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int LANDING_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        // Componentes UI
        ConstraintLayout landing = findViewById(R.id.landing);
        ImageView imagen = findViewById(R.id.logo);
        TextView texto = findViewById(R.id.textoLanding);

        // Creación animacion
        Animation animacion = new AlphaAnimation(1, 0);
        animacion.setInterpolator(new AccelerateInterpolator());
        animacion.setStartOffset(500);
        animacion.setDuration(2000);

        // Asignacion animacion
        texto.setAnimation(animacion);
        imagen.setAnimation(animacion);
        landing.setAnimation(animacion);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, LANDING_TIMEOUT);
    }
}