package com.example.ribhav.smartsociety.Entities;

import java.util.ArrayList;

/**
 * Created by Nitish Kumar on 10-10-2017.
 */

public class Society {
    private String mSocietyName;
    private int mPinCode;
    private ArrayList<User> mMembers;
    private String imageResourceUrl;

    public Society(String societyName, int mPinCode){
        this.mSocietyName=societyName;
        this.mPinCode=mPinCode;
    }

    public String getmSocietyName() {
        return mSocietyName;
    }

    public int getmPinCode() {
        return mPinCode;
    }
}
