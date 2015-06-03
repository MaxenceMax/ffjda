package com.ffjda.ffjudo.model;

import java.io.Serializable;

/**
 * Created by maxence on 03/06/15.
 */
public class RenouvellementAjoutLicence implements Serializable{

    private String saison;
    private Boolean cnil;
    private String numlicence;
    private String dojo_compteur;
    private String naissance;
    private Boolean assurance;
    private String club_compteur;

    public Boolean getCnil() {
        return cnil;
    }

    public void setCnil(Boolean cnil) {
        this.cnil = cnil;
    }

    public Boolean getAssurance() {
        return assurance;
    }

    public void setAssurance(Boolean assurance) {
        this.assurance = assurance;
    }

    /**
     * Add the club choose by user to sent it
     * @param clubNouvelleLicence
     */
    public void addClubNouvelleLicence(ClubNouvelleLicence clubNouvelleLicence)
    {
        this.club_compteur = clubNouvelleLicence.getClubcompeur();
        this.dojo_compteur = clubNouvelleLicence.getDojocode();
    }

    public RenouvellementAjoutLicence() {
    }

    public RenouvellementAjoutLicence(String saison, boolean cnil, String numlicence, String dojo_compteur, String naissance, boolean assurance, String club_compteur) {
        this.saison = saison;
        this.cnil = cnil;
        this.numlicence = numlicence;
        this.dojo_compteur = dojo_compteur;
        this.naissance = naissance;
        this.assurance = assurance;
        this.club_compteur = club_compteur;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public boolean isCnil() {
        return cnil;
    }

    public void setCnil(boolean cnil) {
        this.cnil = cnil;
    }

    public String getNumlicence() {
        return numlicence;
    }

    public void setNumlicence(String numlicence) {
        this.numlicence = numlicence;
    }

    public String getDojo_compteur() {
        return dojo_compteur;
    }

    public void setDojo_compteur(String dojo_compteur) {
        this.dojo_compteur = dojo_compteur;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public boolean isAssurance() {
        return assurance;
    }

    public void setAssurance(boolean assurance) {
        this.assurance = assurance;
    }

    public String getClub_compteur() {
        return club_compteur;
    }

    public void setClub_compteur(String club_compteur) {
        this.club_compteur = club_compteur;
    }
}
