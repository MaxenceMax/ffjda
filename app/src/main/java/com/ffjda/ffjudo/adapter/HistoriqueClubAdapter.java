package com.ffjda.ffjudo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.Club;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxence on 01/06/15.
 */
public class HistoriqueClubAdapter extends ArrayAdapter<Club> {

    Context context;
    int layoutResourceId;
    private List<Club> clubData = new ArrayList<>();
    private View row;

    private HistoInformation mHistoInformation;


    public HistoriqueClubAdapter(Context context, int resource) {
        super(context,resource);
        this.context = context;
        this.layoutResourceId = resource;
    }

    public void swapItems(List<Club> items) {
        clubData.clear();
        clubData.addAll(items);
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        mHistoInformation = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            mHistoInformation = new HistoInformation();
            mHistoInformation.nom = (TextView)row.findViewById(R.id.historique_club_row_club);
            mHistoInformation.arrivee = (TextView)row.findViewById(R.id.historique_club_row_saison_arrivee);
            mHistoInformation.depart = (TextView)row.findViewById(R.id.historique_club_row_saison_depart);
            mHistoInformation.yearLabel= (TextView)row.findViewById(R.id.historique_club_row_year_label);
            mHistoInformation.year = (TextView)row.findViewById(R.id.historique_club_row_years);

            row.setTag(mHistoInformation);
        }
        else
        {
            mHistoInformation = (HistoInformation)row.getTag();
        }

        final Club club = clubData.get(position);

        mHistoInformation.nom.setText(club.getNomClub());
        mHistoInformation.arrivee.setText(club.getAnneeIn());
        mHistoInformation.depart.setText(club.getAnneeOut());

        String tmp = (club.getAnneeIn().split("/"))[0];
        int in = Integer.parseInt(tmp);
        tmp = (club.getAnneeOut().split("/"))[0];
        int out = Integer.parseInt(tmp);
        int result = out -in ;
        mHistoInformation.yearLabel.setText("an");
        if(result>0)
            mHistoInformation.yearLabel.setText("ans");
        result++;



        mHistoInformation.year.setText(""+result);

        return row;
    }

    static class HistoInformation
    {
        TextView nom;
        TextView arrivee;
        TextView depart;
        TextView yearLabel;
        TextView year;
    }
}
