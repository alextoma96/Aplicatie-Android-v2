package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.intern.myapplication.R;

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
        final Bundle bundle = this.getArguments();
        Factura factura = bundle.getParcelable("object");

        TextView numComp = (TextView) getActivity().findViewById(R.id.numClient);
        TextView codIn = (TextView) getActivity().findViewById(R.id.codInC);
        TextView codFis = (TextView) getActivity().findViewById(R.id.codFisC);
        TextView banca = (TextView) getActivity().findViewById(R.id.bancaC);
        TextView codIBAN = (TextView) getActivity().findViewById(R.id.codIBANC);
        TextView adresa = (TextView) getActivity().findViewById(R.id.adresaC);

        numComp.setText(vNumComp(factura));
        codIn.setText(vCodIn(factura));
        codFis.setText(vCodFis(factura));
        banca.setText(vBanca(factura));
        codIBAN.setText(vIBAN(factura));
        adresa.setText(vAdresa(factura));


        Button butonDateGen = (Button) getActivity().findViewById(R.id.flow_date_gen);

        butonDateGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DateGeneraleFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });

        Button butonFurizor = (Button) getActivity().findViewById(R.id.flow_furnizor);

        butonFurizor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FurnizorFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });

        Button butonClienti = (Button) getActivity().findViewById(R.id.flow_clienti);

        butonClienti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ClientFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });
    }
    private String vNumComp(Factura factura){
        if (factura.getClient() != null && factura.getClient().getNume() != null)
            return String.valueOf(factura.getClient().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vCodIn(Factura factura){
        if (factura.getClient() != null && factura.getClient().getCodInregistrare() != null)
            return String.valueOf(factura.getClient().getCodInregistrare());
        else
            return "Nu exista inregistrare";
    }
    private String vCodFis(Factura factura){
        if (factura.getClient() != null && factura.getClient().getCodFiscal() != null)
            return String.valueOf(factura.getClient().getCodFiscal());
        else
            return "Nu exista inregistrare";
    }
    private String vBanca(Factura factura){
        if (factura.getClient() != null && factura.getClient().getBanca() != null)
            return String.valueOf(factura.getClient().getBanca());
        else
            return "Nu exista inregistrare";
    }
    private String vIBAN(Factura factura){
        if (factura.getClient() != null && factura.getClient().getIban() != null)
            return String.valueOf(factura.getClient().getIban());
        else
            return "Nu exista inregistrare";
    }
    private String vAdresa(Factura factura){
        if (factura.getClient() != null && factura.getClient().getAdresa() != null)
            return String.valueOf(factura.getClient().getAdresa());
        else
            return "Nu exista inregistrare";
    }
}
