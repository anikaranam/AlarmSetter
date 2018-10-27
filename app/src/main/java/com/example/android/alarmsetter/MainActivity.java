package com.example.android.alarmsetter;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submitAlarm;
    EditText alarmText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setAlarm(View view) {
        alarmText = (EditText) findViewById(R.id.nameTextBox);
        String text = alarmText.getText().toString();
        String[] words = text.split(" ");
        int x = 0;
        int i = 0;
        int y = 0;
        String message = "";
        for (i = 0; i < words.length; i++) {
            if (words[i].indexOf(':') != -1) {
                String[] numbers = words[i].split(":");
                x = Integer.parseInt(numbers[0]);
                y = Integer.parseInt(numbers[1]);
            }
        }
        if (x > 0 && y > 0 && x < 24 && y < 60) {
            createAlarm(message, x, y);
        } else {
            Toast.makeText(this, "Invalid time", Toast.LENGTH_LONG);
        }
    }
    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
