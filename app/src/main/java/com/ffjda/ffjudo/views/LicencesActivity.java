package com.ffjda.ffjudo.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.adapter.LicencesAdapter;
import com.ffjda.ffjudo.model.Licence;
import com.ffjda.ffjudo.model.Licencie;
import com.ffjda.ffjudo.utils.CheckConnection;
import com.ffjda.ffjudo.utils.DialogCreation;
import com.ffjda.ffjudo.utils.TypefaceUtil;
import com.ffjda.ffjudo.utils.Utils;
import com.ffjda.ffjudo.utils.Variable;

import java.util.ArrayList;
import java.util.List;

public class LicencesActivity extends FfjdaActionBar implements View.OnClickListener, AdapterView.OnItemClickListener{

    // View items
    RelativeLayout mAddLicenceRelativeLayout;
    private TextView mNomTextView;
    private TextView mNumlicenceTextView;
    private Licencie mCurrentLicencie;


    // Usefull item for list view
    private List<Licence> allLicences;

    //Variables pour la listView
    private LicencesAdapter licencesAdapter;
    private ListView licenceListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_licences);
        super.onCreate(savedInstanceState);

        // View initialisation
        mAddLicenceRelativeLayout = (RelativeLayout) findViewById(R.id.activity_licences_add_licence);
        mAddLicenceRelativeLayout.setOnClickListener(this);

        mNomTextView = (TextView) findViewById(R.id.activity_licences_nom);
        mNumlicenceTextView = (TextView) findViewById(R.id.activity_licences_numlicence);


        // Search the current licencie
        licencieInitialisation();
        //search and show all licence
        LicenceInitialisation();

    }


    /**
     * Serach all licence available for the current licencie
     */
    private void LicenceInitialisation()
    {
        //serach All licence
        allLicences = new ArrayList<>(Licence.listAll(Licence.class));
        // List view initialisation
        licenceListView = (ListView) findViewById(R.id.activity_licences_listview);
        licenceListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // addaptater
        licencesAdapter = new LicencesAdapter(this,R.layout.licences_row);
        licenceListView.setAdapter(licencesAdapter);
        licencesAdapter.swapItems(allLicences);

        // Listener
        licenceListView.setOnItemClickListener(this);
    }


    /**
     * Initialisation of the current licencie
     */
    private void licencieInitialisation()
    {
        List<Licencie> licencies = Licencie.listAll(Licencie.class);
        mCurrentLicencie = new Licencie(licencies.get(0));
        mNomTextView.setText(mCurrentLicencie.getPrenom() + " " + mCurrentLicencie.getNom());
        mNumlicenceTextView.setText(mCurrentLicencie.getNum_licence());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_licences, menu);
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
        switch (v.getId()) {
            case R.id.activity_licences_add_licence:
                if(!CheckConnection.isNetworkAvailable(this))
                {
                    Utils.showAlertNoConnexion(this);
                    return;
                }
                Intent intent = new Intent(this,AjoutActivity.class);
                startActivityForResult(intent, Variable.REQUEST_CODE_SUIV);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_fix);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Variable.REQUEST_CODE_SUIV && resultCode == Variable.REQUEST_CODE_COMPLETE_AJOUT)
        {
            DialogCreation.createDialog(this,
                    getString(R.string.Ajout_effectuee),
                    getString(R.string.Ajout_effectuee_desc));
        }

        if(requestCode == Variable.REQUEST_EXIT && resultCode==Variable.RESULT_EXIT)
        {
            finish();
        }

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Licence lic = new Licence(allLicences.get(position));
        Intent detailLicence = new Intent(this,DetailLicenceActivity.class);
        detailLicence.putExtra("licence",lic);
        detailLicence.putExtra("licencie",mCurrentLicencie);
        startActivityForResult(detailLicence, Variable.REQUEST_EXIT);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }
}
