package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bucket.bunti.R;

public class SplashScreen extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent =
                        new Intent(getApplicationContext(),
                                LoginActivity.
                                        class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);

    }
}
