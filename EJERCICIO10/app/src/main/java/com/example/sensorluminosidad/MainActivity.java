/**
 * Clase MainActivity.java
 * ----------------------
 * Esta clase contendrá la lógica d ela aplicación, para mostrar la luminosidad que se lea del sensor.
 */

package com.example.sensorluminosidad;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Se instancian los objetos necesarios
    private SensorManager sensorManager; // Instancia del sensorManager
    private Sensor sensorLuz; // Instancia del sensor de luminosidad
    private TextView textView; // Instancia del TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Se inicializan los objetos
        textView = findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Se comprueba si el sensor de luminosidad está disponible
        if(sensorManager != null){
            sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        // Se el sensorLuz es null
        if(sensorLuz == null){
            getSupportActionBar().setTitle("Sensor de luz no disponible");
        }else{
            getSupportActionBar().setTitle("El sensor esta conectado");
        }

    }

    /**
     * Método registrar el listener para empezar a recibr datos del sensor de luz
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * Método para detener el listener
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    /**
     * Método para recibir los datos del sensor de luz
     * @param event se recibe el envento
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float luminosidad = event.values[0];
        textView.setText("Luminosidad: " + luminosidad);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se va a usar para la resolución de la tarea
    }

}