package com.android.stamp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://android2022.dothome.co.kr/Register.php";
    private Map<String,String> map;

    public RegisterRequest(String id, String password,String name, String nickname, String birth, String area, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        map.put("name", name);
        map.put("nickname", nickname);
        map.put("birth", birth);
        map.put("area", area);

    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

