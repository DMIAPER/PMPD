package com.example.calculdadoraimc_diogenes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Se controla la orientación de las vistas de forma automática
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        //Se llama a la vista formulario.
        setContentView(R.layout.activity_formulario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.formulario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Se declaran los objetos con los que se va a interactur
        Button boton = findViewById(R.id.bCalcular);
        EditText et1 = findViewById(R.id.etNombre);
        EditText et2 = findViewById(R.id.etAltura);
        EditText et3 = findViewById(R.id.etPeso);

        //Se obtiene un objeto ActionBar con el valor de la barra de título
        ActionBar actionBar = getSupportActionBar();
        //Se comprueba si el objeto ActionBar no es nulo
        if(actionBar != null){
            //si no es nulo se oculta
            getSupportActionBar().hide();
        }

        enviarDatos(boton, et1, et2, et3);
    }


    /**
     * Método para enviar los datos y cambiar de vista
     * @param btn se recibe el boton
     * @param et1 se recibe el nombre
     * @param et2 se recibe la altura
     * @param et3 se recibe el pero
     */
    public void enviarDatos(Button btn, EditText et1, EditText et2, EditText et3) {
        //Action listener para el botón
        btn.setOnClickListener(v -> {
            //Si los campos están vacíos se muestra un mensaje
            //Se comprueba que los campos no estén vacíos
            if (et1.getText().toString().isEmpty()) {
                Toast.makeText(this, "Rellene el campo Nombre", Toast.LENGTH_SHORT).show();
            }else if (et2.getText().toString().isEmpty()) {
                Toast.makeText(this, "Rellene el campo Altura", Toast.LENGTH_SHORT).show();
            }else if (et3.getText().toString().isEmpty()) {
                Toast.makeText(this, "Rellene el campo Peso", Toast.LENGTH_SHORT).show();
            }else{
                //Si los campos estan rellenos se convierten a al tipo necesario
                String nombre = et1.getText().toString();
                float altura = Float.parseFloat(et2.getText().toString()) /100f;
                float peso = Float.parseFloat(et3.getText().toString());
                //Se calcula el imc
                float imc = peso / (altura * altura);
                //Se crea un objeto Intent para cambiar de vista
                Intent intent = new Intent(FormularioActivity.this, SplashScreen.class);
                //Se envían los datos para que sean recibidos por la otra actividad
                intent.putExtra("name", nombre);
                intent.putExtra("altura", altura);
                intent.putExtra("peso", peso);
                intent.putExtra("imc", imc);
                //se llama al método startActivity para cambiar de vista
                startActivity(intent);
            }
        });
    }

}