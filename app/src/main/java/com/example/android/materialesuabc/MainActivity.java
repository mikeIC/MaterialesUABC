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

import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;
    private Menu menuAux;
    private int materiaSeleccionada;
    private int unidadSeleccionada;
    private MenuItem item1;

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        titles = getResources().getStringArray(R.array.menu_list);
        drawerList = (ListView) findViewById(R.id.drawer);
        materiaSeleccionada = 0;
        unidadSeleccionada = 0;

        drawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

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
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if (fragment instanceof HomeFragment) {
                            currentPosition = 0;
                        }
                        if (fragment instanceof PerfilFragment) {
                            currentPosition = 1;
                        }
                        if (fragment instanceof InformacionFragment) {
                            currentPosition = 2;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition, true);
                    }
                }
        );
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
        if(currentPosition == 1){
            menu.findItem(R.id.action_edit).setVisible(true);
            if(drawerOpen){
                menu.findItem(R.id.action_edit).setVisible(false);
            }else{
                menu.findItem(R.id.action_edit).setVisible(true);
            }
        }else{
            menu.findItem(R.id.action_edit).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //Cambiar de fragmento con la posicion de la opcion seleccionada
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
                fragment = new InformacionFragment();
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

    //Cambair el titulo del menu
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0){
            title = getResources().getString(R.string.app_name);
            getSupportActionBar().setIcon(R.drawable.book);

        } else {
            title = titles[position];
        }
        changeActionBarIcons(position);
        getSupportActionBar().setTitle(title);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }

    //Cambiar Icono de menu
    private void changeActionBarIcons(int position){
        switch(position) {
            case 1://PERFIL
                getSupportActionBar().setIcon(R.drawable.person);
                break;
            case 2://Informacion
                getSupportActionBar().setIcon(R.drawable.school);
                break;
            default:
        }
    }

    //Al crear el menu:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicial,menu);
        menuAux = menu;
        item1 = menuAux.findItem(R.id.action_edit);
//        item1.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    //Al seleccionar una opcion del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_edit){
            //Iniciar Actividad Editar Perfil
            Intent intent = new Intent(MainActivity.this,PerfilEditarActivity.class);
            startActivity(intent);
        }
        return drawerToggle.onOptionsItemSelected(item);
    }
}
