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

import com.example.intern.myapplication.R;

import java.util.ArrayList;

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

        CheckedTextView dtEst = (CheckedTextView) getActivity().findViewById(R.id.checkedTextView);
        CheckedTextView dtEm = (CheckedTextView) getActivity().findViewById(R.id.checkedTextView7);
        CheckedTextView serieFac = (CheckedTextView) getActivity().findViewById(R.id.checkedTextView9);
        CheckedTextView responsabil = (CheckedTextView) getActivity().findViewById(R.id.checkedTextView10);
        CheckedTextView moneda = (CheckedTextView) getActivity().findViewById(R.id.checkedTextView11);
        CheckedTextView TVA = (CheckedTextView) getActivity().findViewById(R.id.checkedTextView12);

        dtEst.setText(String.valueOf(factura.getDtEstimata()));
        dtEm.setText(String.valueOf(factura.getDtEmitere()));
        serieFac.setText(String.valueOf(factura.getSerieFactura().getCod()) + String.valueOf(factura.getSerieFactura().getSecventa()));
        responsabil.setText(String.valueOf(factura.getAngajat().getNume()));
        moneda.setText(String.valueOf(factura.getMoneda().getNume()));
        TVA.setText(String.valueOf(factura.getCotaTVA().getProcent()));

        

    }

}


