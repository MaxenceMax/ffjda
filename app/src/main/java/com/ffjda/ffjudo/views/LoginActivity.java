package com.ffjda.ffjudo.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.Club;
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
import java.util.Calendar;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    /**
     * The default email to populate the email field with.
     */
    public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // Values for email and password at the time of the login attempt.
    private String mEmail;
    private String mPassword;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private View mLoginStatusView;
    private TextView mLoginStatusMessageView;
    private TextView mCancelTextView;
    // View items
    private TextView pasLicencieTextView;
    private RelativeLayout mEnvoyerRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //View initialization

        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.activity_login_identifiant);
        mPasswordView = (EditText) findViewById(R.id.activity_login_password);

        mLoginFormView = findViewById(R.id.activity_login_form);
        mLoginStatusView = findViewById(R.id.activity_login_status);
        mLoginStatusMessageView = (TextView) findViewById(R.id.activity_login_status_message);

        pasLicencieTextView = (TextView) findViewById(R.id.activity_login_pas_licencie);
        pasLicencieTextView.setOnClickListener(this);
        mEnvoyerRelativeLayout = (RelativeLayout) findViewById(R.id.activity_login_envoyer);
        mEnvoyerRelativeLayout.setOnClickListener(this);

        // Check preferences to know last login
        checkPreferences();
    }

    /**
     * Check last time usr was login
     * Check if a numlicence is avaiblale in preferences
     */
    private void checkPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(Variable.FFJDAPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(Variable.PREFERENCENUMLIC))
        {
            if(sharedPreferences.contains(Variable.PREFERENCELASTLOG))
            {
                long thatDat = sharedPreferences.getLong(Variable.PREFERENCELASTLOG,0);
                long today = Calendar.getInstance().getTimeInMillis();
                long diff = (today - thatDat) / (24*60*60*1000);
                System.out.println("DIFF ="+diff);
                if(diff < 20)
                {
                    Intent envoyerIntent = new Intent(this, LicencesActivity.class);
                    startActivity(envoyerIntent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
                }
            }
            mEmailView.setText(sharedPreferences.getString(Variable.PREFERENCENUMLIC,""));
            mPasswordView.requestFocus();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mEmailView.getText().toString();
        mPassword = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (mPassword.length() < 4) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmail)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
            // Usefull to hide keyboard
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mPasswordView.getWindowToken(), 0);
            showProgress(true);
            mAuthTask = new UserLoginTask();
            mAuthTask.execute(mEmail,mPassword);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_pas_licencie:
                Intent inscriptionIntent = new Intent(this,Inscription1Activity.class);
                startActivity(inscriptionIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                break;
            case R.id.activity_login_envoyer:
                attemptLogin();
                break;
        }
    }

    private void endingActivity()
    {
        SharedPreferences preferences = getSharedPreferences(Variable.FFJDAPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferences.edit();

        editor.putString(Variable.PREFERENCENUMLIC, mEmailView.getText().toString());
        Calendar today = Calendar.getInstance();
        editor.putLong(Variable.PREFERENCELASTLOG,today.getTimeInMillis());
        editor.apply();

        Intent envoyerIntent = new Intent(this, LicencesActivity.class);
        startActivity(envoyerIntent);
        finish();
        overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
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



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String result;
            try {
                // http client
                DefaultHttpClient httpClient = new DefaultHttpClient();
                //url with the post data
                HttpPost httpost = new HttpPost(Variable.jsonUrlRequestLogin);
                JSONObject user = new JSONObject();
                user.put("UserName", params[0]);
                user.put("Password", params[1]);
                JSONObject utils = new JSONObject();
                utils.put("util", user);
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
                System.out.println("final Result :"+ result);
            }catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("bug");
                return false;
            }
            if(result.length() != 0)
            {
                Licencie.deleteAll(Licencie.class);
                Licence.deleteAll(Licence.class);
                Club.deleteAll(Club.class);
                // Si la connexion est ok on récupére alors les informations du licencié !
                try{
                    // Nouveau client pour la reuqte
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    //j'appelle la requète avec l'url et le paramètre correct
                    HttpResponse response = httpclient.execute(new HttpGet(
                        new String(Variable.jsonUrlRequestLicencie+params[0].replace("*", "@"))));
                    //affichage du status de retour !
                    System.out.println("Statut code : "+response.getStatusLine().getStatusCode());
                    // Et je check les résultats
                    String tmp = convertStreamToString(response.getEntity().getContent());
                    System.out.println("Licence récupéré :"+tmp);
                    // je transforme la chaine de caractère en jsonbject
                    JSONObject obj = new JSONObject(tmp);
                    // je transforme la chaine de caractère pour que ce soit du JSON
                    JSONArray jarray = (JSONArray) obj.getJSONArray("informationsResult");


                    // Parsing and saving licence and licencie
                    Licencie licencie = new Licencie();
                    licencie.setNom(((JSONObject)jarray.get(0)).getString("nom").trim());
                    licencie.setPrenom(((JSONObject) jarray.get(0)).getString("prenom").trim());
                    licencie.setSexe(((JSONObject) jarray.get(0)).getString("sexe").trim());
                    licencie.setDate_naissance(((JSONObject) jarray.get(0)).getString("naissance").trim());
                    licencie.setIde_clee(((JSONObject) jarray.get(0)).getString("ide_clee").trim());
                    licencie.setNum_licence(((JSONObject) jarray.get(0)).getString("numLicence").trim());
                    licencie.save();
                    System.out.println(licencie.toString());
                    for(int i=0;i<jarray.length();i++) {
                        JSONObject compJson = (JSONObject) jarray.get(i);
                        Licence licence = new Licence();
                        licence.setDiscipline(compJson.getString("discipline").trim());
                        licence.setDiscode(compJson.getString("discode").trim());
                        // Save histo
                        // Nouveau client pour la reuqte
                        DefaultHttpClient httpclientclub = new DefaultHttpClient();
                        //j'appelle la requète avec l'url et le paramètre correct
                        HttpResponse responseclub = httpclientclub.execute(new HttpGet(
                            new String(Variable.jsonUrlRequestClub+licencie.getNum_licence()+"/"+compJson.getString("discode").trim())));
                        String tmpClub = convertStreamToString(responseclub.getEntity().getContent());
                        System.out.println("club récupéré :"+tmpClub);
                        JSONObject objClub = new JSONObject(tmpClub);
                        JSONArray jarrayClub = (JSONArray) objClub.getJSONArray("getHistoClubResult");
                        for (int iCLub=0;iCLub<jarrayClub.length();iCLub++)
                        {
                            Club c = new Club();
                            c.setAnneeIn(((JSONObject)jarrayClub.get(iCLub)).getString("entree").trim());
                            c.setAnneeOut(((JSONObject) jarrayClub.get(iCLub)).getString("sortie").trim());
                            c.setNomClub(((JSONObject) jarrayClub.get(iCLub)).getString("nom").trim());
                            c.setDiscode(compJson.getString("discode").trim());
                            c.save();
                        }
                        licence.setGrade(compJson.getString("grade").trim());
                        licence.setEstLicencie(compJson.getString("licence").trim());
                        licence.setSaison(compJson.getString("saison").trim());
                        licence.setClub(compJson.getString("club").trim());
                        licence.save();
                        System.out.println(licence);
                    }

                }catch(Exception e)
                {
                    System.out.println(e.toString());
                    return false;
                }
                return true;
            }

            // TODO: register the new account here.System.out.println("json response : "+ in);
            return false;
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
            mAuthTask = null;
            if (success) {
                endingActivity();
            } else {
                showProgress(false);
                mPasswordView
                        .setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
