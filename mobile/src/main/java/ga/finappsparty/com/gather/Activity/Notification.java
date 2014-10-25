package ga.finappsparty.com.gather.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ga.finappsparty.com.gather.R;

public class Notification extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String output = "Inside the activity of Notification two: ";
        TextView dataIntent = (TextView)findViewById(R.id.notificationText);

        Uri url = getIntent().getData();
        Bundle extras = getIntent().getExtras();

//        output = output + url.toString();

        if (extras != null) {
            output = output + " from " + extras.getString("from");

        }

        dataIntent.setText(output);



    }
}
