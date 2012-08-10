package com.tinywebgears.relayme;

import org.json.JSONObject;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinywebgears.relayme.util.OAuthBuilder;
import com.tinywebgears.relayme.util.OAuthHelper;

public class OAuthActivity extends Activity
{
    private static final String TAG = "OAuthActivity";
    public static final String PARAM_OAUTH_RESULT = "result";

    private static OAuthActivity instance;
    private SharedPreferences prefs;
    private TextView oauthStatusLabel;

    public static OAuthActivity get()
    {
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "OAuthActivity.onCreate");
        super.onCreate(savedInstanceState);
        instance = this;
        prefs = UserData.getPrefs(this);

        setContentView(R.layout.oauth);

        final Button oauthGoogle = (Button) this.findViewById(R.id.oauthgoogle);
        oauthGoogle.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                handleOAuthGoogleButton();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        Log.d(TAG, "OAuthActivity.onNewIntent");
        super.onNewIntent(intent);

        setContentView(R.layout.oauth_result);
        oauthStatusLabel = (TextView) findViewById(R.id.authstatus);
        final Button returnMain = (Button) this.findViewById(R.id.returnmain);
        returnMain.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                handleReturnMainButton();
            }
        });

        final String uri = intent.getStringExtra(PARAM_OAUTH_RESULT);
        if (uri != null)
        {
            if (OAuthBuilder.get().extractVerifier(uri) != null)
            {
                Log.i(TAG, "Retrieving access token from url: " + uri);
                try
                {
                    getAccessToken(uri);
                    oauthStatusLabel.setText(R.string.oauth_success);
                }
                catch (OAuthException e)
                {
                    oauthStatusLabel.setText(R.string.oauth_error);
                    Log.e(TAG, "Unable to get an OAuth access token: " + e, e);
                }
            }
            else
                oauthStatusLabel.setText(R.string.oauth_error);
        }
    }

    private void getAccessToken(String uri) throws OAuthException
    {
        String verifier = OAuthBuilder.get().extractVerifier(uri);
        Token accessToken = OAuthBuilder.get().getAccessToken(verifier);
        Log.d(TAG, "Access token: " + accessToken);
        String emailAddress = findEmailAddress();
        Log.d(TAG, "Email: " + emailAddress);

        final Editor edit = prefs.edit();
        edit.putString(UserData.PREF_KEY_OAUTH_ACCESS_TOKEN, accessToken.getToken());
        edit.putString(UserData.PREF_KEY_OAUTH_ACCESS_TOKEN_SECRET, accessToken.getSecret());
        edit.putString(UserData.PREF_KEY_OAUTH_EMAIL_ADDRESS, emailAddress);
        edit.commit();
    }

    private String findEmailAddress()
    {
        try
        {
            String jsonOutput = OAuthBuilder.get().makeSecuredRequest(UserData.getAccessToken(),
                    OAuthHelper.URL_GET_EMAIL);
            JSONObject jsonResponse = new JSONObject(jsonOutput);
            String email = jsonResponse.getString("email");
            Log.i(TAG, "Email address found: " + email);
            return email;
        }
        catch (Exception e)
        {
            Log.e(TAG, "Error fining email address: " + e, e);
        }
        return null;
    }

    @Override
    protected void onStop()
    {
        Log.d(TAG, "OAuthActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "OAuthActivity.onResume");
        super.onResume();
    }

    private void handleOAuthGoogleButton()
    {
        startActivity(new Intent().setClass(getApplicationContext(), GoogleOAuthActivity.class));
    }

    private void handleReturnMainButton()
    {
        returnToMainPage();
    }

    private void returnToMainPage()
    {
        startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
    }
}
