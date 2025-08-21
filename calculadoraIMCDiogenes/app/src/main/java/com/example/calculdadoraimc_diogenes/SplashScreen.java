package com.example.calculdadoraimc_diogenes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_splash_screen);


        //Se obtiene un objeto ActionBar con el valor de la barra de título
        ActionBar actionBar = getSupportActionBar();
        //Se comprueba si el objeto ActionBar no es nulo
        if(actionBar != null){
            //si no es nulo se oculta
            getSupportActionBar().hide();
        }

        //Creación del hilo de ejecución
        ScheduledExecutorService ejecutor = Executors.newSingleThreadScheduledExecutor();
        //Método para controlar el tiempo de espera y cambio de vista
        ejecutor.schedule(()-> { //se utiliza lambda para simplificar el código
            String nombre = getIntent().getStringExtra("name");
            float altura = getIntent().getFloatExtra("altura", 0);
            float peso = getIntent().getFloatExtra("peso", 0);
            float imc = getIntent().getFloatExtra("imc", 0);
            Intent intent = new Intent(SplashScreen.this, Resultado.class);
            //Se envían los datos para que sean recibidos por la otra actividad
            intent.putExtra("name", nombre);
            intent.putExtra("altura", altura);
            intent.putExtra("peso", peso);
            intent.putExtra("imc", imc);
            //se llama al método startActivity para cambiar de vista
            startActivity(intent);
            startActivity(intent);
            finish();
        }, 3000, TimeUnit.MILLISECONDS);

    }
}