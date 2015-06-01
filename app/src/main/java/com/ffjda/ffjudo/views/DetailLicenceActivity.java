package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.Licence;
import com.ffjda.ffjudo.model.Licencie;
import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.Code128;
import com.onbarcode.barcode.android.IBarcode;
import com.onbarcode.barcode.android.QRCode;

public class DetailLicenceActivity extends ActionBarActivity implements View.OnClickListener {

    // Object to displays
    private Licence mCurrentLicence;
    private Licencie mCurrentLicencie;

    // view items
    private TextView mNomTextView;
    private TextView mDateNaissanceTextView;
    private TextView mNumLicenceTextView;
    private TextView mGradeTextView;
    private TextView mClubTextView;
    private TextView mSexeTextView;
    private ImageView mValideImageView;
    private ImageView mHistoImageView;

    // Image for code and qrCode
    private ImageView mImageViewCode128;
    private ImageView mImageViewQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_licence);

        // Get object from intent
        mCurrentLicence = (Licence) getIntent().getSerializableExtra("licence");
        mCurrentLicencie = (Licencie) getIntent().getSerializableExtra("licencie");

        mNomTextView = (TextView) findViewById(R.id.activity_detail_licence_nom);
        mDateNaissanceTextView  = (TextView) findViewById(R.id.activity_detail_licence_naissance);
        mNumLicenceTextView  = (TextView) findViewById(R.id.activity_detail_licence_num_licence);
        mGradeTextView  = (TextView) findViewById(R.id.activity_detail_licence_grade);
        mSexeTextView  = (TextView) findViewById(R.id.activity_detail_licence_sexe);
        mClubTextView  = (TextView) findViewById(R.id.activity_detail_licence_club);
        mImageViewCode128  = (ImageView) findViewById(R.id.activity_detail_licence_code128);
        mImageViewQrcode = (ImageView) findViewById(R.id.activity_detail_licence_add_qrcode);
        mValideImageView = (ImageView) findViewById(R.id.activity_detail_licence_valide);
        mHistoImageView = (ImageView) findViewById(R.id.activity_detail_licence_histo);
        mHistoImageView.setOnClickListener(this);

        //Generate qrCode and Code 128
        codeGeneration();

    }

    /**
     * Creation of the two code
     */
    private void codeGeneration()
    {
        StringBuilder sb = new StringBuilder();
        mNumLicenceTextView.setText(mCurrentLicencie.getNum_licence());
        mNomTextView.setText(mCurrentLicencie.getNom() + " " + mCurrentLicencie.getPrenom());
        mDateNaissanceTextView.setText(mCurrentLicencie.getDate_naissance());

        if(mCurrentLicence.getEstLicencie().equals("YES"))
            mValideImageView.setImageDrawable(getResources().getDrawable(R.drawable.valide_green_picto));
        else
            mValideImageView.setImageDrawable(getResources().getDrawable(R.drawable.pas_valide_red_picto));

        if(mCurrentLicencie.getSexe().equals("M"))
            mSexeTextView.setText("Masculin");
        else
            mSexeTextView.setText("Féminin");

        mClubTextView.setText(mCurrentLicence.getClub());
        mGradeTextView.setText(mCurrentLicence.getGrade());
        generateCode128(mCurrentLicencie.getNum_licence());
        // Information usefull to qrcode
        sb.append(mCurrentLicencie.getNum_licence()).append(";");
        if(mCurrentLicencie.getSexe().equals("M"))
            sb.append("M").append(";");
        else
            sb.append("F").append(";");
        sb.append(mCurrentLicencie.getNom()).append(";");
        sb.append(mCurrentLicencie.getPrenom()).append(";");
        sb.append(mCurrentLicencie.getDate_naissance());
        generateQrCode(sb.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_licence, menu);
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

    private void generateQrCode(String info) {
        QRCode barcode = new QRCode();

			/*
			   QRCode Valid data char set:
			        numeric data (digits 0 - 9);
			        alphanumeric data (digits 0 - 9; upper case letters A -Z; nine other characters: space, $ % * + - . / : );
			        byte data (default: ISO/IEC 8859-1);
			        Kanji characters
			*/
        barcode.setData(info);
        barcode.setDataMode(QRCode.M_AUTO);
        barcode.setVersion(6);
        barcode.setEcl(QRCode.ECL_H);

        //  Set the processTilde property to true, if you want use the tilde character "~" to
        //  specify special characters in the input data. Default is false.
        //  1-byte character: ~ddd (character value from 0 ~ 255)
        //  ASCII (with EXT): from ~000 to ~255
        //  2-byte character: ~6ddddd (character value from 0 ~ 65535)
        //  Unicode: from ~600000 to ~665535
        //  ECI: from ~7000000 to ~7999999
        //  SJIS: from ~9ddddd (Shift JIS 0x8140 ~ 0x9FFC and 0xE040 ~ 0xEBBF)
        barcode.setProcessTilde(false);

        // unit of measure for X, Y, LeftMargin, RightMargin, TopMargin, BottomMargin
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode module width in pixel
        barcode.setX(8f);

        barcode.setLeftMargin(10f);
        barcode.setRightMargin(10f);
        barcode.setTopMargin(10f);
        barcode.setBottomMargin(10f);
        // barcode image resolution in dpi
        barcode.setResolution(100);

        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);


        Bitmap tempBitmap = Bitmap.createBitmap(350,350, Bitmap.Config.RGB_565);
        tempBitmap.eraseColor(Color.rgb(194, 202, 224));
        Canvas tempCanvas = new Canvas(tempBitmap);

			/*
			specify your barcode drawing area
			    */
        RectF bounds = new RectF(0, 0, 0, 0);
        try {
            barcode.drawBarcode(tempCanvas, bounds);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Attach the canvas to the ImageView
        mImageViewQrcode.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

    }

    private void generateCode128(String chaine)
    {
        Code128 barcode = new Code128();
			/*
			   Code 128 Valid data char set:
			        all 128 ASCII characters (Char from 0 to 127)
			*/
        barcode.setData(chaine);

        //  Set the processTilde property to true, if you want use the tilde character "~"
        //  to specify special characters in the input data. Default is false.
        //  1) All 128 ISO/IEC 646 characters, i.e. characters 0 to 127 inclusive, in accordance with ISO/IEC 646.
        //       NOTE This version consists of the G0 set of ISO/IEC 646 and the C0 set of ISO/IEC 6429 with values 28 - 31
        //       modified to FS, GS, RS and US respectively.
        //  2) Characters with byte values 128 to 255 may also be encoded.
        //  3) 4 non-data function characters.
        //  4) 4 code set selection characters.
        //  5) 3 Start characters.
        //  6) 1 Stop character.
        barcode.setProcessTilde(false);
        //Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(856,196, Bitmap.Config.RGB_565);
        tempBitmap.eraseColor(Color.rgb(194, 202, 224));
        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(0);
        // barcode bar module width (X) in pixel
        barcode.setX(4f);
        // barcode bar module height (Y) in pixel
        barcode.setY(155f);


        // barcode image resolution in dpi
        barcode.setResolution(220);

        // disply barcode encoding data below the barcode
        barcode.setShowText(true);
        // barcode encoding data font style
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 38));
        // space between barcode and barcode encoding data
        barcode.setTextMargin(6);
        barcode.setTextColor(AndroidColor.black);

        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);

        // barcode image marg ins
        barcode.setLeftMargin(10f);
        barcode.setRightMargin(10f);
        barcode.setTopMargin(10f);
        barcode.setBottomMargin(10f);

			/*
			specify your barcode drawing area
			    */
        RectF bounds = new RectF(0, 0, 0, 0);

        Canvas tempCanvas = new Canvas(tempBitmap);

        //Draw the image bitmap into the cavas
        try {
            barcode.drawBarcode(tempCanvas, bounds);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Attach the canvas to the ImageView
        mImageViewCode128.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_detail_licence_histo:
                Intent histo = new Intent(this, HistoriqueClubActivity.class);
                histo.putExtra("licence",mCurrentLicence);
                startActivity(histo);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                break;
        }
    }
}
