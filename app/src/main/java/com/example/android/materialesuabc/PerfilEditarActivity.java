package com.example.android.materialesuabc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilEditarActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    ImageView imagen;
    String mCurrentPhotoPath;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 2;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_editar);
        nombre  = (EditText)findViewById(R.id.user_editar_nombre);
        apellido  = (EditText)findViewById(R.id.user_editar_apellido);
        imagen = (ImageView) findViewById(R.id.imagen_perfil);

        if(savedInstanceState != null){
            nombre.setText(savedInstanceState.getString("nombre"));
            apellido.setText(savedInstanceState.getString("apellido"));
        }
    }

    public void botonEditarPerfilClick(View view) {

//        if(view.getId() == R.id.button_borrar_historial){
//
//        }
        if(view.getId() == R.id.picture_btn){
            dispatchTakePictureIntent();
        }
        if(view.getId() == R.id.save_btn){

            SQLiteOpenHelper materialesUABCDatabaseHelper  = new MaterialesUABCDatabaseHelper(PerfilEditarActivity.this);
            ContentValues usuarioValues = new ContentValues();
            usuarioValues.put("NAME",nombre.getText().toString());
            usuarioValues.put("LASTNAME",apellido.getText().toString());

            try{
                SQLiteDatabase db = materialesUABCDatabaseHelper.getWritableDatabase();
                db.update("USUARIO",usuarioValues, "_id =?",new String[]{Integer.toString(1)});
                db.close();

            }catch (SQLiteException e){
                Toast toast = Toast.makeText(this, "Database unavailable: "+e, Toast.LENGTH_SHORT);
                toast.show();
            }

            finish();
        }
        if(view.getId() == R.id.cancel_btn){
            finish();
        }
    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("nombre",nombre.getText().toString());
        savedInstanceState.putString("apellido",apellido.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        }
    }
}
