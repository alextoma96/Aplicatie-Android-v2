package Commons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by idanciu on 9/12/2017.
 */

public class StatusCount implements Parcelable{
    private String status;
    private Integer nrFacturi;

    public StatusCount(String status, Integer nrFacturi) {
        this.status = status;
        this.nrFacturi = nrFacturi;
    }

    protected StatusCount(Parcel in) {
        status = in.readString();
        nrFacturi = in.readInt();
    }

    public static final Creator<StatusCount> CREATOR = new Creator<StatusCount>() {
        @Override
        public StatusCount createFromParcel(Parcel in) { return new StatusCount(in); }

        @Override
        public StatusCount[] newArray(int size) { return new StatusCount[size]; }
    };

    public String getStatus() { return status; }
    public StatusCount setStatus(String status) { this.status = status; return this;}

    public Integer getNrFacturi() { return nrFacturi; }
    public void setNrFacturi(Integer nrFacturi) { this.nrFacturi = nrFacturi; }

    @Override
    public String toString() {
        String facturi = "";
        if(nrFacturi == 1) {
            facturi = "factura";
        } else {
            facturi = "facturi";
        }
        return status + " (" + nrFacturi +  " " + facturi + ")";
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(nrFacturi);
    }
}
