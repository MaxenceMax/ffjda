package com.ffjda.ffjudo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.model.Club;
import com.ffjda.ffjudo.model.ClubNouvelleLicence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxence on 01/06/15.
 */
public class ClubAdapter extends ArrayAdapter<ClubNouvelleLicence> {

    Context context;
    int layoutResourceId;
    private List<ClubNouvelleLicence> clubData = new ArrayList<>();
    private View row;

    private ClubInformation mClubInformation;


    public ClubAdapter(Context context, int resource) {
        super(context,resource);
        this.context = context;
        this.layoutResourceId = resource;
    }

    public void swapItems(List<ClubNouvelleLicence> items) {
        clubData.clear();
        clubData.addAll(items);
        clear();
        addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;
        mClubInformation = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            mClubInformation = new ClubInformation();
            mClubInformation.nom = (TextView)row.findViewById(R.id.club_row_nom);
            mClubInformation.adresse = (TextView)row.findViewById(R.id.club_row_adresse);
            mClubInformation.ville = (TextView)row.findViewById(R.id.club_row_ville);
            mClubInformation.cp= (TextView)row.findViewById(R.id.club_row_code_postal);
            mClubInformation.designation=(TextView)row.findViewById(R.id.club_row_designation);
            mClubInformation.dojocompteur=(TextView)row.findViewById(R.id.club_row_dojo_compteur);

            row.setTag(mClubInformation);
        }
        else
        {
            mClubInformation = (ClubInformation)row.getTag();
        }

        final ClubNouvelleLicence club = clubData.get(position);

        mClubInformation.nom.setText(club.getNom());
        mClubInformation.adresse.setText(club.getAdresse());
        mClubInformation.cp.setText(club.getCp());
        mClubInformation.ville.setText(club.getVille());
        mClubInformation.dojocompteur.setText(club.getDojocode());
        mClubInformation.designation.setText(club.getDesignation());

        return row;
    }

    static class ClubInformation
    {
        TextView nom;
        TextView adresse;
        TextView cp;
        TextView ville;
        TextView designation;
        TextView dojocompteur;
    }

}
