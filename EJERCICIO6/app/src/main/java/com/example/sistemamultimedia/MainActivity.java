/***
 * Se crea una aplicación para reproducir un video y un audio.
 *
 * En esta aplicación habrá un videoView que reproducirá un video desde el directorio raw.
 *
 * Se han creado en el layout cuatro botones quer permitirán interactuar con un mediaplayer,
 * y se reproducirá un mp3. Se podrá pausar y continuar desde el punto en el que se quedó, o parar.
 * También se se controlará que no se reproduzcan varios sonidos al mismo tiempo.
 *
 * @author (DMIAPER) Diógenes Miaja Pérez
 * version: 1.0.0
 * fecha: 20/03/2025
 */

package com.example.sistemamultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

/***
 * Se extiende la clase a AppCompactActivity y se implementa un listener para los botones.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // Declaración de variables
    MediaPlayer mp; // MediaPlayer para el audio
    VideoView vV; // VideoView para el video
    MediaController mc; // Controlador de media para el video

    /***
     * Contructor de la clase.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llamada al constructor de la clase padre
        setContentView(R.layout.activity_main); // Establece el layout de la actividad
        vV = findViewById(R.id.videoView); // Obtiene la referencia al VideoView

        // Se configuran los botones y les asigna un listener
        Button btnPlay = findViewById(R.id.btn_play);
        Button btnStop = findViewById(R.id.btn_stop);
        Button btnContinue = findViewById(R.id.btn_continue);
        Button btnPause = findViewById(R.id.btn_pause);
        // Se configuran los listeners
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        //Se llama al método que construye el video
        establecerVideo();
    }

    /***
     * Método que construye el video.
     */
    public void establecerVideo(){
        // Se crea la ruta del video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_doblaje;
        // Se convierta a la ruta a un tipo URI
        Uri uri = Uri.parse(videoPath);
        // Se crea el controlador de media
        mc = new MediaController(this);
        // Se indica el anchor view, esto sirve para que el controlador se adapte al video
        mc.setAnchorView(vV);
        // Se indica que el controlador se adapte al video
        vV.setMediaController(mc);
        // Se indica la ruta del video
        vV.setVideoURI(uri);
        // Se le agrega un foco para poder interactuar mejor con el video.
        vV.requestFocus();

        /***
         * Se crea un listener para el video. Cuando este preparado se
         * reproduce el video, de este modo se evitan errores.
         */
        vV.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            /***
             * Método que se ejecuta cuando el video está preparado.
             * @param mp
             */
            @Override
            public void onPrepared(MediaPlayer mp){
                // Se establece un loop para que se repita el video.
                mp.setLooping(true);
                // Se controlan los errores
                try{
                    //Se ejecuta el video
                    vV.start();
                // En caso de error
                }catch (Exception e){
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    /***
     * Método que se ejecuta cuando se hace clic sobre los botones para reproducir el audio.
     * @param v
     */
    @Override
    public void onClick(View v) {
        // No se puede usar un switch, ya que los valores de los botones son dinámicos.
        // Se controla que botón se ha pulsado
        if (v.getId() == R.id.btn_play) {
            // Si mp es null, se crea el MediaPlayer y se reproduce el audio
            if (mp == null) {
                // Se crea el MediaPlayer y se reproduce el audio
                mp = MediaPlayer.create(this, R.raw.la_ia_peor_progamador);
                // Se reproduce el audio
                mp.start();
            // Si mp no es null, se controla si está pausado o no
            } else if (!mp.isPlaying()) {
                // Si está pausado, se reproduce el audio
                mp.start();
            // si se esta reproducciendo, se muestra un toast.
            } else {
                Toast.makeText(getApplicationContext(), "Reproduciendo, finalice primero el sonido anterior", Toast.LENGTH_SHORT).show();
            }
        // Si se ha pulsado el botón de parar, se para el audio
        } else if (v.getId() == R.id.btn_stop) {
            // Si mp no es null, se para el audio y se libera el recurso
            if (mp != null) {
                mp.stop();
                mp.release();
                mp = null;
            }
        // Si se ha pulsado el botón de continuar, se continua el audio
        } else if (v.getId() == R.id.btn_continue) {
            // Si mp no es null y está pausado, se reproduce el audio
            if (mp != null && !mp.isPlaying()) {
                mp.start();
            }
        // Si se ha pulsado el botón de pausar, se pausa el audio
        } else if (v.getId() == R.id.btn_pause) {
            // Si mp no es null y está reproduciendo, se pausa el audio
            if (mp != null && mp.isPlaying()) {
                mp.pause();
            }
        }
    }

    //Métodos para finalizar los recusos.

    /***
     * Método que se ejecuta cuando la actividad se pausa.
     */
    @Override
    protected void onPause() {
        // Llamada al método de la clase padre
        super.onPause();
        // Se controlan los errores
        try {
            // Si vV no es null, se pausa el video
            if (vV != null) {
                vV.pause();
            }
            // Si mp no es null y está reproduciendo, se pausa el audio
            if (mp != null && mp.isPlaying()) {
                mp.pause(); // Pausa el audio
            }
        // Se mustra el error.
        } catch (Exception e) {
            Log.e("ERROR", "Error en onPause(): " + e.getMessage(), e);
        }
    }

    /***
     * Método que se ejecuta cuando la actividad se reanuda.
     */
    @Override
    protected void onStop() {
        // Se llama al método de la clase padre
        super.onStop();
        // Se controlan lo errores
        try {
            // Si el video es distinto a null se detiene la reproducción del video
            if (vV != null) {
                vV.stopPlayback();
            }
            // Si el audio es distinto a null se detiene la reproducción del audio y se libera el recurso
            if (mp != null) {
                mp.stop();
                mp.release();
                mp = null;
            }
        // En caso de error se muestra el mensaje
        } catch (Exception e) {
            Log.e("ERROR", "Error en onStop(): " + e.getMessage(), e);
        }
    }

    /***
     * Método que se ejecuta cuando la actividad se destruye.
     */
    @Override
    protected void onDestroy() {
        // se llama al método de la clase padre
        super.onDestroy();
        // Se controlan los errores
        try {
            // Si el video es distinto a null se detiene la reproducción del video y se elimina la referencia
            if (vV != null) {
                vV.stopPlayback();
                vV = null;
            }
            // Si el audio es distinto a null se detiene la reproducción del audio y se libera el recurso
            if (mp != null) {
                mp.stop();
                mp.release();
                mp = null;
            }
        // En caso de error se muestra el mensaje
        } catch (Exception e) {
            Log.e("ERROR", "Error en onDestroy(): " + e.getMessage(), e);
        }
    }

}