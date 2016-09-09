package com.example.android.materialesuabc;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import java.net.URL;

public class InformacionActivity extends AppCompatActivity{
    private int NUM_PAGES = 3;
    private CirclePageIndicator circlePageIndicator;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        circlePageIndicator = (CirclePageIndicator)findViewById(R.id.indicador_informacion);
        viewPager = (ViewPager) findViewById(R.id.viewpagerInformacion);
//        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        circlePageIndicator.setViewPager(viewPager);
        Intent intent = getIntent();
        int materiaID = intent.getIntExtra("materiaId",0);
        if(materiaID == 0){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Green)));
        }
        if(materiaID == 1){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Blue)));
        }
        if(materiaID == 2){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Orange)));
        }
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            InformacionDetalleFragment fragment = new InformacionDetalleFragment();
            Log.d("InformacionActivity", "Position: " + position);
            Bundle bundle = new Bundle();
            bundle.putInt("posicion", position);
//                    bundle.putString("materia",nombreMateria);
//                    bundle.putString("unidad",nombreUnidad);
//                    bundle.putString("pregunta",cursorPregunta1.getString(0));
//                    bundle.putString("respuesta",cursorPregunta1.getString(1));

            fragment.setArguments(bundle);

            return fragment;
//            return new TriviaPreguntaFragment();
        }
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
