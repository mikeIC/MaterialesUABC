package com.example.android.materialesuabc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class PresentacionActivity extends AppCompatActivity {
    private CirclePageIndicator circlePageIndicator;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private int num_pages;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
       if(id == 0){
           num_pages = 10;
       }
        circlePageIndicator = (CirclePageIndicator)findViewById(R.id.indicador_presentacion);
        viewPager = (ViewPager) findViewById(R.id.viewpagerPresentacion);
//        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        circlePageIndicator.setViewPager(viewPager);

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
            return num_pages;
        }
    }

}
