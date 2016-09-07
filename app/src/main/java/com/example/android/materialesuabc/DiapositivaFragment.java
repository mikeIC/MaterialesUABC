package com.example.android.materialesuabc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiapositivaFragment extends Fragment {


    public DiapositivaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diapositiva, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        Bundle bunble = getArguments();
        ImageView imagen = (ImageView) view.findViewById(R.id.ivDiapositiva1);

//        imagen.setImageResource(bunble.getInt("idImagen"));
        Ion.with(imagen)
                .placeholder(R.drawable.icono_cargando)
                .error(R.drawable.error_image)
                .animateLoad(0)
                .animateIn(0)
                .load(bunble.getString("string"));
    }
}
