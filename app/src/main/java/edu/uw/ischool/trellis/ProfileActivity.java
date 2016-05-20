package edu.uw.ischool.trellis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {

    Button messageBtn;
    Button favoriteBtn;
    MainApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);

        app = (MainApp) getApplication();

        app.changeStatusBarColor(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String fullName = firstName + " " + lastName;

        ImageView photo = (ImageView) findViewById(R.id.photo);
        TextView name = (TextView) findViewById(R.id.name);

        name.setText(fullName);

        String imgURL = "https://graph.facebook.com/" + id + "/picture?type=large";

        try {
            URL url = new URL(imgURL);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            photo.setImageBitmap(bmp);
        } catch (IOException e) {
            Log.e("Image Download", e.getMessage());
        }




        messageBtn = (Button) findViewById(R.id.messageBtn);
        favoriteBtn = (Button) findViewById(R.id.favoriteBtn);

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ProfileActivity.this, MessagesActivity.class);
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
                Intent next = new Intent(ProfileActivity.this, SupportActivity.class);
                startActivity(next);
            }
        });

        messagesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ProfileActivity.this, MessagesActivity.class);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ProfileActivity.this, ConversationStarterActivity.class);
                startActivity(next);
            }
        });

        learnMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ProfileActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });
        /********************************************************/
        /********************************************************/
        /********************************************************/


    }

}
