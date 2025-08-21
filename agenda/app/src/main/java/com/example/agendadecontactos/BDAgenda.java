/***
 * Clase BDAgenda que hereda de SQLiteOpenHelper
 * En esta clase se crearán lo métodos que permite interactuar con lo base de datos.
 *
 * - Se intanciará un string para crear la tabla agenda
 * - Se implementarán los métodos onCreate y onUpgrade
 * - Se implementarán los métodos para insertar, consultar, modificar, borrar contactos y
 *   consultar un contaco en concreto
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * Version: 1.0.0
 * Fecha: 2503/2025
 */
package com.example.agendadecontactos;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDAgenda extends SQLiteOpenHelper {
    //Se instancia un string para crear la tabla agenda
    String crearTabla = "CREATE TABLE agenda(NUM_CONTACTO INTEGER PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT, TELEFONO TEXT, CORREO TEXT)";

    //Constructor de la clase BDAgenda
    public BDAgenda (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    // Método para crear la tabla
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(crearTabla);
    }

    // Método para actualizar la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(crearTabla);
    }


    /***
     * Método para insertar un contacto en la tabla agenda
     * @param nombre se recibe el nombre del contacto
     * @param apellidos se recibe los apellidos del contacto
     * @param telefono se recibe el teléfono del contacto
     * @param email se recibe el email del contacto
     */
    public void insertContacto(String nombre, String apellidos, String telefono, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO agenda (NOMBRE, APELLIDO, TELEFONO, CORREO) VALUES ('" + nombre + "', '" + apellidos + "', '" + telefono + "', '" + email+"') ");
        db.close();
    }

    /***
     * Método para consultar todos los contactos de la tabla agenda
     * @return se devuelven todos lo registro de la tabla agenda
     */
    public Cursor consultarContactos(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM agenda", null);
    }

    /***
     * Método para borrar un contacto de la tabla agenda
     * @param id se recibe como parámetro el id del contacto
     */
    public void borrarContacto(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM agenda WHERE NUM_CONTACTO = '" + id + "'");
        db.close();
    }

    /***
     * Método para modificar un contacto de la tabla agenda
     * @param id se recibe el  id del contacto
     * @param nombre se recibe el nombre del conctacto
     * @param apellidos se recibe el apellido del contacto
     * @param telefono se recibe el teléfono del contacto
     * @param email se recibe el email del contacto
     */
    public void modificarContacto(String id, String nombre, String apellidos, String telefono, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE agenda SET NOMBRE = ?, APELLIDO = ?, TELEFONO = ?, CORREO = ? WHERE NUM_CONTACTO = ?";
        db.execSQL(query, new Object[]{nombre, apellidos, telefono, email, id});
        db.close();
    }

    /***
     * Método para consultar un contacto en concreto de la tabla agenda
     * @param id se recibe el id del contacto
     * @return se devuelve el registro del contacto
     */
    public Cursor consultarContacto(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM agenda WHERE NUM_CONTACTO = '" + id + "'", null);
    }

}
