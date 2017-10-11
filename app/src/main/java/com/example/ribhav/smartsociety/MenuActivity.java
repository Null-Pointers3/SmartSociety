package com.example.ribhav.smartsociety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.ribhav.smartsociety.Adapter.MenuAdapter;
import com.example.ribhav.smartsociety.LoginActivities.MerchantActivity;
import com.example.ribhav.smartsociety.ResourceClasses.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                Intent intent=new Intent(MenuActivity.this, MerchantActivity.class);
                startActivity(intent);
            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }
        })
        );
        ArrayList<MenuItem> menuItems=getArrayList();
        MenuAdapter  menuadapter=new MenuAdapter(menuItems);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuadapter);
        mAuth = FirebaseAuth.getInstance();

    }

    private ArrayList<MenuItem> getArrayList() {
        ArrayList<MenuItem> menuitems=new ArrayList<>();
        menuitems.add(new MenuItem(R.drawable.utilities));
        menuitems.add(new MenuItem(R.drawable.utilities));
        menuitems.add(new MenuItem(R.drawable.utilities));
        return menuitems;
    }
    public void SignOut() {
        mAuth.signOut();
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid){
            case R.id.help: SignOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

/*
* public void PushNotification(View view) {
        // The id of the channel.
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setChannel(CHANNEL_ID);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
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
*/
