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
    private String qrCodeData;

    public SaveInfo() {
    }

    protected SaveInfo(Parcel in) {
        jigou = in.readString();
        jigouId = in.readString();
        loudong = in.readString();
        loudongId = in.readString();
        louceng = in.readString();
        loucengId = in.readString();
        fangjian = in.readString();
        fangjianId = in.readString();
        qrCodeData = in.readString();
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

    public String getJigou() {
        return jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public String getJigouId() {
        return jigouId;
    }

    public void setJigouId(String jigouId) {
        this.jigouId = jigouId;
    }

    public String getLoudong() {
        return loudong;
    }

    public void setLoudong(String loudong) {
        this.loudong = loudong;
    }

    public String getLoudongId() {
        return loudongId;
    }

    public void setLoudongId(String loudongId) {
        this.loudongId = loudongId;
    }

    public String getLouceng() {
        return louceng;
    }

    public void setLouceng(String louceng) {
        this.louceng = louceng;
    }

    public String getLoucengId() {
        return loucengId;
    }

    public void setLoucengId(String loucengId) {
        this.loucengId = loucengId;
    }

    public String getFangjian() {
        return fangjian;
    }

    public void setFangjian(String fangjian) {
        this.fangjian = fangjian;
    }

    public String getFangjianId() {
        return fangjianId;
    }

    public void setFangjianId(String fangjianId) {
        this.fangjianId = fangjianId;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
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
        dest.writeString(qrCodeData);
    }
}
