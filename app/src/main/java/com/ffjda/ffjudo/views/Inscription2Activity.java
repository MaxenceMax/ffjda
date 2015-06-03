package com.ffjda.ffjudo.views;

import android.content.Intent;
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
import com.ffjda.ffjudo.model.NouvelleLicence;
import com.ffjda.ffjudo.utils.DialogCreation;
import com.ffjda.ffjudo.utils.Variable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Inscription2Activity extends ActionBarActivity implements View.OnClickListener{


    // View tiems
    private RelativeLayout mSuivantRelativeLayout;
    private RelativeLayout mCodePostalValider;

    // Get Current Licence
    private NouvelleLicence mNouvelleLicence;
    private ClubNouvelleLicence clubNouvelleLicence;

    // String list for saison
    List<String> spinnerArray =  new ArrayList<>();

    // VIew element
    private Spinner activityInscription2Saison;
    private Spinner activityInscription2Discipline;
    private EditText activityInscription2CodePostal;
    private TextView activityInscription2Nom;
    private TextView activityInscription2Designation;
    private TextView activityInscription2DojoCompteur;
    private TextView activityInscription2Adresse;
    private TextView activityInscription2CodePostalChoisi;
    private TextView activityInscription2Ville;
    private ImageView valideImageView;
    private TextView dojoLabelTextView;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-06-01 23:23:14 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        activityInscription2Saison = (Spinner)findViewById( R.id.activity_inscription2_saison );
        activityInscription2Discipline = (Spinner)findViewById( R.id.activity_inscription2_discipline );
        activityInscription2CodePostal = (EditText)findViewById( R.id.activity_inscription2_code_postal );
        activityInscription2Nom = (TextView)findViewById( R.id.activity_inscription2_nom );
        activityInscription2Designation = (TextView)findViewById( R.id.activity_inscription2_designation );
        activityInscription2DojoCompteur = (TextView)findViewById( R.id.activity_inscription2_dojo_compteur );
        activityInscription2Adresse = (TextView)findViewById( R.id.activity_inscription2_adresse );
        activityInscription2CodePostalChoisi = (TextView)findViewById( R.id.activity_inscription2_code_postal_choisi );
        activityInscription2Ville = (TextView)findViewById(R.id.activity_inscription2_ville);
        valideImageView = (ImageView) findViewById(R.id.activity_inscription2_valide);
        dojoLabelTextView = (TextView) findViewById(R.id.activity_inscription2_dojo);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription2);

        // View initialisation
        mSuivantRelativeLayout = (RelativeLayout)findViewById(R.id.activity_inscription2_suivant_layout);
        mSuivantRelativeLayout.setOnClickListener(this);
        mCodePostalValider = (RelativeLayout) findViewById(R.id.activity_inscription2_code_postal_valider);
        mCodePostalValider.setOnClickListener(this);

        // Hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // get nouvelle Licence
        mNouvelleLicence = (NouvelleLicence)getIntent().getSerializableExtra("nouvelleLicence");

        //View initialisation
        findViews();
        // Spinner initialisation
        saisonInitialisation();

    }

    /**
     * Spinner date initialisation
     */
    private void saisonInitialisation() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR)-1;
        int year1 = year+1;
        int year2 = year+2;

        spinnerArray.add(year+"/"+year1);
        spinnerArray.add(year1 + "/" + year2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityInscription2Saison.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inscription2, menu);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_inscription2_suivant_layout :
                if(clubNouvelleLicence == null)
                {
                    DialogCreation.createDialog(this, getString(R.string.Club_manquant), getString(R.string.Club_manquant_desc));
                    return;
                }
                mNouvelleLicence.setSaison(activityInscription2Saison.getSelectedItem().toString());
                Intent inscription3 = new Intent(this,Inscription3Activity.class);
                inscription3.putExtra("nouvelleLicence",mNouvelleLicence);
                startActivityForResult(inscription3, Variable.REQUEST_CODE_SUIV);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                break;
            case R.id.activity_inscription2_code_postal_valider:
                if(activityInscription2CodePostal.length() > 5 || activityInscription2CodePostal.length()==0)
                {
                    DialogCreation.createDialog(this, getString(R.string.bad_cp), getString(R.string.bad_cp_desc));
                    return;
                }

                int discode =0;
                switch (activityInscription2Discipline.getSelectedItemPosition())
                {
                    case 0:
                        discode =0;
                        break;
                    case 1:
                        discode = 3;
                        break;
                    case 2:
                        discode = 4;
                        break;
                    case 3:
                        discode = 4;
                        break;
                    case 4:
                        discode = 5;
                        break;
                    case 5:
                        discode = 6;
                        break;
                    case 6:
                        discode = 7;
                        break;
                    case 7:
                        discode = 8;
                        break;
                }

                Intent code_postal = new Intent(this, CodePostalActivity.class);
                code_postal.putExtra("code",activityInscription2CodePostal.getText().toString());
                code_postal.putExtra("discode",discode+"");
                startActivityForResult(code_postal, Variable.REQUEST_CODE_POSTAL);
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ici");
        if(requestCode == Variable.REQUEST_CODE_POSTAL && resultCode == Variable.RESULT_CODE_POSTAL)
        {
            clubNouvelleLicence =(ClubNouvelleLicence)data.getSerializableExtra("club");
            activityInscription2Nom.setText(clubNouvelleLicence.getNom());
            activityInscription2Designation.setText(clubNouvelleLicence.getDesignation());
            activityInscription2DojoCompteur.setText(clubNouvelleLicence.getDojocode());
            activityInscription2Adresse.setText(clubNouvelleLicence.getAdresse());
            activityInscription2CodePostalChoisi.setText(clubNouvelleLicence.getCp());
            activityInscription2Ville.setText(clubNouvelleLicence.getVille());
            valideImageView.setVisibility(View.VISIBLE);
            dojoLabelTextView.setVisibility(View.VISIBLE);

            mNouvelleLicence.addClubNouvelleLicence(clubNouvelleLicence);
        }
        if(requestCode == Variable.REQUEST_CODE_SUIV && resultCode == Variable.REQUEST_CODE_COMPLETE)
        {
            setResult(Variable.REQUEST_CODE_COMPLETE,data);
            finish();
            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right);
    }
}
