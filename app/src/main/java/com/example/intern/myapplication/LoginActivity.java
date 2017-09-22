package com.example.intern.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Commons.Utilizator;
import Networking.HttpConnectionUtilizatori;

public class LoginActivity extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Login");
        init();
        setHasOptionsMenu(true);
    }


    private void init() {
        consumeHttpConnection();
        etusername = (EditText) getActivity().findViewById(R.id.et_login_username);
        etpassword = (EditText) getActivity().findViewById(R.id.et_login_password);
        cbrmbrcred = (CheckBox) getActivity().findViewById(R.id.cb_login_remember);
        btnLogin = (Button) getActivity().findViewById(R.id.btn_login_button);
        boolean cb;
        if(cbrmbrcred.isChecked()){
            cb=true;
        }else{
            cb=false;
        }
        preferenceSettings = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        boolean preferenceCb=preferenceSettings.getBoolean(CHECKBOX_PREFERENCE_KEY, cb);
        String preferenceUsername = preferenceSettings.getString(USERNAME_PREFERENCE_KEY, username);
        String preferencePassword = preferenceSettings.getString(PASSWORD_PREFERENCE_KEY, password);
        Log.d(preferenceUsername, preferencePassword);
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
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        preferenceEditor.putBoolean(CHECKBOX_PREFERENCE_KEY, cb);
                        preferenceEditor.putString(USERNAME_PREFERENCE_KEY, username);
                        preferenceEditor.putString(PASSWORD_PREFERENCE_KEY, password);
                        boolean successfulSave = preferenceEditor.commit();
                    } else{
                        Toast.makeText(getContext(),R.string.login_toast2, Toast.LENGTH_SHORT).show();
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
                for(Utilizator u : userList) {
                    Log.i("user", u.getNume());
                }
            }
        };
        connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ip", "192.168.8.98") + "/kepres204/api/rs/utilizator/list");
    }


}