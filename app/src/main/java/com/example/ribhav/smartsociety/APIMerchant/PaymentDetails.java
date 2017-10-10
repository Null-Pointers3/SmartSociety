package com.example.ribhav.smartsociety.APIMerchant;

/**
 * Created by Nitish Kumar on 10-10-2017.
 */

public class PaymentDetails {
    private String mOrderID;
    private String TXN_AMNT;
    private String Website;
    private String checkSum;
    private String mMiD;
    private String custID;
//    public PaymentDetails(String mOrderID, String TXN_AMNT, String Website, String checkSum, String mMiD){
//        this.checkSum=checkSum;
//        this.mOrderID=mOrderID;
//        this.TXN_AMNT=TXN_AMNT;
//        this.Website=Website;
//        this.mMiD=mMiD;
//    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public void setmOrderID(String mOrderID) {
        this.mOrderID = mOrderID;
    }

    public void setTXN_AMNT(String TXN_AMNT) {
        this.TXN_AMNT = TXN_AMNT;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public void setmMiD(String mMiD) {
        this.mMiD = mMiD;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public String getmMiD() {
        return mMiD;
    }

    public String getmOrderID() {
        return mOrderID;
    }

    public String getTXN_AMNT() {
        return TXN_AMNT;
    }

    public String getWebsite() {
        return Website;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }
}
