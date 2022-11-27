package com.android.stamp;

import android.icu.text.SimpleDateFormat;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FriendInsert extends StringRequest {

    final static private String URL = "http://android2022.dothome.co.kr/FriendInsert.php";
    private Map<String,String> map;

    public FriendInsert(String userId, String id, String mFormat,  Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        System.out.println(mFormat);
        map.put("userId", userId);
        map.put("id",id);
        map.put("updateDt", mFormat);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        System.out.println("testsuccess");
        return map;
    }
}
