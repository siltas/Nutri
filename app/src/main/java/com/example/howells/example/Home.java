package com.example.howells.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final EditText text_Email = (EditText) findViewById(R.id.text_User);
        final EditText text_User = (EditText) findViewById(R.id.text_User);
        final EditText text_Password = (EditText) findViewById(R.id.text_Password);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
