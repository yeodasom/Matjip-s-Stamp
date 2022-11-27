package com.android.stamp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class FriendSelect extends StringRequest {

    final static private String URL = "http://android2022.dothome.co.kr/FriendSelect.php";
    private Map<String,String> map;

    public FriendSelect(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        System.out.println("success");
        System.out.println(map);
        return map;
    }
}
