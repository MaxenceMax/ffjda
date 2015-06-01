package com.ffjda.ffjudo.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.Licence;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxence on 01/06/15.
 */
public class LicencesAdapter extends ArrayAdapter<Licence> {
    Context context;
    int layoutResourceId;
    private List<Licence> licenceData = new ArrayList<>();
    private View row;

    private LicenceInformation mLicenceInformation;

    public LicencesAdapter(Context context, int layoutResourceId){
        super(context,layoutResourceId);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
    }

    public void swapItems(List<Licence> items) {
        licenceData.clear();
        licenceData.addAll(items);
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        mLicenceInformation = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            mLicenceInformation = new LicenceInformation();
            mLicenceInformation.discipline = (TextView)row.findViewById(R.id.licences_row_discipline);
            mLicenceInformation.saison = (TextView)row.findViewById(R.id.licences_row_saison);
            mLicenceInformation.valide = (ImageView)row.findViewById(R.id.licences_row_valide);

            row.setTag(mLicenceInformation);
        }
        else
        {
            mLicenceInformation = (LicenceInformation)row.getTag();
        }

        final Licence licence = licenceData.get(position);

        mLicenceInformation.discipline.setText(licence.getDiscipline());
        mLicenceInformation.saison.setText(licence.getSaison());

        if(licence.getEstLicencie().equals("YES"))
        {
            mLicenceInformation.valide.setImageDrawable(context.getResources().getDrawable(R.drawable.valide_green_picto));
        }
        else {
            mLicenceInformation.valide.setImageDrawable(context.getResources().getDrawable(R.drawable.pas_valide_red_picto));
        }
        return row;
    }


    static class LicenceInformation
    {
        TextView discipline;
        TextView saison;
        ImageView valide;
    }
}
