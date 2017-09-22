package com.example.intern.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Commons.Utilizator;
import Networking.HttpConnectionUtilizatori;


public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText etusername;
    private EditText etpassword;
    private CheckBox cbrmbrcred;
    Button btnLogin;

    String users=null;
    String pass=null;
    Integer id=null;

    List<Utilizator> userList = new ArrayList<>();

    private String USERNAME_PREFERENCE_KEY="username";

    private String PASSWORD_PREFERENCE_KEY="password";
    private String CHECKBOX_PREFERENCE_KEY="checkbox";

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
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
        consumeHttpConnection();
        etusername = (EditText) findViewById(R.id.et_login_username);
        etpassword = (EditText) findViewById(R.id.et_login_password);
        cbrmbrcred = (CheckBox) findViewById(R.id.cb_login_remember);
        btnLogin = (Button) findViewById(R.id.btn_login_button);
        boolean cb;
        if(cbrmbrcred.isChecked()){
            cb=true;
        }else{
            cb=false;
        }
        preferenceSettings = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        boolean preferenceCb=preferenceSettings.getBoolean(CHECKBOX_PREFERENCE_KEY, cb);
        String preferenceUsername = preferenceSettings.getString(USERNAME_PREFERENCE_KEY, username);
        String preferencePassword = preferenceSettings.getString(PASSWORD_PREFERENCE_KEY, password);
        if (preferenceUsername != null && preferencePassword != null && preferenceCb==true) {
            etusername.setText(preferenceUsername);
            etpassword.setText(preferencePassword);
            cbrmbrcred.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean cb;
                if(v.getId()==R.id.btn_login_button){
                    if(cbrmbrcred.isChecked()){
                        cb=true;
                    }else{
                        cb=false;
                    }
                    final String username=etusername.getText().toString();
                    final String password=etpassword.getText().toString();
                    if(validation(username, password)){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        preferenceEditor.putBoolean(CHECKBOX_PREFERENCE_KEY, cb);
                        preferenceEditor.putString(USERNAME_PREFERENCE_KEY, username);
                        preferenceEditor.putString(PASSWORD_PREFERENCE_KEY, password);
                    } else{
                        Toast.makeText(getApplicationContext(),R.string.login_toast2, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean validation(String u, String p) {
        for (Utilizator user : userList) {
            if (u.equals(user.getUsername()) && p.equals(user.getParola())) {
                users=user.getUsername();
                pass=user.getParola();
            }
        }
        if(users!=null && pass!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public void consumeHttpConnection() {
        HttpConnectionUtilizatori connection = new HttpConnectionUtilizatori() {
            @Override
            protected void onPostExecute(ArrayList<Utilizator> utilizators) {
                super.onPostExecute(utilizators);
                if (utilizators != null) {
                    userList.addAll(utilizators);
                }
            }
        };
        connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("ip", "192.168.8.98") + "/kepres204/api/rs/utilizator/list");
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
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}
