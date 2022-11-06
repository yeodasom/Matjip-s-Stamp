package com.android.stamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.sdk.common.KakaoSdk;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences loginInformation = getSharedPreferences("loginInformation",0);

        TextView name = (TextView) findViewById(R.id.name);
        TextView logout = (TextView) findViewById(R.id.logout);

        //세션에 올린 login정보 호출
        name.setText(loginInformation.getString("name","") + "(" + loginInformation.getString("id","") + ")");
        //getAppKeyHash();

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences pref = getSharedPreferences("loginInformation", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent registerIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(registerIntent);

                Toast.makeText(getApplicationContext(), "로그아웃 하였습니다.", Toast.LENGTH_SHORT).show();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences pref = getSharedPreferences("loginInformation", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent registerIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(registerIntent);

                Toast.makeText(getApplicationContext(), "로그아웃 하였습니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }




    private void getAppKeyHash(){
        try {
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md;
                    md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    String something = new String(Base64.encode(md.digest(), 0));
                    Log.e("Hash Key ", something);
                }
            } catch (Exception e) {
                Log.e("name not found", e.toString());
            }
        } finally {

        }
    }
}