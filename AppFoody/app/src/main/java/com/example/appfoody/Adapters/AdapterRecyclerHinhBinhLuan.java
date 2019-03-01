package com.example.appfoody.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appfoody.R;

import java.util.List;

public class AdapterRecyclerHinhBinhLuan extends RecyclerView.Adapter<AdapterRecyclerHinhBinhLuan.ViewHolder> {
    Context context;
    int layout;
    List<Bitmap> listHinh;
    public AdapterRecyclerHinhBinhLuan(Context context, int layout, List<Bitmap> listHinh)
    {
        this.context=context;
        this.layout=layout;
        this.listHinh=listHinh;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHinhBinhLuan;
        TextView txtSoHinhBinhLuan;
        FrameLayout khungSoHinhBinhLuan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageHinhBinhLuan=itemView.findViewById(R.id.imgHinhBinhLuan);
            txtSoHinhBinhLuan=itemView.findViewById(R.id.txtSoHinhBinhLuan);
            khungSoHinhBinhLuan=itemView.findViewById(R.id.khungSoHinhBinhLuan);
        }
    }
    @NonNull
    @Override
    public AdapterRecyclerHinhBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerHinhBinhLuan.ViewHolder viewHolder, int i) {
        viewHolder.imageHinhBinhLuan.setImageBitmap(listHinh.get(i));
        if(i==3)
        {

            int soHinhConLai=listHinh.size()-4;
            if(soHinhConLai>0)
            {
                viewHolder.khungSoHinhBinhLuan.setVisibility(View.VISIBLE);
                viewHolder.txtSoHinhBinhLuan.setText("+ " +soHinhConLai);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
