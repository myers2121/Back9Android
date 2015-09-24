package com.astral.back9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText, firstNameText, lastNameText, emailText, usernameText, passwordText, phoneText;

    private Button signUp;

    private String firstName, lastName, email, username, password, phone, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setNavigationIcon(R.mipmap.back_button_nav);

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

        toolbarText.setText("REGISTER");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        firstNameText = (TextView) findViewById(R.id.first_name_register);
        lastNameText = (TextView) findViewById(R.id.last_name_register);
        emailText = (TextView) findViewById(R.id.email_register);
        usernameText = (TextView) findViewById(R.id.username_register);
        passwordText = (TextView) findViewById(R.id.password_register);
        phoneText = (TextView) findViewById(R.id.mobile_register);

        signUp = (Button) findViewById(R.id.next_register);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailText.getText().toString();
                phone = phoneText.getText().toString();
                lastName = lastNameText.getText().toString();
                firstName = firstNameText.getText().toString();
                password = passwordText.getText().toString();
                username = usernameText.getText().toString();


                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

// other fields can be set just like with ParseObject
                user.put("PhoneNumber", phone);
                user.put("FirstName", firstName);
                user.put("LastName", lastName);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
