package com.example.android.materialesuabc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class UnidadesActivity extends AppCompatActivity implements UnidadesListFragment.UnidadesListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);
    }

    @Override
    public void itemClicked(long id) {
        Toast.makeText(UnidadesActivity.this, "id: "+id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_unidades,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
