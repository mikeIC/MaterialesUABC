package com.example.android.materialesuabc;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import java.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.viewpagerindicator.CirclePageIndicator;

import java.sql.Time;
import java.util.Date;

public class TriviaActivity extends AppCompatActivity implements TriviaPreguntaFragment.TriviaClickListener{
    //public class TriviaActivity extends AppCompatActivity{
    private static final int NUM_PAGES = 5;
    private static final int NUM_ANSWER = 4;
    private TriviaPreguntaFragment fragmentPreguntas[];
    private FragmentManager fm;
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    private MenuItem itemMenu;
    private MenuItem itemMenuFinalizar;
    private TextView timeView;
    private String time;
    private Button buttonView;
    private Boolean triviaEnded;
    private int materiaEscojida;
    private int unidadEscojida;
    private String nombreMateria;
    private String nombreUnidad;
    private int auxInt;
    private int countError;
    private int countAcierto;
    private int num_pages_aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        View contenedorFrame = findViewById(R.id.framelayout_trivia);

        triviaEnded = false;
        num_pages_aux = NUM_PAGES;
        countError = 0;
        countAcierto = 0;
        auxInt = 0;

        Intent intent = getIntent();
        materiaEscojida = intent.getIntExtra("materia", -1);
        unidadEscojida = intent.getIntExtra("unidad", -1);
        setNombres();
        if (contenedorFrame != null) {
            TriviaContenedorFragment triviaFragment = new TriviaContenedorFragment();
            Bundle bundle = new Bundle();
            bundle.putString("nombre_materia", nombreMateria);
            bundle.putString("nombre_unidad", nombreUnidad);
            bundle.putInt("num_pages", NUM_PAGES);
            triviaFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout_trivia, triviaFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        }
        if (savedInstanceState != null) {
            triviaEnded = savedInstanceState.getBoolean("trivia_ended");
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            unidadEscojida = savedInstanceState.getInt("materia");
            materiaEscojida = savedInstanceState.getInt("unidad");
        }
    }

    public void setNombres() {
        switch (materiaEscojida) {
            case 0:
                nombreMateria = "Materia1";
                break;
            case 1:
                nombreMateria = "Materia2";
                break;
            case 2:
                nombreMateria = "Materia3";
                break;
            default:
                nombreMateria = getResources().getString(R.string.vacio);
        }
        switch (unidadEscojida) {
            case 0:
                nombreUnidad = "Unidad1";
                break;
            case 1:
                nombreUnidad = "Unidad2";
                break;
            case 2:
                nombreUnidad = "Unidad3";
                break;
            default:
                nombreUnidad = getResources().getString(R.string.vacio);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("trivia_ended", triviaEnded);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        savedInstanceState.putInt("materia", materiaEscojida);
        savedInstanceState.putInt("unidad", unidadEscojida);
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                time = String.format("%02d:%02d",
                         minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerPregunta);
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.seguro)
                    .setTitle(R.string.alerta);
            // Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
//                    endTrivia();
                    finish();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void btnClickTrivia(View view) {
        if (view.getId() == R.id.boton_menu_finalizar && !triviaEnded) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.seguro)
                    .setTitle(R.string.alerta);
            // Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    endTrivia();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        if (triviaEnded) {
            finish();
        }
    }

    public void endTrivia() {
        if(!triviaEnded) {
            triviaEnded = true;
            timeView.setTextColor(ContextCompat.getColor(this, R.color.Red));
            buttonView.setText(getResources().getString(R.string.salir));
            buttonView.setBackgroundColor(ContextCompat.getColor(this, R.color.Green3));
            running = false;

            String resultadosString = "Tiempo: " + time + " Errores: " + countError;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(resultadosString).setTitle("Resultados: ");
            // Add the buttons
            builder.setPositiveButton(R.string.ok_salir, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    insertHistorial();
                    finish();
                }
            });
            builder.setNegativeButton(R.string.regresar_pregunta, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(countAcierto <5){
            Toast.makeText(TriviaActivity.this, "Responde todas las preguntas", Toast.LENGTH_SHORT).show();
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void insertHistorial() {

        try {
            SQLiteOpenHelper openHelper = new MaterialesUABCDatabaseHelper(TriviaActivity.this);
            SQLiteDatabase database = openHelper.getWritableDatabase();

//            Cursor cursor = database.query("RESULTADOS", new String[]={"MATERIA","UNIDAD","ERRORES"})
            ContentValues resultadosValues = new ContentValues();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            Log.d("TriviaActivity","Current time => " + c.getTime());

            resultadosValues.put("MATERIA", nombreMateria);
            resultadosValues.put("UNIDAD", nombreUnidad);
            resultadosValues.put("ERRORES", countError);
            resultadosValues.put("ACIERTOS", countAcierto);
            resultadosValues.put("TIME", timeView.getText().toString());
            resultadosValues.put("FECHA", String.valueOf(c.getTime()));
            database.insert("RESULTADOS", null, resultadosValues);

        } catch (SQLException e) {
            Toast.makeText(TriviaActivity.this, "Error SQL: " + e, Toast.LENGTH_SHORT).show();
        }

    }

    public void resetTrivia() {
        running = false;
        seconds = 0;
    }

    @Override
    public void changePage() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerPregunta);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        Log.d("TriviaActivity","Changed paged, Current Page:"+ viewPager.getCurrentItem());
        num_pages_aux--;

        if (countAcierto < NUM_PAGES && num_pages_aux >0) {
            countAcierto++;
        }
        if (viewPager.getCurrentItem() == NUM_PAGES - 1 ) { //&& !triviaEnded ) {
//            Intent intent = new Intent(TriviaActivity.this, );


            auxInt++;
            Log.d("TriviaActivity","auxInt: "+auxInt);

            if(auxInt >= 2){
                Log.d("TriviaActivity","Ending Trivia");
                endTrivia();
            }


        }
    }

    @Override
    public void wrongAnswer() {
        if (countError < (NUM_PAGES * NUM_ANSWER - NUM_PAGES) && !triviaEnded) {
            countError++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trivia, menu);
        itemMenu = menu.findItem(R.id.reloj_item);
        itemMenuFinalizar = menu.findItem(R.id.boton_end_item);
        running = true;
        View view = MenuItemCompat.getActionView(itemMenu);
        timeView = (TextView) view.findViewById(R.id.tv_reloj);

        View view2 = MenuItemCompat.getActionView(itemMenuFinalizar);
        buttonView = (Button) view2.findViewById(R.id.boton_menu_finalizar);
        runTimer();
//        timeView.setText(time);
        if (triviaEnded) {
            endTrivia();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.boton_end_item) {
            endTrivia();
        }
        return super.onOptionsItemSelected(item);
    }
}
