package com.example.examenparcial;
public class Vuelo {

    private int id;
    private String origen;
    private String destino;
    private int cantidad;

    public Vuelo(){

    }

    public Vuelo(int id, String origen, String destino, int cantidad) {
        this.id=id;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }



    public String toString(){
        return ("Origen:"+this.origen+"\n"+"Destino:"+this.destino+"\n"+"Nª Pasajeros:"+this.cantidad);
    }

}

