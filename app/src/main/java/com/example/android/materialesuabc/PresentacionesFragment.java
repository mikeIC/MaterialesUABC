package com.example.android.materialesuabc;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PresentacionesFragment extends Fragment {


    public PresentacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        int idMateria = bundle.getInt("materiaSeleccionada");
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        PresentacionesListFragment presentacionesListFragment = new PresentacionesListFragment();
        presentacionesListFragment.setArguments(bundle);
        ft.replace(R.id.frame_lista_presentaciones, presentacionesListFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return inflater.inflate(R.layout.fragment_presentaciones, container, false);
    }

}
