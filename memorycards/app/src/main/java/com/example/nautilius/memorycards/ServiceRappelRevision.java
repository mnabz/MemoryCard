package com.example.nautilius.memorycards;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;

public class ServiceRappelRevision extends Service {


    public static int NOTIF_ID = 75019;
    public ServiceRappelRevision() {
    }

    public void onCreate(){
        Log.i("RpppelService", "Received start id ");
        sendNotification();
    }



    void sendNotification() {
        //intent.setAction()
        Intent intent = new Intent(getApplicationContext(), ListCarteNonConsulte.class);
        TaskStackBuilder builder = TaskStackBuilder.create(getApplicationContext());
        builder.addParentStack(ListCarteNonConsulte.class);
        builder.addNextIntent(intent);

        PendingIntent pendingIntent = builder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Cartes non vues")
                        .setContentText("cliquez pour commencer lancer la revision et la gestion")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_book_white_18dp)
                        .build();
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(NOTIF_ID, notification);
    }






    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
