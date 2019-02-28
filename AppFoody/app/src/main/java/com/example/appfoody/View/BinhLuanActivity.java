package com.example.appfoody.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.appfoody.Adapters.AdapterHienThiHinhBinhLuanDuocChon;
import com.example.appfoody.Control.BinhLuanController;
import com.example.appfoody.Model.BinhLuanModel;
import com.example.appfoody.R;

import java.util.ArrayList;
import java.util.List;

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTenQuanAn, txtDiaChiQuanAn, txtDangBinhLuan;
    Toolbar toolbar;
    EditText edTieuDeBinhLuan,edNoiDungBinhLuan;
    ImageButton btnChonHinh;
    RecyclerView recyclerChonHinhBinhLuan;
    AdapterHienThiHinhBinhLuanDuocChon adapterHienThiHinhBinhLuanDuocChon;

    final int REQUEST_CHONHINHBINHLUAN = 69;
    String maquanan;
    SharedPreferences sharedPreferences;

    BinhLuanController binhLuanController;
    List<String> listHinhDuocChon;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);

        /*maquanan = getIntent().getStringExtra("maquanan");
        String tenquan = getIntent().getStringExtra("tenquan");
        String diachi = getIntent().getStringExtra("diachi");*/

        sharedPreferences = getSharedPreferences("luudangnhap",MODE_PRIVATE);

        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn = findViewById(R.id.txtDiaChiQuanAn);
        txtDangBinhLuan = findViewById(R.id.txtDangBinhLuan);
        edTieuDeBinhLuan = findViewById(R.id.edTieuDeBinhLuan);
        edNoiDungBinhLuan = findViewById(R.id.edNoiDungBinhLuan);
        toolbar = findViewById(R.id.toolbar);
        btnChonHinh = findViewById(R.id.btnChonHinh);
        recyclerChonHinhBinhLuan = findViewById(R.id.recyclerChonHinhBinhLuan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerChonHinhBinhLuan.setLayoutManager(layoutManager);

        binhLuanController = new BinhLuanController();
        listHinhDuocChon = new ArrayList<>();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        /*txtTenQuanAn.setText(tenquan);
        txtDiaChiQuanAn.setText(diachi);*/

        btnChonHinh.setOnClickListener(this);
        txtDangBinhLuan.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnChonHinh:
                Intent iChonHinhBinhLuan = new Intent(this, ChonHinhBinhLuanActivity.class);
                startActivityForResult(iChonHinhBinhLuan, REQUEST_CHONHINHBINHLUAN);
                break;
            case R.id.txtDangBinhLuan:
                BinhLuanModel binhLuanModel = new BinhLuanModel();
                String tieude = edTieuDeBinhLuan.getText().toString();
                String noidung = edNoiDungBinhLuan.getText().toString();
                String mauser = sharedPreferences.getString("mauser","");

                binhLuanModel.setTieude(tieude);
                binhLuanModel.setNoidung(noidung);
                binhLuanModel.setChamdiem(0);
                binhLuanModel.setLuotthich(0);
                binhLuanModel.setMauser(mauser);

                //binhLuanController.ThemBinhLuan(maquanan, binhLuanModel, listHinhDuocChon);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHONHINHBINHLUAN){
            if (resultCode == RESULT_OK){
                listHinhDuocChon = data.getStringArrayListExtra("listHinhDuocChon");
                adapterHienThiHinhBinhLuanDuocChon = new AdapterHienThiHinhBinhLuanDuocChon(this, R.layout.custom_layout_hienthihinhbinhluanduocchon, listHinhDuocChon);
                recyclerChonHinhBinhLuan.setAdapter(adapterHienThiHinhBinhLuanDuocChon);
                adapterHienThiHinhBinhLuanDuocChon.notifyDataSetChanged();
            }
        }
    }
}
