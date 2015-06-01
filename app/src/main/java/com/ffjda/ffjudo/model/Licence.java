package com.ffjda.ffjudo.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Licence extends SugarRecord<Licence> implements Serializable{

    private String discipline;
    private String discode;
    private String saison;
	private String grade;
	private String estLicencie;
	private String club;

    public Licence(Licence l)
    {
        this.discipline = l.getDiscipline();
        this.discode = l.getDiscode();
        this.saison = l.getSaison();
        this.grade = l.getGrade();
        this.estLicencie = l.getEstLicencie();
        this.club = l.getClub();
    }

    public Licence(String discipline, String discode, String saison, String grade, String estLicencie, String club) {
        this.discipline = discipline;
        this.discode = discode;
        this.saison = saison;
        this.grade = grade;
        this.estLicencie = estLicencie;
        this.club = club;
    }

    @Override
    public String toString() {
        return "Licence{" +
                "discipline='" + discipline + '\'' +
                ", discode='" + discode + '\'' +
                ", saison='" + saison + '\'' +
                ", grade='" + grade + '\'' +
                ", estLicencie='" + estLicencie + '\'' +
                ", club='" + club + '\'' +
                '}';
    }

    public Licence() {
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDiscode() {
        return discode;
    }

    public void setDiscode(String discode) {
        this.discode = discode;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEstLicencie() {
        return estLicencie;
    }

    public void setEstLicencie(String estLicencie) {
        this.estLicencie = estLicencie;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
}
