package com.example.appfoody.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThanhVienModel {
    String hoten, hinhanh;

    private String mathanhvien;
    private DatabaseReference dataNodeThanhVien;

    public ThanhVienModel(){
        dataNodeThanhVien = FirebaseDatabase.getInstance().getReference().child("thanhviens");
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public void ThemThongTinThanhVien(ThanhVienModel thanhVienModel, String uid){
        dataNodeThanhVien.child(uid).setValue(thanhVienModel);
    }
}
