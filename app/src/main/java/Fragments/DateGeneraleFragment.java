package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Commons.Factura;

public class DateGeneraleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_generale, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       

        getActivity().setTitle("Date Generale");
        Bundle bundle = this.getArguments();
        Factura factura = bundle.getParcelable("object");

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


        dtEst.setText(DateFormat.getDateInstance().format(factura.getDtEstimata()));
        dtEm.setText(DateFormat.getDateInstance().format(factura.getDtEmitere()));
        serieCod.setText(String.valueOf(factura.getSerieFactura().getCod()));
        serieSec.setText(String.valueOf(factura.getSerieFactura().getSecventa()));
        responsabil.setText(String.valueOf(factura.getAngajat().getNume()));
        moneda.setText(String.valueOf(factura.getMoneda().getNume()));
        TVA.setText(String.valueOf(factura.getCotaTVA().getProcent()));
        status.setText(String.valueOf(factura.getStatusFactura().getStatus()));
        dtScad.setText(DateFormat.getDateInstance().format(factura.getDtScadenta()));
        creatDe.setText(String.valueOf(factura.getCreatDe().getNume()));
        validatDe.setText(String.valueOf(factura.getValidatDe().getNume()));
        emisDe.setText(String.valueOf(factura.getEmisDe()));
    }

}


