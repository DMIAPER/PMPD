/***
 * Clase principal.
 *
 * Esta clase es la que nos mostrará la clase principal que permitrá al usuario
 * navegar las diferentes vistas.
 *
 * Además, también mostrará todos los contanctos registrados en la tabla agenda.
 *
 *
 */
package com.example.agendadecontactos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Objects;

public class Activity_Principal extends AppCompatActivity {
    //Construcrtor de la vista principal.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarContacto), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Se instancia la base de datos
        BDAgenda db = new BDAgenda(this, "agenda", null, 1);
        //Se instancia un objeto ListView
        ArrayList<Contacto> contacto = new ArrayList<>();
        //Se instancia un objeto ListView
        ListView lista = findViewById(R.id.listaContactos);

        //Se crea la lista de contactos
        crearItems(db, lista, contacto);

        //Se carga la vista para modificar el usuario.
        modificarUsuaior(lista);

        //borrar contacto pulsado largo
        borrarContacto(db, lista, contacto);
    }

    //Se instancia el menú para poder utilizar el botón para registar nuevos contactos.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Se crea un objeto MenuInflater
        MenuInflater inflater = getMenuInflater();
        //Se pinta el menú
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Método que permitirá llamar a la ventana de registro de nuevos contactos
    public boolean onOptionsItemSelected(MenuItem item){
        //Se instancia un objeto String que obtendrá el título del menú
        String titulo = Objects.requireNonNull(item.getTitle()).toString();
        //Mediante un control de flujo se seleccionará la acción a realizar
        //En caso de ser agregar contacto se llamará a la ventana de registro
        if (titulo.equals("agregar_contacto")) {
            Intent intent = new Intent(Activity_Principal.this, AgregarContacto.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //método para listar contactos
    public void crearItems(BDAgenda db, ListView lista, ArrayList<Contacto> contacto) {
        Cursor cursor=null;
        try {
            // Se instancia un objeto Cursor que recibe todos los datos de la tabla
            cursor = db.consultarContactos();
            //so el curso contiene información
            if (cursor.moveToFirst()) {
                System.out.println("Existen datos");
                //se utiliza un bucle para recorrer todos los contactos
                do {
                    //Se instancia un objeto Contacto

                    //se registra el contacto en el arraylist
                    contacto.add(new Contacto(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    ));
                } while (cursor.moveToNext()); //se finalizará cuando no haya más registros.}

                //Se instancia un adaptador personalizado
                lista.setAdapter(new Adaptador(this, R.layout.entrada, contacto) {
                    @Override
                    //Se construye la entrada de la lista
                    public void onEntrada(Object entrada, Adaptador.ViewHolder holder) {
                        // Se obtiene el objeto Contacto
                        Contacto contacto = (Contacto) entrada;
                        // Se establecen los datos en los TextView
                        holder.id.setText(contacto.getId());
                        String nomApe = contacto.getNombre() + " " + contacto.getApellidos();
                        holder.nombreApellido.setText(nomApe);
                        holder.telefono.setText(contacto.getTelefono());
                        holder.email.setText(contacto.getEmail());
                    }
                });
            } else {
                Toast.makeText(this, "No hay contactos registrados", Toast.LENGTH_SHORT).show();
            }
        // Al finalizar el proceso se finaliza el cursor.
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //Método para cambiar la vista y modificar los datos del usuario
    public void modificarUsuaior(ListView lista){
        lista.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Activity_Principal.this, ModificarContacto.class);
            intent.putExtra("id", ((TextView) view.findViewById(R.id.id)).getText().toString());
            startActivity(intent);
        });
    }

    public void borrarContacto(BDAgenda db, ListView lista, ArrayList<Contacto> contacto){
        lista.setOnItemLongClickListener((parent, view, position, id) -> {
            String idContacto = ((TextView) view.findViewById(R.id.id)).getText().toString();
            db.borrarContacto(idContacto);
            Toast.makeText(this, "Contacto borrado", Toast.LENGTH_SHORT).show();

            // Actualiza el ArrayList y notifica al adaptador
            contacto.remove(position);
            ((BaseAdapter) lista.getAdapter()).notifyDataSetChanged();
            return true;
        });
    }
}
