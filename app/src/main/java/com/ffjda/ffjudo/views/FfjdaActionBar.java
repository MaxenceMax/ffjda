package com.ffjda.ffjudo.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.utils.CheckConnection;
import com.ffjda.ffjudo.utils.TypefaceUtil;
import com.ffjda.ffjudo.utils.Utils;
import com.ffjda.ffjudo.utils.Variable;

/**
 * Created by maxence on 15/09/15.
 */
public class FfjdaActionBar extends Activity {
    //Variables pour le menu
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerItemCustomAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ImageView mMenuShower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        // Button menu
        mMenuShower = (ImageView) findViewById(R.id.show_menu);
        mMenuShower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });

        setupDrawer();

        // Navlist initialisation
        // With exception on the login screen to hid deconnexion from menu
        SharedPreferences sharedPreferences = getSharedPreferences(Variable.FFJDAPREFERENCES, Context.MODE_PRIVATE);
        ObjectDrawerItem[] drawerItem;
        if(sharedPreferences.contains(Variable.PREFERENCELASTLOG))
        {
            drawerItem = new ObjectDrawerItem[6];
        }else
        {
            drawerItem = new ObjectDrawerItem[5];
        }
        drawerItem[0] = new ObjectDrawerItem(R.drawable.identity_card_icon_focusable, Variable.itemArray[0]);
        drawerItem[1] = new ObjectDrawerItem(R.drawable.browser_icon_focusable, Variable.itemArray[1]);
        drawerItem[2] = new ObjectDrawerItem(R.drawable.camera_icon_focusable, Variable.itemArray[2]);
        drawerItem[3] = new ObjectDrawerItem(R.drawable.partner_icon_focusable, Variable.itemArray[3]);
        drawerItem[4] = new ObjectDrawerItem(R.drawable.information_icon_focusable, Variable.itemArray[4]);
        if(sharedPreferences.contains(Variable.PREFERENCELASTLOG))
        {
            drawerItem[5] = new ObjectDrawerItem(R.drawable.cross_icon_focusable, Variable.itemArray[5]);
        }



        addDrawerItems(drawerItem);
    }

    /**
     * Do all things to deconnect current user
     */
    private void deconnexion()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Variable.FFJDAPREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(Variable.PREFERENCELASTLOG).commit();
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }

    private void setupDrawer(){
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle(getString(R.string.menu_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(getString(R.string.app_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Open the drawer layout
     */
    public void open()
    {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public Context getLocalContext()
    {
        return this;
    }

    private void addDrawerItems(ObjectDrawerItem objects[]){
        adapter = new DrawerItemCustomAdapter(this, R.layout.navlist_item_row, objects);
        mDrawerList.setAdapter(adapter);

        // Peference to check if the user is connected
        final SharedPreferences sharedPreferences = getSharedPreferences(Variable.FFJDAPREFERENCES, Context.MODE_PRIVATE);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(!sharedPreferences.contains(Variable.PREFERENCELASTLOG))
                        {
                            Utils.showAlertNotConnected(getLocalContext());
                            return;
                        }
                        Intent licences = new Intent(getLocalContext(),LicencesActivity.class);
                        startActivity(licences);
                        setResult(Variable.RESULT_EXIT, null);
                        finish();
                        break;
                    case 1:
                        if(!CheckConnection.isNetworkAvailable(getLocalContext()))
                        {
                            Utils.showAlertNoConnexion(getLocalContext());
                            return;
                        }
                        Intent website = new Intent(getLocalContext(),WebViewActivity.class);
                        startActivity(website);
                        setResult(Variable.RESULT_EXIT, null);
                        finish();
                        break;
                    case 2:
                        if(!sharedPreferences.contains(Variable.PREFERENCELASTLOG))
                        {
                            Utils.showAlertNotConnected(getLocalContext());
                            return;
                        }
                        Intent VogIntent = new Intent(getLocalContext(),VogoActivity.class);
                        startActivity(VogIntent);
                        setResult(Variable.RESULT_EXIT, null);
                        finish();
                        break;
                    case 3:
                        if(!CheckConnection.isNetworkAvailable(getLocalContext()))
                        {
                            Utils.showAlertNoConnexion(getLocalContext());
                            return;
                        }
                        Intent partenaires = new Intent(getLocalContext(),PartenaireActivity.class);
                        startActivity(partenaires);
                        setResult(Variable.RESULT_EXIT, null);
                        finish();
                        break;
                    case 4:
                        if(!sharedPreferences.contains(Variable.PREFERENCELASTLOG))
                        {
                            Utils.showAlertNotConnected(getLocalContext());
                            return;
                        }
                        Intent apropos = new Intent(getLocalContext(),AproposActivity.class);
                        startActivity(apropos);
                        setResult(Variable.RESULT_EXIT, null);
                        finish();
                        break;
                    case 5:
                        deconnexion();
                        break;
                }
                mDrawerLayout.closeDrawers();
            }
        });
    }

    public class DrawerItemCustomAdapter extends ArrayAdapter<ObjectDrawerItem> {

        Context mContext;
        int layoutResourceId;
        ObjectDrawerItem data[] = null;

        public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, ObjectDrawerItem[] data) {

            super(mContext, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.mContext = mContext;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View listItem = convertView;

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            listItem = inflater.inflate(layoutResourceId, parent, false);

            ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
            TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

            ObjectDrawerItem folder = data[position];

            imageViewIcon.setImageResource(folder.icon);
            if(position == data.length-1 && data.length>5)
            {
                textViewName.setTextColor(getResources().getColorStateList(R.color.red_text_clickable));
            }
            textViewName.setText(folder.name);

            return listItem;
        }
    }

    public class ObjectDrawerItem {

        public int icon;
        public String name;

        // Constructor.
        public ObjectDrawerItem(int icon, String name) {

            this.icon = icon;
            this.name = name;
        }
    }
}
