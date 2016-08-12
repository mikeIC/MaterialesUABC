package com.example.android.materialesuabc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TriviaFragment extends Fragment{
    private int numeroFragment;
    private TextView numeroPregunta;


    public TriviaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trivia, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        Bundle bundle = this.getArguments();
        if(bundle!= null){
            numeroFragment= bundle.getInt("posicion",0);
        }

        numeroPregunta = (TextView) view.findViewById(R.id.tvNumeroPregunta);
       Integer integer = numeroFragment+1;
        numeroPregunta.setText(integer.toString());
    }


}
