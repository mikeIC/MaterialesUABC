package com.example.android.materialesuabc;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
//        Fragment fragment = new TriviaFragment();
//
//        fragmentStatePagerAdapter= new FragmentStatePagerAdapter(fm) {
//            @Override
//            public int getCount() {
//                return NUM_PAGES;
//            }
//
//            @Override
//            public android.support.v4.app.Fragment getItem(int position) {
//                return new TriviaFragment();
//            }
//        };


    }

    public void btnClickTrivia(View view) {
        Toast.makeText(TriviaActivity.this, "click happened here", Toast.LENGTH_SHORT).show();

    }


    public void changeFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.pregunta_frame_layout, fragment, "visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
//    private List<Fragment> getFragments(){
//        List<Fragment> fList = new ArrayList<Fragment>();
//        fList.add(TriviaFragment.newInstance());
//        fList.add(TriviaFragment.newInstance());
//        fList.add(TriviaFragment.newInstance());
//        return fList;
//     }

}
