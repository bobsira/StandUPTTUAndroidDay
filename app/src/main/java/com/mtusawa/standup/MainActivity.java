package com.mtusawa.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton alarmToggle;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if (isChecked) {
                    deliverNotification(MainActivity.this);
                    //Set the toast message for the "on" case
                    toastMessage = getString(R.string.alarm_on_toast);
                } else {

                    //Cancel notification if the alarm is turned off
                    mNotificationManager.cancelAll();

                    //Set the toast message for the "off" case
                    toastMessage = getString(R.string.alarm_off_toast);
                }

                //Show a toast to say the alarm is turned on or off
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });




    }

    private void deliverNotification(Context context) {
        //Create the content intent for the notification, which launches this activity
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent contentIntent = new Intent(this, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
