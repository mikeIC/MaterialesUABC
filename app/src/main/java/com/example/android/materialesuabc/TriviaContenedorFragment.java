package com.example.android.materialesuabc;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class TriviaContenedorFragment extends Fragment {
    private CirclePageIndicator circlePageIndicator;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private String nombreMateria;
    private String nombreUnidad;
    private int num_pages;
    private static final String TAG= "ContenedorFragment";

//    private FragmentActivity myContext;


    public TriviaContenedorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        if(bundle!= null) {
            num_pages = bundle.getInt("num_pages", -1);
            nombreMateria = bundle.getString("nombre_materia");
            nombreUnidad = bundle.getString("nombre_unidad");

            Log.d(TAG, "Set Values in bundle:"+num_pages+"::"+nombreMateria+"::"+nombreUnidad);
        }
        return inflater.inflate(R.layout.fragment_trivia_contenedor, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicador_pregunta);
        viewPager = (ViewPager) view.findViewById(R.id.viewpagerPregunta);
//        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        circlePageIndicator.setViewPager(viewPager);
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            TriviaPreguntaFragment fragment = new TriviaPreguntaFragment();
            Log.d(TAG, "Position: "+position);
            Bundle bundle = new Bundle();
            try{
                SQLiteOpenHelper materialesUABCDataBaseHelper = new MaterialesUABCDatabaseHelper(getActivity());
                SQLiteDatabase dataBaseMateriales = materialesUABCDataBaseHelper.getReadableDatabase();

                Cursor cursorPregunta1 = dataBaseMateriales.query(
                        "PREGUNTA",
                        new String[]{"PREGUNTA","RESPUESTA"},
                        "MATERIA = ? AND UNIDAD = ?",
//                    "UNIDAD = ?",
                        new String[]{nombreMateria,nombreUnidad},
//                    new String[]{"Materia1","Unidad2"},
                        null,
                        null,
                        null
                );

                if(cursorPregunta1.moveToFirst()){
                    if(position >0 ){
                        for (int i =0; i< position; i++){
                            cursorPregunta1.moveToNext();
                        }
                    }
                    bundle.putInt("posicion",position);
                    bundle.putString("materia",nombreMateria);
                    bundle.putString("unidad",nombreUnidad);
                    bundle.putString("pregunta",cursorPregunta1.getString(0));
                    bundle.putString("respuesta",cursorPregunta1.getString(1));
                }
                cursorPregunta1.close();
                dataBaseMateriales.close();
            }catch(SQLiteException e){
                Toast.makeText(getActivity(), "SQL Error: "+e, Toast.LENGTH_LONG).show();
            }
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
