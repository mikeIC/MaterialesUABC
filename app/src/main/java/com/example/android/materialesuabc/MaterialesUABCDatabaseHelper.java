package com.example.android.materialesuabc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mike on 2/08/16.
 */
public class MaterialesUABCDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MaterialesUABC"; //El nombre de la base de datos
    private static final int DB_VERSION = 4; // La version de la base de datos


    public MaterialesUABCDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db,0,DB_VERSION);


    }
    private void updateMyDataBase(SQLiteDatabase db, int oldVersion, int newVersion){

        if(oldVersion< 4){
//            db.execSQL("DROP TABLE USUARIO");
//            db.execSQL("DROP TABLE PREGUNTA");

            db.execSQL("CREATE TABLE USUARIO(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "LASTNAME TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER);");
            db.execSQL("CREATE TABLE PREGUNTA(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "PREGUNTA TEXT," +
                    "RESPUESTA TEXT);");


//            insertPregunta(db,"Unidad1","pregunta1","hola");
//            insertUsuario(db,"mike","montiel",R.mipmap.ic_launcher);
//            insert
//            insertTienda(db,"Oxxo","Ave.Revolucion",R.drawable.oxxo_logo);
//            insertTienda(db,"7Eleven","Ave.Constitucion",R.drawable.eleven_logo);
//
//            insertProducto(db,"Arroz","Oxxo",8,0,R.drawable.arrozpic);
//            insertProducto(db,"Leche","Oxxo",15,0,R.drawable.leche);
//            insertProducto(db,"Frijoles","Oxxo",6,0,R.drawable.frijoles);
//            insertProducto(db,"Jamon","Oxxo",66,0,R.drawable.jamon);
//            insertProducto(db,"Salchicha","Oxxo",26,0,R.drawable.salchicha);
//
//            insertProducto(db,"Arroz","Calimax",16,0,R.drawable.arrozpic);
//            insertProducto(db,"Leche","Calimax",16,0,R.drawable.leche);
//            insertProducto(db,"Frijoles","Calimax",16,0,R.drawable.frijoles);
//            insertProducto(db,"Jamon","Calimax",16,0,R.drawable.jamon);
//            insertProducto(db,"Salchicha","Calimax",16,0,R.drawable.salchicha);
//
//            insertProducto(db,"Arroz","7Eleven",18,0,R.drawable.arrozpic);
//            insertProducto(db,"Leche","Eleven",10,0,R.drawable.leche);
//            insertProducto(db,"Frijoles","7Eleven",14,0,R.drawable.frijoles);
//            insertProducto(db,"Jamon","7Eleven",16,0,R.drawable.jamon);
//            insertProducto(db,"Salchicha","7Eleven",17,0,R.drawable.salchicha);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDataBase(db,oldVersion,newVersion);
    }

    public void insertUsuario(SQLiteDatabase db, String nombre, String apellido, int resourceId){
        ContentValues usuarioValues = new ContentValues();
        usuarioValues.put("NAME",nombre);
        usuarioValues.put("LASTNAME",apellido);
        usuarioValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("USUARIO",null,usuarioValues);
    }
    public void insertPregunta(SQLiteDatabase db, String name, String pregunta, String respuesta) {
        ContentValues preguntaValues = new ContentValues();
        preguntaValues.put("NAME", name);
        preguntaValues.put("PREGUNTA", pregunta);
        preguntaValues.put("RESPUESTA", respuesta);
        db.insert("PREGUNTA", null, preguntaValues);
    }
}
