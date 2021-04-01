package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
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

                String message = "This is a local notification";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Local Notification");

                // Notification Sound
                notiSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(notiSound);

                // Notification Intent
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);

                builder.setContentTitle("New Notification");
                builder.setContentText(message);
                builder.setSmallIcon(R.drawable.noti);
                builder.setAutoCancel(true);



                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1, builder.build());
            }
        });
    }
}