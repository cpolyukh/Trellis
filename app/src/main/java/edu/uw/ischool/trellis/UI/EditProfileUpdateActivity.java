package edu.uw.ischool.trellis.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.uw.ischool.trellis.ConversationStarterActivity;
import edu.uw.ischool.trellis.EditProfileActivity;
import edu.uw.ischool.trellis.LearnMoreActivity;
import edu.uw.ischool.trellis.MainApp;
import edu.uw.ischool.trellis.MessagesActivity;
import edu.uw.ischool.trellis.R;
import edu.uw.ischool.trellis.SupportActivity;
import edu.uw.ischool.trellis.User;

public class EditProfileUpdateActivity extends AppCompatActivity {
    ListView supportSkillList;
    ListView conversationTopicList;
    MainApp mainApp;
    User currentUser;


    @Override
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

        TextView usernameView = (TextView) findViewById(R.id.name);
        TextView quoteView = (TextView) findViewById(R.id.quoteView);

        TextView[] textViews = {usernameView, quoteView,
                (TextView) findViewById(R.id.textView17), (TextView) findViewById(R.id.textView22),
                (TextView) findViewById(R.id.textView26), (TextView) findViewById(R.id.textView27)};

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
        for (TextView current : textViews)
        {
            current.setTypeface(myTypeface);
        }

        editButton.setTypeface(myTypeface);

        mainApp = (MainApp) getApplication();
        currentUser = mainApp.getCurrentUser();
        supportSkillList = (ListView) findViewById(R.id.supportSkillListView);

        List<String> supportSkillsList = currentUser.getSupportSkills();

        MyArrayAdapter<String> supportAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, supportSkillsList);

        supportSkillList.setAdapter(supportAdapter);


        mainApp = (MainApp) getApplication();
        currentUser = mainApp.getCurrentUser();
        conversationTopicList = (ListView) findViewById(R.id.conversationTopicsListView);


        usernameView.setText(currentUser.getName());
        quoteView.setText(currentUser.getQuote());

        List<String> conversationTopicsList = currentUser.getConversationTopics();

        MyArrayAdapter<String> conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, conversationTopicsList);

        conversationTopicList.setAdapter(conversationTopicsAdapter);

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

    private static class MyArrayAdapter<String> extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "Futura.ttc");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MyArrayAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            view.setTextColor(Color.parseColor("#6d6d6d"));
            view.setPadding(10, 0, 10, 0);
            view.setTextSize(15);
            return view;
        }

    }
}