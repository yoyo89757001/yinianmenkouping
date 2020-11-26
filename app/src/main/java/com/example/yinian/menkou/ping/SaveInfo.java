package com.example.yinian.menkou.ping;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveInfo implements Parcelable {

    private String jigou;
    private String loudong;
    private String louceng;
    private String fangjian;

    public String getJigou() {
        return jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public String getLoudong() {
        return loudong;
    }

    public void setLoudong(String loudong) {
        this.loudong = loudong;
    }

    public String getLouceng() {
        return louceng;
    }

    public void setLouceng(String louceng) {
        this.louceng = louceng;
    }

    public String getFangjian() {
        return fangjian;
    }

    public void setFangjian(String fangjian) {
        this.fangjian = fangjian;
    }

    public SaveInfo() {
    }

    protected SaveInfo(Parcel in) {
        jigou = in.readString();
        loudong = in.readString();
        louceng = in.readString();
        fangjian = in.readString();
    }

    public static final Creator<SaveInfo> CREATOR = new Creator<SaveInfo>() {
        @Override
        public SaveInfo createFromParcel(Parcel in) {
            return new SaveInfo(in);
        }

        @Override
        public SaveInfo[] newArray(int size) {
            return new SaveInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jigou);
        dest.writeString(loudong);
        dest.writeString(louceng);
        dest.writeString(fangjian);
    }
}
