package com.example.android.materialesuabc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class MainActivity extends AppCompatActivity implements MenuListFragment.MenuListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {

        if(id == 0)
        {
            Intent intent = new Intent(MainActivity.this, UnidadesActivity.class);
            startActivity(intent);
        }
        if(id == 1)
        {
            Intent intent = new Intent(MainActivity.this,PerfilActivity.class);
            startActivity(intent);
        }
        if(id == 2)
        {
            Intent intent = new Intent(MainActivity.this,MateriasActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicial,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
