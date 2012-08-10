package com.tinywebgears.relayme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tinywebgears.relayme.util.OAuthBuilder;
import com.tinywebgears.relayme.util.OAuthHelper;

public class GoogleOAuthActivity extends Activity
{
    private static final String TAG = "GoogleOAuthActivity";

    private static GoogleOAuthActivity instance;
    private WebView webview;

    public static GoogleOAuthActivity get()
    {
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "GoogleOAuthActivity.onCreate");
        super.onCreate(savedInstanceState);
        instance = this;
        webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVisibility(View.VISIBLE);

        setContentView(webview);

        webview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                if (url.startsWith(OAuthHelper.OAUTH_CALLBACK_URL))
                {
                    Intent oauthActivity = new Intent().setClass(getApplicationContext(), OAuthActivity.class);
                    oauthActivity.putExtra(OAuthActivity.PARAM_OAUTH_RESULT, url);
                    startActivity(oauthActivity);
                }
                System.out.println("onPageFinished : " + url);
            }
        });

        String authorizationUrl = OAuthBuilder.get().getAuthorizationUrl();
        webview.loadUrl(authorizationUrl);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        Log.d(TAG, "GoogleOAuthActivity.onNewIntent");
        super.onNewIntent(intent);

    }

    @Override
    protected void onStop()
    {
        Log.d(TAG, "GoogleOAuthActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "GoogleOAuthActivity.onResume");
        super.onResume();
    }
}
