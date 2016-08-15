package com.example.android.materialesuabc;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TriviaFragment extends Fragment{
    private int numeroFragment;
    private TextView numeroPregunta;

    private TextView tvMateria;
    private TextView tvUnidad;
    private TextView tvPregunta;
    private RadioButton rbRespuesta ;

    private String nombreMateria;
    private String nombreUnidad;
    private String preguntaString;
    private String respuestaString;
    private int randomAnswerPosition;



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
        final View view = getView();
        int rbId = 0;
        final int radioButtonIds[]={R.id.opcion1,R.id.opcion2,R.id.opcion3,R.id.opcion4};
        RadioButton radioButtonAux;
        Bundle bundle = this.getArguments();
        if(bundle!= null){
            numeroFragment= bundle.getInt("posicion",0);
            nombreMateria = bundle.getString("materia");
            nombreUnidad = bundle.getString("unidad");
            preguntaString = bundle.getString("pregunta");
            respuestaString = bundle.getString("respuesta");

        }
        Random r = new Random();
        randomAnswerPosition = r.nextInt(3);
//        numeroPregunta = (TextView) view.findViewById(R.id.tvNumeroPregunta);
        tvPregunta = (TextView)view.findViewById(R.id.trivia_pregunta_text);
        rbRespuesta = (RadioButton)view.findViewById(radioButtonIds[randomAnswerPosition]) ;
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_opciones);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i == radioButtonIds[randomAnswerPosition]){


                    Toast.makeText(getActivity(), "Respuesta Correcta", Toast.LENGTH_SHORT).show();
                }else{
                    RadioButton radioButton = (RadioButton) view.findViewById(i);
                    radioButton.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.custom_radio_button_red,null));
                }
            }
        });
//        tvMateria = (TextView) view.findViewById(R.id.tv_nombre_materia);
//        tvUnidad = (TextView) view.findViewById(R.id.tv_nombre_unidad);

        tvPregunta.setText(preguntaString);
//        tvUnidad.setText(nombreUnidad);
//        tvMateria.setText(nombreMateria);
        rbRespuesta.setText(respuestaString);


        Integer integer = numeroFragment+1;
//        numeroPregunta.setText(integer.toString());

        try{
            SQLiteOpenHelper openHelper = new MaterialesUABCDatabaseHelper(getActivity());
            SQLiteDatabase db = openHelper.getReadableDatabase();

            Cursor cursor = db.query("PREGUNTA",
                    new String[]{"RESPUESTA"},
                    "MATERIA =?",
                    new String[]{nombreMateria},
                    null,
                    null,
                    null
                    );
            if(cursor.moveToFirst()){
                boolean boolaux;
                int auxNum = 0;
                for(int i = 0; i< 4;i++){
                    if(i != randomAnswerPosition) {
                        radioButtonAux = (RadioButton) view.findViewById(radioButtonIds[i]);
                        Random random = new Random();
                        int randomRespuesta = random.nextInt(cursor.getCount());
                        cursor.moveToPosition(randomRespuesta);
                        radioButtonAux.setText(cursor.getString(0));
                    }
                }
            }
            cursor.close();
            db.close();
        }catch (SQLException e){
            Toast.makeText(getActivity(), "Error SQL: "+e, Toast.LENGTH_LONG).show();
        }
    }
}
