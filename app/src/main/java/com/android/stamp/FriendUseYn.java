package com.android.stamp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendUseYn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_useyn);

        Response.Listener<String> responseListener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try {

                    JSONObject json = new JSONObject(response);
                    boolean success = json.getBoolean("success");
                    if (success) {

                        System.out.println(success);

                    }else {
                        Toast.makeText(getApplicationContext(), "실패하셨습니다.", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        SharedPreferences loginInformation = getSharedPreferences("loginInformation",0);
        String userId = loginInformation.getString("id","");

        UseYnRequest friend = new UseYnRequest(userId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(FriendUseYn.this);
        queue.add(friend);
    }

}
