package com.android.stamp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity{


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText id = findViewById(R.id.idChk);
        EditText password = (EditText) findViewById(R.id.password_chk);
        Button login = (Button) findViewById(R.id.login);
        TextView regiter = (TextView) findViewById(R.id.regiter);

        regiter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String userId = id.getText().toString();
                String userPassword = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject json = new JSONObject(response);
                            boolean success = json.getBoolean("success");
                            if (success) {

                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                String userId = json.getString("id");
                                String userPassword = json.getString("password");
                                String userBirthDay = json.getString("birthDay");
                                String userName = json.getString("name");
                                String userNickname = json.getString("nickname");
                                String userArea = json.getString("area");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("id", userId);
                                intent.putExtra("password", userPassword);
                                intent.putExtra("birthDay", userBirthDay);
                                intent.putExtra("name", userName);
                                intent.putExtra("nickname", userNickname);
                                intent.putExtra("area", userArea);
                                startActivity(intent);

                                //세션에 로그인 정보 올리기
                                SharedPreferences loginInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = loginInformation.edit();
                                editor.putString("id", userId);
                                editor.putString("birthDay", userBirthDay);
                                editor.putString("name", userName);
                                editor.putString("nickname", userNickname);
                                editor.putString("area", userArea);
                                editor.apply();

                            }else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(userId, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });
    }
}
