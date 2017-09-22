package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.intern.myapplication.FacturiActivity;
import com.example.intern.myapplication.OnSwipeTouchListener;
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

        TextView numComp = (TextView) getActivity().findViewById(R.id.c1);
        TextView codIn = (TextView) getActivity().findViewById(R.id.c2);
        TextView codFis = (TextView) getActivity().findViewById(R.id.c3);
        TextView banca = (TextView) getActivity().findViewById(R.id.c4);
        TextView codIBAN = (TextView) getActivity().findViewById(R.id.c5);
        TextView adresa = (TextView) getActivity().findViewById(R.id.c6);


        String numC = "<b>" + "Nume client: " + "</b>" + vNumComp(factura);
        String codI = "<b>" + "Cod inregistrare: " + "</b>" + vCodIn(factura);
        String codF = "<b>" + "Cod fiscal : " + "</b>" + vCodFis(factura);
        String ban = "<b>" + "Banca: " + "</b>" + vBanca(factura);
        String iban = "<b>" + "Cod IBAN: " + "</b>" + vIBAN(factura);
        String adr = "<b>" + "Adresa: " + "</b>" + vAdresa(factura);

        numComp.setText(Html.fromHtml(numC, Html.FROM_HTML_MODE_LEGACY));
        codIn.setText(Html.fromHtml(codI, Html.FROM_HTML_MODE_LEGACY));
        codFis.setText(Html.fromHtml(codF, Html.FROM_HTML_MODE_LEGACY));
        banca.setText(Html.fromHtml(ban, Html.FROM_HTML_MODE_LEGACY));
        codIBAN.setText(Html.fromHtml(iban, Html.FROM_HTML_MODE_LEGACY));
        adresa.setText(Html.fromHtml(adr, Html.FROM_HTML_MODE_LEGACY));


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

        view.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                Fragment fragment = new FurnizorFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }

            @Override
            public void onSwipeRight() {
                Fragment fragment = new DateGeneraleFragment();
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
