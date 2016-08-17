package com.example.android.materialesuabc;

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

import com.viewpagerindicator.CirclePageIndicator;

public class TriviaActivity extends AppCompatActivity implements TriviaPreguntaFragment.TriviaClickListener{
//public class TriviaActivity extends AppCompatActivity{
    private static final int NUM_PAGES = 5;
    private TriviaPreguntaFragment fragmentPreguntas[];
    private FragmentManager fm;
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
    private int auxInt =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        View contenedorFrame = findViewById(R.id.framelayout_trivia);
        triviaEnded = false;
        Intent intent = getIntent();
        materiaEscojida =intent.getIntExtra("materia",-1);
        unidadEscojida =intent.getIntExtra("unidad",-1);
        setNombres();
        if(contenedorFrame != null){
            TriviaContenedorFragment triviaFragment = new TriviaContenedorFragment();
            Bundle bundle = new Bundle();
            bundle.putString("nombre_materia",nombreMateria);
            bundle.putString("nombre_unidad",nombreUnidad);
            bundle.putInt("num_pages",NUM_PAGES);
            triviaFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout_trivia,triviaFragment);
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
    public void setNombres(){
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

    @Override
    public void changePage() {
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpagerPregunta);
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        if(viewPager.getCurrentItem() == NUM_PAGES-1){
            Toast.makeText(TriviaActivity.this, "Current Item: "+viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(TriviaActivity.this, );
            if(auxInt < 2){
                auxInt++;
            }else{

            }
        }else{
            auxInt = 0;
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
