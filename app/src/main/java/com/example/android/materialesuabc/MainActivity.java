package com.example.android.materialesuabc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity{

    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;
    private Menu menuAux;
    private int materiaSeleccionada;
    private int unidadSeleccionada;


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        titles = getResources().getStringArray(R.array.menu_list);
        drawerList = (ListView) findViewById(R.id.drawer);
        materiaSeleccionada = 0;
        unidadSeleccionada = 0;

        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //Display the correct fragment.
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        } else {
            selectItem(0);
        }
        //Create the ActionBarDrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            //Called when a drawer has settled in a completely closed state
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            //Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if (fragment instanceof HomeFragment) {
                            inicializarSpinners();
                            currentPosition = 0;
                        }
                        if (fragment instanceof PerfilFragment) {
                            currentPosition = 1;
                        }
                        if (fragment instanceof MateriasListFragment) {
                            currentPosition = 2;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition, true);
                    }
                }
        );
    }

    private void inicializarSpinners() {
        Spinner spinnerMaterias = (Spinner) findViewById(R.id.spinner_materias);
        Spinner spinnerUnidades = (Spinner) findViewById(R.id.spinner_unidad);

        ArrayAdapter <CharSequence> adapterMaterias;
        adapterMaterias = ArrayAdapter.createFromResource(this, R.array.materias_list,
                R.layout.custom_spinner_item);
        ArrayAdapter<CharSequence> adapterUnidades = ArrayAdapter.createFromResource(this, R.array.unidades_list,
                R.layout.custom_spinner_item);

        adapterMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterUnidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMaterias.setAdapter(adapterMaterias);
        spinnerUnidades.setAdapter(adapterUnidades);

        spinnerMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                materiaSeleccionada = arg2;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        spinnerUnidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                unidadSeleccionada = arg2;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //Called whenever we call invalidateOptionsMenu()
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
// If the drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
//        menu.findItem(R.id.spinner_materias).setVisible(!drawerOpen);
//        menu.findItem(R.id.action_edit).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_inicial,menu);
//        menuAux = menu;
//
//        MenuItem item2 = menuAux.findItem(R.id.spinner_materias);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item2);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.materias_list,
//                R.layout.custom_spinner_item);
//
//        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdwon_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                materiaSeleccionada = arg2;
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//
//            }
//
//        });

        return super.onCreateOptionsMenu(menu);
    }

    private void selectItem(int position){
        Fragment fragment=null;
        currentPosition = position;
        setActionBarTitle(position);
        switch(position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new PerfilFragment();
                break;
            case 2:
                fragment = new MateriasListFragment();
                break;
            default:
//                fragment = new TopFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_content, fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        //Set the action bar title
        setActionBarTitle(position);
        //Close drawer
        drawerLayout.closeDrawer(drawerList);
    }
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0){
            title = getResources().getString(R.string.app_name);
        } else {
            title = titles[position];
        }
       changeActionBarIcons(position);
//        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle(title);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//       MenuItem item1 = menuAux.findItem(R.id.action_edit);

//        MenuItem item2 = menuAux.findItem(R.id.spinner_materias);
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }

    private void changeActionBarIcons(int position){
//
        switch(position) {
            case 0:
                //UNIDADES

//                item1.setVisible(false);
//                item2.setVisible(true);

                getSupportActionBar().setIcon(R.drawable.book);

                break;
            case 1:
                //PERFIL
                getSupportActionBar().setIcon(R.drawable.person);
//                item1.setVisible(true);
//                item2.setVisible(false);
                break;
            case 2:
                //Informacion
//                item1.setVisible(false);
//                item2.setVisible(false);
                getSupportActionBar().setIcon(R.drawable.school);

                break;
            default:
//                fragment = new TopFragment();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}
