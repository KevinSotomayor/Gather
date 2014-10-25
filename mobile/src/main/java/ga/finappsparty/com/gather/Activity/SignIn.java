package ga.finappsparty.com.gather.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ga.finappsparty.com.gather.R;
import ga.finappsparty.com.gather.Responses.HTTPHandler;

public class SignIn extends Activity {
    private ProgressDialog pDialog;
    private static String url = "http://Sthorms.com:1234/login/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText mailUser = (EditText)findViewById(R.id.mail);
        final EditText pass = (EditText)findViewById(R.id.Pass);
        Button ok = (Button)findViewById(R.id.Entrar);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // PostMethod post = new PostMethod(mailUser.getText().toString(), pass.getText().toString());
                PostMethod post = new PostMethod("pepegarcia@gmail.com","1234");
                post.execute();
            }
        });

        Button cuenta = (Button)findViewById(R.id.cuenta);
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
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

    private class PostMethod extends AsyncTask<Void, Void, Void> {
        public  String mail;
        public String pass;

        public PostMethod(String user, String pass){
            mail = user;
            this.pass = pass;

        }
        public PostMethod(Editable user, Editable email ,String pass, String surname, String iban){

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SignIn.this);
            pDialog.setMessage("Connectando con el servidor...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... params) {
            HTTPHandler sh = new HTTPHandler();
            ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("email", mail));
            par.add(new BasicNameValuePair("password", pass));



            String js = sh.makeServiceCall(url,HTTPHandler.POST,par);

                 Log.d("buena", js);



            try {
               JSONObject obj = new JSONObject(js);
                System.out.println(obj.getInt("msg:"));
                Integer val = new Integer(obj.getInt("msg:"));

                if (val != 0) {
                   Intent home = new Intent(SignIn.this, F_Home.class);
                   startActivity(home);

                    SharedPreferences user = getSharedPreferences("mail", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = user.edit();
                    editor.putString("mail", this.mail);
                    editor.putBoolean("isLogued",true);
                    editor.commit();



                } else {
                    //Toast para usuario incorrecto.
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
        }
    }
}
