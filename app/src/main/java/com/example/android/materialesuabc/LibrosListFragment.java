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
public class LibrosListFragment extends ListFragment {
    static interface LibroListListener{
        void itemClickedLibros(long id);
    }
    private LibroListListener listener;

    public LibrosListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String [] optionsName = getResources().getStringArray(R.array.libros_list);
        ArrayAdapter<String> adapter = new ArrayAdapter< >(inflater.getContext(),android.R.layout.simple_list_item_1,optionsName);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container,savedInstanceState);

    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (LibroListListener) activity;

    }
    @Override
    public void onListItemClick(ListView l , View v , int position, long id) {
        if (listener != null) {
            listener.itemClickedLibros(id);
        }
    }

}
