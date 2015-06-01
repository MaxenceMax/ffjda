package com.ffjda.ffjudo.utils;

import android.annotation.SuppressLint;

public class Variable {
	
	// URL for json request
	public static String jsonUrlRequestLogin = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/connexion";
	public static String jsonUrlRequestLicencie = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/informations/";
	public static String jsonUrlRequestClub = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/histoClub/";
	public static String jsonUrlRequestCompetitionThisYear = "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/infosInscriptionCoursJson/";
	public static String jsonUrlRequestCompetitionForYear= "http://www.ffjda.org/ws_mobile/webRestGet/service.svc/infosInscriptionJson/";

	//Preferences items
	public static final String PREFERENCENUMLIC = "numLic";
	public static final String PREFERENCELASTLOG= "lastLogin";


	public static final String FFJDAPREFERENCES = "ffjdaprefs";


	
	// Usefull to modify button opacity onClick
	public static float OPACITYDOWN =0.55f;
	public static float OPACITYUP = 1.0f;
}
