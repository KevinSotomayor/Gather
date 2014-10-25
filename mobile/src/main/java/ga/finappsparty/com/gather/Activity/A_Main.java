package ga.finappsparty.com.gather.Activity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ga.finappsparty.com.gather.R;
import ga.finappsparty.com.gather.Responses.HTTPHandler;
import ga.finappsparty.com.gather.Util.Creator;


public class A_Main extends Activity {
    private TextView titulo, descripcion, titular, dinero;
    private LinearLayout layout_loading, layout_principal;
    private Button transferButton;
    private String url="http://sthorms.com:1234/pot/";
    public final static String INITIAL_KEY_BOTE="INITIAL_KEY_BOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        //text & Typefaces

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


        scanQRCode();

    }




        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(result!=null) {
            if(isNumber(result.getContents())){
                    GetPots getPots = new GetPots();
                    url+=result.getContents();
                    getPots.execute();
            }
        }else {
            Toast.makeText(this, "NotANumber", Toast.LENGTH_SHORT).show();
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

        @Override
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
            String jsonStr = sh.makeServiceCall(url, HTTPHandler.GET);
            if (jsonStr != null) {
                titulo.setText(jsonStr);

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
