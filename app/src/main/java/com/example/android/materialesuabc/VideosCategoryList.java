package com.example.android.materialesuabc;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosCategoryList extends ListFragment {

    interface VideosCategoryListListener{
        void itemClickedVideosCategory(long id);
    }
    private VideosCategoryListListener listener;

    public VideosCategoryList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String [] optionsName = null;

        Bundle bundle = getArguments();
        int idMateria = bundle.getInt("materiaSeleccionada");
        if(idMateria == 0){
            optionsName = getResources().getStringArray(R.array.videos_temas_list);
        }
        if(idMateria == 1){
            optionsName = getResources().getStringArray(R.array.materiales_ceramicos_videos_list_tema);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter< >(inflater.getContext(),android.R.layout.simple_list_item_1,optionsName);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container,savedInstanceState);

    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (VideosCategoryListListener) activity;

    }
    @Override
    public void onListItemClick(ListView l , View v , int position, long id) {
        if (listener != null) {
            listener.itemClickedVideosCategory(id);
        }
    }

}
