package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.ffjda.ffjudo.R;

public class Inscription2Activity extends ActionBarActivity implements View.OnClickListener{


    // View tiems
    private RelativeLayout mSuivantRelativeLayout;
    private RelativeLayout mCodePostalValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription2);

        // View initialisation
        mSuivantRelativeLayout = (RelativeLayout)findViewById(R.id.activity_inscription2_suivant_layout);
        mSuivantRelativeLayout.setOnClickListener(this);
        mCodePostalValider = (RelativeLayout) findViewById(R.id.activity_inscription2_code_postal_valider);
        mCodePostalValider.setOnClickListener(this);

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
                Intent inscription3 = new Intent(this,Inscription3Activity.class);
                startActivity(inscription3);
                break;
            case R.id.activity_inscription2_code_postal_valider:
                Intent code_postal = new Intent(this, CodePostalActivity.class);
                startActivity(code_postal);
                break;
        }
    }
}
