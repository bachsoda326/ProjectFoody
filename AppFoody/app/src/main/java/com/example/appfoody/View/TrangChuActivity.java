package com.example.appfoody.View;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.appfoody.Adapters.AdapterViewPagerTrangChu;
import com.example.appfoody.R;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener{

    ViewPager viewpager_trangchu;
    RadioButton rd_odau, rd_angi;
    RadioGroup rdgroup_odau_angi;
    ImageView imgThemQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        viewpager_trangchu = findViewById(R.id.viewpager_trangchu);
        rd_odau = findViewById(R.id.rd_odau);
        rd_angi = findViewById(R.id.rd_angi);
        rdgroup_odau_angi = findViewById(R.id.rdgroup_odau_angi);
        imgThemQuanAn = findViewById(R.id.imgThemQuanAn);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewpager_trangchu.setAdapter(adapterViewPagerTrangChu);
        viewpager_trangchu.addOnPageChangeListener(this);
        rdgroup_odau_angi.setOnCheckedChangeListener(this);

        imgThemQuanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iThemQuanAn=new Intent(TrangChuActivity.this, ThemQuanAnActivity.class);
                startActivity(iThemQuanAn);
            }
        });
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
