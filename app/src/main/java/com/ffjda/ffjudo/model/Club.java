package com.ffjda.ffjudo.model;

import com.orm.SugarRecord;

public class Club extends SugarRecord<Club> {
	
	private String nomClub;
	private String anneeIn;
	private String anneeOut;
    private String discode;


    public String getDiscode() {

        return discode;
    }

    public Club(String nomClub, String anneeIn, String anneeOut, String discode) {
        this.nomClub = nomClub;
        this.anneeIn = anneeIn;
        this.anneeOut = anneeOut;
        this.discode = discode;
    }

    public void setDiscode(String discode) {
        this.discode = discode;
    }

    /**
	 * 
	 */
	public Club() {
	}
	
	/**
	 * @param nomClub
	 * @param anneeIn
	 * @param anneeOut
	 */
	public Club(String nomClub, String anneeIn, String anneeOut) {
		this.nomClub = nomClub;
		this.anneeIn = anneeIn;
		this.anneeOut = anneeOut;
	}
	/**
	 * @return the nomClub
	 */
	public String getNomClub() {
		return nomClub;
	}
	/**
	 * @param nomClub the nomClub to set
	 */
	public void setNomClub(String nomClub) {
		this.nomClub = nomClub;
	}
	/**
	 * @return the anneeIn
	 */
	public String getAnneeIn() {
		return anneeIn;
	}
	/**
	 * @param anneeIn the anneeIn to set
	 */
	public void setAnneeIn(String anneeIn) {
		this.anneeIn = anneeIn;
	}
	/**
	 * @return the anneeOut
	 */
	public String getAnneeOut() {
		return anneeOut;
	}
	/**
	 * @param anneeOut the anneeOut to set
	 */
	public void setAnneeOut(String anneeOut) {
		this.anneeOut = anneeOut;
	}
	
	
}
