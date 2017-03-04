package com.example.howells.example;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText text_First = (EditText) findViewById(R.id.text_First);
        final EditText text_Last = (EditText) findViewById(R.id.text_Last);
        final EditText text_Birthdate = (EditText) findViewById(R.id.text_Birthdate);
        final EditText text_Email = (EditText) findViewById(R.id.text_Email);
        final EditText text_User = (EditText) findViewById(R.id.text_User);
        final EditText text_Password = (EditText) findViewById(R.id.text_Password);
        final Button button_Finish = (Button) findViewById(R.id.button_Finish);

        button_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String First = text_First.getText().toString();
                final String Last = text_Last.getText().toString();
                final int Birthdate = Integer.parseInt(text_First.getText().toString());
                final String Email = text_Email.getText().toString();
                final String User = text_User.getText().toString();
                final String Pass = text_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                Intent intent = new Intent(SignUp.this, Home.class);
                                SignUp.this.startActivity(intent);
                            }else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage("Registry Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(First, Last, Birthdate, Email, User, Pass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                queue.add(registerRequest);
            }
        });
    }
}
