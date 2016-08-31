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
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class PresentacionActivity extends AppCompatActivity {
    private LinePageIndicator linePageIndicator;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private int num_pages;
    private long id;
    private int imagesPresentacion1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);

        if(id == 0){
            //presentacion M
           num_pages = 36;
            imagesPresentacion1 = new int[]{
                    R.drawable.diapositiva1,R.drawable.diapositiva2,
                    R.drawable.diapositiva3,R.drawable.diapositiva4,
                    R.drawable.diapositiva5,R.drawable.diapositiva6,
                    R.drawable.diapositiva7,R.drawable.diapositiva8,
                    R.drawable.diapositiva9,R.drawable.diapositiva10,
                    R.drawable.diapositiva11,R.drawable.diapositiva12,
                    R.drawable.diapositiva13,R.drawable.diapositiva14,
                    R.drawable.diapositiva15,R.drawable.diapositiva16,
                    R.drawable.diapositiva17,R.drawable.diapositiva18,
                    R.drawable.diapositiva19,R.drawable.diapositiva20,
                    R.drawable.diapositiva21,R.drawable.diapositiva22,
                    R.drawable.diapositiva23,R.drawable.diapositiva24,
                    R.drawable.diapositiva25,R.drawable.diapositiva26,
                    R.drawable.diapositiva27,R.drawable.diapositiva28,
                    R.drawable.diapositiva29,R.drawable.diapositiva30,
                    R.drawable.diapositiva31,R.drawable.diapositiva32,
                    R.drawable.diapositiva33,R.drawable.diapositiva34,
                    R.drawable.diapositiva35,R.drawable.diapositiva36};

        }
        if(id == 1){
            num_pages = 5;
        }
        if(id == 2){
            num_pages = 10;
        }
        if(id == 3){
            num_pages = 3;
        }
        Toast.makeText(PresentacionActivity.this, "num_pages: "+num_pages, Toast.LENGTH_SHORT).show();


        linePageIndicator = (LinePageIndicator) findViewById(R.id.indicador_presentacion);
        viewPager = (ViewPager) findViewById(R.id.viewpagerPresentacion);
//        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        linePageIndicator.setViewPager(viewPager);

    }
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            DiapositivaFragment fragment = new DiapositivaFragment();
            Log.d("InformacionActivity", "Position: " + position);
            Bundle bundle = new Bundle();
           bundle.putInt("idImagen",imagesPresentacion1[position]);
//            bundle.putLong("id", id);
            bundle.putInt("posicion",position);
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