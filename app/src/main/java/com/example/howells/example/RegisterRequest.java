package com.example.howells.example;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://aoen.hostei.com/Register.php";
    private Map<String, String> params;

    //constructor

    public  RegisterRequest(String First, String Last, int Birthdate, String email, String username, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("First", First);
        params.put("Last", Last);
        params.put("Birthdate", Birthdate + "");
        params.put("email", email);
        params.put("Username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
