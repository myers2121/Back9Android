package com.astral.back9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;



public class ForgotPassword extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText;
    private Button submitButton;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailText = (EditText) findViewById(R.id.forgot_password_email);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setNavigationIcon(R.mipmap.back_button_nav);

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

        toolbarText.setText("FORGOT PASSWORD");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        submitButton = (Button) findViewById(R.id.forgot_password_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent intent = new Intent(ForgotPassword.this, SignIn.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"You have been sent an email", Toast.LENGTH_LONG).show();
                        }
                        else {
                            // Something went wrong. Look at the ParseException to see what's up.
                        }
                    }
                });
            }
        });
    }

}
