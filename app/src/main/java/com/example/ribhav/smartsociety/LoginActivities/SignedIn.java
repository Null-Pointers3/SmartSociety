package com.example.ribhav.smartsociety.LoginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ribhav.smartsociety.MainActivity;
import com.example.ribhav.smartsociety.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(SignedIn.this, MainActivity.class);
            finish();
            startActivity(intent);

        }else {
            setContentView(R.layout.activity_signed_in);
            Intent intent = new Intent(SignedIn.this, MerchantActivity.class);
            startActivity(intent);
        }

    }


}
