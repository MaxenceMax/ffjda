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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.ClubNouvelleLicence;
import com.ffjda.ffjudo.model.Licence;
import com.ffjda.ffjudo.model.Licencie;
import com.ffjda.ffjudo.model.RenouvellementAjoutLicence;
import com.ffjda.ffjudo.utils.DialogCreation;
import com.ffjda.ffjudo.utils.Variable;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class RenouvellementActivity extends ActionBarActivity implements View.OnClickListener {



    // View Elements
    private TextView activityRenouvellementDiscipline;
    private TextView activityRenouvellementNom;
    private TextView activityRenouvellementNumLicence;
    private TextView activityRenouvellementNaissance;
    private Spinner activityRenouvellementSaison;
    private EditText activityRenouvellementCodePostal;
    private RelativeLayout activityRenouvellementCodePostalValider;
    private ImageView activityRenouvellementValide;
    private TextView activityRenouvellementNomDojo;
    private TextView activityRenouvellementDesignation;
    private TextView activityRenouvellementDojo;
    private TextView activityRenouvellementDojoCompteur;
    private TextView activityRenouvellementAdresse;
    private TextView activityRenouvellementCodePostalChoisi;
    private TextView activityRenouvellementVille;
    private RelativeLayout activityRenouvellementSuivantLayout;

    // List usefull for spinners
    private List<String> arraySaison = new ArrayList<>();
    private ClubNouvelleLicence clubNouvelleLicence;
    private RenouvellementAjoutLicence mRenouvellementAjoutLicence;
    private Licencie licencie;
    private Licence licence;

    // Progress show
    private View mLoginFormView;
    private View mLoginStatusView;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-06-03 23:04:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        activityRenouvellementDiscipline = (TextView)findViewById( R.id.activity_renouvellement_discipline );
        activityRenouvellementNom = (TextView)findViewById( R.id.activity_renouvellement_nom );
        activityRenouvellementNumLicence = (TextView)findViewById( R.id.activity_renouvellement_num_licence );
        activityRenouvellementNaissance = (TextView)findViewById( R.id.activity_renouvellement_naissance );
        activityRenouvellementSaison = (Spinner)findViewById( R.id.activity_renouvellement_saison );
        activityRenouvellementCodePostal = (EditText)findViewById( R.id.activity_renouvellement_code_postal );
        activityRenouvellementCodePostalValider = (RelativeLayout)findViewById( R.id.activity_renouvellement_code_postal_valider );
        activityRenouvellementValide = (ImageView)findViewById( R.id.activity_renouvellement_valide );
        activityRenouvellementNomDojo = (TextView)findViewById( R.id.activity_renouvellement_nom_dojo );
        activityRenouvellementDesignation = (TextView)findViewById( R.id.activity_renouvellement_designation );
        activityRenouvellementDojo = (TextView)findViewById( R.id.activity_renouvellement_dojo );
        activityRenouvellementDojoCompteur = (TextView)findViewById( R.id.activity_renouvellement_dojo_compteur );
        activityRenouvellementAdresse = (TextView)findViewById( R.id.activity_renouvellement_adresse );
        activityRenouvellementCodePostalChoisi = (TextView)findViewById( R.id.activity_renouvellement_code_postal_choisi );
        activityRenouvellementVille = (TextView)findViewById( R.id.activity_renouvellement_ville );
        activityRenouvellementSuivantLayout = (RelativeLayout)findViewById( R.id.activity_renouvellement_suivant_layout );
        activityRenouvellementCodePostalValider.setOnClickListener(this);
        activityRenouvellementSuivantLayout.setOnClickListener(this);

        mLoginFormView = findViewById(R.id.activity_renouvellement_hide);
        mLoginStatusView = findViewById(R.id.activity_renouvellement_status);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_renouvellement_suivant_layout:
                if (clubNouvelleLicence == null) {
                    DialogCreation.createDialog(this, getString(R.string.Club_manquant), getString(R.string.Club_manquant_desc));
                    return;
                }
                mRenouvellementAjoutLicence.setSaison(activityRenouvellementSaison.getSelectedItem().toString());
                mRenouvellementAjoutLicence.setNumlicence(licencie.getNum_licence());
                mRenouvellementAjoutLicence.setNaissance(licencie.getDate_naissance());
                Intent ajout2 = new Intent(this, RenouvellementAjout2Activity.class);
                ajout2.putExtra("nouvelleLicence", mRenouvellementAjoutLicence);
                startActivityForResult(ajout2, Variable.REQUEST_CODE_SUIV);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                break;
            case R.id.activity_renouvellement_code_postal_valider:
                if(activityRenouvellementCodePostal.length() > 5 || activityRenouvellementCodePostal.length()==0)
                {
                    DialogCreation.createDialog(this, getString(R.string.bad_cp), getString(R.string.bad_cp_desc));
                    return;
                }
                Intent code_postal = new Intent(this, CodePostalActivity.class);
                code_postal.putExtra("code",activityRenouvellementCodePostal.getText().toString());
                code_postal.putExtra("discode",licence.getDiscode());
                startActivityForResult(code_postal, Variable.REQUEST_CODE_POSTAL);
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renouvellement);

        licence = (Licence)getIntent().getSerializableExtra("licence");
        licencie = (Licencie)getIntent().getSerializableExtra("licencie");

        mRenouvellementAjoutLicence = new RenouvellementAjoutLicence();
        // View initialisation
        findViews();
        saisonInitialisation();
        licencieInitialisation();
        // Hide keayboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //get Last CLub
        LastClubTask last = new LastClubTask();
        last.execute();
        showProgress(true);
    }

    /**
     * Spinner date initialisation
     */
    private void saisonInitialisation() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR)-1;
        int year1 = year+1;
        int year2 = year+2;

        if(calendar.get(Calendar.MONTH)<=5 && calendar.get(Calendar.MONTH)<8)
            arraySaison.add(year + "/" + year1);
        arraySaison.add(year1 + "/" + year2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arraySaison);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityRenouvellementSaison.setAdapter(adapter);
    }

    /**
     * Set the club choose or latest club
     */
    private void setClub()
    {
        activityRenouvellementNomDojo.setText(clubNouvelleLicence.getNom());
        activityRenouvellementDesignation.setText(clubNouvelleLicence.getDesignation());
        activityRenouvellementDojoCompteur.setText(clubNouvelleLicence.getDojocode());
        activityRenouvellementAdresse.setText(clubNouvelleLicence.getAdresse());
        activityRenouvellementCodePostalChoisi.setText(clubNouvelleLicence.getCp());
        activityRenouvellementVille.setText(clubNouvelleLicence.getVille());
        activityRenouvellementValide.setVisibility(View.VISIBLE);
        activityRenouvellementDojo.setVisibility(View.VISIBLE);
        mRenouvellementAjoutLicence.addClubNouvelleLicence(clubNouvelleLicence);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ici");
        if(requestCode == Variable.REQUEST_CODE_POSTAL && resultCode == Variable.RESULT_CODE_POSTAL)
        {
            clubNouvelleLicence =(ClubNouvelleLicence)data.getSerializableExtra("club");
            setClub();
        }
        if(requestCode == Variable.REQUEST_CODE_SUIV && resultCode == Variable.REQUEST_CODE_COMPLETE_AJOUT)
        {
            Intent tmp = new Intent();
            tmp.putExtra("test","test");
            setResult(Variable.REQUEST_CODE_COMPLETE_AJOUT,tmp);
            finish();
            overridePendingTransition(R.anim.slide_fix,R.anim.slide_out_bottom);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_fix,R.anim.slide_out_bottom);
    }

    private void licencieInitialisation()
    {
        licencie = (Licencie) Licencie.listAll(Licencie.class).get(0);
        activityRenouvellementNom.setText(licencie.getPrenom()+" "+licencie.getNom());
        activityRenouvellementNumLicence.setText(licencie.getNum_licence());
        activityRenouvellementNaissance.setText(licencie.getDate_naissance());
        activityRenouvellementDiscipline.setText(licence.getDiscipline());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_renouvellement, menu);
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
    public class LastClubTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            String result;
            try{
                // Nouveau client pour la reuqte
                DefaultHttpClient httpclient = new DefaultHttpClient();
                //j'appelle la requète avec l'url et le paramètre correct
                HttpResponse response = httpclient.execute(new HttpGet(new String(
                        Variable.jsonUrlRequestLastClub+licence.getDiscode()+"/"+licencie.getNum_licence()
                )));
                //affichage du status de retour !
                System.out.println("Statut code : "+response.getStatusLine().getStatusCode());
                // Et je check les résultats
                String tmp = convertStreamToString(response.getEntity().getContent());
                System.out.println("clubs récupéré :"+tmp);
                // je transforme la chaine de caractère en jsonbject
                JSONObject obj = new JSONObject(tmp);
                // je transforme la chaine de caractère pour que ce soit du JSON
                JSONArray jarray = (JSONArray) obj.getJSONArray("dernierclubResult");
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
                    clubNouvelleLicence = new ClubNouvelleLicence();
                    clubNouvelleLicence.setAdresse(compJson.getString("adresse").trim());
                    clubNouvelleLicence.setClubcompeur(compJson.getString("clubcompteur").trim());
                    clubNouvelleLicence.setDesignation(compJson.getString("designation").trim());
                    clubNouvelleLicence.setCp(compJson.getString("cp").trim());
                    clubNouvelleLicence.setDojocode(compJson.getString("dojocode").trim());
                    clubNouvelleLicence.setDojocompteur(compJson.getString("dojocompteur").trim());
                    clubNouvelleLicence.setNom(compJson.getString("nom").trim());
                    clubNouvelleLicence.setVille(compJson.getString("ville").trim());
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
                setClub();
                showProgress(false);
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }
}
