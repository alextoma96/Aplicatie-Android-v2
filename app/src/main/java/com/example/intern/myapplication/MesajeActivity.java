package com.example.intern.myapplication;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Commons.Angajat;
import Commons.DestinatarMesaj;
import Commons.Factura;
import Commons.Mesaj;
import CustomAdapters.FacturaAdapter;
import CustomAdapters.MesajAdapter;
import Fragments.DateGeneraleFragment;
import Networking.HttpConnectionAngajat;
import Networking.HttpConnectionDestinatarMesaj;
import Networking.HttpConnectionFacturi;
import Networking.HttpConnectionMesaj;

public class MesajeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Integer imgid = R.drawable.readmessage;
    ListView lvMesaje;
    ArrayList<Mesaj> listaMesaje = new ArrayList<>();
    ArrayList<DestinatarMesaj> listaDestinatari = new ArrayList<>();
    ArrayList<Angajat> listaAngajati = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaje);
        consumeHttpConnectionDestinatari();
        consumeHttpConnectionAngajati();
        consumeHttpConnectionMesaj();
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
        lvMesaje = (ListView) findViewById(R.id.lista_lv_mesaje);
        if (listaMesaje != null) {
            //ArrayAdapter<Factura> adapter = new ArrayAdapter<Factura>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaFacturi);
            final MesajAdapter mesajAdapter = new MesajAdapter(this, listaMesaje, imgid);
            for(Mesaj m : listaMesaje) {
                Log.i("m", m.getTitlu());
            }
            lvMesaje.setAdapter(mesajAdapter);

            lvMesaje.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), MessageContentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("object", listaMesaje.get(position));
                    startActivity(intent);
                }
            });
        }
    }

    public void consumeHttpConnectionDestinatari() {
        HttpConnectionDestinatarMesaj connection = new HttpConnectionDestinatarMesaj() {
            @Override
            protected void onPostExecute(ArrayList<DestinatarMesaj> dest) {
                super.onPostExecute(dest);
                if(dest != null) {
                    listaDestinatari.addAll(dest);
                }
            }
        };
        connection.execute("http://192.168.8.98/kepres205/api/rs/destinatarmesaj/list");

    }

    public void consumeHttpConnectionAngajati() {
        HttpConnectionAngajat connection = new HttpConnectionAngajat() {
            @Override
            protected void onPostExecute(ArrayList<Angajat> angajats) {
                super.onPostExecute(angajats);
                if(angajats != null) {
                    listaAngajati.addAll(angajats);
                }
            }
        };
        connection.execute("http://192.168.8.98/kepres205/api/rs/angajat/list");

    }

    public void consumeHttpConnectionMesaj() {
        HttpConnectionMesaj connection = new HttpConnectionMesaj() {
            @Override
            protected void onPostExecute(ArrayList<Mesaj> mesajs) {
                init();
                super.onPostExecute(mesajs);
                if(mesajs != null) {
                    listaMesaje.addAll(mesajs);

                }
            }
        };
        connection.execute("http://192.168.8.98/kepres205/api/rs/mesaj/list");

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
            case R.id.nav_mesaj:
                Intent mesajIntent = new Intent(getApplicationContext(),MesajeActivity.class);
                startActivity(mesajIntent);
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
