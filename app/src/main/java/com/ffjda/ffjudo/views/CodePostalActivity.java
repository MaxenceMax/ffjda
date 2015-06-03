package com.ffjda.ffjudo.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.adapter.ClubAdapter;
import com.ffjda.ffjudo.adapter.HistoriqueClubAdapter;
import com.ffjda.ffjudo.model.Club;
import com.ffjda.ffjudo.model.ClubNouvelleLicence;
import com.ffjda.ffjudo.model.Licence;
import com.ffjda.ffjudo.model.Licencie;
import com.ffjda.ffjudo.utils.Variable;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CodePostalActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    // Progress show
    private View mLoginFormView;
    private View mLoginStatusView;
    private View zeroClub;

    // Usefull item for list view
    private List<ClubNouvelleLicence> allClub;

    //Variables pour la listView
    private ClubAdapter clubAdapter;
    //view items
    private TextView activityCodePostalCode;
    private ListView activityCodePostalListview;

    //
    private String codepostal;
    private String discode;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-06-02 00:05:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        activityCodePostalCode = (TextView)findViewById( R.id.activity_code_postal_code );
        activityCodePostalListview = (ListView)findViewById( R.id.activity_code_postal_listview );
        mLoginStatusView = findViewById(R.id.activity_code_postal_status);
        mLoginFormView = findViewById( R.id.activity_code_postal_listview );
        zeroClub = findViewById(R.id.activity_code_postal_text0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_postal);
        // viex initialisation
        findViews();

        codepostal = getIntent().getStringExtra("code");
        discode = getIntent().getStringExtra("discode");

        activityCodePostalCode.setText(codepostal);
        // Listview initialisation
        clubAdapter = new ClubAdapter(this,R.layout.club_row);
        activityCodePostalListview.setAdapter(clubAdapter);
        activityCodePostalListview.setOnItemClickListener(this);
        // async tasks Initialisation
        showProgress(true);
        ClubTask ct = new ClubTask();
        ct.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_code_postal, menu);
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);

            mLoginStatusView.setVisibility(View.VISIBLE);
            mLoginStatusView.animate().setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginStatusView.setVisibility(show ? View.VISIBLE
                                    : View.GONE);
                        }
                    });

            mLoginFormView.setVisibility(View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE
                                    : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ClubNouvelleLicence clubNouvelleLicence = new ClubNouvelleLicence(allClub.get(position));
        Intent resultIntent = new Intent();
        resultIntent.putExtra("club", clubNouvelleLicence);
        setResult(Variable.RESULT_CODE_POSTAL, resultIntent);
        finish();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class ClubTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            String result;
                try{
                    // Nouveau client pour la reuqte
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    //j'appelle la requète avec l'url et le paramètre correct
                    HttpResponse response = httpclient.execute(new HttpGet(new String(
                            Variable.jsonUrlRequestCodePostal+discode+"/"+codepostal
                    )));
                    //affichage du status de retour !
                    System.out.println("Statut code : "+response.getStatusLine().getStatusCode());
                    // Et je check les résultats
                    String tmp = convertStreamToString(response.getEntity().getContent());
                    System.out.println("clubs récupéré :"+tmp);
                    // je transforme la chaine de caractère en jsonbject
                    JSONObject obj = new JSONObject(tmp);
                    // je transforme la chaine de caractère pour que ce soit du JSON
                    JSONArray jarray = (JSONArray) obj.getJSONArray("dojosResult");
                    allClub = new ArrayList<>();
                    for(int i=0;i<jarray.length();i++) {
                       /** "adresse": "5 rue des Ecoles",
                                "clubcompteur": "5455",
                                "cp": "92000",
                                "designation": "GYMNASE EVARISTE GALOIS",
                                "dojocode": "A",
                                "dojocompteur": 4538,
                                "nom": "ENTENTE SPORTIVE NANTERRE",
                                "ville": "Nanterre"*/

                        JSONObject compJson = (JSONObject) jarray.get(i);
                        ClubNouvelleLicence cnl = new ClubNouvelleLicence();
                        cnl.setAdresse(compJson.getString("adresse").trim());
                        cnl.setClubcompeur(compJson.getString("clubcompteur").trim());
                        cnl.setDesignation(compJson.getString("designation").trim());
                        cnl.setCp(compJson.getString("cp").trim());
                        cnl.setDojocode(compJson.getString("dojocode").trim());
                        cnl.setDojocompteur(compJson.getString("dojocompteur").trim());
                        cnl.setNom(compJson.getString("nom").trim());
                        cnl.setVille(compJson.getString("ville").trim());
                        allClub.add(cnl);
                    }

                }catch(Exception e)
                {
                    System.out.println(e.toString());
                    return false;
                }
                return true;
        }



        private String convertStreamToString(InputStream is) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            return sb.toString();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                if (allClub.size()==0)
                    zeroClub.setVisibility(View.VISIBLE);
                clubAdapter.swapItems(allClub);
                showProgress(false);
            } else {
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }
}
