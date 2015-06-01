package com.ffjda.ffjudo.views;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.adapter.HistoriqueClubAdapter;
import com.ffjda.ffjudo.adapter.LicencesAdapter;
import com.ffjda.ffjudo.model.Club;
import com.ffjda.ffjudo.model.Licence;

import java.util.List;

public class HistoriqueClubActivity extends ActionBarActivity {


    // view items
    private TextView mDiscplineTextView;


    // Usefull item for list view
    private List<Club> allClub;

    //Variables pour la listView
    private HistoriqueClubAdapter histoAdapter;
    private ListView clubListView;

    // Licence to display
    private Licence mCurrentLicence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_club);

        // get licence
        mCurrentLicence = (Licence) getIntent().getSerializableExtra("licence");
        //allClub = Club.findWithQuery(Club.class, "Select * from Club where dis_code = ?", "satya");)
        allClub = Club.find(Club.class,"discode = ?",mCurrentLicence.getDiscode());
        System.out.println("nb clubs ="+allClub.size());

        mDiscplineTextView = (TextView) findViewById(R.id.activity_historique_club_discipline);
        mDiscplineTextView.setText(mCurrentLicence.getDiscipline());

        clubListView = (ListView) findViewById(R.id.activity_historique_club_listview);
        histoAdapter = new HistoriqueClubAdapter(this, R.layout.historique_club_row);
        clubListView.setAdapter(histoAdapter);
        histoAdapter.swapItems(allClub);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historique_club, menu);
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
