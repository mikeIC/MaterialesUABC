package com.example.android.materialesuabc;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class TriviaActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private TriviaFragment fragmentPreguntas[];
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private FragmentManager fm;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    private int seconds =0;
    private boolean running;
    private boolean wasRunning;
    private MenuItem itemMenu;
    private MenuItem itemMenuFinalizar;
    private TextView timeView ;
    private String time;
    private Button buttonView;
    private Boolean triviaEnded;
    private int materiaEscojida;
    private int unidadEscojida;
    private String nombreMateria;
    private String nombreUnidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        viewPager = (ViewPager) findViewById(R.id.viewpagerPregunta);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        viewPager.setAdapter(pagerAdapter);

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicador_pregunta);
        circlePageIndicator.setViewPager(viewPager);
        triviaEnded = false;
        Intent intent = getIntent();
        materiaEscojida =intent.getIntExtra("materia",-1);
        unidadEscojida =intent.getIntExtra("unidad",-1);

        switch (materiaEscojida){
            case 0:nombreMateria = "Materia1";
                break;
            case 1:nombreMateria = "Materia2";
                break;
            case 2:nombreMateria = "Materia3";
                break;
            default:nombreMateria = getResources().getString(R.string.vacio);
        }
        switch (unidadEscojida){
            case 0:nombreUnidad = "Unidad1";
                break;
            case 1:nombreUnidad = "Unidad2";
                break;
            case 2:nombreUnidad = "Unidad3";
                break;
            default:nombreUnidad = getResources().getString(R.string.vacio);
        }
//        Toast.makeText(TriviaActivity.this, "materiaEscojida: "+materiaEscojida+" UnidadEscojida: "+unidadEscojida+" NombreMateria: "+nombreMateria+" Nombreunidad: "+nombreUnidad, Toast.LENGTH_LONG).show();
        if (savedInstanceState != null) {
            triviaEnded = savedInstanceState.getBoolean("trivia_ended");
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            unidadEscojida = savedInstanceState.getInt("materia");
            materiaEscojida = savedInstanceState.getInt("unidad");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("trivia_ended",triviaEnded);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        savedInstanceState.putInt("materia",materiaEscojida);
        savedInstanceState.putInt("unidad",unidadEscojida);
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
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
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
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
    public void btnClickTrivia(View view) {
        if(view.getId() == R.id.boton_menu_finalizar && !triviaEnded) {
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

    public void endTrivia(){
        timeView.setTextColor(ContextCompat.getColor(this,R.color.Red));
        buttonView.setText(getResources().getString(R.string.salir));
        buttonView.setBackgroundColor(ContextCompat.getColor(this,R.color.Green3));
        triviaEnded = true;
        running = false;
    }
    public void resetTrivia(){
        running = false;
        seconds = 0;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            TriviaFragment fragment = new TriviaFragment();
            Bundle bundle = new Bundle();

            try{
                SQLiteOpenHelper materialesUABCDataBaseHelper = new MaterialesUABCDatabaseHelper(TriviaActivity.this);
                SQLiteDatabase dataBaseMateriales = materialesUABCDataBaseHelper.getReadableDatabase();

                Cursor cursorPregunta1 = dataBaseMateriales.query(
                        "PREGUNTA",
                        new String[]{"PREGUNTA","RESPUESTA"},
                        "MATERIA = ? AND UNIDAD = ?",
//                    "UNIDAD = ?",
                        new String[]{nombreMateria,nombreUnidad},
//                    new String[]{"Materia1","Unidad2"},
                        null,
                        null,
                        null
                );
                if(cursorPregunta1.moveToFirst()){
                    if(position >0 ){
                        for (int i =0; i< position; i++){
                            cursorPregunta1.moveToNext();
                        }

                    }

                    bundle.putInt("posicion",position);
                    bundle.putString("materia",nombreMateria);
                    bundle.putString("unidad",nombreUnidad);
                    bundle.putString("pregunta",cursorPregunta1.getString(0));
                    bundle.putString("respuesta",cursorPregunta1.getString(1));

                }
                cursorPregunta1.close();
                dataBaseMateriales.close();
            }catch(SQLiteException e){
                Toast.makeText(TriviaActivity.this, "SQL Error: "+e, Toast.LENGTH_LONG).show();
            }


            fragment.setArguments(bundle);

            return fragment;
//            return new TriviaFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trivia,menu);
        itemMenu = menu.findItem(R.id.reloj_item);
        itemMenuFinalizar = menu.findItem(R.id.boton_end_item);
        running = true;
        View view = MenuItemCompat.getActionView(itemMenu);
        timeView = (TextView) view.findViewById(R.id.tv_reloj);

        View view2 = MenuItemCompat.getActionView(itemMenuFinalizar);
        buttonView = (Button) view2.findViewById(R.id.boton_menu_finalizar);
        runTimer();
//        timeView.setText(time);
        if(triviaEnded){
            endTrivia();
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(TriviaActivity.this, "Finalizar Pressed 1", Toast.LENGTH_SHORT).show();

//        if(item.getItemId() == R.id.boton_end_item){
//
//        }


        return super.onOptionsItemSelected(item);
    }


}
