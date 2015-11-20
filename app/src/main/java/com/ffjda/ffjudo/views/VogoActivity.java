package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.ffjda.ffjudo.R;
import com.vogo.playerlib.MainPlayer;
//import com.vogo.vgplayerlib.MainPlayer;

public class VogoActivity extends FfjdaActionBar implements View.OnClickListener{

    private RelativeLayout mRelativeLayoutVogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vogo);
        super.onCreate(savedInstanceState);

        // Initialisation vogo layout
        mRelativeLayoutVogo = (RelativeLayout) findViewById(R.id.activity_vogo_lancer_layout);
        mRelativeLayoutVogo.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vogo, menu);
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
            case R.id.activity_vogo_lancer_layout:
                Intent myIntent = new Intent(this, MainPlayer.class);
                startActivity(myIntent);
                break;
        }
    }
}
