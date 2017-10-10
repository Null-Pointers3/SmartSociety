package com.example.ribhav.smartsociety.LoginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ribhav.smartsociety.MainActivity;
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
    public FirebaseUser currentUser;
    TextView EmailId;
    TextView CustomerId;
    TextView TransactionAmt;
    TextView OrderId;
    TextView MoblieNumber;
    TextView IndustryType;
    TextView Website;
    TextView Theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        EmailId = (TextView) findViewById(R.id.cust_email_id);
        EmailId.setText(currentUser.getEmail());
        CustomerId = (TextView) findViewById(R.id.customer_id);
        CustomerId.setText(SignedIn.paymentElectricity.getCustID());
        TransactionAmt = (TextView) findViewById(R.id.Transaction_amount);
        TransactionAmt.setText(SignedIn.paymentElectricity.getTXN_AMNT());
        OrderId = (TextView) findViewById(R.id.order_id);
        OrderId.setText(SignedIn.paymentElectricity.getmOrderID());
        MoblieNumber = (TextView) findViewById(R.id.cust_mobile_no);
        MoblieNumber.setText("9958854841");
        IndustryType = (TextView) findViewById(R.id.industry_type_id);
        IndustryType.setText("Retail");
        Website = (TextView) findViewById(R.id.website);
        Website.setText(SignedIn.paymentElectricity.getWebsite());
        Theme = (TextView) findViewById(R.id.theme);
        Theme.setText("merchant");
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
        TextView orderIdEditText = (TextView) findViewById(R.id.order_id);
        orderIdEditText.setText(orderId);
    }

    public void onStartTransaction(View view) {
        PaytmPGService Service = PaytmPGService.getStagingService();
        Map<String, String> paramMap = new HashMap<String, String>();


        // these are mandatory parameters

        paramMap.put("ORDER_ID", (SignedIn.paymentElectricity.getmOrderID()));
        paramMap.put("MID", (SignedIn.paymentElectricity.getmMiD()));
        paramMap.put("CUST_ID", (SignedIn.paymentElectricity.getCustID()));
        paramMap.put("CHANNEL_ID", "WAP");
        paramMap.put("INDUSTRY_TYPE_ID", "Retail");
        paramMap.put("WEBSITE", (SignedIn.paymentElectricity.getWebsite()));
        paramMap.put("TXN_AMOUNT", SignedIn.paymentElectricity.getTXN_AMNT());
        paramMap.put("THEME","merchant");
        paramMap.put("EMAIL", currentUser.getEmail());
        paramMap.put("MOBILE_NO", "9958854841");

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

    public void SignOut() {
        mAuth.signOut();
        Intent intent = new Intent(MerchantActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid){
            case R.id.help: SignOut();
        }
        return super.onOptionsItemSelected(item);
    }
}