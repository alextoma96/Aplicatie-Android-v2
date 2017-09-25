package Commons;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by intern on 9/22/2017.
 */

public class Mesaj implements Parcelable {
    private Integer id;
    private Long data;
    private String titlu;
    private String continut;
    private String trimis;
    private String citit;
    private Angajat expeditor;

    public Mesaj(Integer id, Long data, String titlu, String continut, String trimis, String citit, Angajat expeditor) {
        this.id = id;
        this.data = data;
        this.titlu = titlu;
        this.continut = continut;
        this.trimis = trimis;
        this.citit = citit;
        this.expeditor = expeditor;
    }

    protected Mesaj(Parcel in) {
        id = in.readInt();
        data = in.readLong();
        titlu = in.readString();
        continut = in.readString();
        expeditor = in.readParcelable(Angajat.class.getClassLoader());
    }

    public static final Creator<Mesaj> CREATOR = new Creator<Mesaj>() {
        @Override
        public Mesaj createFromParcel(Parcel in) { return new Mesaj(in); }

        @Override
        public Mesaj[] newArray(int size) { return new Mesaj[size]; }
    };

    public Long getData() { return data; }
    public Mesaj setData(Long data) { this.data = data; return this;}

    public Angajat getExpeditor() { return expeditor; }
    public Mesaj setExpeditor(Angajat expeditor) { this.expeditor = expeditor; return this;}

    public Integer getId() { return id; }
    public Mesaj setId(Integer id) { this.id = id; return this;}

    public String getTitlu() { return titlu; }
    public Mesaj setTitlu(String titlu) { this.titlu = titlu; return this;}

    public String getContinut() { return continut; }
    public Mesaj setContinut(String continut) { this.continut = continut; return this;}

    public String getTrimis() { return trimis; }
    public Mesaj setTrimis(String trimis) { this.trimis = trimis; return this; }

    public String getCitit() { return citit; }
    public Mesaj setCitit(String citit) { this.citit = citit; return this;}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(data);
        dest.writeString(titlu);
        dest.writeString(continut);
        dest.writeString(trimis);
        dest.writeString(citit);
        dest.writeParcelable(expeditor, flags);
    }

}
