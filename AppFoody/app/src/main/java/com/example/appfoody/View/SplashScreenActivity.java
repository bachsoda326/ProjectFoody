package com.example.appfoody.View;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfoody.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SplashScreenActivity extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;
    public static final int locationRequestCode=1;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);
        sharedPreferences=getSharedPreferences("toado",MODE_PRIVATE);
        mFusedLocationClient=LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashScreenActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);

        } else {
            // already permission granted
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(SplashScreenActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                {
                    SharedPreferences.Editor  editor =sharedPreferences.edit();
                    editor.putString("latitude", String.valueOf(location.getLatitude()));
                    editor.putString("longitude", String.valueOf(location.getLongitude()));
                    editor.commit();
                }
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iDangNhap = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(iDangNhap);
                finish();
            }
        }, 2000);

    }
}
