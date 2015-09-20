package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AjoutActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView activityAjoutNom;
    private TextView activityAjoutNumLicence;
    private TextView activityAjoutNaissance;
    private Spinner activityAjoutSaison;
    private Spinner activityAjoutDiscipline;
    private EditText activityAjoutCodePostal;
    private RelativeLayout activityAjoutCodePostalValider;
    private ImageView activityAjoutValide;
    private TextView activityAjoutNomDojo;
    private TextView activityAjoutDesignation;
    private TextView activityAjoutDojo;
    private TextView activityAjoutDojoCompteur;
    private TextView activityAjoutAdresse;
    private TextView activityAjoutCodePostalChoisi;
    private TextView activityAjoutVille;
    private RelativeLayout activityAjoutSuivantLayout;
    // List usefull for spinners
    private List<String> arraySaison = new ArrayList<>();
    private List<String> arrayDiscipline = new ArrayList<>();
    private HashMap<String,Integer> disciplineDiscode = new HashMap<>();
    private ClubNouvelleLicence clubNouvelleLicence;
    private RenouvellementAjoutLicence mRenouvellementAjoutLicence;
    private Licencie licencie;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-06-03 16:00:58 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        activityAjoutNom = (TextView)findViewById( R.id.activity_ajout_nom );
        activityAjoutNumLicence = (TextView)findViewById( R.id.activity_ajout_num_licence );
        activityAjoutNaissance = (TextView)findViewById( R.id.activity_ajout_naissance );
        activityAjoutSaison = (Spinner)findViewById( R.id.activity_ajout_saison );
        activityAjoutDiscipline = (Spinner)findViewById( R.id.activity_ajout_discipline );
        activityAjoutCodePostal = (EditText)findViewById( R.id.activity_ajout_code_postal );
        activityAjoutCodePostalValider = (RelativeLayout)findViewById( R.id.activity_ajout_code_postal_valider );
        activityAjoutValide = (ImageView)findViewById( R.id.activity_ajout_valide );
        activityAjoutNomDojo = (TextView)findViewById( R.id.activity_ajout_nom_dojo );
        activityAjoutDesignation = (TextView)findViewById( R.id.activity_ajout_designation );
        activityAjoutDojo = (TextView)findViewById( R.id.activity_ajout_dojo );
        activityAjoutDojoCompteur = (TextView)findViewById( R.id.activity_ajout_dojo_compteur );
        activityAjoutAdresse = (TextView)findViewById( R.id.activity_ajout_adresse );
        activityAjoutCodePostalChoisi = (TextView)findViewById( R.id.activity_ajout_code_postal_choisi );
        activityAjoutVille = (TextView)findViewById( R.id.activity_ajout_ville );
        activityAjoutSuivantLayout = (RelativeLayout) findViewById(R.id.activity_ajout_suivant_layout);

        activityAjoutSuivantLayout.setOnClickListener(this);
        activityAjoutCodePostalValider.setOnClickListener(this);
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
        activityAjoutSaison.setAdapter(adapter);
    }
    private void licencieInitialisation()
    {
        licencie = (Licencie) Licencie.listAll(Licencie.class).get(0);
        activityAjoutNom.setText(licencie.getPrenom()+" "+licencie.getNom());
        activityAjoutNumLicence.setText(licencie.getNum_licence());
        activityAjoutNaissance.setText(licencie.getDate_naissance());
    }

    /**
     * Spinner date initialisation
     */
    private void disciplineInitialisation() {
        List<Licence> l = Licence.listAll(Licence.class);
        boolean discfind;
        for(int i = 0;i< Variable.DISCIPLINEARRAY.size();i++)
        {
            discfind = false;
            for(int j=0;j<l.size();j++)
            {
                if(Variable.DISCIPLINEARRAY.get(i).equals(l.get(j).getDiscipline())) {
                    discfind=true;
                    continue;
                }
            }
            if(!discfind) {
                arrayDiscipline.add(Variable.DISCIPLINEARRAY.get(i));
                disciplineDiscode.put(Variable.DISCIPLINEARRAY.get(i), Variable.DISCODEARRAY.get(i));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrayDiscipline);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAjoutDiscipline.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_ajout_suivant_layout:
                if (clubNouvelleLicence == null) {
                    DialogCreation.createDialog(this, getString(R.string.Club_manquant), getString(R.string.Club_manquant_desc));
                    return;
                }
                mRenouvellementAjoutLicence.setSaison(activityAjoutSaison.getSelectedItem().toString());
                mRenouvellementAjoutLicence.setNumlicence(licencie.getNum_licence());
                mRenouvellementAjoutLicence.setNaissance(licencie.getDate_naissance());
                Intent ajout2 = new Intent(this, RenouvellementAjout2Activity.class);
                ajout2.putExtra("nouvelleLicence", mRenouvellementAjoutLicence);
                startActivityForResult(ajout2, Variable.REQUEST_CODE_SUIV);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                break;
            case R.id.activity_ajout_code_postal_valider:
                if(activityAjoutCodePostal.length() > 5 || activityAjoutCodePostal.length()==0)
                {
                    DialogCreation.createDialog(this, getString(R.string.bad_cp), getString(R.string.bad_cp_desc));
                    return;
                }
                Intent code_postal = new Intent(this, CodePostalActivity.class);
                code_postal.putExtra("code",activityAjoutCodePostal.getText().toString());
                code_postal.putExtra("discode",disciplineDiscode.get(activityAjoutDiscipline.getSelectedItem().toString())+"");
                startActivityForResult(code_postal, Variable.REQUEST_CODE_POSTAL);
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        // View initialisation-
        findViews();
        // Initialisaiton of spinner's
        disciplineInitialisation();
        saisonInitialisation();
        //Licencie initialisation
        licencieInitialisation();

        mRenouvellementAjoutLicence = new RenouvellementAjoutLicence();

        //Hide keyboard
        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ici");
        if(requestCode == Variable.REQUEST_CODE_POSTAL && resultCode == Variable.RESULT_CODE_POSTAL)
        {
            clubNouvelleLicence =(ClubNouvelleLicence)data.getSerializableExtra("club");
            activityAjoutNomDojo.setText(clubNouvelleLicence.getNom());
            activityAjoutDesignation.setText(clubNouvelleLicence.getDesignation());
            activityAjoutDojoCompteur.setText(clubNouvelleLicence.getDojocode());
            activityAjoutAdresse.setText(clubNouvelleLicence.getAdresse());
            activityAjoutCodePostalChoisi.setText(clubNouvelleLicence.getCp());
            activityAjoutVille.setText(clubNouvelleLicence.getVille());
            activityAjoutValide.setVisibility(View.VISIBLE);
            activityAjoutDojo.setVisibility(View.VISIBLE);

            mRenouvellementAjoutLicence.addClubNouvelleLicence(clubNouvelleLicence);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_renouvellement_ajout, menu);
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
}
