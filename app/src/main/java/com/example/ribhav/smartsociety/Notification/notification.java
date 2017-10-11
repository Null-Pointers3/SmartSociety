//package com.example.ribhav.smartsociety.Notification;
//
//import android.app.AlarmManager;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.TaskStackBuilder;
//import android.view.View;
//
//import com.example.ribhav.smartsociety.R;
//
//import java.util.Calendar;
//
///**
// * Created by Nitish Kumar on 11-10-2017.
// */
//
//public class notification {
//    public void PushNotification(View view) {
//        // The id of the channel.
//        String CHANNEL_ID = "my_channel_01";
//        NotificationCompat.Builder mBuilder =
//                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.mipmap.ic_launcher_round)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!")
//                        .setChannel(CHANNEL_ID);
//// Creates an explicit intent for an Activity in your app
//        Intent resultIntent = new Intent(this, ResultActivity.class);
//
//// The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your app to the Home screen.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(ResultActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.InboxStyle inboxStyle =
//                new NotificationCompat.InboxStyle();
//        String events = "Please Pay the Amount";
//// Sets a title for the Inbox in expanded layout
//        inboxStyle.setBigContentTitle("Event tracker details:");
//
//// Moves events into the expanded layout
//
//        inboxStyle.addLine(events);
//// Moves the expanded layout object into the notification object.
//        mBuilder.setStyle(inboxStyle);
//
//// mNotificationId is a unique integer your app uses to identify the
//// notification. For example, to cancel the notification, you can pass its ID
//// number to NotificationManager.cancel().
//        mNotificationManager.notify(0, mBuilder.build());
//
//    }
//    public static void setReminder(Context context,Class<?> cls,int hour, int min)
//    {
//        Calendar calendar = Calendar.getInstance();
//        Calendar setcalendar = Calendar.getInstance();
//        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
//        setcalendar.set(Calendar.MINUTE, min);
//        setcalendar.set(Calendar.SECOND, 0);
//        // cancel already scheduled reminders
//        cancelReminder(context,cls);
//
//        if(setcalendar.before(calendar))
//            setcalendar.add(Calendar.DATE,1);
//
//        // Enable a receiver
//        ComponentName receiver = new ComponentName(context, cls);
//        PackageManager pm = context.getPackageManager();
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);
//
//        Intent intent1 = new Intent(context, cls);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//                DAILY_REMINDER_REQUEST_CODE, intent1,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);
//    }
//}
//
