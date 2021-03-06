package com.example.appfoody.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appfoody.Model.BinhLuanModel;
import com.example.appfoody.Model.ChiNhanhQuanAnModel;
import com.example.appfoody.Model.QuanAnModel;
import com.example.appfoody.R;
import com.example.appfoody.View.ChiTietQuanAnActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecyclerOdau extends RecyclerView.Adapter<AdapterRecyclerOdau.ViewHolder> {

    List<QuanAnModel> quanAnModelList;
    int resource;
    Context context;

    public AdapterRecyclerOdau(Context context,List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnOdau;
        Button btnDatMonOdau;
        ImageView imgHinhQuanAnOdau;
        TextView txtTieuDeBinhLuan, txtTieuDeBinhLuan2, txtNoiDungBinhLuan, txtNoiDungBinhLuan2, txtChamDiemBinhLuan, txtChamDiemBinhLuan2, txtTongBinhLuan, txtTongAnhBinhLuan,txtDiemTrungBinhQuanAn,txtDiaChiQuanAnOdau,txtKhoangCachQuanAn;
        CircleImageView  circleImgUser, circleImgUser2;
        LinearLayout containerBinhLuan, containerBinhLuan2;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenQuanAnOdau = itemView.findViewById(R.id.txtTenQuanAnOdau);
            btnDatMonOdau = itemView.findViewById(R.id.btnDatMonOdau);
            imgHinhQuanAnOdau = itemView.findViewById(R.id.imgHinhQuanAnOdau);
            txtTieuDeBinhLuan = itemView.findViewById(R.id.txtTieuDeBinhLuan);
            txtTieuDeBinhLuan2 = itemView.findViewById(R.id.txtTieuDeBinhLuan2);
            txtNoiDungBinhLuan = itemView.findViewById(R.id.txtNoiDungBinhLuan);
            txtNoiDungBinhLuan2 = itemView.findViewById(R.id.txtNoiDungBinhLuan2);
            circleImgUser = itemView.findViewById(R.id.circleImgUser);
            circleImgUser2 = itemView.findViewById(R.id.circleImgUser2);
            containerBinhLuan = itemView.findViewById(R.id.containerBinhLuan);
            containerBinhLuan2 = itemView.findViewById(R.id.containerBinhLuan2);
            txtChamDiemBinhLuan = itemView.findViewById(R.id.txtChamDiemBinhLuan);
            txtChamDiemBinhLuan2 = itemView.findViewById(R.id.txtChamDiemBinhLuan2);
            txtTongBinhLuan = itemView.findViewById(R.id.txtTongBinhLuan);
            txtTongAnhBinhLuan = itemView.findViewById(R.id.txtTongAnhBinhLuan);
            txtDiemTrungBinhQuanAn=itemView.findViewById(R.id.txtDiemTrungBinhQuanAn);
            txtDiaChiQuanAnOdau= itemView.findViewById(R.id.txtDiaChiQuanAnOdau);
            txtKhoangCachQuanAn= itemView.findViewById(R.id.txtKhoangCachQuanAn);
            cardView=itemView.findViewById(R.id.cardViewOdau);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerOdau.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerOdau.ViewHolder viewHolder, int i) {
        final QuanAnModel quanAnModel = quanAnModelList.get(i);
        viewHolder.txtTenQuanAnOdau.setText(quanAnModel.getTenquanan());
        viewHolder.txtTongBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");

        if(quanAnModel.isGiaohang() == true)
            viewHolder.btnDatMonOdau.setVisibility(View.VISIBLE);

        if(quanAnModel.getHinhanhquanan().size() > 0){
            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
            long ONE_MEGABYTE = 5 * 1024 * 1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    viewHolder.imgHinhQuanAnOdau.setImageBitmap(bitmap);
                }
            });
        }

        if(quanAnModel.getBinhLuanModelList().size() > 0){
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(0);
            viewHolder.txtTieuDeBinhLuan.setText(binhLuanModel.getGoodbye());
            viewHolder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
            viewHolder.txtChamDiemBinhLuan.setText(binhLuanModel.getChamdiem() + "");
            if(binhLuanModel.getThanhVienModel() != null) {
                setHinhAnhUser(viewHolder.circleImgUser, binhLuanModel.getThanhVienModel().getHinhanh());
            }

            if(quanAnModel.getBinhLuanModelList().size() > 1){
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhLuanModelList().get(1);
                viewHolder.txtTieuDeBinhLuan2.setText(binhLuanModel2.getGoodbye());
                viewHolder.txtNoiDungBinhLuan2.setText(binhLuanModel2.getNoidung());
                viewHolder.txtChamDiemBinhLuan2.setText(binhLuanModel2.getChamdiem() + "");
                if(binhLuanModel2.getThanhVienModel() != null) {
                    setHinhAnhUser(viewHolder.circleImgUser2, binhLuanModel2.getThanhVienModel().getHinhanh());
                }
            }

            int tongSoAnhBinhLuan = 0;
            int tongDiemBinhLuan=0;
            for (BinhLuanModel binhLuanModel1 : quanAnModel.getBinhLuanModelList()) {
                tongSoAnhBinhLuan += binhLuanModel1.getHinhAnhList().size();
                tongDiemBinhLuan +=binhLuanModel.getChamdiem();
            }
            double diemTrungBinhCuaQuanAn=tongDiemBinhLuan/quanAnModel.getBinhLuanModelList().size();
            viewHolder.txtDiemTrungBinhQuanAn.setText(String.format("%.1f",diemTrungBinhCuaQuanAn));
            viewHolder.txtTongAnhBinhLuan.setText(tongSoAnhBinhLuan + "");
        } else {
            viewHolder.containerBinhLuan.setVisibility(View.GONE);
            viewHolder.containerBinhLuan2.setVisibility(View.GONE);
        }
        //Lấy chi nhánh quán ăn và hiển thị địa chỉ và km
        if(quanAnModel.getChiNhanhQuanAnModelList().size()>0)
        {
            ChiNhanhQuanAnModel chiNhanhQuanAnModelTam =quanAnModel.getChiNhanhQuanAnModelList().get(0);
            for(ChiNhanhQuanAnModel chiNhanhQuanAnModel:quanAnModel.getChiNhanhQuanAnModelList())
            {
                if(chiNhanhQuanAnModelTam.getKhoangcach() > chiNhanhQuanAnModel.getKhoangcach())
                {
                    chiNhanhQuanAnModelTam=chiNhanhQuanAnModel;
                }
            }
            viewHolder.txtDiaChiQuanAnOdau.setText(chiNhanhQuanAnModelTam.getDiachi());
            viewHolder.txtKhoangCachQuanAn.setText(String.format("%.1f",chiNhanhQuanAnModelTam.getKhoangcach())+ "km");
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietQuanAn=new Intent(context, ChiTietQuanAnActivity.class);
                iChiTietQuanAn.putExtra("quanan",quanAnModel);
                context.startActivity(iChiTietQuanAn);
            }
        });
    }



    private void setHinhAnhUser(final CircleImageView circleImageView, String linkHinh){
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkHinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }

}
