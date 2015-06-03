package com.ffjda.ffjudo.utils;

import android.annotation.SuppressLint;

import java.util.Arrays;
import java.util.List;

public class Variable {
	
	// URL for json request
	public static String jsonUrlRequestLogin = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/connexion";
	public static String jsonUrlRequestLicencie = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/informations/";
	public static String jsonUrlRequestClub = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/histoClub/";
	public static String jsonUrlRequestCodePostal = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/codepostal/";
	public static String jsonUrlPostNouvelleLicence = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/nouveaulicencie";
	public static String jsonUrlRequestLastClub ="http://www.ffjda.org/ws_mobile/webRestGet/service.svc/dernierclub/";
	public static String jsonUrlRequestCompetitionThisYear = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/infosInscriptionCoursJson/";
	public static String jsonUrlRequestCompetitionForYear= "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/infosInscriptionJson/";

	//Url for inscription
	public static String URLCONDITION ="http://www.ffjda.org/zelic/conditions/notice_assurance_suiv.htm";
	public static String URLREFUS="http://www.ffjda.org/zelic/conditions/refus_assurance_suiv.htm";
	public static String URLCNIL="http://www.ffjda.org/zelic/conditions/cnil_suiv.htm";

	public static List<String> DISCIPLINEARRAY =
			Arrays.asList("JUDO JUJITSU", "KENDO", "NAGINATA",
					"JODO","IAIDO","KYUDO","SPORT CHANBARA");
	public static List<Integer> DISCODEARRAY =
			Arrays.asList(0,3,4,5,6,7,8);

	// Result activity
	public static final int RESULT_CODE_POSTAL=2001;
	public static final int REQUEST_CODE_POSTAL=2002;
	public static final int REQUEST_CODE_SUIV=2003;
	public static final int REQUEST_CODE_COMPLETE=2004;
	public static final int REQUEST_CODE_COMPLETE_AJOUT=2005;

	//Preferences items
	public static final String PREFERENCENUMLIC = "numLic";
	public static final String PREFERENCELASTLOG= "lastLogin";
	public static final String FFJDAPREFERENCES = "ffjdaprefs";


	
	// Usefull to modify button opacity onClick
	public static float OPACITYDOWN =0.55f;
	public static float OPACITYUP = 1.0f;
}
