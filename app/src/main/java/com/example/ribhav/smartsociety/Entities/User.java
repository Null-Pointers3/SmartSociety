package com.example.ribhav.smartsociety.Entities;

import android.telephony.PhoneNumberUtils;

/**
 * Created by ribhav on 10/10/17.
 */

public class User {
    private String userName;
    private String flatNumber;
    private int pincode;
    private Society society;
    private Long Contact_no;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public Long getContact_no() {
        return Contact_no;
    }

    public void setContact_no(Long contact_no) {
        Contact_no = contact_no;
    }
}
