package edu.uw.ischool.trellis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditProfileUpdateActivity extends AppCompatActivity {
    LinearLayout supportSkillList;
    LinearLayout conversationTopicList;
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
        mainApp = (MainApp) getApplication();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mainApp.setupToolBar(this);
        mainApp.changeStatusBarColor(this);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");

        Button editButton = (Button) findViewById(R.id.editButton);
        editButton.setTypeface(myTypeface);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileUpdateActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });


        // Overrides all fonts of TextView children
        ScrollView profileView = (ScrollView) findViewById(R.id.profileView);
        RelativeLayout profileHeader = (RelativeLayout) findViewById(R.id.profileHeader);
        overrideFonts(getBaseContext(), profileView);
        overrideFonts(getBaseContext(), profileHeader);
        addSkill = (EditText) findViewById(R.id.addSkill);
        addConversation = (EditText) findViewById(R.id.addConversation);


        // Gets Current user and autofills their user name and quote
        currentUser = mainApp.getCurrentUser();

        TextView usernameView = (TextView) findViewById(R.id.name);
        TextView quoteView = (TextView) findViewById(R.id.quoteView);

        usernameView.setText(currentUser.getName());
        //quoteView.setText(currentUser.getQuote());



        // Adds existing support skills list and onclick listener
        supportSkillList = (LinearLayout) findViewById(R.id.supportSkillListView);
        supportAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, supportSkillsList);

        final int supportAdapterCount = supportAdapter.getCount();

        for (int i = 0; i < supportAdapterCount; i++) {
            View item = supportAdapter.getView(i, null, null);
            supportSkillList.addView(item);
        }

        supportSkillList.setScrollContainer(false);

        //When "+" button is clicked to add a new skill
        addSkillButton = (ImageView) findViewById(R.id.addSkillBtn);
        addSkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toAdd = "- " + addSkill.getText().toString();
                if (!toAdd.isEmpty()) {
                    TextView tv = (TextView) newItem(toAdd);
                    supportSkillList.addView(tv);
                    addSkill.getText().clear();
                }

            }
        });


        // Adds existing conversation topics list and onclick listener
        conversationTopicList = (LinearLayout) findViewById(R.id.conversationTopicsListView);
        conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, conversationTopicsList);

        final int conversationAdapterCount = conversationTopicsAdapter.getCount();

        for (int i = 0; i < conversationAdapterCount; i++) {
            View item = conversationTopicsAdapter.getView(i, null, null);
            conversationTopicList.addView(item);
        }

        addConversationButton = (ImageView) findViewById(R.id.addConvoBtn);
        addConversationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toAdd = "- " + addConversation.getText().toString();
                if(!toAdd.isEmpty()) {
                    TextView tv = (TextView) newItem(toAdd);
                    conversationTopicList.addView(tv);
                    addConversation.getText().clear();
                }
            }
        });




    }

    private View newItem(String text) {
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView tv = (TextView) inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        tv.setTextColor(Color.parseColor("#6d6d6d"));
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "Futura.ttc");
        tv.setTypeface(font);
        tv.setPadding(10, 0, 10, 0);
        tv.setTextSize(15);
        tv.setText(text);

        return tv;

    }

    private void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView || v instanceof EditText ) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "Futura.tcc"));
            }
        } catch (Exception e) {
        }
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
            //supportSkillList.setAdapter(supportAdapter);
        }
    }

    public void addConversations(View v) {
        String newTopic = "- " + addConversation.getText().toString();

        if (!newTopic.equals("")) {
            currentUser.addSupportConversationTopic(newTopic);
            List<String> newConversationTopicList = currentUser.getConversationTopics();

            conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                    android.R.layout.simple_expandable_list_item_1, newConversationTopicList);
            //conversationTopicList.setAdapter(conversationTopicsAdapter);
        }
    }
}