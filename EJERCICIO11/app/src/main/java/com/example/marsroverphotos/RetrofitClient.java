package com.example.marsroverphotos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // URL base del servicio de la NASA
    private static final String BASE_URL = "https://api.nasa.gov/";

    // Instancia única de Retrofit
    private static Retrofit retrofit = null;

    // Método para obtener una instancia de Retrofit
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Establece la URL base
                    .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos Java
                    .build();
        }
        return retrofit;
    }

}
