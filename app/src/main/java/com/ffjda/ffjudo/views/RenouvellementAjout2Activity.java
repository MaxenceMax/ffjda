package com.ffjda.ffjudo.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.NouvelleLicence;
import com.ffjda.ffjudo.model.RenouvellementAjoutLicence;
import com.ffjda.ffjudo.utils.DialogCreation;
import com.ffjda.ffjudo.utils.Variable;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

public class RenouvellementAjout2Activity extends ActionBarActivity implements View.OnClickListener{

    private LinearLayout activityRenouvellementAjout2Status;
    private CheckBox activityRenouvellementAjout2Assurance;
    private TextView activityRenouvellementAjout2Contitions;
    private TextView activityRenouvellementAjout2Refus;
    private CheckBox activityRenouvellementAjout2Cnil;
    private TextView activityRenouvellementAjout2CnilLien;
    private RelativeLayout activityRenouvellementAjout2ValiderLayout;

    private RenouvellementAjoutLicence mNouvelleLicence;
    // Progress show
    private View mLoginFormView;
    private View mLoginStatusView;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-06-03 18:14:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        activityRenouvellementAjout2Status = (LinearLayout)findViewById( R.id.activity_renouvellement_ajout2_status );
        activityRenouvellementAjout2Assurance = (CheckBox)findViewById( R.id.activity_renouvellement_ajout2_assurance );
        activityRenouvellementAjout2Contitions = (TextView)findViewById( R.id.activity_renouvellement_ajout2_contitions );
        activityRenouvellementAjout2Refus = (TextView)findViewById( R.id.activity_renouvellement_ajout2_refus );
        activityRenouvellementAjout2Cnil = (CheckBox)findViewById( R.id.activity_renouvellement_ajout2_cnil );
        activityRenouvellementAjout2CnilLien = (TextView)findViewById( R.id.activity_renouvellement_ajout2_cnil_lien );
        activityRenouvellementAjout2ValiderLayout = (RelativeLayout)findViewById( R.id.activity_renouvellement_ajout2_valider_layout );
        mLoginFormView = findViewById(R.id.activity_renouvellement_ajout2_scroolView);
        mLoginStatusView = findViewById(R.id.activity_renouvellement_ajout2_status);
        activityRenouvellementAjout2CnilLien.setOnClickListener(this);
        activityRenouvellementAjout2Contitions.setOnClickListener(this);
        activityRenouvellementAjout2Refus.setOnClickListener(this);
        activityRenouvellementAjout2ValiderLayout.setOnClickListener(this);

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renouvellement_ajout2);

        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // get nouvelle Licence
        mNouvelleLicence = (RenouvellementAjoutLicence)getIntent().getSerializableExtra("nouvelleLicence");

        // view initialisation
        findViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_renouvellement_ajout2, menu);
        return true;
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
    }

    /**
     *
     */
    private void endActivityAjout() {
        setResult(Variable.REQUEST_CODE_COMPLETE_AJOUT);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_renouvellement_ajout2_cnil_lien :
                DialogCreation.createDialogForUrl(this, getString(R.string.Redirection),
                        getString(R.string.Redirection_desc),
                        getString(R.string.yes),
                        getString(R.string.no),
                        Variable.URLCNIL);
                break;
            case R.id.activity_renouvellement_ajout2_contitions:
                DialogCreation.createDialogForUrl(this,getString(R.string.Redirection),
                        getString(R.string.Redirection_desc),
                        getString(R.string.yes),
                        getString(R.string.no),
                        Variable.URLCONDITION);
                break;
            case R.id.activity_renouvellement_ajout2_refus :
                DialogCreation.createDialogForUrl(this,getString(R.string.Redirection),
                        getString(R.string.Redirection_desc),
                        getString(R.string.yes),
                        getString(R.string.no),
                        Variable.URLREFUS);
                break;
            case R.id.activity_renouvellement_ajout2_valider_layout:
                mNouvelleLicence.setCnil(!activityRenouvellementAjout2Cnil.isChecked());
                mNouvelleLicence.setAssurance(!activityRenouvellementAjout2Assurance.isChecked());
                System.out.println(mNouvelleLicence);

                TakeLicenceTask tlt = new TakeLicenceTask();
                showProgress(true);
                tlt.execute();
                break;
        }
    }

    /**
     * Get the local context
     * @return
     */
    private Context getLocalContext()
    {
        return this;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class TakeLicenceTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            String result;
            try {
                // http client
                DefaultHttpClient httpClient = new DefaultHttpClient();
                //url with the post data
                HttpPost httpost = new HttpPost(Variable.jsonUrlPostNouvelleLicence);
                JSONObject user = new JSONObject();
                user.put("clubcompteur", mNouvelleLicence.getClub_compteur());
                user.put("dojocompteur",mNouvelleLicence.getDojo_compteur());
                user.put("assurance", mNouvelleLicence.getAssurance());
                user.put("naissance", mNouvelleLicence.getNaissance().replaceAll("/", Matcher.quoteReplacement("/")));
                user.put("saison", mNouvelleLicence.getSaison().replaceAll("/", Matcher.quoteReplacement("/")));
                user.put("numLicence",mNouvelleLicence.getNumlicence());
                user.put("cnil", mNouvelleLicence.getCnil());
                JSONObject utils = new JSONObject();
                utils.put("lic", user);
                //passes the results to a string builder/entity
                StringEntity se = new StringEntity(utils.toString());
                //sets the post request as the resulting string
                httpost.setEntity(se);
                //sets a request header so the page receving the request
                //will know what to do with it
                httpost.setHeader("Accept", "application/json");
                httpost.setHeader("Content-type", "application/json");
                HttpResponse response = httpClient.execute(httpost);
                // 	Read content & Log
                System.out.println("Request Sent : "+convertStreamToString(httpost.getEntity().getContent()));
                System.out.println("Statut code : "+response.getStatusLine().getStatusCode());
                result = convertStreamToString(response.getEntity().getContent());
                System.out.println("Result"+result);
                if(!result.equals("YES"))
                    return false;
            }catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("bug");
                return false;
            }

            // TODO: register the new account here.System.out.println("json response : "+ in);
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
                endActivityAjout();
            } else {
                DialogCreation.createDialog(getLocalContext(),
                        getString(R.string.Inscription_echec),
                        getString(R.string.Inscription_erreur_desc));
                showProgress(false);
            }
        }
    }
}
