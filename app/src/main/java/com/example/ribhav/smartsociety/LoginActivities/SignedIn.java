package com.example.ribhav.smartsociety.LoginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ribhav.smartsociety.MainActivity;
import com.example.ribhav.smartsociety.MenuActivity;
import com.example.ribhav.smartsociety.R;
import com.example.ribhav.smartsociety.ResourceClasses.MenuItem;
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

<<<<<<< HEAD
        }
        setContentView(R.layout.activity_signed_in);
        Intent intent = new Intent(SignedIn.this,MerchantActivity.class);
        startActivity(intent);
=======
        } else {
            Intent intent = new Intent(SignedIn.this, MenuActivity.class);
            finish();
            startActivity(intent);

        }
>>>>>>> Nimit
    }


}
