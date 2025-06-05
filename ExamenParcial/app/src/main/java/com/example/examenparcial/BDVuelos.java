package com.example.examenparcial;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BDVuelos extends SQLiteOpenHelper {

    Context contexto;

    static String createBDSQL ="CREATE TABLE vuelos (id integer primary key autoincrement, origen text, destino text, cantidad integer)";


    public BDVuelos(Context context){
        super(context.getApplicationContext(),"BDVuelos",null,1 );
        contexto=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(createBDSQL);
        }catch (SQLException e){
            Toast.makeText(contexto, "Error al crear la base de datos "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vuelos");
            onCreate((sqLiteDatabase));
        }catch (SQLException e){
            Toast.makeText(contexto, "Error al actualizar la base de datos "+e,Toast.LENGTH_SHORT).show();
        }
    }

    public void insertContacto(String origen, String destino, int numpax){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO vuelos (origen, destino, cantidad) VALUES ('" + origen + "', '" + destino + "', '" + numpax + "')");
        db.close();
    }

    public Cursor consultarVuelos(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM vuelos", null);
    }

}
