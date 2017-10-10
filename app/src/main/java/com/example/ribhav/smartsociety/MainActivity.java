package com.example.ribhav.smartsociety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ribhav.smartsociety.LoginActivities.UserLoginActivity;

public class MainActivity extends AppCompatActivity {
    private Button loginAsUser;
    private Button loginAsSociety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginAsUser=(Button) findViewById(R.id.loginUser);
        loginAsSociety=(Button) findViewById(R.id.loginSociety);

        loginAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
