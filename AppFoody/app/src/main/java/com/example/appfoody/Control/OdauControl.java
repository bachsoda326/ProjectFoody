package com.example.appfoody.Control;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.appfoody.Adapters.AdapterRecyclerOdau;
import com.example.appfoody.Control.Interfaces.OdauInterface;
import com.example.appfoody.Model.QuanAnModel;
import com.example.appfoody.R;

import java.util.ArrayList;
import java.util.List;

public class OdauControl {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerOdau adapterRecyclerOdau;

    public  OdauControl(Context context){
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerOdau){

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecyclerOdau(quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);

        OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterRecyclerOdau.notifyDataSetChanged();
            }
        };

        quanAnModel.getDanhSachQuanAn(odauInterface);
    }
}
