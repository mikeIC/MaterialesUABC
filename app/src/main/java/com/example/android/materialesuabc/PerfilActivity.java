package com.example.android.materialesuabc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class PerfilActivity extends Activity {

    public static final int CAM_REQUEST = 1;
    public static final int EDITAR_PERFIL_REQUEST_CODE = 1;
    private ShareActionProvider shareActionProvider;
    private TextView nombre;
    private TextView apellido;
    private ImageView imagen_perfil;

    Button btnFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre = (TextView) findViewById(R.id.user_nombre);
        apellido = (TextView) findViewById(R.id.user_apellido);
        imagen_perfil = (ImageView) findViewById(R.id.imagen_perfil);
        btnFoto = (Button) findViewById(R.id.picture_btn);

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

    public void botonPerfilClick(View view) {

        if(view.getId() == R.id.picture_btn)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = getFile();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(intent,CAM_REQUEST);
        }

        if(view.getId() == R.id.history_btn){
//            Intent intent = new Intent(PerfilActivity.this, HistorialActivity.class);
//            startActivity(intent);
        }
        if(view.getId() == R.id.edit_btn){
//            Intent intent = new Intent(PerfilActivity.this, PerfilEditarActivity.class);
//            intent.putExtra("nombre",nombre.getText().toString());
//            intent.putExtra("apellido",apellido.getText().toString());
//            intent.putExtra("direccion",direccion.getText().toString());
//            intent.putExtra("telefono",telefono.getText().toString());
//            startActivityForResult(intent,EDITAR_PERFIL_REQUEST_CODE);
        }

    }

    private File getFile()
    {
        File folder = new File("sdcar/camera_app");

        if( !(folder.exists()))
        {
            folder.mkdir();
        }

        File image_file = new File(folder,"foto_perfil.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camera_app/foto_perfil.jpg";
        imagen_perfil.setImageDrawable(Drawable.createFromPath(path));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        MenuItem menuItem = menu.findItem(R.id.action_edit);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        return super.onCreateOptionsMenu(menu);
    }
}



