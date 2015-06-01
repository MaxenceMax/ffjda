
package com.ffjda.ffjudo.model;

import android.content.Context;

import com.orm.SugarRecord;


public class Competition {
	
	private String libelle;
	private String ville;
	private String adresse;
	private String dateDeb;
	private String dateFin;
	private String contact;
	private String mobile;
	private String mail;
	private String saison;
	private String identifiant;

	
		
	/**
	 * @param libelle
	 * @param ville
	 * @param adresse
	 * @param dateDeb
	 * @param dateFin
	 * @param contact
	 * @param mobile
	 * @param mail
	 * @param saison
	 * @param identifiant
	 */
	
	
	public Competition(String libelle, String ville, String adresse,
			String dateDeb, String dateFin, String contact, String mobile,
			String mail, String saison, String identifiant, Context ctx) {
		this.libelle = libelle;
		this.ville = ville;
		this.adresse = adresse;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.contact = contact;
		this.mobile = mobile;
		this.mail = mail;
		this.saison = saison;
		this.identifiant = identifiant;
	}
	
	/**
	 * 
	 */
	public Competition() {
		super();
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the dateDeb
	 */
	public String getDateDeb() {
		return dateDeb;
	}
	/**
	 * @param dateDeb the dateDeb to set
	 */
	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}
	/**
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}
	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the saison
	 */
	public String getSaison() {
		return saison;
	}
	/**
	 * @param saison the saison to set
	 */
	public void setSaison(String saison) {
		this.saison = saison;
	}
	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	

}
