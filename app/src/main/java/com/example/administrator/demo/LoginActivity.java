package com.example.administrator.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private String username;
    private String userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        final TextView usernameview = (TextView) findViewById(R.id.login_username);
        final TextView passwordview = (TextView) findViewById(R.id.login_password);
        Button login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
