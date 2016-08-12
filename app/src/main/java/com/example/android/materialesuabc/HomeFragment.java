package com.example.android.materialesuabc;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private int materiaSeleccionada;
    private int unidadSeleccionada;
    public Spinner spinnerMaterias;
    public Spinner spinnerUnidades;
    public Button botonStartTrivia;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(savedInstanceState != null){
            unidadSeleccionada = savedInstanceState.getInt("unidad");
            materiaSeleccionada = savedInstanceState.getInt("materia");
        }else{
            materiaSeleccionada =0;
            unidadSeleccionada =0;
        }
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("materia",materiaSeleccionada);
        outState.putInt("unidad",unidadSeleccionada);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        assert view != null;
        spinnerMaterias = (Spinner) view.findViewById(R.id.spinner_materias);
        spinnerUnidades = (Spinner) view.findViewById(R.id.spinner_unidad);
        botonStartTrivia = (Button) view.findViewById(R.id.button_start_trivia);

        botonStartTrivia.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapterMaterias;
        adapterMaterias = ArrayAdapter.createFromResource(view.getContext(), R.array.materias_list,
                R.layout.custom_spinner_item);
        ArrayAdapter<CharSequence> adapterUnidades = ArrayAdapter.createFromResource(view.getContext(), R.array.unidades_list,
                R.layout.custom_spinner_item);

        adapterMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterUnidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMaterias.setAdapter(adapterMaterias);
        spinnerUnidades.setAdapter(adapterUnidades);

        if(unidadSeleccionada !=0 || materiaSeleccionada != 0){
            spinnerMaterias.setSelection(materiaSeleccionada);
            spinnerUnidades.setSelection(unidadSeleccionada);
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

        spinnerUnidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                unidadSeleccionada = arg2;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
        if(view.getId() == R.id.button_start_trivia){
//            Intent intent = new Intent(getActivity(),ScreenSlidePagerActivity.class);
            Intent intent = new Intent(getActivity(),TriviaActivity.class);
            intent.putExtra("materia",materiaSeleccionada);
            intent.putExtra("unidad",unidadSeleccionada);
            startActivity(intent);
        }

    }
}
