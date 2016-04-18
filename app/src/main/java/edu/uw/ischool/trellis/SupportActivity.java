package edu.uw.ischool.trellis;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        TextView firstBox = (TextView) findViewById(R.id.textView8);

        firstBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, ProfileActivity.class);
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
                Intent next = new Intent(SupportActivity.this, SupportActivity.class);
                startActivity(next);
            }
        });

        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, MessagesActivity.class);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, ConversationStarterActivity.class);
                startActivity(next);
            }
        });

        readMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });
        /********************************************************/
        /********************************************************/
        /********************************************************/

    }

}
