package jp.developer.bbee.myapplication.sales.shop;

import android.os.Parcel;
import android.os.Parcelable;

public class Shop implements Parcelable {
    final private String id;
    final private String name;
    final private String address;

    public Shop(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    protected Shop(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(address);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
}
