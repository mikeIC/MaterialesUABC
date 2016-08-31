package com.example.android.materialesuabc;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment{
    private SQLiteDatabase db;
    private Cursor cursor;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        ListView listHistorial = (ListView) view.findViewById(R.id.listview_historial);
        try{
            SQLiteOpenHelper databaseHelper = new MaterialesUABCDatabaseHelper(getActivity());
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("RESULTADOS", new String[]{"_id","ERRORES ","ACIERTOS","TIME"},null,null,null,null,null);
            Log.d("PerfilFragment","Cursor created ");


            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.custom_list_item,
                            cursor,
                            new String[]{"ERRORES","ACIERTOS","TIME"},
                            new int[]{R.id.list_text1,R.id.list_text2,R.id.list_text3}, 0);
            Log.d("PerfilFragment","Cursor Adapter Created ");
            listHistorial.setAdapter(cursorAdapter);
            } catch(SQLiteException e) {
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
    }
    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void onResume() {
        super.onResume();
        Log.d("PerfilFragment","Resumed Fragment");

        try{
            MaterialesUABCDatabaseHelper starbuzzDatabaseHelper = new MaterialesUABCDatabaseHelper(getActivity());
            db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor newCursor =  db.query("RESULTADOS", new String[]{"_id", "MATERIA","UNIDAD","ERRORES ","ACIERTOS","TIME" ,"FECHA"},null,null,null,null,null);

            View view = getView();
            ListView listHistorial = (ListView)view.findViewById(R.id.listview_historial);

            CursorAdapter adapter = (CursorAdapter) listHistorial.getAdapter();
            adapter.changeCursor(newCursor);
            cursor = newCursor;

        } catch(SQLiteException e) {

            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}
