package ga.finappsparty.com.gather.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.Timer;
import java.util.TimerTask;

import ga.finappsparty.com.gather.R;

public class F_Home extends Activity {
    private int numMessagesTwo = 0;
    private NotificationManager myNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_container);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                notificationTwo();
            }
        };
        timer.schedule(task, 0, 3600);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.f__home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.f__home, container, false);
            return rootView;
        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notificationTwo() {

        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
        notiBuilder.setContentTitle("¿Hacer transferéncia?");
        notiBuilder.setContentText("¿Vas a realizar una transferéncia, está seguro?");
        notiBuilder.setTicker("Transferéncia");
        notiBuilder.setSmallIcon(R.drawable.app_icon);

        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();

        String[] events = new String[2];
        events[0] = new String("Bote: Boda de Maria");
        events[1] = new String("150€");

        inbox.setBigContentTitle("Detalles de la transacción: ");
        for (int i = 0; i<events.length; i++ ) {
            inbox.addLine(events[i]);
        }

        notiBuilder.setStyle(inbox);
        notiBuilder.setNumber(++numMessagesTwo);

        notiBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(this, Notification.class);
        resultIntent.putExtra("notificationId", 112);

        TaskStackBuilder task = TaskStackBuilder.create(this);
        task.addParentStack(Notification.class);

        task.addNextIntent(resultIntent);

        PendingIntent resultPending = task.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);
        notiBuilder.setContentIntent(resultPending);

        myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(112, notiBuilder.build());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notificationThree() {

        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
        notiBuilder.setContentTitle("¿Hacer transferéncia?");
        notiBuilder.setContentText("¿Vas a realizar una transferéncia, está seguro?");
        notiBuilder.setTicker("Transferéncia");
        notiBuilder.setSmallIcon(R.drawable.app_icon);

        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();

        String[] events = new String[2];
        events[0] = new String("Bote: Máquina de escribir");
        events[1] = new String("150€");

        inbox.setBigContentTitle("Detalles de la transacción: ");
        for (int i = 0; i<events.length; i++ ) {
            inbox.addLine(events[i]);
        }

        notiBuilder.setStyle(inbox);
        notiBuilder.setNumber(++numMessagesTwo);

        notiBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(this, Notification.class);
        resultIntent.putExtra("notificationId", 112);

        TaskStackBuilder task = TaskStackBuilder.create(this);
        task.addParentStack(Notification.class);

        task.addNextIntent(resultIntent);

        PendingIntent resultPending = task.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);
        notiBuilder.setContentIntent(resultPending);

        myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(112, notiBuilder.build());
    }


}
