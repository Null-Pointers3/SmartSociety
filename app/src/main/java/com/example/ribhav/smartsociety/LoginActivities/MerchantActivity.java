package com.example.ribhav.smartsociety.LoginActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ribhav.smartsociety.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.paytm.pgsdk.PaytmMerchant;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MerchantActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    String userName;
    String Email;
    public FirebaseUser currentUser;
    TextView EmailId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        EmailId = (TextView) findViewById(R.id.cust_email_id);
        EmailId.setText(currentUser.getEmail());
        Log.d("MerchantActivity",currentUser.getEmail());

    }

    //This is to refresh the order id: Only for the Sample App's purpose.
    @Override
    protected void onStart(){
        super.onStart();
        initOrderId();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    private void initOrderId() {
        Random r = new Random(System.currentTimeMillis());
        String orderId = "ORDER" + (1 + r.nextInt(2)) * 10000
                + r.nextInt(10000);
        EditText orderIdEditText = (EditText) findViewById(R.id.order_id);
        orderIdEditText.setText(orderId);
    }

    public void onStartTransaction(View view) {
        PaytmPGService Service = PaytmPGService.getStagingService();
        Map<String, String> paramMap = new HashMap<String, String>();


        // these are mandatory parameters

        paramMap.put("ORDER_ID", ((TextView) findViewById(R.id.order_id)).getText().toString());
        paramMap.put("MID", ((TextView) findViewById(R.id.merchant_id)).getText().toString());
        paramMap.put("CUST_ID", ((TextView) findViewById(R.id.customer_id)).getText().toString());
        paramMap.put("CHANNEL_ID", ((TextView) findViewById(R.id.channel_id)).getText().toString());
        paramMap.put("INDUSTRY_TYPE_ID", ((TextView) findViewById(R.id.industry_type_id)).getText().toString());
        paramMap.put("WEBSITE", ((TextView) findViewById(R.id.website)).getText().toString());
        paramMap.put("TXN_AMOUNT", ((TextView) findViewById(R.id.transaction_amount)).getText().toString());
        paramMap.put("THEME", ((TextView) findViewById(R.id.theme)).getText().toString());
        paramMap.put("EMAIL", currentUser.getEmail());
        paramMap.put("MOBILE_NO", ((TextView) findViewById(R.id.cust_mobile_no)).getText().toString());

        PaytmOrder Order = new PaytmOrder(paramMap);

        PaytmMerchant Merchant = new PaytmMerchant(
                "https://pguat.paytm.com/paytmchecksum/paytmCheckSumGenerator.jsp",
                "https://pguat.paytm.com/paytmchecksum/paytmCheckSumVerify.jsp");

        Service.initialize(Order, null);

        Service.startPaymentTransaction(this, true, true,
                new PaytmPaymentTransactionCallback() {
                    @Override
                    public void someUIErrorOccurred(String inErrorMessage) {
                        // Some UI Error Occurred in Payment Gateway Activity.
                        // // This may be due to initialization of views in
                        // Payment Gateway Activity or may be due to //
                        // initialization of webview. // Error Message details
                        // the error occurred.
                        Toast.makeText(getApplicationContext(), "Ui/Webview error occured.", Toast.LENGTH_LONG).show();

                    }

//                    @Override
//                    public void onTransactionSuccess(Bundle inResponse) {
//                        // After successful transaction this method gets called.
//                        // // Response bundle contains the merchant response
//                        // parameters.
//                        Log.d("LOG", "Payment Transaction is successful " + inResponse);
//                        Toast.makeText(getApplicationContext(), "Payment Transaction is successful ", Toast.LENGTH_LONG).show();
//                    }

//                    @Override
//                    public void onTransactionFailure(String inErrorMessage,
//                                                     Bundle inResponse) {
//                        // This method gets called if transaction failed. //
//                        // Here in this case transaction is completed, but with
//                        // a failure. // Error Message describes the reason for
//                        // failure. // Response bundle contains the merchant
//                        // response parameters.
//                        Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
//                        Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
//                        recreate();
//                    }

                    @Override
                    public void onTransactionResponse(Bundle bundle) {

                    }

                    @Override
                    public void networkNotAvailable() { // If network is not
                        // available, then this
                        // method gets called.
                        Toast.makeText(getBaseContext(), "No Internet connection.", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void clientAuthenticationFailed(String inErrorMessage) {
                        // This method gets called if client authentication
                        // failed. // Failure may be due to following reasons //
                        // 1. Server error or downtime. // 2. Server unable to
                        // generate checksum or checksum response is not in
                        // proper format. // 3. Server failed to authenticate
                        // that client. That is value of payt_STATUS is 2. //
                        // Error Message describes the reason for failure.
                        Toast.makeText(getBaseContext(), "Client Authentication Failed.", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onErrorLoadingWebPage(int iniErrorCode,
                                                      String inErrorMessage, String inFailingUrl) {

                    }

                    // had to be added: NOTE
                    @Override
                    public void onBackPressedCancelTransaction() {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onTransactionCancel(String s, Bundle bundle) {

                    }

                });
    }
}