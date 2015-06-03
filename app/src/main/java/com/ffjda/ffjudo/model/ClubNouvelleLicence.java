package com.ffjda.ffjudo.model;

import java.io.Serializable;

/**
 * Created by maxence on 01/06/15.
 */
public class ClubNouvelleLicence implements Serializable{

    private String adresse;
    private String clubcompeur;
    private String cp;
    private String designation;
    private String dojocode;
    private String nom;
    private String ville;
    private String dojocompteur;



    @Override
    public String toString() {
        return "ClubNouvelleLicence{" +
                "adresse='" + adresse + '\'' +
                ", clubcompeur='" + clubcompeur + '\'' +
                ", cp='" + cp + '\'' +
                ", designation='" + designation + '\'' +
                ", dojocode='" + dojocode + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", dojocompteur='" + dojocompteur + '\'' +
                '}';
    }

    public String getDojocompteur() {
        return dojocompteur;
    }

    public void setDojocompteur(String dojocompteur) {
        this.dojocompteur = dojocompteur;
    }

    public ClubNouvelleLicence(String adresse, String clubcompeur, String cp, String designation, String dojocode, String nom, String ville, String dojocompteur) {
        this.adresse = adresse;
        this.clubcompeur = clubcompeur;
        this.cp = cp;
        this.designation = designation;
        this.dojocode = dojocode;
        this.nom = nom;
        this.ville = ville;
        this.dojocompteur = dojocompteur;
    }

    public ClubNouvelleLicence() {
    }

    public ClubNouvelleLicence(ClubNouvelleLicence cnl) {
        this.adresse = cnl.getAdresse();
        this.clubcompeur = cnl.getClubcompeur();
        this.cp = cnl.getCp();
        this.designation = cnl.getDesignation();
        this.dojocode = cnl.getDojocode();
        this.nom = cnl.getNom();
        this.ville = cnl.getVille();
        this.dojocompteur = cnl.getDojocompteur();
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getClubcompeur() {
        return clubcompeur;
    }

    public void setClubcompeur(String clubcompeur) {
        this.clubcompeur = clubcompeur;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDojocode() {
        return dojocode;
    }

    public void setDojocode(String dojocode) {
        this.dojocode = dojocode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ClubNouvelleLicence(String adresse, String clubcompeur, String cp, String designation, String dojocode, String nom, String ville) {
        this.adresse = adresse;
        this.clubcompeur = clubcompeur;
        this.cp = cp;
        this.designation = designation;
        this.dojocode = dojocode;
        this.nom = nom;
        this.ville = ville;
    }
}
