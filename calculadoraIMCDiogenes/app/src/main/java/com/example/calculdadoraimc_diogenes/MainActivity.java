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
//import android.os.Handler; //esta libería es para usarse en el método obsoleto
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_main);
        //Se obtiene un objeto ActionBar con el valor de la barra de título
        ActionBar actionBar = getSupportActionBar();
        //Se comprueba si el objeto ActionBar no es nulo
        if(actionBar != null){
            //si no es nulo se oculta
            getSupportActionBar().hide();
        }

        /*
        //Método obsoleto para controlar el tiempo de espera y cambio de vista
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() { //Se modifica la función run
                //Se crea un objeto Intent para cambiar de vista
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                //se llama al método startActivity para cambiar de vista
                startActivity(intent);
                //Se llama al método finish() para que no se pueda volver a esta vista.
                finish();
                }
            },3000);
        */


        //Creación del hilo de ejecución
        ScheduledExecutorService ejecutor = Executors.newSingleThreadScheduledExecutor();
        //Método para controlar el tiempo de espera y cambio de vista
        ejecutor.schedule(()-> { //se utiliza lambda para simplificar el código
            Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
            startActivity(intent);
            finish();
        }, 3000, TimeUnit.MILLISECONDS);

    }
}