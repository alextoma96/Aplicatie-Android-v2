package com.example.intern.myapplication;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Commons.StatusCount;
import Networking.HttpConnectionStatus;
import Utils.Constant;

public class StatusActivity extends Fragment implements Constant {
    public static String status = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        consumeHttpConnection();
        return inflater.inflate(R.layout.activity_status, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Status factura");
    }

    ListView lvStatus;
    ArrayList<StatusCount> listaStatus = new ArrayList<>();

    public void initComponents() {
        lvStatus = (ListView) getActivity().findViewById(R.id.lista_lv_status);
        final ArrayAdapter<StatusCount> adapter = new ArrayAdapter<StatusCount>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaStatus);
        lvStatus.setAdapter(adapter);
        lvStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StatusCount statusObj = adapter.getItem(position);
                status = statusObj.getStatus();
                Fragment fragment = new ClientActivity();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
            }
        });

    }

    public void consumeHttpConnection() {
        HttpConnectionStatus connection = new HttpConnectionStatus() {
            @Override
            protected void onPostExecute(ArrayList<StatusCount> status) {
                initComponents();
                super.onPostExecute(status);
                if (status != null) {
                    listaStatus.addAll(status);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.toast_lvStatus), Toast.LENGTH_SHORT).show();
                }
            }
        };
        connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ip", "192.168.196.2:8080") + "/kepres2Web/api/rs/statusCount/list");
    }


}
