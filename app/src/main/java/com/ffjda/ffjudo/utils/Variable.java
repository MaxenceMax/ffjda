package com.ffjda.ffjudo.utils;

import android.annotation.SuppressLint;

import java.util.Arrays;
import java.util.List;

public class Variable {

	// menu definition
	public final static String[] itemArray = { "Mes licences", "Site FFJudo", "Live","Partenaires","A propos","Déconnexion"};

	public final static String YOUTUBEKEY = "AIzaSyDtH4im3XsaiGNWDkelUxpYe3FI_58Utyw";

	// URL for json request
	public static String jsonUrlRequestLogin = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/connexion";
	public static String jsonUrlRequestLicencie = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/informations/";
	public static String jsonUrlRequestClub = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/histoClub/";
	public static String jsonUrlRequestCodePostal = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/codepostal/";
	public static String jsonUrlPostNouvelleLicence = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/nouveaulicencie";
	public static String jsonUrlRequestLastClub ="http://www.ffjda.org/ws_mobile/webRestGet/service.svc/dernierclub/";
	public static String jsonUrlRequestRenouvellement="http://www.ffjda.org/ws_mobile/webRestGet/service.svc/renouvellement";
	public static String jsonUrlRequestCompetitionThisYear = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/infosInscriptionCoursJson/";
	public static String jsonUrlRequestCompetitionForYear= "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/infosInscriptionJson/";

	// Mail address for bug
	public static String MAILBUG ="appffjda@ffjudo.com";

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
	public static final int REQUEST_EXIT=2006;
	public static final int RESULT_EXIT=2007;

	//Preferences items
	public static final String PREFERENCENUMLIC = "numLic";
	public static final String PREFERENCELASTLOG= "lastLogin";
	public static final String FFJDAPREFERENCES = "ffjdaprefs";


	
	// Usefull to modify button opacity onClick
	public static float OPACITYDOWN =0.55f;
	public static float OPACITYUP = 1.0f;
}
