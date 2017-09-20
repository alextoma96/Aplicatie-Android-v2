package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import Commons.Client;
import Commons.Factura;

public class ClientFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Client");
        Bundle bundle = this.getArguments();
        Factura factura = bundle.getParcelable("objectt");

        TextView numComp = (TextView) getActivity().findViewById(R.id.numClient);
        TextView codIn = (TextView) getActivity().findViewById(R.id.codInC);
        TextView codFis = (TextView) getActivity().findViewById(R.id.codFisC);
        TextView banca = (TextView) getActivity().findViewById(R.id.bancaC);
        TextView codIBAN = (TextView) getActivity().findViewById(R.id.codIBANC);
        TextView adresa = (TextView) getActivity().findViewById(R.id.adresaC);

        numComp.setText(String.valueOf(factura.getClient().getNume()));
        codIn.setText(String.valueOf(factura.getClient().getCodInregistrare()));
        codFis.setText(String.valueOf(factura.getClient().getCodFiscal()));
        banca.setText(String.valueOf(factura.getClient().getBanca()));
        codIBAN.setText(String.valueOf(factura.getClient().getIban()));
        adresa.setText(String.valueOf(factura.getClient().getAdresa()));
    }

}
