package com.example.listadelibros_digenes;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Se llama a la vista que se va a mostar
        setContentView(R.layout.activity_main);

        //Método para que se muestre el contendio debajo del barra de estado del dispositivo
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llPrincipal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Se instancia el objeto libros
        ArrayList<Libro> libros = new ArrayList<>();
        //Se registran los libros con sus datos.
        libros.add(new Libro("Apocalipsis Z. El principio del fin: 1", "Manel Loureiro", R.drawable.apocaplisis_z,11.35));
        libros.add(new Libro("La fortaleza digital", "Dan Brown", R.drawable.fortaleza_digital, 11.35));
        libros.add(new Libro("La última cena", "Javier Sierra", R.drawable.la_ulitma_cena, 14.20));
        libros.add(new Libro("La noche más oscura", "Geoff Johns", R.drawable.la_noche_mas_oscura, 60.00));

        //Se instancia un objeto ListView
        ListView lista = findViewById(R.id.lvPrincipal);

        //se llama al métdod crearItems para mostrar libros.
        crearItems(lista, libros);

        //Se llama al listener para realizar una función cuando se pulse la lista.
        listaOnClick(lista);

    }

    public void crearItems(ListView lista, ArrayList<Libro> libros){
        // se construye el adaptador de la lista
        lista.setAdapter(new Apadtador(this, R.layout.entrada, libros){
            @Override
            //Se construye la entrada de la lista
            public void onEntrada(Object entrada, View view) {
                //Se instancia un objeto TextView
                TextView titulo = view.findViewById(R.id.tvTitulo);
                //Se le asigna el título
                titulo.setText(((Libro) entrada).getTitulo());
                //Se instancia un objeto TextView
                TextView autor =  view.findViewById(R.id.tvAutor);
                //Se le asigna el autor
                autor.setText(((Libro) entrada).getAutor());
                //se instancia un objeto ImageView
                ImageView imagen = view.findViewById(R.id.img_View);
                //Se le asigna la portada del libro
                imagen.setImageResource(((Libro) entrada).getImagen());
            }
        });
    }

    public void listaOnClick(ListView lista){
        //Se usa un Lambda para simplificar el código
        lista.setOnItemClickListener((parent, view, posicion, id) -> {
            //Se crea un objeto libros que contendrá la entrada seleccionada
            Libro elegido = (Libro) parent.getItemAtPosition(posicion);
            //Mensaje que devuelve el Toast
            CharSequence texto = "El libro '" + elegido.getTitulo() + "', tiene un precio de " + elegido.getPrecio() + "€";
            //Toas para mostrar el mensaje
            Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
        });
    }

    /**
     * Método que crea el menú
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Se crea un objeto MenuInflater
        MenuInflater inflater = getMenuInflater();
        //Se pinta el menú
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * Método para gestionar las acciones que se realizan en el menú
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //como se quiere usar un switch se crea una variable de tipo String
        //que contendrá el título del item seleccionado
        //Porque no se pueden utilizar los R.id, ya no son constantes y el switch no funciona.
        String selItem = item.getTitle().toString();
        //Control de flujo para realizar acciones
        switch (selItem){
            //Si el item seleccionado es contacto se mostrará un mensaje
            case "Contacto":
                CharSequence contacto = "dmiaper@hotmail.com";
                //Mensaje que devuelve el Toast
                Toast.makeText(this, contacto, Toast.LENGTH_SHORT).show();
                return true;
            //Si el item seleccionado es cerrar se cerrará la aplicación
            case "Cerrar":
                finishAffinity();
                return true;
            //Si el item seleccionado es acerca de se mostrará un mensaje
            case "Acerca de":
                CharSequence acerca = "Dev: Diógenes Miaja Pérez - V. 1.0.0";
                Toast.makeText(this, acerca, Toast.LENGTH_SHORT).show();
                return true;
            //Se devuelve el item seleccionado al onOptionItemSelected
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}