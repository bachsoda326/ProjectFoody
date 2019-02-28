package com.example.appfoody.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import com.example.appfoody.Model.ChonHinhBinhLuanModel;
import com.example.appfoody.R;

import java.util.List;

public class AdapterChonHinhBinhLuan extends RecyclerView.Adapter<AdapterChonHinhBinhLuan.ViewHolderChonHinh> {
    Context context;
    int resource;
    List<ChonHinhBinhLuanModel> listDuongDan;

    public AdapterChonHinhBinhLuan(Context context, int resource, List<ChonHinhBinhLuanModel> listDuongDan){
        this.context = context;
        this.resource = resource;
        this.listDuongDan = listDuongDan;
    }

    public class ViewHolderChonHinh extends RecyclerView.ViewHolder {
        ImageView imageView;
        CheckBox checkBox;

        public ViewHolderChonHinh(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgChonHinhBinhLuan);
            checkBox = itemView.findViewById(R.id.checkboxChonHinhBinhLuan);
        }
    }

    @NonNull
    @Override
    public ViewHolderChonHinh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        ViewHolderChonHinh viewHolderChonHinh = new ViewHolderChonHinh(view);

        return viewHolderChonHinh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChonHinh viewHolderChonHinh, final int i) {
        final ChonHinhBinhLuanModel chonHinhBinhLuanModel = listDuongDan.get(i);
        Uri uri = Uri.parse(chonHinhBinhLuanModel.getDuongDan());
        viewHolderChonHinh.imageView.setImageURI(uri);
        viewHolderChonHinh.checkBox.setChecked(chonHinhBinhLuanModel.isCheck());
        viewHolderChonHinh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                listDuongDan.get(i).setCheck(checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDuongDan.size();
    }

}
