package ga.finappsparty.com.gather.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kevinsotomayor on 25/10/14.
 */
public class BoteObject implements Parcelable {
    private String pot_name;
    private String name;
    private String subname;
    private String pot_concept;
    private String iban;

    public BoteObject(String pot_name, String pot_concept, String iban, String subname, String name) {
        this.pot_name = pot_name;
        this.pot_concept = pot_concept;
        this.iban = iban;
        this.subname = subname;
        this.name = name;
    }

    public String getPot_name() {

        return pot_name;
    }

    public void setPot_name(String pot_name) {
        this.pot_name = pot_name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPot_concept() {
        return pot_concept;
    }

    public void setPot_concept(String pot_concept) {
        this.pot_concept = pot_concept;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BoteObject (Parcel in){
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public void readFromParcel(Parcel in){
        pot_name = in.readString();
        name = in.readString();
        subname=in.readString();
        pot_concept=in.readString();
        iban = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pot_name);
        dest.writeString(name);
        dest.writeString(subname);
        dest.writeString(pot_concept);
        dest.writeString(iban);
    }
    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public BoteObject createFromParcel(Parcel in){
            return new BoteObject(in);
        }
        public BoteObject[] newArray(int size){
            return new BoteObject[size];
        }
    };
}
