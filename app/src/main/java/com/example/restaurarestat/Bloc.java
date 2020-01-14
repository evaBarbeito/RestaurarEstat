package com.example.restaurarestat;

import android.os.Parcel;
import android.os.Parcelable;

public class Bloc implements Parcelable {

    /*Parcelable interface, 14/1/2020
     is a faster alternative to Java's Serializable interface,
     to send a custom object from our MainActivity to another activity,
      after we click that particular item in a RecyclerView.
     */
    private String horaInici;
    private String temperatura;
    private String humitat;

    public Bloc(String horaInici, String temperatura, String tempsCalorFred){
        this.horaInici = horaInici;
        this.temperatura = temperatura;
        this.humitat = tempsCalorFred;

    }

    //idem ordre
    protected Bloc(Parcel in) {
        horaInici = in.readString();
        temperatura = in.readString();
        humitat = in.readString();
    }

    public static final Creator<Bloc> CREATOR = new Creator<Bloc>() {
        @Override
        public Bloc createFromParcel(Parcel in) {
            return new Bloc(in);
        }

        @Override
        public Bloc[] newArray(int size) {
            return new Bloc[size];
        }
    };

    public String getHoraInici() {
        return horaInici;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getHumitat() {
        return humitat;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void setHumitat(String humitat) {
        this.humitat = humitat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //ha de seguir mateix ordre que el constructor Bloc(Parcel in)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(horaInici);
        dest.writeString(temperatura);
        dest.writeString(humitat);
    }
}
