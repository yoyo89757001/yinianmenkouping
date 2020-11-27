package com.example.yinian.menkou.ping.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveInfo implements Parcelable {

    private String jigou;
    private String jigouId;
    private String loudong;
    private String loudongId;
    private String louceng;
    private String loucengId;
    private String fangjian;
    private String fangjianId;

    protected SaveInfo(Parcel in) {
        jigou = in.readString();
        jigouId = in.readString();
        loudong = in.readString();
        loudongId = in.readString();
        louceng = in.readString();
        loucengId = in.readString();
        fangjian = in.readString();
        fangjianId = in.readString();
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

    public String getJigouId() {
        return jigouId;
    }

    public void setJigouId(String jigouId) {
        this.jigouId = jigouId;
    }

    public String getLoudongId() {
        return loudongId;
    }

    public void setLoudongId(String loudongId) {
        this.loudongId = loudongId;
    }

    public String getLoucengId() {
        return loucengId;
    }

    public void setLoucengId(String loucengId) {
        this.loucengId = loucengId;
    }

    public String getFangjianId() {
        return fangjianId;
    }

    public void setFangjianId(String fangjianId) {
        this.fangjianId = fangjianId;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jigou);
        dest.writeString(jigouId);
        dest.writeString(loudong);
        dest.writeString(loudongId);
        dest.writeString(louceng);
        dest.writeString(loucengId);
        dest.writeString(fangjian);
        dest.writeString(fangjianId);
    }

    @Override
    public String toString() {
        return "SaveInfo{" +
                "jigou='" + jigou + '\'' +
                ", jigouId='" + jigouId + '\'' +
                ", loudong='" + loudong + '\'' +
                ", loudongId='" + loudongId + '\'' +
                ", louceng='" + louceng + '\'' +
                ", loucengId='" + loucengId + '\'' +
                ", fangjian='" + fangjian + '\'' +
                ", fangjianId='" + fangjianId + '\'' +
                '}';
    }
}
