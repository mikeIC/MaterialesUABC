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
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private ImageView imagen_perfil;
    private Button boton_imagen;

    String userChoosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome_webfont.ttf");

        nombre = (TextView) findViewById(R.id.user_nombre);
        apellido = (TextView) findViewById(R.id.user_apellido);
        imagen_perfil = (ImageView) findViewById(R.id.imagen_perfil);
        boton_imagen = (Button) findViewById(R.id.btnSelectPhoto);

//        font.getStyle();
        boton_imagen.setTypeface(font);


//        boton_imagen.setTypeface(font);

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

    public void botonImagenClick(View view) {

        if(view.getId() == R.id.btnSelectPhoto)
        {
            selectImage();
        }
    }

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
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imagen_perfil.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
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

        AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActivity.this);
        builder.setTitle("Agregar foto!");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = Utility.checkPermission(PerfilActivity.this);
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




