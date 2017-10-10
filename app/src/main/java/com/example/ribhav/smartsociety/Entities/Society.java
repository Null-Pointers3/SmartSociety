package com.example.ribhav.smartsociety.Entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nitish Kumar on 10-10-2017.
 */

public class Society {
    private String mSocietyName;
    private String mPinCode;
    public User mMembers;
    private String imageResourceUrl;

    public Society(String societyName, String mPinCode, String url){
        this.mSocietyName=societyName;
        this.mPinCode=mPinCode;
        this.imageResourceUrl=url;
    }

    public String getmSocietyName() {
        return mSocietyName;
    }

    public String getmPinCode() {
        return mPinCode;
    }
}
