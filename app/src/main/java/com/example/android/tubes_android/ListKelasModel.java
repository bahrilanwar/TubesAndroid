package com.example.android.tubes_android;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by user on 08-May-18.
 */

public class ListKelasModel implements Parcelable{

    private String id;
    private String classcode;
    private String classname;
    private String time;
    private String day;
    private String status;

    public ListKelasModel(){}

    public ListKelasModel(String id, String classcode, String classname, String time, String day, String status){
        this.id = id;
        this.classcode = classcode;
        this.classname = classname;
        this.time = time;
        this.day = day;
        this.status = status;
    }

    public static final Parcelable.Creator<ListKelasModel> CREATOR = new Parcelable.Creator<ListKelasModel>() {
        @Override
        public ListKelasModel createFromParcel(Parcel in) {
            return new ListKelasModel(in);
        }

        @Override
        public ListKelasModel[] newArray(int size) {
            return new ListKelasModel[size];
        }
    };

    public String getId(){
        return id;
    }

    public String getClasscode(){
        return classcode;
    }

    public String getClassname(){
        return classname;
    }

    public String getTime(){
        return time;
    }

    public String getDay(){
        return day;
    }

    public String getStatus(){
        return status;
    }

    public ListKelasModel(Parcel in) {
        this.id = in.readString();
        this.classcode = in.readString();
        this.classname = in.readString();
        this.time = in.readString();
        this.day = in.readString();
        this.status = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.classcode);
        parcel.writeString(this.classname);
        parcel.writeString(this.time);
        parcel.writeString(this.day);
        parcel.writeString(this.status);
    }
}
