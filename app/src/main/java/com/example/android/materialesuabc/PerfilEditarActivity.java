package com.example.android.materialesuabc;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilEditarActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    ImageView imagen_perfil;
    TextView texto;
    String mCurrentPhotoPath;
    String userChoosenTask;
    Bitmap thumbnail;
    File photoFile = null;

    public static final int REQUEST_CAMERA = 0;
    public static final int SELECT_FILE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 2;
//    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;
    private Button boton_guardar;
    private Button boton_cancelar;
    private Button boton_imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome_webfont.ttf");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_perfil_editar);
        nombre  = (EditText)findViewById(R.id.user_editar_nombre);
        apellido  = (EditText)findViewById(R.id.user_editar_apellido);
        imagen_perfil = (ImageView) findViewById(R.id.imagen_perfil);
        texto = (TextView) findViewById(R.id.textPhoto);
        boton_guardar = (Button) findViewById(R.id.save_btn);
        boton_cancelar = (Button) findViewById(R.id.cancel_btn);
        boton_imagen = (Button) findViewById(R.id.btnSelectPhoto);

        boton_imagen.setTypeface(font);
        boton_guardar.setTypeface(font);
        boton_cancelar.setTypeface(font);


        if(savedInstanceState != null){
            nombre.setText(savedInstanceState.getString("nombre"));
            apellido.setText(savedInstanceState.getString("apellido"));
//            photoFile.setsavedInstanceState.getString("photoFile");
//            Uri uri = Uri.fromFile(photoFile);
//            imagen_perfil.setImageURI(uri);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("nombre",nombre.getText().toString());
        savedInstanceState.putString("apellido",apellido.getText().toString());
//        savedInstanceState.putString("photoFile",photoFile.toString());
    }

    public void botonEditarPerfilClick(View view) {

//        if(view.getId() == R.id.button_borrar_historial){
//
//        }
//        if(view.getId() == R.id.picture_btn){
//            dispatchTakePictureIntent();
//        }
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

    public void botonImagenClick(View view) {

        if(view.getId() == R.id.btnSelectPhoto)
        {
            selectImage();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imagen.setImageBitmap(imageBitmap);
//        }
//    }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                        data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        data.getExtras(MediaStore.EXTRA_OUTPUT,);
        imagen_perfil.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagen_perfil.setImageBitmap(thumbnail);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }



    private void selectImage() {
        final CharSequence[] items = { "Tomar foto", "Seleccionar desde galeria",
                "Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar foto!");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = Utility.checkPermission(PerfilEditarActivity.this);
                if (items[item].equals("Tomar foto")) {
                    userChoosenTask = "Tomar foto";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Seleccionar desde galeria")) {
                    userChoosenTask = "Selecionar desde galeria";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context)
        {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
            {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Tomar foto"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Selecionar desde galeria"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
}
