package com.example.ribhav.smartsociety;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ribhav.smartsociety.APIMerchant.PaymentDetails;
import com.example.ribhav.smartsociety.APIMerchant.PaymentLoader;
import com.example.ribhav.smartsociety.LoginActivities.UserLoginActivity;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<PaymentDetails>{
    private Button loginAsUser;
    private Button loginAsSociety;
    private static final String baseUrl="http://172.16.10.157:3000/electricity/kk";
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
        getLoaderManager().initLoader(1,null,this);

    }

    @Override
    public Loader<PaymentDetails> onCreateLoader(int i, Bundle bundle) {
        return new PaymentLoader(this,baseUrl);
    }

    @Override
    public void onLoadFinished(Loader<PaymentDetails> loader, PaymentDetails paymentDetails) {

    }

    @Override
    public void onLoaderReset(Loader<PaymentDetails> loader) {

    }
}
