package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Se instancia una base de datos
        BDVuelos bdVuelos = new BDVuelos(this);
        bdVuelos.getWritableDatabase();

        //Se obtinene los objetos para interactuar.
        Button btReserva = findViewById(R.id.btReserva);
        Button btRegistros = findViewById(R.id.btRegistros);
        Spinner spOrigen = findViewById(R.id.spinner);
        Spinner spDestino = findViewById(R.id.spinner2);
        EditText etNumpax = findViewById(R.id.etNumpax);

        realizarReserva(bdVuelos, btReserva, spOrigen, spDestino, etNumpax);

        mostarRegistros(btRegistros);
    }

    public void realizarReserva(BDVuelos bd, Button btn, Spinner spOrigen, Spinner spDestino, EditText etNumpax){
        btn.setOnClickListener(view -> {

            String origen = spOrigen.getSelectedItem().toString();
            String destino = spDestino.getSelectedItem().toString();
            String numpax = etNumpax.getText().toString();

            if(comprobarCampos(numpax)) {
                bd.insertContacto(origen, destino, Integer.parseInt(numpax));
                Intent intent = new Intent(getApplicationContext(), ActivityReserva.class);
                intent.putExtra("origen", origen);
                intent.putExtra("destino", destino);
                intent.putExtra("numpax", numpax);
                startActivity(intent);
            }

        });
    }

    public void mostarRegistros(Button btn){
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityRegistros.class);
            startActivity(intent);
        });
    }


    public boolean comprobarCampos(String numpax){
        if(numpax.isEmpty()){
            Toast.makeText(getApplicationContext(), "Debe introducir el número de pasajeros", Toast.LENGTH_SHORT).show();
            return false;
        }else if(numpax.equals("0")){
            Toast.makeText(getApplicationContext(), "El número de pasajeros no puede ser 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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