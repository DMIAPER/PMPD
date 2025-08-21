/**
 * Curiosityphoto.java
 * ------------------
 * Esta clse representa la respuesta principal del servicio de la NASA.
 * Contiene una lista de objetos Photo (cada uno es una imagen del rover).
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * @since 22/05/2025
 */

package com.example.marsroverphotos;

import java.util.List;

public class CuriosityResponse {
    // se instancia la lista de fotos
    private List<Photo> photos;

    // se devuelven los datos de la lista de fotos
    public List<Photo> getPhotos() {
        return photos;
    }

    // Se establece la lista de fotos
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
