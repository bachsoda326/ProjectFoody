package com.example.appfoody.View;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.appfoody.Adapters.AdapterViewPagerTrangChu;
import com.example.appfoody.R;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener{

    ViewPager viewpager_trangchu;
    RadioButton rd_odau, rd_angi;
    RadioGroup rdgroup_odau_angi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        viewpager_trangchu = findViewById(R.id.viewpager_trangchu);
        rd_odau = findViewById(R.id.rd_odau);
        rd_angi = findViewById(R.id.rd_angi);
        rdgroup_odau_angi = findViewById(R.id.rdgroup_odau_angi);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewpager_trangchu.setAdapter(adapterViewPagerTrangChu);
        viewpager_trangchu.addOnPageChangeListener(this);
        rdgroup_odau_angi.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                rd_odau.setChecked(true);
                break;
            case 1:
                rd_angi.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rd_odau:
                viewpager_trangchu.setCurrentItem(0);
                break;
            case R.id.rd_angi:
                viewpager_trangchu.setCurrentItem(1);
                break;
        }
    }
}
