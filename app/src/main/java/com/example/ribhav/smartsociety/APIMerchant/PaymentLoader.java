package com.example.ribhav.smartsociety.APIMerchant;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

/**
 * Created by Nitish Kumar on 10-10-2017.
 */

public class PaymentLoader extends AsyncTaskLoader<PaymentDetails>{

    private String url;
    public PaymentLoader(Context context, String o) {
        super(context);
        url=o;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public PaymentDetails loadInBackground() {
        if(url==null) {

            Log.e("this", "no url");
            return null;
        }
        PaymentDetails payment=Utility.getPayment(url);
        return payment;
    }

}
