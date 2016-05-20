package edu.uw.ischool.trellis;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.uw.ischool.trellis.UI.ToolbarView;


public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    Button guestButton;
    CallbackManager callbackManager;
    MainApp app;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);


        app = (MainApp) getApplication();
        app.changeStatusBarColor(this);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
        Button guestButton = (Button) findViewById(R.id.guestButton);

        guestButton.setTypeface(myTypeface);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "user_friends");

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
                accessToken = currentAccessToken;
            }
        };

        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                Log.e("onSuccess", "--------" + loginResult.getAccessToken());
                Log.e("Token", "--------" + loginResult.getAccessToken().getToken());
                Log.e("Permision", "--------" + loginResult.getRecentlyGrantedPermissions());
                final Profile profile = Profile.getCurrentProfile();
                Log.e("ProfileDataNameF", "--" + profile.getFirstName());
                Log.e("ProfileDataNameL", "--" + profile.getLastName());
                Log.e("ProfileDataID", "--" + profile.getId());

                Log.e("Image URI", "--" + profile.getLinkUri());

                Log.e("OnGraph", "------------------------");
                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();

                final GraphRequestAsyncTask request = new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                Log.e("GraphResponse", "-------------" + response.toString());
                                try {
                                    // Log.e("Dataresponse", "-------------" + response.getJSONObject().getJSONArray("data").toString());
                                    String friends = response.getJSONObject().getJSONArray("data").toString();
                                    Log.e("Dataresponse", "-------------" + friends);
                                    User user = new User(profile.getFirstName(), profile.getLastName(), "", "", false, friends, profile.getId());
                                    Log.e("User", "-------------" + user.toString());

                                    app.setCurrentUser(user);

                                } catch (JSONException e) {
                                    Log.e("Dataresponse", "-------------FAILED");
                                }
                            }
                        }
                ).executeAsync();

                Intent next = new Intent(LoginActivity.this, SupportSelectionActivity.class);
                startActivity(next);
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(),"Login cancelled",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(),exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        guestButton = (Button) findViewById(R.id.guestButton);
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(LoginActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
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
}
