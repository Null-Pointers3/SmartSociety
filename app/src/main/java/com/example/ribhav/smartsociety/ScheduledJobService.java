package com.example.ribhav.smartsociety;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.ribhav.smartsociety.LoginActivities.MerchantActivity;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by ribhav on 11/10/17.
 */

public class ScheduledJobService extends JobService {
    private static final String TAG = ScheduledJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(final JobParameters params) {
        //Offloading work to a new thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                uploadDatToFirebase(params);
            }
        }).start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    private void uploadDatToFirebase(final JobParameters parameters) {
        try{
            codeYouWantToRun(parameters);
            Thread.sleep(252000000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            jobFinished(parameters, true);
        }
    }

    public void codeYouWantToRun(final JobParameters parameters) {


                // The id of the channel.
                String CHANNEL_ID = "my_channel_01";
                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher_round)
                                .setContentTitle("Payment")
                                .setContentText("Due date approaching")
                                .setChannel(CHANNEL_ID);
// Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, MerchantActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your app to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MerchantActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.InboxStyle inboxStyle =
                        new NotificationCompat.InboxStyle();
                String events = "Please Pay the Amount";
// Sets a title for the Inbox in expanded layout
                inboxStyle.setBigContentTitle("Event tracker details:");

// Moves events into the expanded layout

                inboxStyle.addLine(events);
// Moves the expanded layout object into the notification object.
                mBuilder.setStyle(inboxStyle);

// mNotificationId is a unique integer your app uses to identify the
// notification. For example, to cancel the notification, you can pass its ID
// number to NotificationManager.cancel().
                mNotificationManager.notify(0, mBuilder.build());




    }
}
