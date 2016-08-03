package com.example.android.materialesuabc;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnidadesListFragment extends ListFragment {
    static interface UnidadesListListener{
        void itemClicked(long id);
    };
    private UnidadesListListener listener;

    public UnidadesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String [] optionsName = getResources().getStringArray(R.array.unidades_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String >(inflater.getContext(),android.R.layout.simple_list_item_1,optionsName);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container,savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (UnidadesListListener)activity;

    }

    @Override
    public void onListItemClick(ListView l , View v , int position, long id){
        if(listener!=null){
            listener.itemClicked(id);
        }
    }

}
