/*
 * Gestor de cursos, calcula el importe que costará el curso según el descuento o no que seleccione
 * el usuario
 *
 * Se calculará el importe con el descuento que ha indicado el usuario.
 * Se mostrará el importe por un toast.
 *
 * Versión: 1.0
 * Dev @dmiaper (Diógenes Miaja Pérez)
 */
package com.pmdm.gestiondecursos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.Locale;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //variables para interactuar con la aplicación
    RadioGroup grupoBotones;
    RadioButton botonSel;
    CheckBox oro, plata;
    Button calcular;
    int selCurso;
    String curso, importe, porcentaje, mensaje;
    Float num1, num2;

    //funcion para calcular el importe
    public String calImporte(float num1, float num2){
        //se devuelve el valor e String
        String impTotal;
        //variable para realizar el cálculo
        float preTotal;

        //condicional para controlar si el usuario no ha seleccionado ningún descuento
        //se utiliza la librería local para evitar posibles errores en el uso del formato del string
        if(num2 == 0){
            //se devuelve el valor sin descuento.
            impTotal = String.format(new Locale("es", "ES"), "%.2f", num1)+" €";
        }else{
            //si el usuario a seleccionado algún descuente se calcula el importe del curso con los descuentos.
            preTotal = num1-(num1*num2/100);
            impTotal = String.format(new Locale("es", "ES"),"%.2f", preTotal)+" €";
        }

        //se devuelve el valor obtenido
        return impTotal;

    }

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


        //vinculamos la variable groupButton con el radioGruop
        grupoBotones = findViewById(R.id.rbGrupo);
        //vinculamos las variables con los objetos checkbox.
        oro = findViewById(R.id.cbDescuento1);
        plata = findViewById(R.id.cbDescuento2);
        //vinculamos la variable button con el botñon del layout.
        calcular = findViewById(R.id.btCalcular);

        //fución que se ejecuta cuando se presiona el botón calcular en el layout.
        calcular.setOnClickListener(v->{
            //comprobamos si se ha seleccionado un curso
            selCurso = grupoBotones.getCheckedRadioButtonId();
            //se comprueba si se ha seleccionado un curso antes de calcular el importe
            if (selCurso !=-1){
                //se obtiene el curso seleccionado.
                botonSel = findViewById(selCurso);
                //Se obtiene el Curso seleccionado
                curso = botonSel.getText().toString();
                //Se obtiene el tag, que incluye el importe del curso
                importe = botonSel.getTag().toString();
                //se convierte el importe a un valor float para realizar los calculos.
                num1 = Float.parseFloat(importe);
                //se obtiene lo descuentos
                if(oro.isChecked() && plata.isChecked()){
                    //si el ususario a seleccionado los dos porcentajes
                    //se obtiene el valor del primer descuento
                    porcentaje = oro.getTag().toString();
                    //se convierte a un tipo float para poder operar con el
                    num2=Float.parseFloat((porcentaje));
                    //se obtiene el valor del segundo descuento.
                    porcentaje =plata.getTag().toString();
                    //se convierte y se suma al primer descuento
                    num2=num2+Float.parseFloat(porcentaje);
                    //se llama a la función que calcula el importe.
                    importe = calImporte(num1, num2);
                    //se da formato al texto que se muestra en el toast
                    mensaje = "El "+curso+" cuesta "+importe+" con los dos descuentos";
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }else if(oro.isChecked()){
                    //si el ususario a seleccionado el porcentaje oro
                    //se obtiene el valor del primer descuento
                    porcentaje = oro.getTag().toString();
                    //se convierte a un tipo float para poder operar con el
                    num2=Float.parseFloat((porcentaje));
                    importe = calImporte(num1, num2);
                    //se da formato al texto que se muestra en el toast
                    mensaje = "El "+curso+" cuesta "+importe+" con el descuento oro.";
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }else if(plata.isChecked()){
                    //si el ususario a seleccionado el porcentaje plata
                    //se obtiene el valor del primer descuento
                    porcentaje = plata.getTag().toString();
                    //se convierte a un tipo float para poder operar con el
                    num2=Float.parseFloat((porcentaje));
                    importe = calImporte(num1, num2);
                    //se da formato al texto que se muestra en el toast
                    mensaje = "El "+curso+" cuesta "+importe+" con el descuento plata.";
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }else{
                    //si el ususario no ha seleccionado ningún porcentaje
                    importe = calImporte(num1, 0);
                    //se da formato al texto que se muestra en el toast
                    mensaje = "El "+curso+" cuesta "+importe+" sin descuentos.";
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Debe seleccionar un curso, para calcular el importe ", Toast.LENGTH_SHORT).show();
            }

        });

    }
}