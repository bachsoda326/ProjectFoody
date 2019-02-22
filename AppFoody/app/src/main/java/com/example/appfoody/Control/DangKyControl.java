package com.example.appfoody.Control;

import com.example.appfoody.Model.ThanhVienModel;

public class DangKyControl {
    ThanhVienModel thanhVienModel;

    public DangKyControl(){
        thanhVienModel = new ThanhVienModel();
    }

    public void ThemThanhVienControl(ThanhVienModel thanhVienModel, String uid){
        thanhVienModel.ThemThongTinThanhVien(thanhVienModel, uid);
    }
}
