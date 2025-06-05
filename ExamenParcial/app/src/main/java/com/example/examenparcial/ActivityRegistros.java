package com.example.examenparcial;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class ActivityRegistros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BDVuelos bd = new BDVuelos(getApplicationContext());
        ListView lista = findViewById(R.id.lista);
        ArrayList<Vuelo> vuelo = new ArrayList<>();

        crearItems(bd, lista, vuelo);
    }

    public void crearItems(BDVuelos bd, ListView lista, ArrayList<Vuelo> vuelo){

        //
        Cursor cursor = bd.consultarVuelos();
        if(cursor.moveToFirst()){
            do {
                vuelo.add(new Vuelo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }

        lista.setAdapter(new Adaptador(this, R.layout.lista_registro, vuelo){
            @Override
            //Se construye la entrada de la lista
            public void onEntrada(Object entrada, View view) {
                int id = ((Vuelo) entrada).getId();
                TextView tvId = view.findViewById(R.id.id);
                tvId.setText(String.valueOf(id));

                //Se añade el origen a la lista de registros
                String origen = ((Vuelo) entrada).getOrigen();
                TextView tvOrigen = view.findViewById(R.id.tvOrigen);
                tvOrigen.setText(origen);

                //Se añade el destino a la lista de registros
                String destino = ((Vuelo) entrada).getDestino();
                TextView tvDestino = view.findViewById(R.id.tvDestino);
                tvDestino.setText(destino);

                //Se añade el número de pasajaros a la lista de registros
                int cantidad = ((Vuelo) entrada).getCantidad();
                TextView tvNumpax = view.findViewById(R.id.tvNumpax);
                tvNumpax.setText(String.valueOf(cantidad));
            }
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