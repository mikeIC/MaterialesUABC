package com.example.android.materialesuabc;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private int materiaSeleccionada;
    public Spinner spinnerMaterias;
    public int indexAux;
    public int indexAux2;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(savedInstanceState != null){
            materiaSeleccionada = savedInstanceState.getInt("materia");
        }else{
            materiaSeleccionada =0;
        }
        indexAux = 0;
        indexAux2 = 0;
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("materia",materiaSeleccionada);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        assert view != null;
        spinnerMaterias = (Spinner) view.findViewById(R.id.spinner_materias);

        ArrayAdapter<CharSequence> adapterMaterias;
        adapterMaterias = ArrayAdapter.createFromResource(view.getContext(), R.array.materias_list,
                R.layout.custom_spinner_item);

        adapterMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMaterias.setAdapter(adapterMaterias);

        if(materiaSeleccionada != 0){
            spinnerMaterias.setSelection(materiaSeleccionada);
        }

        spinnerMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                materiaSeleccionada = arg2;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        final ScrollView scrollView = (ScrollView)getView().findViewById(R.id.scrollViewHome);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if(scrollView.getScrollY()<649) {

                   if(scrollView.getScrollY() <2) {
                       indexAux2++;
                       if (indexAux2 > 30) {
                           scrollView.smoothScrollBy(0,2);
                       }
                   }else{
                       scrollView.smoothScrollBy(0,1);
                   }
                }else{
                    if(indexAux>25){
                        scrollView.scrollTo(0,0);

                        indexAux = 0;
                    }else{
                        indexAux++;
                    }
                }
                handler.postDelayed(this, 50);
            }
        });
    }

    @Override
    public void onClick(View view) {

//        if(view.getId() == R.id.button_start_trivia){
////            Intent intent = new Intent(getActivity(),ScreenSlidePagerActivity.class);
//            Intent intent = new Intent(getActivity(),TriviaActivity.class);
//            intent.putExtra("materia",materiaSeleccionada);
//            startActivity(intent);
//        }

    }
}
