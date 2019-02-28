package com.example.appfoody.Model;

public class ChonHinhBinhLuanModel {
    String duongDan;
    boolean isCheck;

    public ChonHinhBinhLuanModel(String duongDan, boolean isCheck){
        this.duongDan = duongDan;
        this.isCheck = isCheck;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
