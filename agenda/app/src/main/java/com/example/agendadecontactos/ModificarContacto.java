/***
 * Clase para modificar un contacto.
 *
 * Esta clase permitirá modificar un contacto ya existente.
 *
 * - Metodo para consultar los datos del contacto, nos devolverá todos los datos del contacto.
 *   y nos mostrará en los dtaos de los campos de la vista.
 * - Método para modificar los datos del contacto si los campos no estan vacíos
 *   se registrarán en la base de datos.
 * - Método para comprobar que los campos no estan vacíos.
 * - Método para cancelar la edición del contacto.
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * fecha: 21/03/2025
 */
package com.example.agendadecontactos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModificarContacto extends AppCompatActivity {

    // SE sobrescribe el método onCreate para mostrar la vista de modificar contactos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarContacto), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Se instancia la base de datos
        BDAgenda db = new BDAgenda(this, "agenda", null, 1);
        //Se instancia los botones
        Button btnModificar = findViewById(R.id.btModificar);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        // Se llama a los métodos para dar funcionalidad a la vista.
        consultarDato(db, getIntent().getStringExtra("id"));
        modificarContacto(btnModificar, db);
        cancelarVista(btnCancelar);
    }

    /***
     * Método para consultar los datos del contacto, nos devolverá todos los datos del contacto.
     * @param db se recibe la base de datos
     * @param id se recibe el id del contacto
     */
    public void consultarDato(BDAgenda db, String id) {
        // Se instancia los campos de la vista
        EditText nombre = findViewById(R.id.etNombre);
        EditText apellidos = findViewById(R.id.etApellidos);
        EditText telefono = findViewById(R.id.etTelefono);
        EditText email = findViewById(R.id.etEmail);

        // Se consulta el contacto en la base de datos
        Cursor cursor = db.consultarContacto(id);
        // Se recorre el cursor
        if (cursor.moveToFirst()) {
            //Bucle para obtener todos los datos del contacto
            do {
                nombre.setText(cursor.getString(1));
                apellidos.setText(cursor.getString(2));
                telefono.setText(cursor.getString(3));
                email.setText(cursor.getString(4));
            } while (cursor.moveToNext()); //Cuando no haya mas registros se finaliza la consulta.
        }
    }

    /***
     * Método para modificar los datos del contacto si los campos no estan vacíos
     * @param btn se recibe el botón modificar
     * @param db se recibe la base de datos
     */
    public void modificarContacto(Button btn, BDAgenda db) {
        // Listener para escuchar el botón modificar
        btn.setOnClickListener(v -> {
            //Se instancia los campos de la vista
            String id = getIntent().getStringExtra("id");
            EditText nombre = findViewById(R.id.etNombre);
            EditText apellidos = findViewById(R.id.etApellidos);
            EditText telefono = findViewById(R.id.etTelefono);
            EditText email = findViewById(R.id.etEmail);

            //Se comprueba que los campos no estén vacíos
            boolean camposRellenos = comprobarCampos(nombre, apellidos, telefono, email);
            // Se crea un objeto Intent
            Intent intent = new Intent(ModificarContacto.this, Activity_Principal.class);
            // Se comprueba que los campos no estén vacíos
            if (camposRellenos) {
                //Se modifica el contacto en la base de datos
                db.modificarContacto(id, nombre.getText().toString(), apellidos.getText().toString(), telefono.getText().toString(), email.getText().toString());
                //Se inicia la actividad
                startActivity(intent);
                //Se finaliza la vista
                finish();
            }
        });
    }

    /***
     * Método para comprobar que los campos no estan vacíos.
     * @param etNombre se recibe el campo nombre
     * @param etApellidos se recibe el campo apellidos
     * @param etTelefono se recibe el campo teléfono
     * @param etEmail se recibe el campo email
     * @return se devuelve un booleano
     */
    public boolean comprobarCampos(EditText etNombre, EditText etApellidos, EditText etTelefono, EditText etEmail) {
        // Control de flujos, si alguno de los campos está vacío se muestra un mensaje de error
        if (etNombre.getText().toString().isEmpty()) {
            etNombre.setError("Campo obligatorio");
            Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etApellidos.getText().toString().isEmpty()) {
            etApellidos.setError("Campo obligatorio");
            Toast.makeText(this, "El campo Apellidos es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etTelefono.getText().toString().isEmpty()) {
            etTelefono.setError("Campo obligatorio");
            Toast.makeText(this, "El campo teléfon es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Campo obligatorio");
            Toast.makeText(this, "El campo email es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    //Método para cancelar la vista y volver a la pantalla principal.
    public void cancelarVista(Button btn) {
        //Se crea un objeto Intent
        Intent intent = new Intent(ModificarContacto.this, Activity_Principal.class);
        //Se crea un objeto OnClickListener
        btn.setOnClickListener(v -> {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //Se inicia la actividad
            startActivity(intent);
            //Se finaliza la vista
            finish();
        });
    }
}
