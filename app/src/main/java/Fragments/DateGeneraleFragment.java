package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.text.DateFormat;

import Commons.Factura;

public class DateGeneraleFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_generale, container, false);
    }

    @Override
    public void onCreate(Bundle saveInstancesState){
        super.onCreate(saveInstancesState);
        //setContentView(R.id.fragment_date_generale);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       

        getActivity().setTitle("Date Generale");
        final Bundle bundle = this.getArguments();
        final Factura factura = bundle.getParcelable("object");

        TextView dtEst = (TextView) getActivity().findViewById(R.id.dtEst);
        TextView dtEm = (TextView) getActivity().findViewById(R.id.dtEm);
        TextView serieCod = (TextView) getActivity().findViewById(R.id.serieCod);
        TextView serieSec = (TextView) getActivity().findViewById(R.id.serieSec);
        TextView responsabil = (TextView) getActivity().findViewById(R.id.responsabil);
        TextView moneda = (TextView) getActivity().findViewById(R.id.moneda);
        TextView TVA = (TextView) getActivity().findViewById(R.id.tva);
        TextView status = (TextView) getActivity().findViewById(R.id.status);
        TextView dtScad = (TextView) getActivity().findViewById(R.id.dtScad);
        TextView creatDe = (TextView) getActivity().findViewById(R.id.creatDe);
        TextView validatDe = (TextView) getActivity().findViewById(R.id.validatDe);
        TextView emisDe = (TextView) getActivity().findViewById(R.id.emisDe);


        dtEst.setText(vDtEst(factura));
        dtEm.setText(vDtEm(factura));
        serieCod.setText(vSerieCod(factura));
        serieSec.setText(vSerieSec(factura));
        responsabil.setText(vResponsabil(factura));
        moneda.setText(vMoneda(factura));
        TVA.setText(vTVA(factura));
        status.setText(vStatus(factura));
        dtScad.setText(vDtScad(factura));
        creatDe.setText(vCreatDe(factura));
        validatDe.setText(vValidatDe(factura));
        emisDe.setText(vEmisDe(factura));

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

    private String vDtEst(Factura factura){
        if (factura.getDtEstimata() != null)
            return DateFormat.getDateInstance().format(factura.getDtEstimata());
        else
            return "Nu exista inregistrare";
    }
    private String vDtEm(Factura factura){
        if (factura.getDtEmitere() != null)
            return DateFormat.getDateInstance().format(factura.getDtEmitere());
        else
            return "Nu exista inregistrare";
    }
    private String vSerieCod(Factura factura){
        if (factura.getSerieFactura() != null && factura.getSerieFactura().getCod() != null)
            return String.valueOf(factura.getSerieFactura().getCod());
        else
            return "Nu exista inregistrare";
    }
    private String vSerieSec(Factura factura){
        if (factura.getSerieFactura() != null && factura.getSerieFactura().getSecventa() != null)
            return String.valueOf(factura.getSerieFactura().getSecventa());
        else
            return "Nu exista inregistrare";
    }
    private String vResponsabil(Factura factura){
        if (factura.getAngajat() != null && factura.getAngajat().getNume() != null)
            return String.valueOf(factura.getAngajat().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vMoneda(Factura factura){
        if (factura.getMoneda() != null && factura.getMoneda().getNume() != null)
            return String.valueOf(factura.getMoneda().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vTVA(Factura factura){
        if (factura.getCotaTVA() != null && factura.getCotaTVA().getCod() != null)
            return String.valueOf(factura.getCotaTVA().getCod());
        else
            return "Nu exista inregistrare";
    }
    private String vStatus(Factura factura){
        if (factura.getStatusFactura() != null && factura.getStatusFactura().getStatus() != null)
            return String.valueOf(factura.getStatusFactura().getStatus());
        else
            return "Nu exista inregistrare";
    }
    private String vDtScad(Factura factura){
        if (factura.getDtScadenta() != null)
            return DateFormat.getDateInstance().format(factura.getDtScadenta());
        else
            return "Nu exista inregistrare";
    }
    private String vCreatDe(Factura factura){
        if (factura.getCreatDe() != null && factura.getCreatDe().getNume() != null)
            return String.valueOf(factura.getCreatDe().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vValidatDe(Factura factura){
        if (factura.getValidatDe() != null && factura.getValidatDe().getNume() != null)
            return String.valueOf(factura.getValidatDe().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vEmisDe(Factura factura){
        if (factura.getEmisDe() != null && factura.getEmisDe().getNume() != null)
            return String.valueOf(factura.getEmisDe().getNume());
        else
            return "Nu exista inregistrare";
    }
}


