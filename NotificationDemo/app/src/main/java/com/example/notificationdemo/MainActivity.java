package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static Uri notiSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton notiBtn = findViewById(R.id.notiBtn);

        notiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Local Notification");
                notiSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                builder.setContentTitle("New Notification");
                builder.setContentText("This is a local notification");
                builder.setSmallIcon(R.drawable.noti);
                builder.setSound(notiSound);
                builder.setAutoCancel(true);



                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1, builder.build());
            }
        });
    }
}