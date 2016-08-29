package com.example.android.materialesuabc;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformacionFragment extends Fragment {


    public InformacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        MateriasListFragment materiasListFragment = new MateriasListFragment();
        ft.replace(R.id.frame_lista_materias, materiasListFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return inflater.inflate(R.layout.fragment_informacion, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_link_pagina);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://fcqi.tij.uabc.mx/usuarios/cacademico/index.php");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }


}
