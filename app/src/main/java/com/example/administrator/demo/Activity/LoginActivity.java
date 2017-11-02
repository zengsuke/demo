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
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.LoginReply;
import io.grpc.examples.helloworld.LoginRequest;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.grpc.okhttp.internal.Platform.logger;


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
                Boolean login = HelloWorldClient("10.0.2.2",50051,inputname,inputpassword);
                if (inputname.isEmpty() || inputpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (login) {
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

        private ManagedChannel channel;
        private GreeterGrpc.GreeterBlockingStub blockingStub;

        /** Construct client connecting to HelloWorld server at {@code host:port}. */
        public Boolean HelloWorldClient(String host, int port,String name,String password) {

            try {
                channel = ManagedChannelBuilder.forAddress(host, port)
                        .usePlaintext(true)
                        .build();
                blockingStub = GreeterGrpc.newBlockingStub(channel);
                LoginRequest request = LoginRequest.newBuilder().setName(name).setPassword(password).build();
                LoginReply response = blockingStub.logon(request);
                System.out.println(response.getMe()+"fuck u");
                if(response.getMe().equals("yes")){
                    return true;
                }else {
                    return false;
                }
            }catch (Exception e){
                return false;
            }

        }

        public void shutdown() throws InterruptedException {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }

        /** Say hello to server. */


        /**
         * Greet server. If provided, the first element of {@code args} is the name to use in the
         * greeting.
         */
}



