package pmdm.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Calculadora extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculadora, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Se crea un objeto Button
        Button btnOperar = view.findViewById(R.id.btOperar);
        // Se crea un objeto OnClickListener
        btnOperar.setOnClickListener(new View.OnClickListener() {
            // Cuando se presione el botón se cargará la web.
            @Override
            public void onClick(View v) {
                // Se comprueba que los campos EditText no estén vacíos
                EditText etOper1 = view.findViewById(R.id.etOper1);
                EditText etOper2 = view.findViewById(R.id.etOper2);

                if(etOper1.getText().toString().isEmpty() || etOper2.getText().toString().isEmpty()){
                    // Si los campos están vacíos se muestra un Toast
                    Toast.makeText(getContext(), "Debes introducir los dos operandos", Toast.LENGTH_SHORT).show();
                }else{
                    // Se convierten los operandos a double
                    double operando1 = Double.parseDouble(etOper1.getText().toString());
                    double operando2 = Double.parseDouble(etOper2.getText().toString());

                    // Se crea un objeto RadioGroup
                    RadioGroup rgOperaciones = view.findViewById(R.id.rgOperaciones);
                    // Se comprueba que radioButton esta seleccionado
                    if(rgOperaciones.getCheckedRadioButtonId() == -1){
                        // Si no esta seleccionado se muestra un Toast
                        Toast.makeText(getContext(), "Debes seleccionar una operación", Toast.LENGTH_SHORT).show();
                    }else if(rgOperaciones.getCheckedRadioButtonId() == R.id.rbSumar){
                        // Se llama al método sumar
                        sumar(view, operando1, operando2);
                    }else if(rgOperaciones.getCheckedRadioButtonId() == R.id.rbRestar){
                        // Se llama al metodo restar
                        restar(view, operando1, operando2);
                    }else if(rgOperaciones.getCheckedRadioButtonId() == R.id.rbMulti){
                        // Se llama al método multiplicar
                        multiplicar(view, operando1, operando2);
                    }else if(rgOperaciones.getCheckedRadioButtonId() == R.id.rbDividir){
                        // Se llama al método dividir
                        dividir(view, operando1, operando2);
                    }
                }
            }
        });
    }

    /**
     * Método que realiza la operación de suma.
     * @param view recibe la vista
     * @param operador1 recibe el operador 1 de tipo double
     * @param operador2 recibe el operador 2 de tipo double
     */
    public void sumar(View view, double operador1, double operador2){
        // Se crea un objeto TextView
        TextView tvResultado = view.findViewById(R.id.tvResultado);
        // Se calcula y convierte el resultado en String
        String resultado = String.valueOf(operador1 + operador2);
        // Se llama al método imprimirResultado
        imprimirResultado(view, resultado);
    }

    /**
     * Método que realiza la operación de restar
     * @param view recibe la vista
     * @param operador1 recibe el operador 1 de tipo double
     * @param operador2 recibe el operador 2 de tipo double
     */
    public void restar(View view, double operador1, double operador2){
        // Se crea un objeto TextView
        TextView tvResultado = view.findViewById(R.id.tvResultado);
        // Se calcula y  convierte el resultado en String
        String resultado = String.valueOf(operador1 - operador2);
        // Se llama al método imprimirResultado
        imprimirResultado(view, resultado);
    }

    /**
     * Método que realiza la operación de multiplicar
     * @param view recibe la vista
     * @param operador1 recibe el operador 1 de tipo double
     * @param operador2 recibe el operador 2 de tipo double
     */
    public void multiplicar(View view, double operador1, double operador2){
        // Se crea un objeto TextView
        TextView tvResultado = view.findViewById(R.id.tvResultado);
        // Se calcula y  convierte el resultado en String
        String resultado = String.valueOf(operador1 * operador2);
        // Se llama al método imprimirResultado
        imprimirResultado(view, resultado);
    }

    /**
     * Método que realiza la operación de dividir
     * @param view recibe la vista
     * @param operador1 recibe el operador 1 de tipo double
     * @param operador2 recibe el operador 2 de tipo double
     */
    public void dividir(View view, double operador1, double operador2){
        // Si el segundo operando es 0 se muestra un Toast
        if(operador2 == 0){
            // Se crea un objeto TextView
            TextView tvResultado = view.findViewById(R.id.tvResultado);
            String err = "No se puede dividir entre 0";
            tvResultado.setText(err);
        }else{
            // Variable para realizar los calculos
            String resultado;
            // Se calcula y convierte el resultado en String
            resultado = String.valueOf(operador1 / operador2);
            imprimirResultado(view, resultado);
        }
    }

    /**
     * Método que imprime el resultado de la operación
     * @param view se recibe el view
     * @param resultado se recibe el resultado de la operación
     */
    public void imprimirResultado(View view, String resultado){
        // Se crea un objeto TextView
        TextView tvResultado = view.findViewById(R.id.tvResultado);
        // Si el resultado tiene decimales se eliminan
        if(resultado.endsWith(".0")){
            resultado = resultado.substring(0, resultado.length() - 2);
        }
        // Se agrega el texto a mostrar.
        resultado = "El resultado de la multiplicación es: \n" +resultado;
        // Se muestra el resultado
        tvResultado.setText(resultado);
    }
}