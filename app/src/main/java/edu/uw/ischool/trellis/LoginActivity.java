package edu.uw.ischool.trellis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import edu.uw.ischool.trellis.UI.ToolbarView;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main);
        ToolbarView toolbar = (ToolbarView) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }



}
