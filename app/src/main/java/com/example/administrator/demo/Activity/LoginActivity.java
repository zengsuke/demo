package com.example.administrator.demo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo.Class.CircularAnim;
import com.example.administrator.demo.R;


public class LoginActivity extends AppCompatActivity{
    private String username="zmj";
    private String userpass="123";
    private String usernameInfo;
    private String passwordInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        final TextView usernameview = (TextView) findViewById(R.id.login_username);
        final TextView passwordview = (TextView) findViewById(R.id.login_password);
        final ProgressBar mProgressBar=(ProgressBar)findViewById(R.id.ProgressBar);
        Button login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String inputname= usernameview.getText().toString().trim();
                String inputpassword=passwordview.getText().toString().trim();
                if (inputname.isEmpty() || inputpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputname.equals(username) && inputpassword.equals(userpass)) {
                    CircularAnim.fullActivity(LoginActivity.this, mProgressBar)
                            .colorOrImageRes(R.color.colorPrimary)
                            .go(new CircularAnim.OnAnimationEndListener() {
                                @Override
                                public void onAnimationEnd() {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码有误", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }


}
