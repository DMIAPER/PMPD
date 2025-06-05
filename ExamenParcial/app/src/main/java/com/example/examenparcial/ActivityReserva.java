package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reserva);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView origen = findViewById(R.id.tvOrigen);
        TextView destino = findViewById(R.id.tvDestino);
        TextView numPax = findViewById(R.id.tvNumPax);
        Button btVolver = findViewById(R.id.btVolver);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String vOrigen = origen.getText().toString()+" "+extras.getString("origen");
        String vDestino = destino.getText().toString() +" "+extras.getString("destino");
        String vNumPax = numPax.getText().toString()+" "+extras.getString("numpax");

        origen.setText(vOrigen);
        destino.setText(vDestino);
        numPax.setText(vNumPax);

        volver(btVolver);
    }

    public void volver(Button btVolver){
        btVolver.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Método que crea el menú
     * @param menu recibe como parámeto el obneto creado de menú
     * @return se devuele ve true para que se pinte el menú
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Se crea un objeto MenuInflater
        MenuInflater inflater = getMenuInflater();
        //Se pinta el menú
        inflater.inflate(R.menu.menu, menu);
        //Se devuelve true para que se pinte el menú
        return true;
    }

    /**
     * Método que se ejecuta cuando se selecciona un item del menú
     * @param item recibe como parámetro el item seleccionado
     * @return se para que se ejecute la acción
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Se instancia una variable
        String selItem;
        //Se obtiene el item seleccionado
        selItem =  String.valueOf(item.getTitle());
        //Control de flujo para realizar acciones
        if(selItem.equals("Cerrar")){
            //Se cierra la aplicación
            finishAffinity();
            return true;
        }
        return false;
    }
}