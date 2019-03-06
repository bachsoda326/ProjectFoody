package com.example.appfoody.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appfoody.Model.BinhLuanModel;
import com.example.appfoody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class AdapterBinhLuan   extends RecyclerView.Adapter<AdapterBinhLuan.ViewHolder> {
    Context context;
    int layout;
    List<BinhLuanModel> binhLuanModelList;

    public  AdapterBinhLuan(Context context, int layout, List<BinhLuanModel> binhLuanModelList)
    {
        this.context=context;
        this.layout=layout;
        this.binhLuanModelList=binhLuanModelList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        final CircleImageView circleImageView;
        TextView txtTieuDeBinhLuan,txtNoiDungBinhLuan,txtChamDiemBinhLuan;
        RecyclerView recycleHinhBinhLuan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.circleImgUser);
            txtTieuDeBinhLuan=itemView.findViewById(R.id.txtTieuDeBinhLuan);
            txtNoiDungBinhLuan=itemView.findViewById(R.id.txtNoiDungBinhLuan);
            txtChamDiemBinhLuan=itemView.findViewById(R.id.txtChamDiemBinhLuan);
            recycleHinhBinhLuan=itemView.findViewById(R.id.recycleHinhBinhLuan);
        }
    }
    @NonNull
    @Override
    public AdapterBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder =new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBinhLuan.ViewHolder viewHolder, int i) {

         final BinhLuanModel binhLuanModel=binhLuanModelList.get(i);
         viewHolder.txtTieuDeBinhLuan.setText(binhLuanModel.getGoodbye());
         viewHolder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
         viewHolder.txtChamDiemBinhLuan.setText(binhLuanModel.getChamdiem()+"");
         setHinhAnhUser(viewHolder.circleImageView,"user.png");
         final List<Bitmap> bitmapList = new ArrayList<>();
         for(String linkHinh : binhLuanModel.getHinhAnhList())
         {
             StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkHinh);
             long ONE_MEGABYTE = 1024 * 1024;
             storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                 @Override
                 public void onSuccess(byte[] bytes) {
                     Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                     bitmapList.add(bitmap);
                     if(bitmapList.size()==binhLuanModel.getHinhAnhList().size())
                     {
                         AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan =new AdapterRecyclerHinhBinhLuan(context,R.layout.custom_layout_hinhbinhluan,bitmapList);
                         RecyclerView.LayoutManager layoutManager= new GridLayoutManager(context,2);
                         viewHolder.recycleHinhBinhLuan.setLayoutManager(layoutManager);
                         viewHolder.recycleHinhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                         adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                     }
                 }
             });
         }
    }

    @Override
    public int getItemCount() {
        int soBinhLuan=binhLuanModelList.size();
        if(soBinhLuan>5)
        {
            return 5;
        }else {
            return binhLuanModelList.size();
        }
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


}
