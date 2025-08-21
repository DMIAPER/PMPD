package com.example.marsroverphotos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Se declaran las variables.
    private EditText etApiKey;
    private NumberPicker numberPicker;
    private ImageView ivRoverNasa;
    private Button cargarApi;
    // lista que almacenará las fotos
    private List<Photo> listaFotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Se inicializan las variables
        etApiKey = findViewById(R.id.etApiKey);
        numberPicker = findViewById(R.id.numberPicker);
        ivRoverNasa = findViewById(R.id.ivRoverNasa);
        cargarApi = findViewById(R.id.btCargaApi);

        // Configuramos el NumberPicker
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(0);

        //etApiKey.setText("5WIrADWBg2dmLiQpDcEacbzvpkBVffoZCXB2eB6L");

        // Acción al pulsar el botón de cargar API
        cargarApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Si hay un texto se cargan las fotos
                if(!etApiKey.getText().toString().trim().isEmpty()){
                    cargarFotosDeNASA();
                }else{
                    // Si no se informa
                    Toast.makeText(MainActivity.this, "Introduce tu API KEY", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Acción al cambiar el valor seleccionado en el NumberPicker
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            // Si la lista de fotos no está vacía, se carga la imagen correspondiente
            // el valor seleccionado en el NumberPicker es menor al número de fotos disponibles
            if (listaFotos != null && newVal < listaFotos.size()) {
                // Se obtiene la URL de la imagen correspondiente
                String url = listaFotos.get(newVal).getImgSrc();
                // Se carga la imagen mediatne Picasso
                Picasso.get().load(url).into(ivRoverNasa);
            }
        });

        // Si hay un API Key guardado, se carga automáticamente
        if(!etApiKey.getText().toString().trim().isEmpty()) {
            // Se lanza la carga automática de imagenes al inicar la app
            cargarFotosDeNASA();
        }else{
            Toast.makeText(MainActivity.this, "Introduce tu API KEY", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarFotosDeNASA() {
        // Se obtiene la api key del campo de texto
        String apiKey = etApiKey.getText().toString().trim();
        // Se crea una instancia de Retrofit
        ApiServices apiService = RetrofitClient.getClient().create(ApiServices.class);
        // Se realiza la llamada a la API
        Call<CuriosityResponse> call = apiService.getMarsPhotos(1000, apiKey);
        // Se procesa la respuesta
        call.enqueue(new Callback<CuriosityResponse>() {
            @Override
            public void onResponse(Call<CuriosityResponse> call, Response<CuriosityResponse> response) {
                //  Si response es correcto se obtiene la lista de fotos
                if (response.isSuccessful() && response.body() != null) {
                    // Se obtiene la lista de fotos
                    listaFotos = response.body().getPhotos();
                    // Si no hay fotos se informa
                    if (listaFotos == null || listaFotos.isEmpty()) {
                        Toast.makeText(MainActivity.this, "No hay imágenes disponibles para este sol", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // Se actualizará el límite del numberPicker
                    numberPicker.setMaxValue(listaFotos.size() - 1);
                    // Se mostrará la primera imagen
                    String url = listaFotos.get(0).getImgSrc().replaceFirst("http", "https");
                    // Cargar la primera imagen
                    Picasso.get().load(url).into(ivRoverNasa);
                // Si no se puede acceder a la API se informa
                } else {
                    // Se informa del error 403
                    if (response.code() == 403) {
                        Toast.makeText(MainActivity.this, "Error 403: Acceso prohibido. Verifica tu API KEY.", Toast.LENGTH_LONG).show();
                    // En caso de otro error se informa
                    } else {
                        Toast.makeText(MainActivity.this, "Respuesta inválida: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            // Si falla la conexión se informa
            @Override
            public void onFailure(Call<CuriosityResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al conectar con la API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Retrofit", "Fallo en la llamada", t);
            }
        });
    }
}