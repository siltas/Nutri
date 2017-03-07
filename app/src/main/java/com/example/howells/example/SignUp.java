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
/*
creating a link between the input fields in the layout/activity and the code,
so that we can accept the information and send it to the database
*/
        final EditText text_First = (EditText) findViewById(R.id.text_First);
        final EditText text_Last = (EditText) findViewById(R.id.text_Last);
        final EditText text_Birthdate = (EditText) findViewById(R.id.text_Birthdate);
        final EditText text_Email = (EditText) findViewById(R.id.text_Email);
        final EditText text_User = (EditText) findViewById(R.id.text_User);
        final EditText text_Password = (EditText) findViewById(R.id.text_Password);
        //Register button
        final Button button_Finish = (Button) findViewById(R.id.button_Finish);

        /* Sets the onclick listener for the register
        button(determines what is done when Finish is clicked. */
        button_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reads the text field of the register information and inputs it into these variables
                final String First = text_First.getText().toString();
                final String Last = text_Last.getText().toString();
                final int Birthdate = Integer.parseInt(text_First.getText().toString());
                final String Email = text_Email.getText().toString();
                final String User = text_User.getText().toString();
                final String Pass = text_Password.getText().toString();

                /*
                Creates an object(responseListener of type Response.Listener<T>(These classes are found in the volley library,
                the library used to send and recieve info from databases.
                */
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    //The @Override function is used to modify a class that is already created,
                    //so basically what this code is saying is; edit the onResponse class in the volley database
                    //to ...
                    @Override
                    public void onResponse(String response) {
                        try {
                            //take the jsonresponse. The response from the database is a JSONObject
                            // and this response is accepted into the program. either yes or no(because its bool)
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                /*
                                Ima be real honest, im now getting a real grasp on what "this" does,
                                when i really find out, ill explain this properly.Intent runs activities
                                So my assumption(i think im right) is this is running classes from the Home and SignUp classes
                                */
                                Intent intent = new Intent(SignUp.this, Home.class);
                                SignUp.this.startActivity(intent);
                            }else
                            {
                                //Tells you if registry fails
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage("Registry Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                            //if you didn't get a response from database and error comes up, wont run without this try/catch loop.
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //Creates object of type RegisterRequest(This constructs with the register info)
                RegisterRequest registerRequest = new RegisterRequest(First, Last, Birthdate, Email, User, Pass, responseListener);
                //just more volley code to send register info to database
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                queue.add(registerRequest);
            }
        });
    }
}
