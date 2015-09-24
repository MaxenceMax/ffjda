package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.utils.Variable;

public class AproposActivity extends FfjdaActionBar implements View.OnClickListener {

    // Button envoyer un mail
    private RelativeLayout mRelativeLayoutMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_apropos);
        super.onCreate(savedInstanceState);

        //view initialisation
        mRelativeLayoutMail = (RelativeLayout) findViewById(R.id.activity_aprops_envoyer_mail);
        mRelativeLayoutMail.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apropos, menu);
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
            case R.id.activity_aprops_envoyer_mail:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", Variable.MAILBUG, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.envoyer_mail)));
                break;
        }
    }
}
