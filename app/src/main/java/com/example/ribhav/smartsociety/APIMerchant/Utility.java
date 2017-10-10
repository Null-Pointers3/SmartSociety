package com.example.ribhav.smartsociety.APIMerchant;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Nitish Kumar on 10-10-2017.
 */

public class Utility {
    private static final String LOG_TAG = "hello";
    private Utility() {
    }

    static PaymentDetails getPayment(String url){
        URL url1=ceateurl(url);
        String jsonResponse = "";
        try {
            jsonResponse = httpRequest(url1);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making http request");
        }
        Log.e(LOG_TAG,jsonResponse);
        PaymentDetails payment=getPayments(jsonResponse);
        return payment;
    }

    private static PaymentDetails getPayments(String jsonResponse) {
        if(jsonResponse==null){
            Log.e(LOG_TAG,"no json string");
            return null;
        }
        PaymentDetails payment=new PaymentDetails();

        try {
            JSONObject baseJ=new JSONObject(jsonResponse);
            payment.setmOrderID(baseJ.getString("order_id"));
            payment.setTXN_AMNT(baseJ.getString("txn_amnt"));
            payment.setCheckSum(baseJ.getString("checksum"));
            payment.setWebsite(baseJ.getString("website"));
            payment.setmMiD(baseJ.getString("merchantId"));
            payment.setCustID(baseJ.getString("custId"));
            Log.e(LOG_TAG,baseJ.getString("merchantId"));

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the json object", e);
        }
        return payment;
    }

    private static String httpRequest(URL url) throws IOException{
        String jsonResult = "";
        if (url == null)
            return jsonResult;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(70000 /* milliseconds */);
            urlConnection.setConnectTimeout(30000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.e(LOG_TAG,urlConnection.getResponseCode()+"");
//            Toast.makeText(,"code is"+urlConnection.getResponseCode(),Toast.LENGTH_SHORT).show();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK || urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                inputStream = urlConnection.getInputStream();
                jsonResult = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code:" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "error fetching result", e);
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
        }
        Log.e(LOG_TAG,jsonResult.toString());
        return jsonResult.toString();
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader buffreader = new BufferedReader(reader);
            String line = buffreader.readLine();
            while (line != null) {
                output.append(line);
                line = buffreader.readLine();
            }
        }
        return output.toString();
    }

    private static URL ceateurl(String url) {
        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Malformed URL", e);
        }
        return url1;
    }
}
