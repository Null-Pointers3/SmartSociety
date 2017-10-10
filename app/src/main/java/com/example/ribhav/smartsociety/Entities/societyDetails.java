package com.example.ribhav.smartsociety.Entities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.ribhav.smartsociety.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class societyDetails extends AppCompatActivity {

    private static final int RC_PHOTO_PICKER = 2;
    EditText sName;
    EditText pin;
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

    private void updateDatabase() {
        Society currentSociety=returnSociety();
        String sName=currentSociety.getmSocietyName();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference society=database.getReference().child(sName);
        society.push().setValue(currentSociety);
    }

    private Society returnSociety() {
        sName=(EditText)findViewById(R.id.socName);
        pin=(EditText)findViewById(R.id.socPin);
        String socName=sName.getText().toString();
        String sPin=pin.getText().toString();
        Society currentSoc=new Society(socName,sPin);
        return currentSoc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_details);
    }

    public void loadImage(View view) {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(intent,"Complete Action using"),RC_PHOTO_PICKER);
    }
}
