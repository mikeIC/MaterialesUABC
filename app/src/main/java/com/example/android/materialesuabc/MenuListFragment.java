package com.example.android.materialesuabc;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends ListFragment {
    static interface MenuListListener{
        void itemClicked(long id);
    };
    private MenuListListener listener;

    public MenuListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        String [] optionsName = getResources().getStringArray(R.array.menu_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String >(inflater.getContext(),android.R.layout.simple_list_item_1,optionsName);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container,savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (MenuListListener)activity;

    }

    @Override
    public void onListItemClick(ListView l , View v , int position, long id){
        if(listener!=null){
            listener.itemClicked(id);
        }
    }
}
