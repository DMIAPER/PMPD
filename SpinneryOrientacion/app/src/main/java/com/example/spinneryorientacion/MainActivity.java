package com.example.spinneryorientacion;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_main);

        //Objetos auxiliares
        Spinner spinnerColors = findViewById(R.id.spColors);
        TextView tvResultado = findViewById(R.id.tvResultado);
        //Método para rellar el spinner con el string-array de los colores.
        rellenarSpinner(spinnerColors);
        /**
         * Método que recibe dos parámetros:
         *  - Spinner, para ver cual es color seleccionado.
         *  - TextView, para mostrar el resultado.
          */
        mostrarResultado(spinnerColors, tvResultado);

    }

    private void rellenarSpinner(Spinner spinnerColors){
        // Se crea un adaptador para rellenar el Spinner con los elementos que contiene el string-array
        // del recurso sting.xml
        ArrayAdapter<CharSequence> adapater = ArrayAdapter.createFromResource(
                // Contexto actul.
                // Se llama al array-string que contiene los ítems.
                // Diseño predefinido para mostrar cada elemento del Spinner
                this, R.array.selector_colors, android.R.layout.simple_spinner_item);
        // Establecer el diseño que se utilizará cuando se desplieguen las opciones del Spinner
        adapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Se asigna el adaptador al Spinner
        spinnerColors.setAdapter(adapater);
    }

    //Método para mostar los resultados tras la selección del spinner
    private void mostrarResultado(Spinner sp, TextView tv){
        //Listener para seleciconar un color
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //Método que se ejecuta al seleccionar un color
            public void onItemSelected(AdapterView<?> adapterView, View view, int seleccion, long l) {
                //Se utiliza un switch para realizar un acción según la seleccion del spinner.
                switch (seleccion){
                    case 1:
                        //Se llama al string Rojo
                        tv.setText(R.string.dRojo);
                        //Se asigna el color rojo al texto.
                        tv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        break;
                    case 2:
                        //se muestra el string para azul.
                        tv.setText(R.string.dAzul);
                        //Se asigna el color azul al texto.
                        tv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
                        break;
                    case 3:
                        //Se muestra el string para verde
                        tv.setText(R.string.dVerde);
                        //Se asigna el color verde al texto
                        tv.setTextColor(ContextCompat.getColor (MainActivity.this, R.color.green));
                        break;
                    case 4:
                        //se muestra el string para naranja
                        tv.setText(R.string.dNaranja);
                        //Se asigna el color naranja al texto
                        tv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.orange));
                        break;
                    default:
                        tv.setText("");
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}