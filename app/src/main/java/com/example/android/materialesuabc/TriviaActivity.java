package com.example.android.materialesuabc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TriviaActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 10;
    private RadioButton opcion1;
    private RadioButton opcion2;
    private RadioButton opcion3;
    private RadioButton opcion4;
    private TriviaFragment fragmentPreguntas[];
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private FragmentManager fm;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        viewPager = (ViewPager) findViewById(R.id.viewpagerPregunta);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);



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
    public void btnClickTrivia(View view) {
        Toast.makeText(TriviaActivity.this, "click happened here", Toast.LENGTH_SHORT).show();

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            TriviaFragment fragment = new TriviaFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("posicion",position);
            fragment.setArguments(bundle);

            return fragment;
//            return new TriviaFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


//    public void changeFragment(Fragment fragment) {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.pregunta_frame_layout, fragment, "visible_fragment");
//        ft.addToBackStack(null);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.commit();
//    }
//    private List<Fragment> getFragments(){
//        List<Fragment> fList = new ArrayList<Fragment>();
//        fList.add(TriviaFragment.newInstance());
//        fList.add(TriviaFragment.newInstance());
//        fList.add(TriviaFragment.newInstance());
//        return fList;
//     }

}
