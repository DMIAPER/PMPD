/***
 * Clase que representa al objeto Contacto.
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * fecha: 21/03/2025
 */
package com.example.agendadecontactos;

/**
 * Clase que contiene los atributos para contruir un contacto.
 */
public class Contacto {
    private final String id;
    private final String nombre;
    private final String apellidos;
    private final String telefono;
    private final String email;

    /*
     * Constructor de la clase Contacto.
     */
    public Contacto(String id, String nombre, String apellidos, String telefono, String email){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    /*
     * Métodos para obtener los atributos de la clase Contacto.
     */
    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getEmail(){
        return email;
    }

}
