package com.example.appfoody.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.appfoody.Control.Interfaces.OdauInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String giodongcua, giomocua, tenquanan, videogioithieu, maquanan;
    long luotthich;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhLuanModelList;

    DatabaseReference nodeRoot;

    public QuanAnModel() {
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public List<String> getHinhanhquanan() {
        return hinhanhquanan;
    }

    public void setHinhanhquanan(List<String> hinhanhquanan) {
        this.hinhanhquanan = hinhanhquanan;
    }

    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }

    public void getDanhSachQuanAn(final OdauInterface odauInterface) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Lấy danh sách quán ăn
                DataSnapshot dataSnapshotQuanAn = dataSnapshot.child("quanans");
                for (DataSnapshot valueQuanAn : dataSnapshotQuanAn.getChildren()
                     ) {
                    QuanAnModel quanAnModel = valueQuanAn.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(valueQuanAn.getKey());
                    //Lấy danh sách hình ảnh quán ăn theo mã
                    DataSnapshot dataSnapshotHinhQuanAn = dataSnapshot.child("hinhanhquanans").child(quanAnModel.getMaquanan());
                    List<String> hinhAnhList = new ArrayList<>();
                    for (DataSnapshot valueHinhQuanAn : dataSnapshotHinhQuanAn.getChildren()
                         ) {
                        hinhAnhList.add(valueHinhQuanAn.getValue(String.class));
                    }
                    quanAnModel.setHinhanhquanan(hinhAnhList);
                    //Lấy danh sách bình luận quán ăn
                    DataSnapshot dataSnapshotBinhLuan = dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());
                    List<BinhLuanModel> binhLuanlist = new ArrayList<>();
                    for (DataSnapshot valueBinhLuan : dataSnapshotBinhLuan.getChildren()
                         ) {
                        BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                        binhLuanModel.setMabinhluan(valueBinhLuan.getKey());
                        ThanhVienModel thanhVienModel = dataSnapshot.child("thanhviens").child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                        binhLuanModel.setThanhVienModel(thanhVienModel);

                        List<String> hinhAnhBinhLuanList = new ArrayList<>();
                        DataSnapshot dataSnapshotHinhAnhBinhLuan = dataSnapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());
                        for (DataSnapshot valueHinhAnhBinhLuan : dataSnapshotHinhAnhBinhLuan.getChildren()
                             ) {
                            hinhAnhBinhLuanList.add(valueHinhAnhBinhLuan.getValue(String.class));
                        }
                        binhLuanModel.setHinhAnhList(hinhAnhBinhLuanList);

                        binhLuanlist.add(binhLuanModel);
                    }
                    quanAnModel.setBinhLuanModelList(binhLuanlist);

                    odauInterface.getDanhSachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}
