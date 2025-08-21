/*
* Aplicaió que recibe uno datos a través de unos campos
* y cuando se plsa el botón mostrar se muestran los datos
* introducidos en un textfild
*
* Versión: 1.0
* Dev @dmiaper (Diógenes Miaja Pérez)
*/
package com.pmdm.datospersonales;
//se han importado todas la librerías necesarias.

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables que obtiene el valor de los objetos del layout.
    TextView datos, nombre, apellidos, email, telefono;
    //variables para almacenar los datos que se reciben.
    String nom, ape, em, tel;

    /*
    * Función que valida los datos introducidos y muestra su valor en un textview.
    */
    public void imprimirDatos(View view) {
        //se obtienen los valores introducidos en los edittext
        nom = nombre.getText().toString();
        ape = apellidos.getText().toString();
        em = email.getText().toString();
        tel = telefono.getText().toString();

        //Variables que almacenan el contenido que deben tener los datos introducidos.
        String valNom = "^[A-Za-zÑñÁáÉéÍíÓóÚú]{2,}( [A-Za-zÑñÁáÉéÍíÓóÚú]{2,})*$";
        String valEm = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String valTel = "([6-9]{1}[0-9]{8})";

        if (nom.isEmpty() || ape.isEmpty() || em.isEmpty() || tel.isEmpty()) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }else if(!nom.matches(valNom)) {
            Toast.makeText(this, "El nombre intoducido no es valido", Toast.LENGTH_LONG).show();
        }else if(!ape.matches(valNom)){
            Toast.makeText(this, "Los apellidos introducidos no son validos", Toast.LENGTH_LONG).show();
        }else if(!em.matches(valEm)){
            Toast.makeText(this, "El email no es valido", Toast.LENGTH_LONG).show();
        }else if(!tel.matches(valTel)){
            Toast.makeText(this, "El número introducido no es valido", Toast.LENGTH_LONG).show();
        }else{
            datos.setText("los datos personales del alumno son:\n"+nombre.getText()+"\n"
                    +apellidos.getText()+"\n"+email.getText()+"\n"+telefono.getText());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.tfNombre);
        apellidos = findViewById(R.id.tfApellidos);
        email = findViewById(R.id.tfEmail);
        telefono = findViewById(R.id.tfTelefono);
        datos = findViewById(R.id.tvDatos);

    }
}