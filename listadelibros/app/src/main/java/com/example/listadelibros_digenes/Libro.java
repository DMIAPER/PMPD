package com.example.listadelibros_digenes;

/**
 * Clase que construye los objetos de tipo libro, con sus atributos
 */
public class Libro {
    private final String titulo; //se crea una variable de tipo string, que contendrá el titulo del libro
    private final String autor; //se crea una variable de tipo string, que contendrá el autor del libro
    private final int imagen; //se crea una variable de tipo int, que contendrá la imagen del libro
    private final double precio; //se crea una variable de tipo double, que contendrá el precio del libro

    /**
     * Constructor de la clase libros
     * @param titulo se recibe el título del libro
     * @param autor se recibe el autor del libro
     * @param imagen se recibe la imagen del libro
     * @param precio se recibe el precio del libro
     */
    public Libro(String titulo, String autor, int imagen, double precio){
        this.titulo = titulo;
        this.autor = autor;
        this.imagen = imagen;
        this.precio = precio;
    }

    //GETTERS
    //Método que devuelve el título del libro
    public String getTitulo(){
        return titulo;
    }

    //Método que devuelve el autor del libro
    public String getAutor(){
        return autor;
    }

    //Método que devuelve la imagen del libro
    public int getImagen(){
        return imagen;
    }

    //Método que devuelve el precio del libro
    public double getPrecio(){
        return precio;
    }
}
