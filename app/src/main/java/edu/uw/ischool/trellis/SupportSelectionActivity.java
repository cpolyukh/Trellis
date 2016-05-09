package edu.uw.ischool.trellis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import edu.uw.ischool.trellis.UI.ToolbarView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.Serializable;

public class SupportSelectionActivity extends AppCompatActivity implements Serializable{

    Button seekSupportBtn;
    Button giveSupportBtn;
    MainApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_selection);

        seekSupportBtn = (Button) findViewById(R.id.seekSupportBtn);
        giveSupportBtn = (Button) findViewById(R.id.giveSupportBtn);

        app = (MainApp) getApplication();


        seekSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportSelectionActivity.this, LearnMoreActivity.class);

                startActivity(next);

            }
        });

        giveSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportSelectionActivity.this, LearnMoreActivity.class);
                startActivity(next);

            }
        });


    }

}
