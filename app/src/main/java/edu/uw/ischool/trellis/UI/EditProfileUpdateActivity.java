package edu.uw.ischool.trellis.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.uw.ischool.trellis.ConversationStarterActivity;
import edu.uw.ischool.trellis.EditProfileActivity;
import edu.uw.ischool.trellis.LearnMoreActivity;
import edu.uw.ischool.trellis.MessagesActivity;
import edu.uw.ischool.trellis.R;
import edu.uw.ischool.trellis.SupportActivity;

public class EditProfileUpdateActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_update);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });


        /********************************************************/
        /********************** NEW TOOLBAR SETUP *******************/
        /********************************************************/
        LinearLayout friendsIcon = (LinearLayout) findViewById(R.id.friendsLayoutIcon);
        LinearLayout messagesIcon = (LinearLayout) findViewById(R.id.messagesLayoutIcon);
        LinearLayout conversationIcon = (LinearLayout) findViewById(R.id.conversationStartersLayoutIcon);
        LinearLayout learnMoreIcon = (LinearLayout) findViewById(R.id.learnMoreLayoutIcon);
        LinearLayout profileIcon = (LinearLayout) findViewById(R.id.profileLayoutIcon);


        friendsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, SupportActivity.class);
                startActivity(next);
            }
        });

        messagesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, MessagesActivity.class);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, ConversationStarterActivity.class);
                startActivity(next);
            }
        });

        learnMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });
        /********************************************************/
        /********************************************************/
        /********************************************************/

    }

}