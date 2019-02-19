package com.example.appfoody.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfoody.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.appfoody.R.id.btnLoginGG;

public class LoginActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {
    Button btnLoginGG;
    Button btnLoginFB;
    Button btnDangNhap;
    EditText edEmail,edPassWord;
    TextView txtDangKyMoi;
    TextView txtQuenMatKhau;
    GoogleApiClient apiClient;
    public static int REQUESTCODE_DANGNHAP_GOOGLE=99;
    public static int KIEMTRA_PROVIDER_DANGNHAP=0;
    FirebaseAuth firebaseAuth;
    CallbackManager mCallBackFacebook;
    LoginManager loginManager;
    List<String> permissionFB =Arrays.asList("email","public_profile");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_login);

        mCallBackFacebook=CallbackManager.Factory.create();
        loginManager=LoginManager.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        btnDangNhap=(Button) findViewById(R.id.btnDangNhap);
        btnLoginGG=(Button) findViewById(R.id.btnLoginGG);
        btnLoginFB=(Button) findViewById(R.id.btnLoginFB);
        txtDangKyMoi=(TextView) findViewById(R.id.txtDangKyMoi);
        edEmail=(EditText) findViewById(R.id.edEmail);
        edPassWord=(EditText) findViewById(R.id.edPassWord);
        txtQuenMatKhau =(TextView) findViewById(R.id.txtQuenMatKhau);

        btnLoginFB.setOnClickListener(this);
        btnLoginGG.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        txtDangKyMoi.setOnClickListener(this);
        txtQuenMatKhau.setOnClickListener(this);

        taoClientDangNhapGoogle();
    }
    private void DangNhapFaceBook()
    {
        loginManager.logInWithReadPermissions(this,permissionFB);
        loginManager.registerCallback(mCallBackFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DANGNHAP=2;
                String tokenID=loginResult.getAccessToken().getToken();
                chungThucDangNhapFireBase(tokenID);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    private void taoClientDangNhapGoogle()
    {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
         apiClient =new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    private void dangNhapGoogle(GoogleApiClient apiClient)
    {
        KIEMTRA_PROVIDER_DANGNHAP=1;
        Intent loginIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(loginIntent,REQUESTCODE_DANGNHAP_GOOGLE);
    }
    private void chungThucDangNhapFireBase(String tokenID)
    {

        if(KIEMTRA_PROVIDER_DANGNHAP==1)
        {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID,null);
            firebaseAuth.signInWithCredential(authCredential);
        }else if(KIEMTRA_PROVIDER_DANGNHAP==2)
        {
            AuthCredential authCredential= FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUESTCODE_DANGNHAP_GOOGLE) {
            if (resultCode == RESULT_OK) {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenID = account.getIdToken();
                chungThucDangNhapFireBase(tokenID);

            }
        }else{
            mCallBackFacebook.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.txtQuenMatKhau:
                Intent iKhoiPhucMatKhau=new Intent(LoginActivity.this,KhoiPhucMatKhauActivity.class);
                startActivity(iKhoiPhucMatKhau);
                break;
            case R.id.btnDangNhap:
                dangNhap();
                break;
            case R.id.txtDangKyMoi:
                Intent iDangky=new Intent(LoginActivity.this,DangKiActivity.class);
                startActivity(iDangky);
                break;
            case R.id.btnLoginGG:
                dangNhapGoogle(apiClient);
                break;
            case R.id.btnLoginFB:
                DangNhapFaceBook();
                break;


        }
    }
    private  void dangNhap()
    {
        String email =edEmail.getText().toString();
        String password=edPassWord.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password);
    }
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user =firebaseAuth.getCurrentUser();
        if(user !=null)
        {
           Intent iTrangChu= new Intent(this,TrangChuActivity.class);
           startActivity(iTrangChu);
        }else{

        }
    }
}
