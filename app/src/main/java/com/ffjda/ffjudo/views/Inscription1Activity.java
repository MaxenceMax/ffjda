package com.ffjda.ffjudo.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.NouvelleLicence;
import com.ffjda.ffjudo.utils.DialogCreation;
import com.ffjda.ffjudo.utils.StringValidator;
import com.ffjda.ffjudo.utils.Variable;

import java.util.Calendar;

public class Inscription1Activity extends ActionBarActivity implements View.OnClickListener{

    // View tiems
    private RelativeLayout mSuivantRelativeLayout;
    private EditText mNaissanceEditText;


    // Date picker
    private int year, month, day;
    private static int _MAJORITY = 18;
    private DatePicker datePicker;
    private Calendar calendar;

    // View elements
    private EditText activityInscription1Nom;
    private EditText activityInscription1Prenom;
    private Spinner activityInscription1Sexe;
    private EditText activityInscription1Adresse;
    private EditText activityInscription1CodePostal;
    private EditText activityInscription1Ville;
    private EditText activityInscription1Email;
    private EditText activityInscription1Telephone;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-06-01 20:05:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        activityInscription1Nom = (EditText)findViewById( R.id.activity_inscription1_nom );
        activityInscription1Prenom = (EditText)findViewById( R.id.activity_inscription1_prenom );
        activityInscription1Sexe = (Spinner)findViewById( R.id.activity_inscription1_sexe );
        activityInscription1Adresse = (EditText)findViewById( R.id.activity_inscription1_adresse );
        activityInscription1CodePostal = (EditText)findViewById( R.id.activity_inscription1_code_postal );
        activityInscription1Ville = (EditText)findViewById( R.id.activity_inscription1_ville );
        activityInscription1Email = (EditText)findViewById( R.id.activity_inscription1_email );
        activityInscription1Telephone = (EditText)findViewById( R.id.activity_inscription1_telephone );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // View initialisation
        mSuivantRelativeLayout = (RelativeLayout)findViewById(R.id.activity_inscription1_suivant_layout);
        mSuivantRelativeLayout.setOnClickListener(this);
        mNaissanceEditText = (EditText) findViewById(R.id.activity_inscription1_naissance);
        wakeUpDate();

        findViews();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inscription1, menu);
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
            case R.id.activity_inscription1_suivant_layout :
                //check if all edit text are completed
                StringValidator testString = new StringValidator();
                if( activityInscription1Nom.getText().toString().trim().isEmpty() ||
                    activityInscription1Prenom.getText().toString().trim().isEmpty()||
                    activityInscription1Adresse.getText().toString().trim().isEmpty() ||
                    activityInscription1CodePostal.getText().toString().trim().isEmpty()||
                    activityInscription1Ville.getText().toString().trim().isEmpty() ||
                    activityInscription1Email.getText().toString().trim().isEmpty())
                {
                    DialogCreation.createDialog(this, getString(R.string.champs_requis), getString(R.string.champs_requis_desc));
                    return;
                }else if(!testString.validateEmail(activityInscription1Email.getText().toString()))
                {
                    DialogCreation.createDialog(this,getString(R.string.bad_email),getString(R.string.bad_email_desc));
                    return;
                }
                else if(activityInscription1CodePostal.length() != 5) {
                    DialogCreation.createDialog(this,getString(R.string.bad_cp),getString(R.string.bad_cp_desc));
                    return;
                }
                else
                {
                    NouvelleLicence nv = new NouvelleLicence();
                    nv.setNom(activityInscription1Nom.getText().toString().trim());
                    nv.setPrenom(activityInscription1Prenom.getText().toString().trim());
                    nv.setDate_naissance(mNaissanceEditText.getText().toString().trim());
                    nv.setAdresse(activityInscription1Adresse.getText().toString().trim());
                    nv.setCode_postal(activityInscription1CodePostal.getText().toString().trim());
                    nv.setVille(activityInscription1Ville.getText().toString().trim());
                    nv.setMail(activityInscription1Email.getText().toString().trim());
                    if(activityInscription1Sexe.getSelectedItemPosition()==0)
                        nv.setSexe("F");
                    else
                        nv.setSexe("M");
                    if(activityInscription1Telephone.getText().toString().trim().isEmpty())
                        nv.setTelephone(" ");
                    else
                        nv.setTelephone(activityInscription1Telephone.getText().toString().trim());

                    Intent inscription2 = new Intent(this,Inscription2Activity.class);
                    inscription2.putExtra("nouvelleLicence",nv);
                    startActivityForResult(inscription2, Variable.REQUEST_CODE_SUIV);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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



    /**
     * Initialize date picker
     *
     */
    private void wakeUpDate()
    {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = 01;
        day = 01;
        showDate(year-_MAJORITY, month, day);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year-_MAJORITY, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        StringBuilder tmp = new StringBuilder();
        if( day <10)
        {
            tmp.append("0").append(day);
        }
        else
        {
            tmp.append(day);
        }
        if( month+1 <10)
        {
            tmp.append("/").append("0").append(month + 1);
        }
        else
        {
            tmp.append("/").append(month + 1);
        }
        tmp.append("/").append(year);
        mNaissanceEditText.setText(tmp.toString());
    }
}
