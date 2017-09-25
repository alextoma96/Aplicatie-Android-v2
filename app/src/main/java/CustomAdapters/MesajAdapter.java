package CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.util.List;

import Commons.Mesaj;
import Commons.StatusCount;

/**
 * Created by idanciu on 9/25/2017.
 */

public class MesajAdapter extends ArrayAdapter<Mesaj> {
    private final Context context;
    private List<Mesaj> list;
    private Integer imgid[];

    int[] listItemBackground = new int[] { R.layout.list_background1,
            R.layout.list_background2 };


    public MesajAdapter(Activity context, List<Mesaj> itemname, Integer imgid[]) {
        super(context, R.layout.mesaj_list, itemname);
        this.context = context;
        this.list = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mesaj_list, null,true);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView titlu = (TextView) rowView.findViewById(R.id.tv_mesaj_titlu);
        TextView data = (TextView) rowView.findViewById(R.id.tv_data);
        TextView continut = (TextView) rowView.findViewById(R.id.tv_continut);
        TextView expeditorLabel = (TextView) rowView.findViewById(R.id.tv_expeditor_label);
        TextView expeditor = (TextView) rowView.findViewById(R.id.tv_expeditor);


        imageView.setImageResource(imgid[position]);
        titlu.setText((CharSequence) list.get(position).getTitlu());
        data.setText((CharSequence) String.valueOf(list.get(position).getData()));
        continut.setText((CharSequence) list.get(position).getContinut());
        expeditorLabel.setText("Expeditor: ");
        expeditor.setText((CharSequence) list.get(position).getExpeditor().getNume());
        return rowView;

    };
}