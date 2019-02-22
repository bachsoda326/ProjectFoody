package com.example.appfoody.Model;

import java.util.List;

public class BinhLuanModel {
    long chamdiem, luotthich;
    ThanhVienModel thanhVienModel;
    String noidung, goodbye, mauser, mabinhluan;
    List<String> hinhAnhList;

    public BinhLuanModel(){

    }

    public long getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(long chamdiem) {
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
}
