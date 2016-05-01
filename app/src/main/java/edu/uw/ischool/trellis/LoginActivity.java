package edu.uw.ischool.trellis;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.util.ArrayList;
import java.util.List;

import edu.uw.ischool.trellis.UI.ToolbarView;


public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    Button guestButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        List<String> permissions = new ArrayList<String>();
        permissions.add("user_friends");
        permissions.add("public_profile");

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(permissions);
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent next = new Intent(LoginActivity.this, SupportSelectionActivity.class);
                startActivity(next);

            }

            @Override
            public void onCancel() { }

            @Override
            public void onError(FacebookException exception) { }
        });


        guestButton = (Button) findViewById(R.id.guestButton);
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(LoginActivity.this, SupportSelectionActivity.class);
                startActivity(next);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
