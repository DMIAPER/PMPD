package com.example.examenparcial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Adaptador de ListaView
 */
public abstract class Adaptador extends BaseAdapter {

    private ArrayList<?> entradas; //Se instancia un ArrayList sin indicar el tipo de objeto
    private int layout; //Se instancia un entero que contendrá el layout de la entrada
    private Context contexto; //Se instancia un objeto Context

    /**
     * Constructor de la clase Apadtador
     * @param contexto se recibe el contexto
     * @param layout se recibe el layout
     * @param entradas se reciben las entradas de los libros
     */
    public Adaptador(Context contexto, int layout, ArrayList<?> entradas){
        super();
        this.contexto = contexto;
        this.layout = layout;
        this.entradas = entradas;
    }

    //Se modifica el constructor de la clase Apadtador
    @Override
    public View getView(int posicion, View view, ViewGroup parent){
        //Se comprueba si la vista es nula
        if(view == null){
            //Se instancia un objeto LayoutInflater
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Se pinta la vista
            view = inflater.inflate(layout, null);
        }
        //Se llama al método onEntrada
        onEntrada(entradas.get(posicion), view);
        //Se devuelve la vista
        return view;
    }

    //Se modifican los métodos de la clase BaseAdapter
    @Override
    public int getCount(){
        return entradas.size();
    }

    @Override
    public Object getItem(int posicion){
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion){
        return posicion;
    }

    /**
     * Se devuelve cada una de las entradas de la lista
     * @param entrada se recibe la entrada que se mostrará
     * @param view se recibe el view que contendrá los datos
     */
    public abstract void onEntrada(Object entrada, View view);
}
