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
import android.widget.Toast;

public class Navegador extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navegador, container, false);
    }

    /*
     * Método que da funcionalidad al botón "Ir"
     *
     * Cuando se pulsa el botón se obtiene la url introducida en el EditText
     * Se comprueba si se ha introducido una url y si no se muestra un Toast
     * Se comprueba si la url contiene el esquema http o https si no lo contiene se le añade
     *
     * Se crea un objeto WebView y se le asigna la url
     * Si se ha introducido una url se carga la web.
     *
     */

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Se crea un objeto Button
        Button btnIr = view.findViewById(R.id.btnNav);
        // Se crea un objeto OnClickListener
        btnIr.setOnClickListener(new View.OnClickListener() {
            // Cuando se presione el botón se cargará la web.
            @Override
            public void onClick(View v) {
                // Se crea un objeto EditText
                EditText etUri = view.findViewById(R.id.etUri);
                // Se obtiene la url introducida en el EditText
                String url = etUri.getText().toString();
                // Se crea un objeto WebView
                WebView wvUrl = view.findViewById(R.id.wvUrl);
                // Se habilita el JavaScript
                wvUrl.getSettings().setJavaScriptEnabled(true);
                // Se comprueba si se ha introducido una url
                if (url.isEmpty()) {
                    // Se muestra un Toast si no se ha introducido una url
                    Toast.makeText(getContext(), "La URL no puede estar vacía", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.WEB_URL.matcher(url).matches()){
                    // Se muestra un Toast si la url no es válida
                    Toast.makeText(getContext(), "La URL no es válida", Toast.LENGTH_SHORT).show();
                }else{
                    // Se comprueba si la url contiene el esquema http o https si no lo contiene se le añade
                    url = asignarHttps(url);
                    // Se carga la web
                    wvUrl.loadUrl(url);
                }
            }
        });
    }

    /**
     * Método para devolver una URL con el esquema HTTPS si no lo tiene
     * @param url, se recibe la URL sin procesar
     * @return se devuelve la URL procesada
     */
    public String asignarHttps(String url) {
        // Si la url no contiene esquema, se usa HTTPS
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            // Si no tiene ningún esquema, asume HTTPS
            return "https://" + url;
        }
        // Se devuelve la url con el esquema HTTPS
        return url;
    }

}