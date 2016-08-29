package com.example.android.materialesuabc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformacionDetalleFragment extends Fragment {
    private int numeroPagina;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;

    public InformacionDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacion_detalle, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        Bundle bundle = this.getArguments();

        if(bundle != null){
            numeroPagina = bundle.getInt("posicion");
            textView1 =(TextView) view.findViewById(R.id.tvInformacion1);
            textView2 =(TextView) view.findViewById(R.id.tvInformacion2);
            textView3 =(TextView) view.findViewById(R.id.tvInformacion3);
            textView4 =(TextView) view.findViewById(R.id.tvInformacion4);
            textView5 =(TextView) view.findViewById(R.id.tvInformacion5);
            textView6 =(TextView) view.findViewById(R.id.tvInformacion6);
            textView7 =(TextView) view.findViewById(R.id.tvInformacion7);
            textView8 =(TextView) view.findViewById(R.id.tvInformacion8);
            textView9 =(TextView) view.findViewById(R.id.tvInformacion9);
            textView10 =(TextView) view.findViewById(R.id.tvInformacion10);
            textView11 =(TextView) view.findViewById(R.id.tvInformacion11);
            textView12 =(TextView) view.findViewById(R.id.tvInformacion12);
            textView13 =(TextView) view.findViewById(R.id.tvInformacion13);
        }
        if(numeroPagina == 0){
            textView1.setText(getResources().getText(R.string.texto_informacion1));
            textView1.setTextSize(10);
            textView1.setPadding(0,5,0,8);
            textView2.setText(getResources().getText(R.string.texto_informacion2));
            textView2.setPadding(0,5,0,0);

        }
        if(numeroPagina == 1){
            textView1.setText(getResources().getText(R.string.texto_informacion3));
            textView2.setText(getResources().getText(R.string.texto_informacion4));

            textView1.setPadding(0,5,0,8);
            textView2.setPadding(0,5,0,0);
        }
        if(numeroPagina == 2){
            textView1.setText(getResources().getText(R.string.texto_informacion5));
            textView2.setText(getResources().getText(R.string.texto_informacion6));
            textView3.setText(getResources().getText(R.string.texto_informacion7));
            textView4.setText(getResources().getText(R.string.texto_informacion8));
            textView5.setText(getResources().getText(R.string.texto_informacion9));
            textView6.setText(getResources().getText(R.string.texto_informacion10));
            textView7.setText(getResources().getText(R.string.texto_informacion11));
            textView8.setText(getResources().getText(R.string.texto_informacion12));
            textView9.setText(getResources().getText(R.string.texto_informacion13));
            textView10.setText(getResources().getText(R.string.texto_informacion14));
            textView11.setText(getResources().getText(R.string.texto_informacion15));
            textView12.setText(getResources().getText(R.string.texto_informacion16));
            textView13.setText(getResources().getText(R.string.texto_informacion17));
            textView1.setPadding(0,5,0,0);
            textView2.setPadding(0,5,0,0);
            textView3.setPadding(0,5,0,0);
            textView4.setPadding(0,5,0,0);
            textView5.setPadding(0,5,0,0);
            textView6.setPadding(0,5,0,0);
            textView7.setPadding(0,5,0,0);
            textView8.setPadding(0,5,0,0);
            textView9.setPadding(0,5,0,0);
            textView10.setPadding(0,5,0,0);
            textView11.setPadding(0,5,0,0);
            textView12.setPadding(0,5,0,0);
            textView13.setPadding(0,5,0,0);
        }


    }
}
