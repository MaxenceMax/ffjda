package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.utils.TypefaceUtil;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TypefaceUtil.overrideFont(getApplicationContext(), "MONOSPACE", "fonts/San_fransisco.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Timer to end activity after splash duration
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        launchLoginActivity();
                    }
                };
                timer.schedule(task, 3000);
            }
        });
    }

    private void launchLoginActivity()
    {
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long&
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
