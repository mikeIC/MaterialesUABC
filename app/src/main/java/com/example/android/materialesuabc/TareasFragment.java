package com.example.android.materialesuabc;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TareasFragment extends Fragment {


    public TareasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        int idMateria = bundle.getInt("idMateria",0);
        Log.d("TareasFragment","idMateria: "+idMateria);

        return inflater.inflate(R.layout.fragment_tareas, container, false);
    }

}
