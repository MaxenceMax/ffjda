package com.ffjda.ffjudo.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.ffjda.ffjudo.R;

public class Utils {
	
	/**
	 * Change view alpha on touch 
	 * @param view the view to change alpha
	 * @param event event receive from onTouchListener in main view
	 * @param opacityDown opacity to set when user is touching screen
	 * @param opacityUp opacity when user is releasing screen
	 */
	public static void changeAlpha(View view, MotionEvent event,float opacityDown,float opacityUp)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			AlphaAnimation alphaUp = new AlphaAnimation(opacityDown, opacityDown);
	        alphaUp.setFillAfter(true);
	        view.startAnimation(alphaUp);
		}
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			AlphaAnimation alphaUp = new AlphaAnimation(opacityUp, opacityUp);
	        alphaUp.setFillAfter(true);
	        view.startAnimation(alphaUp);
		}		
	}
	
	/**
	 * Show a alert dialog, to notify user that he can't be connected now
	 */
	public static void showCustomAlertDialogu(Context c,String title,String body)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		// Set the dialog characteristics
		builder.setTitle(title) .setMessage(body);
		// Add the buttons
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int id) {}
				});
		// Get the AlertDialog
		AlertDialog dialog = builder.create();
		// Launch the AlertDialog
		dialog.show();
	}
	
	
	/**
	 * Show a alert dialog, to notify user that he can't be connected now
	 */
	public static void showAlertErrorOnLogin(Context c)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		// Set the dialog characteristics
		builder.setTitle(R.string.attention) .setMessage(R.string.erreur_connexion);
		// Add the buttons
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int id) {}
				});
		// Get the AlertDialog
		AlertDialog dialog = builder.create();
		// Launch the AlertDialog
		dialog.show();
	}
	
	/**
	 * Show a alert dialog, to notify user haven't internet connexion
	 */
	public static void showAlertNoConnexionAtLogin(Context c)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		// Set the dialog characteristics
		builder.setTitle(R.string.attention) .setMessage(R.string.no_connexion_at_login);
		// Add the buttons
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int id) {}
				});
		// Get the AlertDialog
		AlertDialog dialog = builder.create();
		// Launch the AlertDialog
		dialog.show();
	}
	
	/**
	 * Show a alert dialog, to notify user haven't internet connexion
	 */
	public static void showAlertNoConnexion(Context c)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		// Set the dialog characteristics
		builder.setTitle(R.string.attention) .setMessage(R.string.no_connexion);
		// Add the buttons
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int id) {}
				});
		// Get the AlertDialog
		AlertDialog dialog = builder.create();
		// Launch the AlertDialog
		dialog.show();
	}
	
	/**
	 * use full to know if a Internet connection is available 
	 * @param c context to get system service
	 * @return true if connection is good, false if is not
	 */
	public static boolean isNetworkAvailable(Context c) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
