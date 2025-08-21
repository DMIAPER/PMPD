package com.example.marsroverphotos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    /**
     * Obtiene las fotos del rover Curiosity en función del sol (día marciano) y la API key del usuario.
     *
     * @param sol Día marciano (por ejemplo, 1000).
     * @param apiKey Clave API personal de la NASA (debe ser proporcionada por el usuario).
     * @return Un Call que devuelve un objeto CuriosityResponse (la lista de fotos).
     */
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    Call<CuriosityResponse> getMarsPhotos(
            @Query("sol") int sol,
            @Query("api_key") String apiKey
    );

}
