package com.example.android.materialesuabc;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PerfilActivity extends AppCompatActivity {

    public static final int REQUEST_CAMERA = 0;
    public static final int SELECT_FILE = 1;
    public static final int EDITAR_PERFIL_REQUEST_CODE = 1;
    private ShareActionProvider shareActionProvider;
    private TextView nombre;
    private TextView apellido;
    private Bitmap imagen_bit;
    private ImageView imagen_perfil;

    public PerfilActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome_webfont.ttf");

        nombre = (TextView) findViewById(R.id.user_nombre);
        apellido = (TextView) findViewById(R.id.user_apellido);
        imagen_perfil = (ImageView) findViewById(R.id.imagen_perfil);
//        Bitmap imagen_bit = ((BitmapDrawable)  imagen_perfil.getDrawable()).getBitmap();

//        if (savedInstanceState!= null)
//            imagen_perfil = savedInstanceState.getParcelable("bitmap");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.person);


        //CREATE cursor
        try {
            SQLiteOpenHelper materialesUABCDatabaseHelper = new MaterialesUABCDatabaseHelper(this);
            SQLiteDatabase db = materialesUABCDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query("USUARIO", new String[]{"NAME", "LASTNAME", "IMAGE_RESOURCE_ID"},
                    "_id = ?", new String[]{Integer.toString(1)}, null, null, null);
            //move to first record in cursor
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String apellidoText = cursor.getString(1);
                int userImg = cursor.getInt(2);

                nombre.setText(nameText);
                apellido.setText(apellidoText);
                //imagen_perfil.setImageResource(userImg);
            } else {
                nombre.setText(getResources().getText(R.string.vacio));
                apellido.setText(getResources().getText(R.string.vacio));
                imagen_perfil.setImageResource(R.mipmap.ic_launcher);
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast.makeText(this, "Database unavailable: " + e, Toast.LENGTH_LONG).show();
        }

    }

//    @Override
//    public void onSaveInstanceState(Bundle toSave) {
//        super.onSaveInstanceState(toSave);
//        toSave.putParcelable("bitmap",imagen_bit);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
//        MenuItem menuItem = menu.findItem(R.id.action_edit);
//        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        return super.onCreateOptionsMenu(menu);
        //
        //
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(PerfilActivity.this,PerfilEditarActivity.class);
        startActivity(intent);

//        // handle item selection
//        switch (item.getItemId()) {
//            case R.id.action_edit:
//                 do
//                Toast.makeText(PerfilActivity.this, "Hi", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
                return super.onOptionsItemSelected(item);
    }
}




