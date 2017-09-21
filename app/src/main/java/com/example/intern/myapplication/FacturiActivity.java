package com.example.intern.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Commons.Factura;
import Fragments.ArticoleFragment;
import Fragments.DateGeneraleFragment;
import Networking.HttpConnectionFacturi;
import Utils.Constant;



public class FacturiActivity extends Fragment implements Constant{

    private String STATUS_PREFERENCE_KEY = "status";
    private String CLIENT_PREFERENCE_KEY = "client";
    TextView tvNoInvoice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        consumeHttpConnection();
        return inflater.inflate(R.layout.activity_facturi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Facturi");
    }

    ListView lvFacturi;
    ArrayList<Factura> listaFacturi = new ArrayList<>();


    public void initComponents() {
        tvNoInvoice = (TextView) getActivity().findViewById(R.id.textNoInvoice);
        lvFacturi = (ListView) getActivity().findViewById(R.id.lista_lv_facturi);
        if (listaFacturi != null) {
            ArrayAdapter<Factura> adapter = new ArrayAdapter<Factura>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaFacturi);
            lvFacturi.setAdapter(adapter);
            lvFacturi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Fragment fragment = new DateGeneraleFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();

                /*bundle.putString("dtEstEm", listaFacturi.get((int) id).getDtEstimata().toString().substring(0,10));
                bundle.putString("dtEm", listaFacturi.get((int) id).getDtEmitere().toString().substring(0,10));
                bundle.putString("serieFac", listaFacturi.get((int) id).getSerieFactura().getCod().toString() + listaFacturi.get((int) id).getSerieFactura().getSecventa().toString());
                bundle.putString("responsabil", listaFacturi.get((int) id).getAngajat().getNume());
                bundle.putString("moneda", listaFacturi.get((int) id).getMoneda().getNume());
                bundle.putString("TVA", listaFacturi.get((int) id).getCotaTVA().getNume());*/

                    bundle.putParcelable("object", listaFacturi.get(position));
                    fragment.setArguments(bundle);
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                }
            });
        } else {
            tvNoInvoice.setText(getResources().getString(R.string.toast_lvfacturi));
        }
    }

    public void consumeHttpConnection() {
        HttpConnectionFacturi connection = new HttpConnectionFacturi() {
            @Override
            protected void onPostExecute(ArrayList<Factura> facturas) {
                initComponents();
                super.onPostExecute(facturas);
                if(facturas != null) {
                    listaFacturi.addAll(facturas);
                }
            }
        };
        connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ip", "192.168.8.98") + "/kepres204/api/rs/factura/list");
    }

}
