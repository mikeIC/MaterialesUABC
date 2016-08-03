package com.example.android.materialesuabc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MateriasActivity extends AppCompatActivity  implements MateriasListFragment.MateriasListListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
    }

    @Override
    public void itemClicked(long id) {
        Toast.makeText(MateriasActivity.this, "id: "+id, Toast.LENGTH_SHORT).show();
    }
}
