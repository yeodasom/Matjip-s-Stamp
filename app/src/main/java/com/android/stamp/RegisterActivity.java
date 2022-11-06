package com.android.stamp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] area = {"지역을 선택해주세요", "서울특별시", "제주특별시", "세종특별시","대전광역시","대구광역시"};
    String area_result;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Spinner spinner = (Spinner)findViewById(R.id.area);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, area);

        ad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(ad);


        final EditText id = (EditText) findViewById(R.id.id);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText nickname = (EditText) findViewById(R.id.nickname);
        final EditText birth = (EditText)findViewById(R.id.birth);
        Button registerBtn = (Button) findViewById(R.id.join);
        Button cancel = (Button) findViewById(R.id.cancel);

        registerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String userId = id.getText().toString();
                String userPassword = password.getText().toString();
                String userName = name.getText().toString();
                String userNickname = nickname.getText().toString();
                String userBirth = birth.getText().toString();
                String userArea = area_result;

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "회원가입 성공하였습니다..", Toast.LENGTH_SHORT).show();
                        Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(registerIntent);

                    }
                };
                RegisterRequest request = new RegisterRequest(userId, userPassword, userName, userNickname, userBirth, userArea, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(request);
            }

        });

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), area[position], Toast.LENGTH_LONG).show();
        area_result = area[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}