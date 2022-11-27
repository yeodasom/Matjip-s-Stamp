package com.android.stamp;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UseYnRequest extends StringRequest {

    final static private String URL = "http://android2022.dothome.co.kr/UseYnRequst.php";
    private Map<String,String> map;

    public UseYnRequest(String userId, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map=new HashMap<>();

        map.put("userId", userId);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        System.out.println("UseYnRequest_success");
        System.out.println(map);
        return map;
    }
}
