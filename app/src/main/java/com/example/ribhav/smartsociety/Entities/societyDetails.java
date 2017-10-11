package com.example.ribhav.smartsociety.Entities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.bumptech.glide.Glide;
import com.example.ribhav.smartsociety.MenuActivity;
import com.example.ribhav.smartsociety.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class societyDetails extends AppCompatActivity {

    private FirebaseDatabase database;
    private static final int RC_PHOTO_PICKER = 2;
    EditText sName;
    private FirebaseStorage mFirebaseStorage;
    StorageReference mStorage;
    Society currentSociety;
    EditText pin;
    ImageView photo;
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
                Intent intent=new Intent(societyDetails.this, MenuActivity.class);
                finish();
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateDatabase() {

        String sName=currentSociety.getmSocietyName();
        mStorage=mFirebaseStorage.getReference("soc_Photos");
        DatabaseReference society=database.getReference().child(sName);
        society.push().setValue(currentSociety);

        photo.setVisibility(View.VISIBLE);
        Glide.with(photo.getContext())
                .load(currentSociety.getImageResourceUrl())
                .into(photo);
    }

    private Society returnSociety() {
        sName=(EditText)findViewById(R.id.socName);
        pin=(EditText)findViewById(R.id.socPin);
        photo=(ImageView)findViewById(R.id.photo);
        String socName=sName.getText().toString();
        String sPin=pin.getText().toString();
        Society currentSoc=new Society(socName,sPin);
        return currentSoc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_details);
        mFirebaseStorage=FirebaseStorage.getInstance();
        mStorage=mFirebaseStorage.getReference().child("photo");
        database=FirebaseDatabase.getInstance();
        currentSociety=returnSociety();
        photo=(ImageView)findViewById(R.id.photo);
    }


    public void loadImage(View view) {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(intent,"Complete Action using"),RC_PHOTO_PICKER);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef = mStorage.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadURl = taskSnapshot.getDownloadUrl();
                    currentSociety.setImageResourceUrl(downloadURl.toString());


                }
            });
        }
    }}