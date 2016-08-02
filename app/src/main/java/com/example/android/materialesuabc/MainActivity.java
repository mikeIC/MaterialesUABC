package com.example.android.materialesuabc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity implements MenuListFragment.MenuListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {

        if(id == 0)
        {

        }
        if(id == 1)
        {
            Intent intent = new Intent(MainActivity.this,PerfilActivity.class);
            startActivity(intent);
        }
        if(id == 2)
        {

        }
    }
}
