package edu.uw.ischool.trellis;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class SupportActivity extends AppCompatActivity {
    Application app;
    ListView list;

    List<Supporter> allSupporters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        //TODO: Fix this code! Almost working

        app = getApplication();

        //Intent launchingIntent = getIntent();

        list = (ListView) findViewById(R.id.lstSupporters);

        addDefaultSupporters();

        //TODO: make sure that this list only contains supporters that the given
        //user is not already connected to
        allSupporters = Supporter.getAllSupporters();

        Supporter[] supporterArray = new Supporter[allSupporters.size()];
        for (int i = 0; i < allSupporters.size(); i++) {
            supporterArray[i] = allSupporters.get(i);
        }
        SupporterArrayAdapter adapter = new SupporterArrayAdapter(this, supporterArray);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(SupportActivity.this, ProfileActivity.class);

                //TODO: send extras necessary to make sure the profile clicked matches the profile displayed
                //on the next page
                startActivity(next);
            }
        });





/*
        TextView firstBox = (TextView) findViewById(R.id.textView8);

        firstBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, ProfileActivity.class);
                startActivity(next);
            }
        });*/

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


    //Used for testing since we don't have actual supporters yet. These are simply default supporters
    //used as an example of how supporters would be displayed
    private void addDefaultSupporters() {
        Supporter newSupporter = new Supporter("Jen", "Nguyen", "\"Happy to just talk or grab some coffee! Don&apos;t be afraid to reach out :)\"");
        newSupporter.setSupporteeCount(10);
        newSupporter = new Supporter("Ahn", "Nest", "\"I am always available to talk, message me at anytime!\"");
        newSupporter.setSupporteeCount(2);
        newSupporter = new Supporter("Buttress", "Ofsupport", "\"I will support the life you want to build for yourself.\"");
        newSupporter.setSupporteeCount(0);
    }

}
