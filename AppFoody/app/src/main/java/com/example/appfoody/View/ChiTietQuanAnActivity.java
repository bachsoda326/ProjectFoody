package com.example.appfoody.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.appfoody.Adapters.AdapterBinhLuan;
import com.example.appfoody.Control.ThucDonController;
import com.example.appfoody.Model.QuanAnModel;
import com.example.appfoody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTenQuanAn,txtDiaChiQuanAn,txtTongSoHinhAnh,txtTongSoBinhLuan,txtTongSoCheckIn,txtTongSoLuuLai,txtThoiGianHoatDong,txtTrangThaiHoatDong,txtTieuDeToolbar;
    ImageView imHinhQuanAn;
    Button btnBinhLuan;
    QuanAnModel quanAnModel;
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerViewBinhLuan;
    AdapterBinhLuan adapterBinhLuan;

    ThucDonController thucDonController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_chitietquanan);

        quanAnModel=getIntent().getParcelableExtra("quanan");

        txtTenQuanAn=(TextView) findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn=(TextView) findViewById(R.id.txtDiaChiQuanAn);
        txtTongSoHinhAnh=(TextView) findViewById(R.id.tongSoHinhAnh);
        txtTongSoBinhLuan=(TextView) findViewById(R.id.tongSoBinhLuan);
        txtTongSoCheckIn=(TextView) findViewById(R.id.tongSoCheckIn);
        txtTongSoLuuLai=(TextView) findViewById(R.id.tongSoLuuLai);
        imHinhQuanAn=(ImageView) findViewById(R.id.imHinhQuanAn);
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        txtThoiGianHoatDong=findViewById(R.id.txtThoiGianHoatDong);
        txtTrangThaiHoatDong=findViewById(R.id.txtTrangThaiHoatDong);
        txtTieuDeToolbar=findViewById(R.id.txtTieuDeToolbar);
        recyclerViewBinhLuan=findViewById(R.id.recycleBinhLuanChiTietQuanAn);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        thucDonController = new ThucDonController();

        btnBinhLuan.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    protected void onStart() {
        super.onStart();
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Date date;
        String giohientai=dateFormat.format(calendar.getTime());
        String giomocua=quanAnModel.getGiomocua();
        String giodongcua=quanAnModel.getGiodongcua();


        try {
            Date dateHienTai=dateFormat.parse(giohientai);
            Date dateMoCua=dateFormat.parse(giomocua);
            Date dateDongCua=dateFormat.parse(giodongcua);
            if(dateHienTai.after(dateMoCua)&& dateHienTai.before(dateDongCua))
            {
                txtTrangThaiHoatDong.setText(R.string.dangmocua);
            }else {
                txtTrangThaiHoatDong.setText(R.string.dadongcua);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtTieuDeToolbar.setText(quanAnModel.getTenquanan());

        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChiQuanAn.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size()+"");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhLuanModelList().size()+"");
        txtThoiGianHoatDong.setText(giomocua+ "-" +giodongcua);

        StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imHinhQuanAn.setImageBitmap(bitmap);
            }
        });
        //Load danh sách bình luận quán ăn
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerViewBinhLuan.setLayoutManager(layoutManager);
        adapterBinhLuan= new AdapterBinhLuan(this,R.layout.custom_layout_binhluan,quanAnModel.getBinhLuanModelList());
        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBinhLuan:
                Intent iBinhLuan = new Intent(this,BinhLuanActivity.class);
                iBinhLuan.putExtra("maquanan",quanAnModel.getMaquanan());
                iBinhLuan.putExtra("tenquan",quanAnModel.getTenquanan());
                iBinhLuan.putExtra("diachi",quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
                startActivity(iBinhLuan);
        }
    }
}
