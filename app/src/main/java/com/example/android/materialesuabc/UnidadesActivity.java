package com.example.android.materialesuabc;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class UnidadesActivity extends AppCompatActivity implements UnidadesListFragment.UnidadesListListener {
    int materiaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);
        materiaSeleccionada = 0;

        if(savedInstanceState != null){
            materiaSeleccionada = savedInstanceState.getInt("materia");

        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.book);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("materia", materiaSeleccionada);

    }

    @Override
    public void itemClicked(long id) {
        Toast.makeText(UnidadesActivity.this, "id: "+id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_unidades,menu);
        MenuItem item = menu.findItem(R.id.spinner_materias);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.materias_list,
               R.layout.custom_spinner_item);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.materias_list,
//                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
//        spinner.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        spinner.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorWhite));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                materiaSeleccionada = arg2;
                Toast.makeText(UnidadesActivity.this, "arg2: "+arg2, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        return super.onCreateOptionsMenu(menu);
    }
}
