package com.example.intern.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import Commons.Factura;
import CustomAdapters.FacturaAdapter;
import Fragments.ClientFragment;
import Fragments.DateGeneraleFragment;
import Fragments.FurnizorFragment;
import Networking.HttpConnectionFacturi;

public class FacturiActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String STATUS_PREFERENCE_KEY = "status";
    private String CLIENT_PREFERENCE_KEY = "client";
    Integer imgid = R.drawable.factura;
    ListView lvFacturi;
    ArrayList<Factura> listaFacturi = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturi);
        Button butonDateGen = (Button) findViewById(R.id.flow_date_gen);
        Button butonClienti = (Button) findViewById(R.id.flow_clienti);
        Button butonFurizor = (Button) findViewById(R.id.flow_furnizor);
        butonDateGen.setVisibility(View.GONE);
        butonClienti.setVisibility(View.GONE);
        butonFurizor.setVisibility(View.GONE);
        consumeHttpConnection();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {
        lvFacturi = (ListView) findViewById(R.id.lista_lv_facturi);
        if (listaFacturi != null) {
            //ArrayAdapter<Factura> adapter = new ArrayAdapter<Factura>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaFacturi);
            final FacturaAdapter facturaAdapter = new FacturaAdapter(this, listaFacturi, imgid);
            lvFacturi.setAdapter(facturaAdapter);
            lvFacturi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Fragment fragment = new DateGeneraleFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();

                    bundle.putParcelable("object", listaFacturi.get(position));
                    fragment.setArguments(bundle);
                    ft.replace(R.id.activity_facturi_list, fragment);
                    ft.commit();
                }
            });

        }


    }

    public void consumeHttpConnection() {
        HttpConnectionFacturi connection = new HttpConnectionFacturi() {
            @Override
            protected void onPostExecute(ArrayList<Factura> facturas) {
                init();
                super.onPostExecute(facturas);
                if(facturas != null) {
                    listaFacturi.addAll(facturas);
                }
            }
        };
        connection.execute("https://api.myjson.com/bins/1adho1");

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void displaySelectedScreen(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_facturi:
                Intent statusIntent = new Intent(getApplicationContext(), StatusActivity.class);
                startActivity(statusIntent);
                break;
            case R.id.nav_aboutUs:
                fragment = new DetailsActivity();
                break;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.nav_utilizatori:
                Intent userIntent = new Intent(getApplicationContext(), UtilizatoriActivity.class);
                startActivity(userIntent);
                break;
            case R.id.nav_login:
                Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}
