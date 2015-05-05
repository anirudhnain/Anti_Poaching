package com.example.anirudh_pc.anti_poaching;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by anand on 3/5/15.
 */
public class Create extends Activity {



    NotificationManager NM;
    EditText one,two;
    TextView tdor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createanimal);


        one = (EditText)findViewById(R.id.tspecies);
        two = (EditText)findViewById(R.id.thealth);
        String currentDateString = DateFormat.getDateInstance().format(new Date());
        tdor=(TextView)findViewById(R.id.doranswer);
        tdor.setText(currentDateString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("deprecation")
    public void notify(View vobj){
        String title = "Animal Details Created Successfully";//one.getText().toString();
        String body = "Success";//two.getText().toString();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

       /* NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(android.R.drawable.
                stat_notify_more,title,System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(
                getApplicationContext(),0, new Intent(),0);
        notify.setLatestEventInfo(getApplicationContext(),title,body,pending);
        NM.notify(0, notify);*/
       NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.twitter)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri);


        notificationManager.notify(0, mBuilder.build());
    }

}
