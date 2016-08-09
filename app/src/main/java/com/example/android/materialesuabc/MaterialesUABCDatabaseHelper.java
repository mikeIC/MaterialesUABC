package com.example.android.materialesuabc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mike on 2/08/16.
 */
public class MaterialesUABCDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MaterialesUABC.db"; //El nombre de la base de datos
    private static final int DB_VERSION = 6; // La version de la base de datos


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

        }

        if(oldVersion>=4 ){

            insertUsuario(db,"Erik","Macias",1);
            insertUsuario(db,"Mike","Montiel",1);
            insertUsuario(db,"Rigo","Nava",1);
        }

        if(oldVersion>=5 ){

            insertUsuario(db,"Daniel","nop",1);
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
