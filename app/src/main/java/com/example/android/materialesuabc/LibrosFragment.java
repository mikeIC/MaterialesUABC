package com.example.android.materialesuabc;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LibrosFragment extends Fragment {


    public LibrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        LibrosListFragment librosListFragment = new LibrosListFragment();
        ft.replace(R.id.frame_lista_libros, librosListFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return inflater.inflate(R.layout.fragment_libros, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

    }
}
