package Commons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by intern on 9/22/2017.
 */

public class DestinatarMesaj implements Parcelable {
    private String nume;
    private String email;
    private String telefon;
    private Mesaj mesaj;

    public DestinatarMesaj(String nume, String email, String telefon, Mesaj mesaj) {
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
        this.mesaj = mesaj;
    }

    protected DestinatarMesaj(Parcel in) {
        nume = in.readString();
        email = in.readString();
        telefon = in.readString();
        mesaj = in.readParcelable(Mesaj.class.getClassLoader());
    }

    public static final Creator<DestinatarMesaj> CREATOR = new Creator<DestinatarMesaj>() {
        @Override
        public DestinatarMesaj createFromParcel(Parcel in) { return new DestinatarMesaj(in); }

        @Override
        public DestinatarMesaj[] newArray(int size) { return new DestinatarMesaj[size]; }
    };

    public String getNume() { return nume; }
    public DestinatarMesaj setNume(String nume) { this.nume = nume; return this;}

    public String getEmail() { return email; }
    public DestinatarMesaj setEmail(String email) { this.email = email; return this;}

    public String getTelefon() { return telefon; }
    public DestinatarMesaj setTelefon(String telefon) { this.telefon = telefon; return this; }

    public Mesaj getAngajat() { return mesaj; }
    public DestinatarMesaj setMesaj(Mesaj mesaj) { this.mesaj = mesaj; return this;}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(email);
        dest.writeString(telefon);
        dest.writeParcelable(mesaj, flags);
    }

    @Override
    public String toString() {
        String destinatar = "Nume: " + nume +
                ", \nE-mail: " + email +
                ", \nTelefon: " + telefon +
                ", \nMesaj: ";
        if (mesaj != null && mesaj.getContinut() != null)
            destinatar = destinatar + mesaj.getContinut();
        else
            destinatar = destinatar + "null";
        return   destinatar;
    }
}