package com.example.eladivino;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

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

        // Instancia de SharedPreferences
        SharedPreferences preferencias = this.getSharedPreferences("puntuacion", Context.MODE_PRIVATE);
        // Instancia de los objetos de la vista
        TextView tvPuntuacion = findViewById(R.id.tvPuntuacion);
        EditText etNumero = findViewById(R.id.etNumero);
        Button btComporbar = findViewById(R.id.btComporbar);

        // Obtener puntuación inicial o valor por defecto
        String puntuacion = preferencias.getString("puntos", "0"); // Valor por defecto es "0"
        tvPuntuacion.setText(puntuacion);

        // Instanciar la clase Adivino
        int puntosIniciales = Integer.parseInt(puntuacion);
        Adivino adivino = new Adivino(puntosIniciales);

        //Se almacena el número secreto
        guardarNumeroSecreto(adivino.getNUM_SECRETO());

        // Configurar botón de comprobación
        comprobarNumero(btComporbar, adivino, etNumero, tvPuntuacion, preferencias);
    }

    /**
     * Método para comprobar el número introducido y registrarlo en preferencias de la app
     * @param btn recibe el botón para realizar el listener
     * @param adivino recibe el objeto adivino para comprobar el número
     * @param etNumero recibe el número introducido
     * @param tvPuntuacion recibe la puntuación de la app
     * @param preferencias recibe las preferencias de la app
     */
    public void comprobarNumero(Button btn, Adivino adivino, EditText etNumero, TextView tvPuntuacion, SharedPreferences preferencias) {
        btn.setOnClickListener(v -> {
            ScheduledExecutorService sleepTime = Executors.newSingleThreadScheduledExecutor();
            try {
                // Convertir el número introducido
                int numeroIngresado = Integer.parseInt(etNumero.getText().toString());

                // Verificar si el número es correcto
                boolean ganado = adivino.adivinarNumero(numeroIngresado, this);

                if (ganado) {
                    // Guardar nueva puntuación en SharedPreferences
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("puntos", String.valueOf(adivino.getPuntoUsuario()));
                    editor.apply();

                    // Actualizar la puntuación en la vista
                    tvPuntuacion.setText(String.valueOf(adivino.getPuntoUsuario()));

                    //Finalizamos la aplicación tras 4
                    sleepTime.schedule(this::finish, 4, TimeUnit.SECONDS);

                } else if (adivino.getIntentos()==0) {
                    //Finalizamos la aplicación tras 4
                    sleepTime.schedule(this::finish, 4, TimeUnit.SECONDS);
                }
            } catch (NumberFormatException e) {
                etNumero.setError("Introduce un número válido");
            }
        });
    }

    //Método para almacenar el número secreto de forma local
    public void guardarNumeroSecreto(int numeroSecreto){
        //Se crea el fichero
        File file = new File("num_secreto.txt");
        //Se abre el fichero en modo escritura
        //No se utiliza close(), que estamos controlando el cierre automáticamente.
        try(OutputStreamWriter escritura = new OutputStreamWriter(openFileOutput(file.getName(), Context.MODE_PRIVATE))){
            //se escribe el número secreto en el fichero, borrando el contenido anterior
            escritura.write(String.valueOf(numeroSecreto));
            //Se guarda el contenido del fichero
            escritura.flush();
            //Se muestra un mensaje de confirmación
            Toast.makeText(this, "Número secreto guardado correctamente", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            //Si ocurre un error se muestra un mensaje de error
            Toast.makeText(this, "Error al guardar el número secreto", Toast.LENGTH_SHORT).show();
            //Y se finaliza la actividad.
            finish();
        }

    }
}