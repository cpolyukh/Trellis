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

public class SupportSelectionActivity extends AppCompatActivity {

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

                User newUser = new User("Christina", "Ghirmai", "Always here to talk about anything you want to talk about!",
                                "I'm a senior at UW this year! I have had depression in the past and know a lot of " +
                                "people who have gone through that sort of thing. I know what it's like and I know " +
                                "sometimes all it takes is having someone who wants to listen so I'd love to be that " +
                                "person for you. Don't be afraid to reach out!", false);
                app.setCurrentUser(newUser);
            }
        });

        giveSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportSelectionActivity.this, LearnMoreActivity.class);
                startActivity(next);

                User newUser = new User("Christina", "Ghirmai", "Always here to talk about anything you want to talk about!",
                                "I'm a senior at UW this year! I have had depression in the past and know a lot of " +
                                "people who have gone through that sort of thing. I know what it's like and I know " +
                                "sometimes all it takes is having someone who wants to listen so I'd love to be that " +
                                "person for you. Don't be afraid to reach out!", true);
                app.setCurrentUser(newUser);
            }
        });


    }

}
