package com.example.appfoody.Model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
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

public class QuanAnModel implements Parcelable {
    boolean giaohang;
    String giodongcua, giomocua, tenquanan, videogioithieu, maquanan;
    long luotthich;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhLuanModelList;
    List<ChiNhanhQuanAnModel> chiNhanhQuanAnModelList;

    protected QuanAnModel(Parcel in) {
        giaohang = in.readByte() != 0;
        giodongcua = in.readString();
        giomocua = in.readString();
        tenquanan = in.readString();
        videogioithieu = in.readString();
        maquanan = in.readString();
        luotthich = in.readLong();
        tienich = in.createStringArrayList();
        hinhanhquanan = in.createStringArrayList();
        chiNhanhQuanAnModelList=new ArrayList<ChiNhanhQuanAnModel>();
        in.readTypedList(chiNhanhQuanAnModelList,ChiNhanhQuanAnModel.CREATOR);
        binhLuanModelList=new ArrayList<BinhLuanModel>();
        in.readTypedList(binhLuanModelList,BinhLuanModel.CREATOR);
    }

    public static final Creator<QuanAnModel> CREATOR = new Creator<QuanAnModel>() {
        @Override
        public QuanAnModel createFromParcel(Parcel in) {
            return new QuanAnModel(in);
        }

        @Override
        public QuanAnModel[] newArray(int size) {
            return new QuanAnModel[size];
        }
    };

    public List<ChiNhanhQuanAnModel> getChiNhanhQuanAnModelList() {
        return chiNhanhQuanAnModelList;
    }

    public void setChiNhanhQuanAnModelList(List<ChiNhanhQuanAnModel> chiNhanhQuanAnModelList) {
        this.chiNhanhQuanAnModelList = chiNhanhQuanAnModelList;
    }

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

    public void getDanhSachQuanAn(final OdauInterface odauInterface, final Location vitrihientai) {

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
                    //Lấy chi nhánh quán ăn
                    DataSnapshot snapshotChiNhanhQuanAn=dataSnapshot.child("chinhanhquanans").child(quanAnModel.getMaquanan());

                    List<ChiNhanhQuanAnModel> chiNhanhQuanAnModels=new ArrayList<>();

                    for(DataSnapshot valueChiNhanhQuanAn: snapshotChiNhanhQuanAn.getChildren())
                    {
                        ChiNhanhQuanAnModel chiNhanhQuanAnModel =valueChiNhanhQuanAn.getValue(ChiNhanhQuanAnModel.class);
                        Location vitriquanan= new Location("");
                        vitriquanan.setLatitude(chiNhanhQuanAnModel.getLatitude());
                        vitriquanan.setLongitude(chiNhanhQuanAnModel.getLongitude());
                        double khoangcach=(vitrihientai.distanceTo(vitriquanan))/1000;
                        chiNhanhQuanAnModel.setKhoangcach(khoangcach);
                        chiNhanhQuanAnModels.add(chiNhanhQuanAnModel);
                    }
                    quanAnModel.setChiNhanhQuanAnModelList(chiNhanhQuanAnModels);
                    odauInterface.getDanhSachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (giaohang ? 1 : 0));
        dest.writeString(giodongcua);
        dest.writeString(giomocua);
        dest.writeString(tenquanan);
        dest.writeString(videogioithieu);
        dest.writeString(maquanan);
        dest.writeLong(luotthich);
        dest.writeStringList(tienich);
        dest.writeStringList(hinhanhquanan);
        dest.writeTypedList(chiNhanhQuanAnModelList);
        dest.writeTypedList(binhLuanModelList);
    }
}
