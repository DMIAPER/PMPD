package com.example.calculdadoraimc_diogenes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvResultado = findViewById(R.id.tvResultado);
        TextView tvAltura = findViewById(R.id.tvAltura);
        TextView tvPeso = findViewById(R.id.tvPeso);
        TextView tvIMC = findViewById(R.id.tvIMC);

        String nombre = getIntent().getStringExtra("name") +" "+ getString(R.string.menResultdo);
        float altura = getIntent().getFloatExtra("altura", 0)*100;
        float peso = getIntent().getFloatExtra("peso", 0);
        float imc = getIntent().getFloatExtra("imc", 0);

        if(imc < 16.5){
            String texto = nombre+" Bajo peso severo";
            tvResultado.setText(texto);
        }else if(imc < 18.5){
            String texto = nombre+" Bajo peso";
            tvResultado.setText(texto);
        }else if(imc < 25) {
            String texto = nombre+" Peso normal";
            tvResultado.setText(texto);
        }else if(imc < 30) {
            String texto = nombre+" Sobrepeso";
            tvResultado.setText(texto);
        }else if(imc < 35) {
            String texto = nombre+" Obesidad tipo 1 (moderada)";
            tvResultado.setText(texto);
        }else if(imc < 40) {
            String texto = nombre+" Obesidad tipo 2 (severa)";
            tvResultado.setText(texto);
        }else if(imc >= 40) {
            String texto = nombre+" Obesidad tipo 3 (mórbida)";
            tvResultado.setText(texto);
        }

        String alt = getString(R.string.alturaResult)+" "+altura;
        String pes = getString(R.string.pesoResult)+" "+peso;
        String imc1 = getString(R.string.imcResult)+" "+imc;

        tvAltura.setText(alt);
        tvPeso.setText(pes);
        tvIMC.setText(imc1);

        //Se obtiene un objeto ActionBar con el valor de la barra de título
        ActionBar actionBar = getSupportActionBar();
        //Se comprueba si el objeto ActionBar no es nulo
        if(actionBar != null){
            //si no es nulo se oculta
            getSupportActionBar().hide();
        }
    }
}