package ga.finappsparty.com.gather.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ga.finappsparty.com.gather.R;
import ga.finappsparty.com.gather.Responses.HTTPHandler;

public class SignUp extends Activity {
    private ProgressDialog pDialog;
    private static String url = "http://sthorms.com:1234/signup/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText mail = (EditText)findViewById(R.id.mail);
        EditText user = (EditText)findViewById(R.id.name);
        EditText surname = (EditText)findViewById(R.id.surname);
        final EditText pass = (EditText)findViewById(R.id.pass);
        final EditText passRepeat = (EditText)findViewById(R.id.passRepeat);
        EditText iban = (EditText)findViewById(R.id.iban);
        Button regist = (Button)findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!pass.getText().equals(passRepeat.getText())){
                    //Incluir Toast para contraseñas no coincidentes.
                } else {*/
                    PostMethod post =  new PostMethod("joan", "joanmramon@gmail.com","1234","molinas","12345678987");
                    post.execute();
                //}
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
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
        public  String user;
        public String pass;
        public String mail;
        public String surname;
        public String iban;


        public PostMethod(String mail, String pass){
            this.mail = mail;
            this.pass = pass;

        }
        public PostMethod(String user, String email ,String pass, String surname, String iban){
            this.user = user;
            this.pass = pass;
            this.mail = email;
            this.surname = surname;
            this.iban = iban;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SignUp.this);
            pDialog.setMessage("Connectando con el servidor...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... params) {
            HTTPHandler sh = new HTTPHandler();
            ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();

            if (mail.equals(null)){
                par.add(new BasicNameValuePair("email", mail));
                par.add(new BasicNameValuePair("password", pass));
            } else {
                par.add(new BasicNameValuePair("name", user));
                par.add(new BasicNameValuePair("email", mail));
                par.add(new BasicNameValuePair("surname", surname));
                par.add(new BasicNameValuePair("password", pass));
                par.add(new BasicNameValuePair("iban", iban));

            }



            String js = sh.makeServiceCall(url,HTTPHandler.POST,par);

            Log.d("buena", js);

            JSONObject obj = null;
            try {
                obj = new JSONObject(js);
                System.out.println(obj.getInt("msg:"));
                Integer val = new Integer(obj.getInt("msg:"));

                if (val == 0) {
                    //Toast error al registrar
                } else if(val == -1 ){
                    //Toast usuario ya existente
                } else {
                    Intent intent = new Intent(SignUp.this, F_Home.class);
                    startActivity(intent);
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
