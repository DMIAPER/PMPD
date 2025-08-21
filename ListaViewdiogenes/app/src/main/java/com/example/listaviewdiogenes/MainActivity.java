package com.example.listaviewdiogenes;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Se indica que controle las vistas según la orientación del dispositivo
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_main);

        //ArrayList para almacenar las ciudades.
        ArrayList<String> listaCiudades = new ArrayList<>();
        //Se añaden las ciudades a la lista
        listaCiudades.add("Madrid");
        listaCiudades.add("Barcelona");
        listaCiudades.add("Valencia");
        listaCiudades.add("Sevilla");

        //Se crea los objetos de la vista
        ListView lista = findViewById(R.id.lvLista);
        TextView tvResultado = findViewById(R.id.tvResultado);

        //Se llama a los métodos de la lógica de la app.
        //Método para rellenar los datos de la lista
        rellenarLista(lista, listaCiudades);
        //Método para mostrar el número de habitantes según la ciudad seleccionada
        mostrarHabitantes(lista, tvResultado, listaCiudades);
    }

    public void rellenarLista(ListView lista, ArrayList listaCiudades){
        //Se crear un adaptador para rellenar la lista de las ciudades.
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this, //referencia a la actividad actual
                android.R.layout.simple_list_item_1,
                listaCiudades //lista de ciudades para rellenar la lista
        );
        //Se asigna el adaptador a la lista
        lista.setAdapter(adaptador);
    }

    //Método que mostrará en el TextView el número de habitantes de la ciudad seleccionada
    public void mostrarHabitantes(ListView lista, TextView tv, ArrayList listaCiudades){
        //Se crea un listener para cuando se selecciona una ciudad de la lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //Método que se ejecuta cuando se selecciona una ciudad de la lista
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                //Se crea el mensaje que se mostrará en el TextView
                String mensaje =("Nº de habitantes de "+ listaCiudades.toArray()[i] + " " + getResources().getStringArray(R.array.habitantes)[i]);
                //Se muestra el mensaje en el TextView
                tv.setText(mensaje);
            }
        });
    }
}