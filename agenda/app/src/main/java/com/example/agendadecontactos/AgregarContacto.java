/***
 * Clase para crear un nuevo contacto.
 *
 * En esta clase se crearán los métodos para poder registrar un nuevo contacto.
 *
 * - Método para contruir la vista.
 * - Método para indicar los campos obligatoris.
 * - Método para comprobar los datos introducidos.
 * - Método para guardar los datos del contacto.
 * - Método para cancelar la vista y volver a la vista principal.
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * fecha: 21/03/2025
 */

package com.example.agendadecontactos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgregarContacto extends AppCompatActivity {

    // Se sobrescribe el método onCreate para mostrar la vista de registro de contactos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.agregarContacto), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etApellidos = findViewById(R.id.etApellidos);
        EditText etTelefono = findViewById(R.id.etTelefono);
        EditText etEmail = findViewById(R.id.etEmail);

        BDAgenda db = new BDAgenda(this, "agenda", null, 1);

        Button btnCrear = findViewById(R.id.btModificar);
        Button btnCancelar = findViewById(R.id.btnCancelar);


        camposObligatorios(etNombre, etApellidos, etTelefono, etEmail);
        guardarContacto(db, btnCrear, etNombre, etApellidos, etTelefono, etEmail);
        cancelarVista(btnCancelar);
    }

    // Método para indicar los campos obligatorios
    public void camposObligatorios(EditText etNombre, EditText etApellidos, EditText etTelefono, EditText etEmail){
        etNombre.setError("Campo obligatorio");
        etApellidos.setError("Campo obligatorio");
        etTelefono.setError("Campo obligatorio");
        etEmail.setError("Campo obligatorio");
    }

    // Método para comprobar los datos introducidos
    public boolean comprobarCampos(EditText etNombre, EditText etApellidos, EditText etTelefono, EditText etEmail){

        if(etNombre.getText().toString().isEmpty()){
            etNombre.setError("Campo obligatorio");
            Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etApellidos.getText().toString().isEmpty()){
            etApellidos.setError("Campo obligatorio");
            Toast.makeText(this, "El campo Apellidos es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etTelefono.getText().toString().isEmpty()){
            etTelefono.setError("Campo obligatorio");
            Toast.makeText(this, "El campo teléfon es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Campo obligatorio");
            Toast.makeText(this, "El campo email es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }

    }

    //Métedo para guardar los datos del contacto
    public void guardarContacto(BDAgenda db, Button btn, EditText etNombre, EditText etApellidos, EditText etTelefono, EditText etEmail){

        //Se crea un objeto OnClickListener
        btn.setOnClickListener(v -> {
            //Se crea un objeto Intent
            Intent intent = new Intent(AgregarContacto.this, Activity_Principal.class);
            boolean camposRellenos = comprobarCampos(etNombre, etApellidos, etTelefono, etEmail);
            if(camposRellenos) {
                db.insertContacto(etNombre.getText().toString(), etApellidos.getText().toString(), etTelefono.getText().toString(), etEmail.getText().toString());
                //Se inicia la actividad
                startActivity(intent);
                //Se finaliza la vista
                finish();
            }
        });

    }

    //Método para cancelar la vista y volver a la vista principal.
    public void cancelarVista(Button btn){
        //Se crea un objeto Intent
        Intent intent = new Intent(AgregarContacto.this, Activity_Principal.class);
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