/*
 * Calculadora que solo será capaz de realizar las siguientes operaciones:
 * Sumar
 * Restar
 * Multiplicar
 * Divir
 *
 * Se controlarán las excepciones:
 * Evitar que los campos estén vacíos
 * La división por 0 enviará un toast informando que nos e puede realizar la operación.
 *
 * Versión: 1.0
 * Dev @dmiaper (Diógenes Miaja Pérez)
 */

package com.pmdm.calculadorabasica;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    //variables para almacenar los objetos del layout e interactuar con la aplicación
    Button sumar, restar, multiplicar, dividir, borrar;
    TextView result;
    EditText ope1, ope2;
    String operador1, operador2, t_Original_Resultado;
    boolean validar;
    float num1, num2, resultado;

    //función para verificar que los campos no estan vacios
    public boolean validarCampos(@NonNull String num1, String num2){
        return !num1.isEmpty() && !num2.isEmpty();
    }

    //función para convertir los String en float
    public float convertiNumero(String num){
        return Float.parseFloat(num);
    }

    @SuppressLint("SetTextI18n")
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

        //se obtiene la referencia de los botones para utilziarlos en java.
        sumar=findViewById(R.id.btSumar);
        restar = findViewById(R.id.btRestar);
        multiplicar = findViewById(R.id.btMultiplicar);
        dividir = findViewById(R.id.btDividir);
        borrar = findViewById(R.id.btBorrar);
        //se obtiene la referencia del los operadores para utilizarlos en java
        ope1 = findViewById(R.id.etNOperador1);
        ope2 = findViewById(R.id.etNOperador2);
        result = findViewById(R.id.tvResultado);
        t_Original_Resultado = getString(R.string.resultado);

        /*
         * Se utiliza el método lambda para llamar al evento para el botón sumar
         */
        sumar.setOnClickListener(v -> {
            //se convierte el valor introducido a un tipo String
            operador1 = ope1.getText().toString();
            operador2 = ope2.getText().toString();
            //se comprueba de que tenga información los campos
            validar = validarCampos(operador1, operador2);

            //si los campos contienen información
            if(validar){
                //se convierte el valor en un tipo de dato float.
                num1 = convertiNumero(operador1);
                num2 = convertiNumero(operador2);
                //se realiza la operación de suma
                resultado = num1 + num2;

                //se imprime el resultado obtenido en el campo Resultado del layout.
                result.setText(String.valueOf(resultado));

            //en caso que alguno de los campos este vacio se muestra un toas informando de que falta un operador.
            }else{
                Toast.makeText(MainActivity.this, "Falta un operador", Toast.LENGTH_SHORT).show();
            }
        });

        /*
         * Se utiliza el método lambda para llamar al evento para el botón restar
         */
        restar.setOnClickListener(v -> {

            //se convierte el valor introducido a un tipo String
            operador1 = ope1.getText().toString();
            operador2 = ope2.getText().toString();
            //se comprueba de que tenga información los campos
            validar = validarCampos(operador1, operador2);

            //si los campos contienen información
            if(validar){
                //se convierte el valor en un tipo de dato float.
                num1 = convertiNumero(operador1);
                num2 = convertiNumero(operador2);
                //se realiza la operación restar
                resultado = num1 - num2;

                //se imprime el resultado obtenido en el campo Resultado del layout.
                result.setText(String.valueOf(resultado));

                //en caso que alguno de los campos este vacio se muestra un toas informando de que falta un operador.
            }else{
                Toast.makeText(MainActivity.this, "Falta un operador", Toast.LENGTH_SHORT).show();
            }

        });

        /*
         * Se utiliza el método lambda para llamar al evento para el botón multiplicar
         */
        multiplicar.setOnClickListener(v-> {

            //se convierte el valor introducido a un tipo String
            operador1 = ope1.getText().toString();
            operador2 = ope2.getText().toString();
            //se comprueba de que tenga información los campos
            validar = validarCampos(operador1, operador2);

            //si los campos contienen información
            if(validar){
                //se convierte el valor en un tipo de dato float.
                num1 = convertiNumero(operador1);
                num2 = convertiNumero(operador2);
                //se realiza la operación multiplicar
                resultado = num1 * num2;

                //se imprime el resultado obtenido en el campo Resultado del layout.
                result.setText(String.valueOf(resultado));

                //en caso que alguno de los campos este vacio se muestra un toas informando de que falta un operador.
            }else{
                Toast.makeText(MainActivity.this, "Falta un operador", Toast.LENGTH_SHORT).show();
            }

        });

        /*
         * Se utiliza el método lambda para llamar al evento para el botón dividir
         */
        dividir.setOnClickListener(v ->{

            //se convierte el valor introducido a un tipo String
            operador1 = ope1.getText().toString();
            operador2 = ope2.getText().toString();
            //se comprueba de que tenga información los campos
            validar = validarCampos(operador1, operador2);

            //si los campos contienen información
            if(validar){
                if(operador2.equals("0")){
                    Toast.makeText(MainActivity.this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                }else {
                    //se convierte el valor en un tipo de dato float.
                    num1 = convertiNumero(operador1);
                    num2 = convertiNumero(operador2);
                    //se realiza la operación dividir
                    resultado = num1 / num2;
                    //se imprime el resultado obtenido en el campo Resultado del layout.
                    result.setText(String.valueOf(resultado));
                }


                //en caso que alguno de los campos este vacio se muestra un toas informando de que falta un operador.
            }else{
                Toast.makeText(MainActivity.this, "Falta un operador", Toast.LENGTH_SHORT).show();
            }

        });

        /*
         * Se utiliza el método lambda para llamar al evento para el botón borrar
         */
        borrar.setOnClickListener(v ->{
            ope1.setText("");
            ope2.setText("");
            result.setText(t_Original_Resultado);
        });

    }
}