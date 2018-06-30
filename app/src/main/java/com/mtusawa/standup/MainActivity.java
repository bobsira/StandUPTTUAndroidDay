package com.mtusawa.standup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton alarmToggle;

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
                    //Set the toast message for the "on" case
                    toastMessage = getString(R.string.alarm_on_toast);
                } else {
                    //Set the toast message for the "off" case
                    toastMessage = getString(R.string.alarm_off_toast);
                }

                //Show a toast to say the alarm is turned on or off
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
