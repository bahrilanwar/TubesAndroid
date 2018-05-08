package com.example.android.tubes_android;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Model Photo
 * Created by ASUS on 31/03/2018.
 */

@IgnoreExtraProperties
public class KelasModel implements Parcelable {
    private String user, kodeRuangan, title, caption, time, status, hari, displayName, id, keperluan;

    public KelasModel(){
    }

    public KelasModel(String user, String kodeRuangan, String title, String caption, String time, String status, String hari, String keperluan) {
        this.user = user;
        this.kodeRuangan = kodeRuangan;
        this.title = title;
        this.caption = caption;
        this.time = time;
        this.status = status;
        this.hari = hari;
        this.displayName = displayName;
        this.keperluan = keperluan;
        //this.comments = new ArrayList<>();
    }

    public static final Creator<KelasModel> CREATOR = new Creator<KelasModel>() {
        @Override
        public KelasModel createFromParcel(Parcel in) {
            return new KelasModel(in);
        }

        @Override
        public KelasModel[] newArray(int size) {
            return new KelasModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKodeRuangan(){ return kodeRuangan; }

    public void setKodeRuangan() { this.kodeRuangan = kodeRuangan; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHari() { return hari; }

    public void setHari(String hari) { this.hari = hari; }

    public String getDisplayName() { return displayName; }

    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getKeperluan() { return keperluan; }

    public void setKeperluan(String keperluan) { this.keperluan = keperluan; }

    public KelasModel(Parcel in) {
        this.id = in.readString();
        this.user = in.readString();
        this.kodeRuangan = in.readString();
        this.title = in.readString();
        this.caption = in.readString();
        this.time = in.readString();
        this.status = in.readString();
        this.hari = in.readString();
        this.displayName = in.readString();
        this.keperluan = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.user);
        parcel.writeString(this.kodeRuangan);
        parcel.writeString(this.title);
        parcel.writeString(this.caption);
        parcel.writeString(this.time);
        parcel.writeString(this.status);
        parcel.writeString(this.hari);
        parcel.writeString(this.displayName);
        parcel.writeString(this.keperluan);
    }

}
