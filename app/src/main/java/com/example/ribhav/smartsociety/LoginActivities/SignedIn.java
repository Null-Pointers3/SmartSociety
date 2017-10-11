package com.example.ribhav.smartsociety.LoginActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ribhav.smartsociety.APIMerchant.PaymentDetails;
import com.example.ribhav.smartsociety.MainActivity;
import com.example.ribhav.smartsociety.MenuActivity;
import com.example.ribhav.smartsociety.R;
import com.example.ribhav.smartsociety.ScheduledJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedIn extends AppCompatActivity {
//    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    public static PaymentDetails paymentElectricity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        if (currentUser == null) {
            Intent intent = new Intent(SignedIn.this, MainActivity.class);
//            finish();
            startActivity(intent);

        } else {

            setContentView(R.layout.activity_signed_in);
            Intent intent = new Intent(SignedIn.this, MenuActivity.class);
//            finish();
            startActivity(intent);
        }


        scheduleJob(this);

    }
    public static void scheduleJob(Context context) {
        //creating new firebase job dispatcher
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        //creating new job and adding it with dispatcher
        Job job = createJob(dispatcher);
        dispatcher.mustSchedule(job);
    }

    public static Job createJob(FirebaseJobDispatcher dispatcher){

        Job job = dispatcher.newJobBuilder()
                //persist the task across boots
                .setLifetime(Lifetime.FOREVER)
                //.setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                //call this service when the criteria are met.
                .setService(ScheduledJobService.class)
                //unique id of the task
                .setTag("UniqueTagForYourJob")
                //don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // We are mentioning that the job is periodic.
                .setRecurring(true)
                // Run between 30 - 60 seconds from now.
                .setTrigger(Trigger.executionWindow(30, 60))
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                //.setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                //Run this job only when the network is available.
                .setConstraints(Constraint.ON_ANY_NETWORK, Constraint.DEVICE_CHARGING)
                .build();
        return job;
    }

    public static Job updateJob(FirebaseJobDispatcher dispatcher) {
        Job newJob = dispatcher.newJobBuilder()
                //update if any task with the given tag exists.
                .setReplaceCurrent(true)
                //Integrate the job you want to start.
                .setService(ScheduledJobService.class)
                .setTag("UniqueTagForYourJob")
                // Run between 30 - 60 seconds from now.
                .setTrigger(Trigger.executionWindow(30, 60))
                .build();
        return newJob;
    }

    public void cancelJob(Context context){

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        //Cancel all the jobs for this package
        dispatcher.cancelAll();
        // Cancel the job for this tag
        dispatcher.cancel("UniqueTagForYourJob");

    }
}

