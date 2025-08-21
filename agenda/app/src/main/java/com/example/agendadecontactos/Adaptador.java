/***
 * Clase para contruir la lista con los regitros de la tabla.
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * fecha: 21/03/2025
 */
package com.example.agendadecontactos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        this.contexto = contexto;
        this.layout = layout;
        this.entradas = entradas;
    }

    //Se modifica el constructor de la clase Apadtador
    @Override
    public View getView(int posicion, View view, ViewGroup parent){
        ViewHolder viewHolder;

        //Se comprueba si la vista es nula
        if(view == null){
            //Se instancia un objeto LayoutInflater
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(layout, parent, false);

            // Se inicializan los ViewHolders
            viewHolder = new ViewHolder();
            viewHolder.id = view.findViewById(R.id.id);
            viewHolder.nombreApellido = view.findViewById(R.id.nombreApellido);
            viewHolder.telefono = view.findViewById(R.id.telefono);
            viewHolder.email = view.findViewById(R.id.email);

            // Se asigna la vista al objeto view
            view.setTag(viewHolder);
        }else{
            // Se obtiene el ViewHolder de la vista existente
            viewHolder = (ViewHolder) view.getTag();
        }
        //Se llama al método onEntrada
        onEntrada(entradas.get(posicion), viewHolder);
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
     * @param holder se recibe el view que contendrá los datos
     */
    public abstract void onEntrada(Object entrada, ViewHolder holder);

    // Clase ViewHolder para almacenar las referencias a las vistas
    public static class ViewHolder {
        public TextView id;
        public TextView nombreApellido;
        public TextView telefono;
        public TextView email;
    }
}
