package com.example.appfoody.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BinhLuanModel implements Parcelable {
    double chamdiem;
    long luotthich;
    ThanhVienModel thanhVienModel;
    String noidung, goodbye, mauser, mabinhluan;
    List<String> hinhAnhList;

    public BinhLuanModel(){

    }

    protected BinhLuanModel(Parcel in) {
        chamdiem = in.readDouble();
        luotthich = in.readLong();
        noidung = in.readString();
        goodbye = in.readString();
        mauser = in.readString();
        mabinhluan = in.readString();
        hinhAnhList = in.createStringArrayList();
        thanhVienModel=in.readParcelable(ThanhVienModel.class.getClassLoader());
    }

    public static final Creator<BinhLuanModel> CREATOR = new Creator<BinhLuanModel>() {
        @Override
        public BinhLuanModel createFromParcel(Parcel in) {
            return new BinhLuanModel(in);
        }

        @Override
        public BinhLuanModel[] newArray(int size) {
            return new BinhLuanModel[size];
        }
    };

    public double getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(double chamdiem) {
        this.chamdiem = chamdiem;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public ThanhVienModel getThanhVienModel() {
        return thanhVienModel;
    }

    public void setThanhVienModel(ThanhVienModel thanhVienModel) {
        this.thanhVienModel = thanhVienModel;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getGoodbye() {
        return goodbye;
    }

    public void setGoodbye(String goodbye) {
        this.goodbye = goodbye;
    }

    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }

    public String getMabinhluan() {
        return mabinhluan;
    }

    public void setMabinhluan(String mabinhluan) {
        this.mabinhluan = mabinhluan;
    }

    public List<String> getHinhAnhList() {
        return hinhAnhList;
    }

    public void setHinhAnhList(List<String> hinhAnhList) {
        this.hinhAnhList = hinhAnhList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(chamdiem);
        dest.writeLong(luotthich);
        dest.writeString(noidung);
        dest.writeString(goodbye);
        dest.writeString(mauser);
        dest.writeString(mabinhluan);
        dest.writeStringList(hinhAnhList);
        dest.writeParcelable(thanhVienModel,flags);
    }
}
