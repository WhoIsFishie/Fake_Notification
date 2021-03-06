package com.fishie.fakenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //idk what this does
        //all i know is as long as it exists the code is fine so let it be
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "gobama"
            val descriptionText = "obama"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("obama", name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        //init
        val btn_click_me = findViewById(R.id.caculate) as Button;
        var Title = findViewById(R.id.title) as EditText;
        var Notif = findViewById(R.id.content) as EditText;
        //end init


        // set on-click listener
        var textTitle = Title.text;
        var textContent = Notif.text;
        btn_click_me.setOnClickListener {

            //construct notif
            var builder = NotificationCompat.Builder(this, "obama")
                .setSmallIcon(R.drawable.ic_launcher_background) //this can be customized in the future if i feel like it
                .setContentTitle(textTitle) //assign title
                .setContentText(textContent) //assign content
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notification_id = Random() //give custom id so app can make many Notification

            //show Notification
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notification_id.nextInt(100), builder.build())
            }

            //debug
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show();
        }
    }
}