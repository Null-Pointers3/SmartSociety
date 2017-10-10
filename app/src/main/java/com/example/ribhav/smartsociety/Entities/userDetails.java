package com.example.ribhav.smartsociety.Entities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ribhav.smartsociety.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userDetails extends AppCompatActivity {

    EditText name;
    EditText contact;
    EditText pin;
    EditText SocietyName;
    EditText flat;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tickmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()){
            case (R.id.tick) :
               updateDatabase();
                return true;
        }
    return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details2);

    }
    public User returnCurrentUser(){
        name=(EditText)findViewById(R.id.userName);
        contact=(EditText)findViewById(R.id.contact);
        pin=(EditText)findViewById(R.id.pinCode);
        SocietyName=(EditText) findViewById(R.id.societyName);
        flat=(EditText)findViewById(R.id.flatNo);
        User currentUser=new User();
        currentUser.setFlatNumber(flat.getText().toString());
        currentUser.setPincode(pin.getText().toString());
        currentUser.setSocietyname(SocietyName.getText().toString());
        currentUser.setUserName(name.getText().toString());
        currentUser.setContact_No(contact.getText().toString());
        return currentUser;
    }

    public void updateDatabase(){
        User currentUser=returnCurrentUser();
        String societyName=currentUser.getSocietyname();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference society=database.getReference().child(societyName);
        DatabaseReference members=database.getReference().child(societyName).child("members");
        members.push().setValue(currentUser);

    }

}
