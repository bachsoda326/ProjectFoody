package com.example.appfoody.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appfoody.Control.OdauControl;
import com.example.appfoody.Model.QuanAnModel;
import com.example.appfoody.R;

import java.util.List;

public class OdauFragment extends Fragment {
    OdauControl odauControl;
    RecyclerView recyclerOdau;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau, container, false);
        recyclerOdau = view.findViewById(R.id.recylerOdau);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        odauControl = new OdauControl(getContext());

        odauControl.getDanhSachQuanAnController(recyclerOdau);
    }
}
