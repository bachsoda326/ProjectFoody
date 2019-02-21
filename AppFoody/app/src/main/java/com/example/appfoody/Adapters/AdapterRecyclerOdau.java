package com.example.appfoody.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appfoody.Model.QuanAnModel;
import com.example.appfoody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerOdau extends RecyclerView.Adapter<AdapterRecyclerOdau.ViewHolder> {

    List<QuanAnModel> quanAnModelList;
    int resource;

    public AdapterRecyclerOdau(List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnOdau;
        Button btnDatMonOdau;
        ImageView imgHinhQuanAnOdau;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenQuanAnOdau = itemView.findViewById(R.id.txtTenQuanAnOdau);
            btnDatMonOdau = itemView.findViewById(R.id.btnDatMonOdau);
            imgHinhQuanAnOdau = itemView.findViewById(R.id.imgHinhQuanAnOdau);
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
        QuanAnModel quanAnModel = quanAnModelList.get(i);
        viewHolder.txtTenQuanAnOdau.setText(quanAnModel.getTenquanan());
        if(quanAnModel.isGiaohang() == true)
            viewHolder.btnDatMonOdau.setVisibility(View.VISIBLE);

        if(quanAnModel.getHinhanhquanan().size() > 0){
            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    viewHolder.imgHinhQuanAnOdau.setImageBitmap(bitmap);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }

}
