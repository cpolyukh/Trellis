package edu.uw.ischool.trellis;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditProfileUpdateActivity extends AppCompatActivity {
    ListView supportSkillList;
    ListView conversationTopicList;
    MainApp mainApp;
    User currentUser;
    List<String> supportSkillsList = new ArrayList<String>();
    List<String> conversationTopicsList = new ArrayList<String>();
    MyArrayAdapter<String> supportAdapter;
    MyArrayAdapter<String> conversationTopicsAdapter;
    ImageView addSkillButton;
    ImageView addConversationButton;
    EditText addSkill;
    EditText addConversation;

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
        addSkill = (EditText) findViewById(R.id.addSkill);
        addConversation = (EditText) findViewById(R.id.addConversation);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
        for (TextView current : textViews)
        {
            current.setTypeface(myTypeface);
        }
        addSkill.setTypeface(myTypeface);
        editButton.setTypeface(myTypeface);
        addConversation.setTypeface(myTypeface);

        mainApp = (MainApp) getApplication();
        currentUser = mainApp.getCurrentUser();
        supportSkillList = (ListView) findViewById(R.id.supportSkillListView);

        supportSkillsList = currentUser.getSupportSkills();
        supportAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, supportSkillsList);

        supportSkillList.setAdapter(supportAdapter);


        mainApp = (MainApp) getApplication();
        currentUser = mainApp.getCurrentUser();
        conversationTopicList = (ListView) findViewById(R.id.conversationTopicsListView);


        usernameView.setText(currentUser.getName());
        quoteView.setText(currentUser.getQuote());

        conversationTopicsList = currentUser.getConversationTopics();

        conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, conversationTopicsList);

        conversationTopicList.setAdapter(conversationTopicsAdapter);

        //When "+" button is clicked to add a new skill
        addSkillButton = (ImageView) findViewById(R.id.imageView2);
        addSkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems(v);
            }
        });

        addConversationButton = (ImageView) findViewById(R.id.imageView3);
        addConversationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConversations(v);
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

    public void addItems(View v) {
        String newSkill = "- " + addSkill.getText().toString();

        if (!newSkill.equals("")) {
            currentUser.addSupportSkill(newSkill);
            List<String> newSupportSkillsList = currentUser.getSupportSkills();

            supportAdapter = new MyArrayAdapter<>(getBaseContext(),
                    android.R.layout.simple_expandable_list_item_1, newSupportSkillsList);
            supportSkillList.setAdapter(supportAdapter);
        }
    }

    public void addConversations(View v) {
        String newTopic = "- " + addConversation.getText().toString();

        if (!newTopic.equals("")) {
            currentUser.addSupportConversationTopic(newTopic);
            List<String> newConversationTopicList = currentUser.getConversationTopics();

            conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                    android.R.layout.simple_expandable_list_item_1, newConversationTopicList);
            conversationTopicList.setAdapter(conversationTopicsAdapter);
        }
    }
}