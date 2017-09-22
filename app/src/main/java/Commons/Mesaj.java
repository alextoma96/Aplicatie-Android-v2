package Commons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by intern on 9/22/2017.
 */

public class Mesaj implements Parcelable {
    private String titlu;
    private String continut;
    private String trimis;
    private String citit;

    public Mesaj(String titlu, String continut, String trimis, String citit) {
        this.titlu = titlu;
        this.continut = continut;
        this.trimis = trimis;
        this.citit = citit;
    }

    protected Mesaj(Parcel in) {
        titlu = in.readString();
        continut = in.readString();
        trimis = in.readString();
        citit = in.readString();
    }

    public static final Creator<Mesaj> CREATOR = new Creator<Mesaj>() {
        @Override
        public Mesaj createFromParcel(Parcel in) { return new Mesaj(in); }

        @Override
        public Mesaj[] newArray(int size) { return new Mesaj[size]; }
    };

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
        dest.writeString(titlu);
        dest.writeString(continut);
        dest.writeString(trimis);
        dest.writeString(citit);
    }

    @Override
    public String toString() {
        String utilizator = "Titlu: " + titlu +
                ", \nContinut: " + continut +
                ", \nTrimis: " + trimis +
                ",\nCitit: " + citit ;

    }
}
