package com.example.android.tubes_android;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by user on 08-May-18.
 */

public class ListKelasModel implements Parcelable{

    private String id;
    private String user;
    private String classcode;
    private String classname;
    private String time;
    private String day;
    private String status;
    private String needs;
    private String idnumber;
    private String pn;

    public ListKelasModel(){}

    public ListKelasModel(String id, String user, String classcode, String classname, String time, String day, String status, String needs, String idnumber, String pn){
        this.id = id;
        this.user = user;
        this.classcode = classcode;
        this.classname = classname;
        this.time = time;
        this.day = day;
        this.status = status;
        this.needs = needs;
        this.idnumber = idnumber;
        this.pn = pn;
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

    public String getUser(){
        return user;
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

    public String getNeeds(){
        return needs;
    }

    public String getIdnumber(){
        return idnumber;
    }

    public String getPn(){
        return pn;
    }

    public ListKelasModel(Parcel in) {
        this.id = in.readString();
        this.user = in.readString();
        this.classcode = in.readString();
        this.classname = in.readString();
        this.time = in.readString();
        this.day = in.readString();
        this.status = in.readString();
        this.needs = in.readString();
        this.idnumber = in.readString();
        this.pn = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.user);
        parcel.writeString(this.classcode);
        parcel.writeString(this.classname);
        parcel.writeString(this.time);
        parcel.writeString(this.day);
        parcel.writeString(this.status);
        parcel.writeString(this.needs);
        parcel.writeString(this.idnumber);
        parcel.writeString(this.pn);
    }
}
