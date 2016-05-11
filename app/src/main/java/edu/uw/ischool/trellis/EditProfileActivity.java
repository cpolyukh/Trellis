package edu.uw.ischool.trellis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button doneBtn = (Button) findViewById(R.id.doneBtn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        /********************************************************/
        /********************** TOOLBAR SETUP *******************/
        /********************************************************/
        ImageButton supportIcon = (ImageButton) findViewById(R.id.supportIcon);
        ImageButton messageIcon = (ImageButton) findViewById(R.id.messageIcon);
        ImageButton conversationIcon = (ImageButton) findViewById(R.id.conversationIcon);
        ImageButton readMoreIcon = (ImageButton) findViewById(R.id.readMoreIcon);
        ImageButton settingsIcon = (ImageButton) findViewById(R.id.settingsIcon);

        supportIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, SupportActivity.class);
                startActivity(next);
            }
        });

        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, MessagesActivity.class);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, ConversationStarterActivity.class);
                startActivity(next);
            }
        });

        readMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });
        /********************************************************/
        /********************************************************/
        /********************************************************/

    }

}
