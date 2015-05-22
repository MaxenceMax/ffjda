package com.ffjda.ffjudo.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ffjda.ffjudo.R;

import java.util.Calendar;

public class Inscription1Activity extends ActionBarActivity implements View.OnClickListener{

    // View tiems
    private RelativeLayout mSuivantRelativeLayout;
    private EditText mNaissanceEditText;
    private Spinner mSexeSpinner;


    // Date picker
    private int year, month, day;
    private static int _MAJORITY = 18;
    private DatePicker datePicker;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription1);

        // View initialisation
        mSuivantRelativeLayout = (RelativeLayout)findViewById(R.id.activity_inscription1_suivant_layout);
        mSuivantRelativeLayout.setOnClickListener(this);
        mNaissanceEditText = (EditText) findViewById(R.id.activity_inscription1_naissance);
        wakeUpDate();
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
                Intent inscription2 = new Intent(this,Inscription2Activity.class);
                startActivity(inscription2);
                break;
        }
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
        StringBuilder tmp = new StringBuilder().append(day);
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
