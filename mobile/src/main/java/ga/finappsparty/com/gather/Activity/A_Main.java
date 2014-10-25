package ga.finappsparty.com.gather.Activity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import ga.finappsparty.com.gather.R;
import ga.finappsparty.com.gather.Responses.HTTPHandler;
import ga.finappsparty.com.gather.Util.Creator;


public class A_Main extends Activity {
    private TextView titulo, descripcion, titular, dinero;
    private ImageView image;
    private LinearLayout layout_loading, layout_principal;
    private Button transferButton;
    private static String url = "http://Sthorms.com:1234/pot/";
    public final static String INITIAL_KEY_BOTE="INITIAL_KEY_BOTE";
    private final int DELAY_MILIS = 1500;
    private static final String POT = "POT_NAME";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String POT_CONCEPT = "POT_CONCEPT";
    private static final String POT_IMAGE = "POT_IMAGE";
    private static final String IBAN = "IBAN";
    JSONArray pot = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        //text & Typefaces
        image = (ImageView)findViewById(R.id.imageBote);

        titulo = (TextView)findViewById(R.id.tituloBote);
        titulo.setTypeface(Creator.genFont(getAssets(), 4));

        descripcion = (TextView)findViewById(R.id.descripcionBote);
        descripcion.setTypeface(Creator.genFont(getAssets(), 6));

        titular = (TextView)findViewById(R.id.nomTitularBote);
        titular.setTypeface(Creator.genFont(getAssets(), 5));

        dinero = (TextView)findViewById(R.id.dineroTransBote);
        dinero.setTypeface(Creator.genFont(getAssets(), 5));


        transferButton = (Button)findViewById(R.id.transferButton);
        transferButton.setTypeface(Creator.genFont(getAssets(), 6));
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences login = getSharedPreferences("isLogued", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = login.edit();
                boolean isLogued = login.getBoolean("isLogue",false);

                if(isLogued) {
                    Log.d("log", "Logueado");
                } else {
                    Log.d("log", "NO Logueado");
                    editor.putBoolean("isLogued", true);
                    Intent l = new Intent(A_Main.this, SignIn.class);
                    startActivity(l);


                }
                editor.commit();


            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scanQRCode();
                overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);

            }
        }, DELAY_MILIS);

    }


        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(result!=null && isNumber(result.getContents())) {
            Log.d("valor",result.getContents());
                String urlGet = url + result.getContents();
                GetPots getPots = new GetPots(urlGet);
                getPots.execute();

        }else {
            Toast.makeText(this, "No es un número", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNumber(String str){
        try {
            int i = Integer.parseInt(str);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    private void scanQRCode(){
        IntentIntegrator integratos = new IntentIntegrator(A_Main.this);
        integratos.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }


    private class GetPots extends AsyncTask<Void, Void, Void> {
        String url;

        public GetPots(String url) {
            this.url = url;
        }
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            layout_loading = (LinearLayout)findViewById(R.id.a_main_layout_loading);
            layout_principal = (LinearLayout)findViewById(R.id.a_main_layout_view);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            HTTPHandler sh = new HTTPHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(this.url, HTTPHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {

                try {
                    JSONArray p = new JSONArray(jsonStr);
                    JSONObject json_data = p.getJSONObject(0);
                    titulo.setText(json_data.getString(POT));
                    descripcion.setText(json_data.getString(POT_CONCEPT));
                    titular.setText(json_data.getString(NAME) + " " + json_data.getString(SURNAME));

                    URL newurl = new URL(json_data.getString(POT_IMAGE));
                    Bitmap bitmapFactory = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    image.setImageBitmap(bitmapFactory);


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;


            }
            return null;
        }

        @Override
        protected void onPostExecute (Void result){
            super.onPostExecute(result);
            layout_loading.setVisibility(View.GONE);
            layout_principal.setVisibility(View.VISIBLE);
        }
    }

}
