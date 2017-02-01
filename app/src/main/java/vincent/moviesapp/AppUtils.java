package vincent.moviesapp;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Tene on 01.02.2017.
 */

public class AppUtils {


    public static  void LaunchToastNotification(Context context, int notificatinId, int imageIg, String title, String message, String [] events){

        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setSmallIcon(imageIg) ;
        mBuilder.setContentTitle(title) ;
        mBuilder.setContentText(message );


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        if(null != events){
            // Moves events into the expanded layout
            for (int i=0; i < events.length; i++) {
                inboxStyle.addLine(events[i]);
            }// Moves the expanded layout object into the notification object.
            mBuilder.setStyle(inboxStyle);
        }


        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(notificatinId, mBuilder.build());

    }

}
