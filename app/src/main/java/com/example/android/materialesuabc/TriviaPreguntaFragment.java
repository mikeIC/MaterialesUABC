package com.example.android.materialesuabc;


import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
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
public class TriviaPreguntaFragment extends Fragment{


    private static final String TAG= "PreguntaFragment";
    private int numeroFragment;
    private TextView numeroPregunta;

    private TextView tvPregunta;
    private RadioButton rbRespuesta ;

    private  RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioGroup radioGroup;

    private String nombreMateria;
    private String nombreUnidad;
    private String preguntaString;
    private String respuestaString;
    private int randomAnswerPosition;
    private boolean respuestaEncontrada;


    interface TriviaClickListener{
        void changePage();
        void wrongAnswer();
    }
    private TriviaClickListener listener;


    public TriviaPreguntaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        respuestaEncontrada = false;
        Random r = new Random();
        randomAnswerPosition = r.nextInt(3);
        if(savedInstanceState != null){
            randomAnswerPosition = savedInstanceState.getInt("indexRespuesta");
            respuestaEncontrada = savedInstanceState.getBoolean("encontrada");
        }

        return inflater.inflate(R.layout.fragment_trivia_pregunta, container, false);
    }
    public void setButtonBackground(){
       View view = getView();
        int radioButtonIds2[]={R.id.opcion1,R.id.opcion2,R.id.opcion3,R.id.opcion4};
        RadioButton radioButton1 = (RadioButton) view.findViewById(radioButtonIds2[randomAnswerPosition]);
        radioButton1.setClickable(true);
        radioButton1.setChecked(true);

        for(int i = 0; i < 4;i++){
            if(i != randomAnswerPosition) {
                RadioButton radioButton2 = (RadioButton) view.findViewById(radioButtonIds2[i]);
                radioButton2.setClickable(false);
                radioButton2.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.custom_radio_button_red,null));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("encontrada",respuestaEncontrada);
        outState.putInt("indexRespuesta",randomAnswerPosition);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (TriviaClickListener) activity;

    }

    @Override
    public void onStart() {

        super.onStart();
        final View view = getView();
        int rbId = 0;

        final int radioButtonIds[]={R.id.opcion1,R.id.opcion2,R.id.opcion3,R.id.opcion4};
        RadioButton radioButtonAux;
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            numeroFragment = bundle.getInt("posicion", 0);
            nombreMateria = bundle.getString("materia");
            nombreUnidad = bundle.getString("unidad");
            preguntaString = bundle.getString("pregunta");
            respuestaString = bundle.getString("respuesta");
        }
        tvPregunta = (TextView) view.findViewById(R.id.trivia_pregunta_text);
        tvPregunta.setText(preguntaString);

        if(respuestaEncontrada){
            Log.d("TriviaPreguntaFragment","Respuesta Encontrada: "+respuestaEncontrada);
            setButtonBackground();
        }else {

            rbRespuesta = (RadioButton) view.findViewById(radioButtonIds[randomAnswerPosition]);
            radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_opciones);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton = (RadioButton) view.findViewById(i);
                    if (i == radioButtonIds[randomAnswerPosition]) {

                        listener.changePage();
                        respuestaEncontrada = true;
                        Toast.makeText(getActivity(), "Respuesta Correcta", Toast.LENGTH_SHORT).show();
                    } else {
                        radioButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_radio_button_red, null));
                        radioButton.setClickable(false);
                        respuestaEncontrada = false;
                        listener.wrongAnswer();
                    }

                }
            });

            rbRespuesta.setText(respuestaString);
            Integer integer = numeroFragment + 1;

            try {
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
                if (cursor.moveToFirst()) {
                    boolean boolaux;
                    int auxNum = 0;
                    for (int i = 0; i < 4; i++) {
                        if (i != randomAnswerPosition) {
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
            } catch (SQLException e) {
                Toast.makeText(getActivity(), "Error SQL: " + e, Toast.LENGTH_LONG).show();
            }

        }
    }
}
