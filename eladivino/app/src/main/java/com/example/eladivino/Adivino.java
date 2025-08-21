package com.example.eladivino;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import java.util.Random;

public class Adivino {

    //Se declarán los atributos de la clase
    private final int NUM_SECRETO;
    private int puntuacion = 10;
    private int intentos = 10;
    private int puntoUsuario;

    /**
     * Constructor de la clase Adivino
     * @param puntuacion se recibe como parámetro la puntuación que este almacenada en el dispositivo
     */
    public Adivino(int puntuacion) {
        this.NUM_SECRETO = generarNumeroAleatorio();
        this.puntoUsuario = puntuacion;
    }

    //Se genera un número aleatorio entre 1 y 25
    private int generarNumeroAleatorio() {
        return new Random().nextInt(25)+1;
    }

    /**
     * Método para adivinar número
     * Mediante un control de flujo se comprueba si el número introducido es correcto o no
     * En caso de que se quede sin intentos se mostrará un mensaje en pantalla.
     * Si el usuario falla se mostrará un mensaje informado que ha fallado y que introduzca otro número
     * Si el usuario acierta se mostará un mensaje felicitandolo, a demas se añadira la puntuación a su cuenta
     * @param numero
     * @param context
     */
    public boolean adivinarNumero(int numero, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(intentos!=0) {
            if (numero == NUM_SECRETO) {
                builder.setTitle("Has acertado");
                builder.setMessage("El número secreto era " + NUM_SECRETO);
                puntoUsuario += puntuacion;
                intentos = 10;
                puntuacion = 10;
                Dialog dialogo = builder.create();
                dialogo.show();
                return true;
            } else {
                if(numero < NUM_SECRETO){
                    builder.setTitle("Has fallado");
                    intentos -= 1;
                    puntuacion -= 1;
                    builder.setMessage("El número secreto es menor. Te quedan " + intentos + " intentos. Vuelve a introducir un número");
                }else {
                    builder.setTitle("Has fallado");
                    intentos -= 1;
                    puntuacion -= 1;
                    builder.setMessage("El número secreto es mayor. Te quedan " + intentos + " intentos. Vuelve a introducir un número");
                }

                Dialog dialogo = builder.create();
                dialogo.show();
                return false;
            }
        }else {
            builder.setTitle("Lo lamento te has quedado sin intentos");
            builder.setMessage("El número secreto era " + NUM_SECRETO);
            Dialog dialogo = builder.create();
            dialogo.show();
            return false;
        }

    }

    /**
     * Getters se devuelven los valores de los atributos
     * @return
     */
    public int getPuntoUsuario() {
        return puntoUsuario;
    }
    public int getNUM_SECRETO() {
        return NUM_SECRETO;
    }

    public int getIntentos(){
        return intentos;
    }
}
