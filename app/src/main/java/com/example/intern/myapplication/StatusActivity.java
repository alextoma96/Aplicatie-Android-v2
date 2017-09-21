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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Commons.StatusCount;
import CustomAdapters.StatusAdapter;
import Networking.HttpConnectionStatus;
import Utils.Constant;

public class StatusActivity extends Fragment implements Constant {
    public static String status = "";

    Integer[] imgid = {
            R.drawable.validated,
            R.drawable.archived,
            R.drawable.finished,
            R.drawable.emitted,
            R.drawable.draft,
            R.drawable.activated
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        consumeHttpConnection();
        return inflater.inflate(R.layout.activity_status, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Facturi per status");
    }

    ListView lvStatus;
    ArrayList<StatusCount> listaStatus = new ArrayList<>();

    public void initComponents() {
        lvStatus = (ListView) getActivity().findViewById(R.id.lista_lv_status);
        //final ArrayAdapter<StatusCount> adapter = new ArrayAdapter<StatusCount>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaStatus);
        final StatusAdapter statusAdapter = new StatusAdapter(this.getActivity(), listaStatus, imgid);
        lvStatus.setAdapter(statusAdapter);
        lvStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StatusCount statusObj = statusAdapter.getItem(position);
                status = statusObj.getStatus();
                    Fragment fragment = new FacturiActivity();
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
        connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ip", "192.168.8.98") + "/kepres204/api/rs/statusCount/list");
    }


}
