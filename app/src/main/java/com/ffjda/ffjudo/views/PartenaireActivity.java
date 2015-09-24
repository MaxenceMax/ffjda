package com.ffjda.ffjudo.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ffjda.ffjudo.R;
import com.ffjda.ffjudo.utils.Variable;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.security.KeyPairGenerator;
import java.security.Provider;

public class PartenaireActivity extends FfjdaActionBar {


    // Web view 1
    private WebView webView1;
    private myWebViewClient mWebViewClient1;
    private myWebChromeClient mWebChromeClient1;

    // Youtube spec
    public static final String VIDEO_ID_1 = "bW3yo5G9pPM";
    public static final String VIDEO_ID_2 = "hb-HsDvSTx4";
    private FrameLayout customViewContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;


    // View
    private View mCustomView;
    private RelativeLayout mRelativeLayout;


    private WebView webView2;
    private myWebViewClient mWebViewClient2;
    private myWebChromeClient mWebChromeClient2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_partenaire);
        super.onCreate(savedInstanceState);

        // View initialisation
        customViewContainer = (FrameLayout) findViewById(R.id.customViewContainer);
        webView1 = (WebView) findViewById(R.id.activity_partenaire_webview1);
        webView2 = (WebView) findViewById(R.id.activity_partenaire_webview2);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.content_frame);

        // Web view 1 initialisation
        mWebViewClient1 = new myWebViewClient();
        webView1.setWebViewClient(mWebViewClient1);
        mWebChromeClient1 = new myWebChromeClient(webView1);
        webView1.setWebChromeClient(mWebChromeClient1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setDisplayZoomControls(false);
        webView1.getSettings().setBuiltInZoomControls(true);
        webView1.getSettings().setAppCacheEnabled(true);
        webView1.getSettings().setBuiltInZoomControls(true);
        webView1.getSettings().setSaveFormData(true);
        webView1.loadUrl("http://www.youtube.com/embed/" + VIDEO_ID_1 + "?autoplay=1&vq=small");

        // WebView 2
        mWebViewClient2 = new myWebViewClient();
        webView2.setWebViewClient(mWebViewClient2);
        mWebChromeClient2 = new myWebChromeClient(webView2);
        webView2.setWebChromeClient(mWebChromeClient2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setDisplayZoomControls(false);
        webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setAppCacheEnabled(true);
        webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setSaveFormData(true);
        webView2.loadUrl("http://www.youtube.com/embed/" + VIDEO_ID_2 + "?autoplay=1&vq=small");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_partenaire, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView1.onPause();
        webView2.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        webView1.onResume();
        webView2.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        if (inCustomView()) {
            hideCustomView();
        }
    }

    public boolean inCustomView() {
        return (mCustomView != null);
    }

    public void hideCustomView() {
        mWebChromeClient1.onHideCustomView();
        mWebChromeClient2.onHideCustomView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    class myWebChromeClient extends WebChromeClient {
        private Bitmap mDefaultVideoPoster;
        private View mVideoProgressView;
        private WebView webView;

        public myWebChromeClient(WebView webView)
        {
            this.webView = webView;
        }

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            onShowCustomView(view, callback);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onShowCustomView(View view,CustomViewCallback callback) {

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            webView.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.GONE);
            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view);
            customViewCallback = callback;
        }

        @Override
        public View getVideoLoadingProgressView() {

            if (mVideoProgressView == null) {
                LayoutInflater inflater = LayoutInflater.from(getLocalContext());
                //mVideoProgressView = inflater.inflate(R.layout.video_progress, null);
            }
            return mVideoProgressView;
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();    //To change body of overridden methods use File | Settings | File Templates.
            if (mCustomView == null)
                return;

            webView.setVisibility(View.VISIBLE);
            customViewContainer.setVisibility(View.GONE);

            // Hide the custom view.
            mCustomView.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.VISIBLE);

            // Remove the custom view from its container.
            customViewContainer.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();

            mCustomView = null;
        }
    }


}
