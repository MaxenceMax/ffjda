package com.ffjda.ffjudo.model;

import java.io.Serializable;

/**
 * Created by maxence on 01/06/15.
 */
public class NouvelleLicence implements Serializable{

    private String club_compteur;
    private String nom;
    private String prenom;
    private String sexe;
    private Boolean assurance;
    private String date_naissance;
    private String code_postal;
    private String ville;
    private String dojo_compteur;
    private String adresse;
    private String saison;
    private Boolean cnil;
    private String telephone;
    private String mail;

    public NouvelleLicence(String club_compteur, String nom, String prenom, String sexe, Boolean assurance, String date_naissance, String code_postal, String ville, String dojo_compteur, String adresse, String saison, Boolean cnil, String telephone, String mail) {
        this.club_compteur = club_compteur;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.assurance = assurance;
        this.date_naissance = date_naissance;
        this.code_postal = code_postal;
        this.ville = ville;
        this.dojo_compteur = dojo_compteur;
        this.adresse = adresse;
        this.saison = saison;
        this.cnil = cnil;
        this.telephone = telephone;
        this.mail = mail;
    }

    public NouvelleLicence() {
    }

    public String getClub_compteur() {
        return club_compteur;
    }

    public void setClub_compteur(String club_compteur) {
        this.club_compteur = club_compteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Boolean getAssurance() {
        return assurance;
    }

    public void setAssurance(Boolean assurance) {
        this.assurance = assurance;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDojo_compteur() {
        return dojo_compteur;
    }

    public void setDojo_compteur(String dojo_compteur) {
        this.dojo_compteur = dojo_compteur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public Boolean getCnil() {
        return cnil;
    }

    public void setCnil(Boolean cnil) {
        this.cnil = cnil;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    @Override
    public String toString() {
        return "NouvelleLicence{" +
                "club_compteur='" + club_compteur + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", assurance=" + assurance +
                ", date_naissance='" + date_naissance + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                ", dojo_compteur='" + dojo_compteur + '\'' +
                ", adresse='" + adresse + '\'' +
                ", saison='" + saison + '\'' +
                ", cnil=" + cnil +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
