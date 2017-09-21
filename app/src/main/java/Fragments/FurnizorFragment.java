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
        final Bundle bundle = this.getArguments();
        Factura factura = bundle.getParcelable("object");

        TextView numComp = (TextView) getActivity().findViewById(R.id.numComp);
        TextView codIn = (TextView) getActivity().findViewById(R.id.codIn);
        TextView codFis = (TextView) getActivity().findViewById(R.id.codFis);
        TextView banca = (TextView) getActivity().findViewById(R.id.banca);
        TextView codIBAN = (TextView) getActivity().findViewById(R.id.codIBAN);
        TextView adresa = (TextView) getActivity().findViewById(R.id.adresa);

        numComp.setText(vNumComp(factura));
        codIn.setText(vCodIn(factura));
        codFis.setText(vCodFis(factura));
        banca.setText(vBanca(factura));
        codIBAN.setText(vIBAN(factura));
        adresa.setText(vAdresa(factura));

        Button butonFacturi = (Button) getActivity().findViewById(R.id.flow_date_gen);

        butonFacturi.setOnClickListener(new View.OnClickListener() {
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
        if (factura.getIdentitateCompanie() != null && factura.getIdentitateCompanie().getNume() != null)
            return String.valueOf(factura.getIdentitateCompanie().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vCodIn(Factura factura){
        if (factura.getIdentitateCompanie() != null && factura.getIdentitateCompanie().getCodInregistrare() != null)
            return String.valueOf(factura.getIdentitateCompanie().getCodInregistrare());
        else
            return "Nu exista inregistrare";
    }
    private String vCodFis(Factura factura){
        if (factura.getIdentitateCompanie() != null && factura.getIdentitateCompanie().getCodFiscal() != null)
            return String.valueOf(factura.getIdentitateCompanie().getCodFiscal());
        else
            return "Nu exista inregistrare";
    }
    private String vBanca(Factura factura){
        if (factura.getIdentitateCompanie() != null && factura.getIdentitateCompanie().getBanca() != null)
            return String.valueOf(factura.getIdentitateCompanie().getBanca());
        else
            return "Nu exista inregistrare";
    }
    private String vIBAN(Factura factura){
        if (factura.getIdentitateCompanie() != null && factura.getIdentitateCompanie().getIban() != null)
            return String.valueOf(factura.getIdentitateCompanie().getIban());
        else
            return "Nu exista inregistrare";
    }
    private String vAdresa(Factura factura){
        if (factura.getIdentitateCompanie() != null && factura.getIdentitateCompanie().getAdresa() != null)
            return String.valueOf(factura.getIdentitateCompanie().getAdresa());
        else
            return "Nu exista inregistrare";
    }
}
