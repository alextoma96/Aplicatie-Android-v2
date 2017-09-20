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

import Commons.Factura;
import Commons.IdentitateCompanie;

public class FurnizorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_furnizor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Furnizor");
        Bundle bundle = this.getArguments();
        IdentitateCompanie companie = bundle.getParcelable("object");

        TextView numComp = (TextView) getActivity().findViewById(R.id.numComp);
        TextView codIn = (TextView) getActivity().findViewById(R.id.codIn);
        TextView codFis = (TextView) getActivity().findViewById(R.id.codFis);
        TextView banca = (TextView) getActivity().findViewById(R.id.banca);
        TextView codIBAN = (TextView) getActivity().findViewById(R.id.codIBAN);
        TextView adresa = (TextView) getActivity().findViewById(R.id.adresa);

        numComp.setText(String.valueOf(companie.getNume()));
        codIn.setText(String.valueOf(companie.getCodInregistrare()));
        codFis.setText(String.valueOf(companie.getCodFiscal()));
        banca.setText(String.valueOf(companie.getBanca()));
        codIBAN.setText(String.valueOf(companie.getIban()));
        adresa.setText(String.valueOf(companie.getAdresa()));

    }
}
