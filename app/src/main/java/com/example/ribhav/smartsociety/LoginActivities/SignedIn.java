package com.example.ribhav.smartsociety.LoginActivities;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ribhav.smartsociety.APIMerchant.PaymentDetails;
import com.example.ribhav.smartsociety.APIMerchant.PaymentLoader;
import com.example.ribhav.smartsociety.MainActivity;
import com.example.ribhav.smartsociety.MenuActivity;
import com.example.ribhav.smartsociety.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedIn extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PaymentDetails> {
//    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    public static PaymentDetails paymentElectricity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        if (currentUser == null) {
            Intent intent = new Intent(SignedIn.this, MainActivity.class);
            finish();
            startActivity(intent);

        }else {

            getLoaderManager().initLoader(1,null,this);
            setContentView(R.layout.activity_signed_in);
            Intent intent = new Intent(SignedIn.this, MenuActivity.class);
            finish();
            startActivity(intent);
        }

    }


    @Override
    public Loader<PaymentDetails> onCreateLoader(int i, Bundle bundle) {
        return new PaymentLoader(this,MainActivity.baseUrl);
    }

    @Override
    public void onLoadFinished(Loader<PaymentDetails> loader, PaymentDetails paymentDetails) {
        paymentElectricity=paymentDetails;
        Intent intent = new Intent(SignedIn.this, MerchantActivity.class);
        startActivity(intent);
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<PaymentDetails> loader) {

    }
}
