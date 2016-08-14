package com.example.android.materialesuabc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MaterialesUABCDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MaterialesUABC"; //El nombre de la base de datos
    private static final int DB_VERSION = 9; // La version de la base de datos


    public MaterialesUABCDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db,0,DB_VERSION);


    }
    private void updateMyDataBase(SQLiteDatabase db, int oldVersion, int newVersion){

        if(oldVersion<=7){
            db.execSQL("DROP TABLE USUARIO");
            db.execSQL("DROP TABLE PREGUNTA");

            db.execSQL("CREATE TABLE USUARIO(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "LASTNAME TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER);");
            db.execSQL("CREATE TABLE PREGUNTA(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "MATERIA TEXT," +
                    "UNIDAD TEXT," +
                    "PREGUNTA TEXT," +
                    "RESPUESTA TEXT);");

            insertPregunta(db,"Materia1","Unidad1","pregunta1","respuesta1");
            insertPregunta(db,"Materia1","Unidad1","pregunta2","respuesta2");
            insertPregunta(db,"Materia1","Unidad1","pregunta3","respuesta3");
            insertPregunta(db,"Materia1","Unidad1","pregunta4","respuesta4");
            insertPregunta(db,"Materia1","Unidad1","pregunta5","respuesta5");

            insertPregunta(db,"Materia1","Unidad2","pregunta1","respuesta1");
            insertPregunta(db,"Materia1","Unidad2","pregunta2","respuesta2");
            insertPregunta(db,"Materia1","Unidad2","pregunta3","respuesta3");
            insertPregunta(db,"Materia1","Unidad2","pregunta4","respuesta4");
            insertPregunta(db,"Materia1","Unidad2","pregunta5","respuesta5");

            insertPregunta(db,"Materia1","Unidad3","pregunta1","respuesta1");
            insertPregunta(db,"Materia1","Unidad3","pregunta2","respuesta2");
            insertPregunta(db,"Materia1","Unidad3","pregunta3","respuesta3");
            insertPregunta(db,"Materia1","Unidad3","pregunta4","respuesta4");
            insertPregunta(db,"Materia1","Unidad3","pregunta5","respuesta5");

        }
        if(oldVersion <=8){
            //***************************************INICIO PREGUNTAS UNIDAD 2
            insertPregunta(db,"Materia2","Unidad1","pregunta1","respuesta1");
            insertPregunta(db,"Materia2","Unidad1","pregunta2","respuesta2");
            insertPregunta(db,"Materia2","Unidad1","pregunta3","respuesta3");
            insertPregunta(db,"Materia2","Unidad1","pregunta4","respuesta4");
            insertPregunta(db,"Materia2","Unidad1","pregunta5","respuesta5");

            insertPregunta(db,"Materia2","Unidad2","pregunta1","respuesta1");
            insertPregunta(db,"Materia2","Unidad2","pregunta2","respuesta2");
            insertPregunta(db,"Materia2","Unidad2","pregunta3","respuesta3");
            insertPregunta(db,"Materia2","Unidad2","pregunta4","respuesta4");
            insertPregunta(db,"Materia2","Unidad2","pregunta5","respuesta5");

            insertPregunta(db,"Materia2","Unidad3","pregunta1","respuesta1");
            insertPregunta(db,"Materia2","Unidad3","pregunta2","respuesta2");
            insertPregunta(db,"Materia2","Unidad3","pregunta3","respuesta3");
            insertPregunta(db,"Materia2","Unidad3","pregunta4","respuesta4");
            insertPregunta(db,"Materia2","Unidad3","pregunta5","respuesta5");
            //******************************************FIN PREGUNTAS UNIDAD 2

            //***************************************INICIO PREGUNTAS UNIDAD 3
            insertPregunta(db,"Materia3","Unidad1","pregunta1","respuesta1");
            insertPregunta(db,"Materia3","Unidad1","pregunta2","respuesta2");
            insertPregunta(db,"Materia3","Unidad1","pregunta3","respuesta3");
            insertPregunta(db,"Materia3","Unidad1","pregunta4","respuesta4");
            insertPregunta(db,"Materia3","Unidad1","pregunta5","respuesta5");

            insertPregunta(db,"Materia3","Unidad2","pregunta1","respuesta1");
            insertPregunta(db,"Materia3","Unidad2","pregunta2","respuesta2");
            insertPregunta(db,"Materia3","Unidad2","pregunta3","respuesta3");
            insertPregunta(db,"Materia3","Unidad2","pregunta4","respuesta4");
            insertPregunta(db,"Materia3","Unidad2","pregunta5","respuesta5");

            insertPregunta(db,"Materia3","Unidad3","pregunta1","respuesta1");
            insertPregunta(db,"Materia3","Unidad3","pregunta2","respuesta2");
            insertPregunta(db,"Materia3","Unidad3","pregunta3","respuesta3");
            insertPregunta(db,"Materia3","Unidad3","pregunta4","respuesta4");
            insertPregunta(db,"Materia3","Unidad3","pregunta5","respuesta5");
            //******************************************FIN PREGUNTAS UNIDAD 3
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
    public void insertPregunta(SQLiteDatabase db,String materia, String unidad, String pregunta, String respuesta) {
        ContentValues preguntaValues = new ContentValues();
        preguntaValues.put("MATERIA",materia);
        preguntaValues.put("UNIDAD", unidad);
        preguntaValues.put("PREGUNTA", pregunta);
        preguntaValues.put("RESPUESTA", respuesta);
        db.insert("PREGUNTA", null, preguntaValues);
    }
}
