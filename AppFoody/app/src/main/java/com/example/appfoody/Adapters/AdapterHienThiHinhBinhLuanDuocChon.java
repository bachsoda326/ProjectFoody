package com.example.appfoody.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.appfoody.R;

import java.util.List;

public class AdapterHienThiHinhBinhLuanDuocChon extends RecyclerView.Adapter<AdapterHienThiHinhBinhLuanDuocChon.ViewHolderHinhBinhLuan> {
    Context context;
    int resource;
    List<String> list;

    public AdapterHienThiHinhBinhLuanDuocChon(Context context, int resource, List<String> list){
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    public class ViewHolderHinhBinhLuan extends RecyclerView.ViewHolder {
        ImageView imageView, imgXoa;

        public ViewHolderHinhBinhLuan(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgChonHinhBinhLuan);
            imgXoa = itemView.findViewById(R.id.imgXoa);
        }
    }

    @NonNull
    @Override
    public ViewHolderHinhBinhLuan onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        ViewHolderHinhBinhLuan viewHolderHinhBinhLuan = new ViewHolderHinhBinhLuan(view);

        return viewHolderHinhBinhLuan;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHinhBinhLuan viewHolderHinhBinhLuan, int i) {
        Uri uri = Uri.parse(list.get(i));
        viewHolderHinhBinhLuan.imageView.setImageURI(uri);
        viewHolderHinhBinhLuan.imgXoa.setTag(i);
        viewHolderHinhBinhLuan.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vitri = (int) v.getTag();
                list.remove(vitri);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
