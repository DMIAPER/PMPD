/***
 * Clase main.
 *
 * Esta clase simplemente sirve para mostar el SplashScreen de la aplicación,
 * tras el inicio de la misma.
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * fecha: 21/03/2025
 */

package com.example.agendadecontactos;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarContacto), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Se instancia un objeto ActionBar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //si no es nulo se oculta
            getSupportActionBar().hide();
        }

        //Creación del hilo de ejecución
        ScheduledExecutorService ejecutor = Executors.newSingleThreadScheduledExecutor();
        //Método para controlar el tiempo de espera y cambio de vista
        ejecutor.schedule(()-> { //se utiliza lambda para simplificar el código
            Intent intent = new Intent(MainActivity.this, Activity_Principal.class);
            startActivity(intent);
            finish();
        }, 3000, TimeUnit.MILLISECONDS);

    }
}