package com.ffjda.ffjudo.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by maxence on 27/05/15.
 */
public class Licencie extends SugarRecord implements Serializable{

    private String nom;
    private String prenom;
    private String sexe;
    private String ide_clee;
    private String num_licence;
    private String date_naissance;

    public Licencie() {
    }

    public Licencie(String nom, String prenom, String sexe, String ide_clee, String num_licence, String date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.ide_clee = ide_clee;
        this.num_licence = num_licence;
        this.date_naissance = date_naissance;
    }

    public Licencie (Licencie l)
    {
        this.nom = l.getNom();
        this.prenom = l.getPrenom();
        this.sexe = l.getSexe();
        this.ide_clee = l.getIde_clee();
        this.num_licence = l.getNum_licence();
        this.date_naissance = l.getDate_naissance();
    }

    @Override
    public String toString() {
        return "Licencie{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", ide_clee='" + ide_clee + '\'' +
                ", num_licence='" + num_licence + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                '}';
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setIde_clee(String ide_clee) {
        this.ide_clee = ide_clee;
    }

    public void setNum_licence(String num_licence) {
        this.num_licence = num_licence;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getIde_clee() {
        return ide_clee;
    }

    public String getNum_licence() {
        return num_licence;
    }

    public String getDate_naissance() {
        return date_naissance;
    }
}
