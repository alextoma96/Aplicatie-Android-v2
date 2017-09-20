package com.example.intern.myapplication;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Commons.Factura;
import Fragments.ArticoleFragment;
import Fragments.DateGeneraleFragment;
import Networking.HttpConnectionFacturi;
import Utils.Constant;



public class FacturiActivity extends Fragment implements Constant{
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
        lvFacturi = (ListView) getActivity().findViewById(R.id.lista_lv_facturi);
        ArrayAdapter<Factura> adapter = new ArrayAdapter<Factura>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaFacturi);
        lvFacturi.setAdapter(adapter);
        lvFacturi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getActivity().getApplicationContext(), FacturaDetailsActivity.class);
                intent.putExtra(FACTURA_KEY, position);
                startActivity(intent);*/
                Fragment fragment = new DateGeneraleFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                //int idd = (int) id;
                //bundle.putParcelableArray("factura", listaFacturi.get((int) id));
                bundle.putString("dtEstEm", listaFacturi.get((int) id).getDtEstimata().toString().substring(0,10));
                bundle.putString("dtEm", listaFacturi.get((int) id).getDtEmitere().toString().substring(0,10));
                bundle.putString("serieFac", listaFacturi.get((int) id).getSerieFactura().getCod().toString() + listaFacturi.get((int) id).getSerieFactura().getSecventa().toString());
                bundle.putString("responsabil", listaFacturi.get((int) id).getAngajat().getNume());
                bundle.putString("moneda", listaFacturi.get((int) id).getMoneda().getNume());
                bundle.putString("TVA", listaFacturi.get((int) id).getCotaTVA().getNume());
                //bundle.putString("ceva", listaFacturi.get(idd).getClient().getNume().toString());
                fragment.setArguments(bundle);
                ft.replace(R.id.content_main, fragment);
                ft.commit();
            }
        });
    }

    public void consumeHttpConnection() {
        HttpConnectionFacturi connection = new HttpConnectionFacturi() {
            @Override
            protected void onPostExecute(ArrayList<Factura> facturas) {
                initComponents();
                super.onPostExecute(facturas);
                if(facturas != null) {
                    listaFacturi.addAll(facturas);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.toast_lvfacturi), Toast.LENGTH_SHORT).show();
                }
            }
        };
        connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ip", "192.168.8.98") + "/kepres203/api/rs/factura/list");
    }

}
