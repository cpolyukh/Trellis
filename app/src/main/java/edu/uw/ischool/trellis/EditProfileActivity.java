package edu.uw.ischool.trellis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    LinearLayout supportSkillList;
    LinearLayout conversationTopicList;
    MainApp mainApp;
    User currentUser;
    List<String> supportSkillsList = new ArrayList<String>();
    List<String> conversationTopicsList = new ArrayList<String>();
    MyArrayAdapter<String> supportAdapter;
    MyArrayAdapter<String> conversationTopicsAdapter;
    Button editButton;
    ScrollView profileView;
    RelativeLayout profileHeader;
    TextView usernameView;
    TextView quoteView;
    TextView bioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mainApp = (MainApp) getApplication();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mainApp.setupToolBar(this);
        mainApp.changeStatusBarColor(this);

        //Set up font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");

        //Set up all buttons, views, fields, etc.
        editButton = (Button) findViewById(R.id.editButton);
        profileView = (ScrollView) findViewById(R.id.profileView);
        profileHeader = (RelativeLayout) findViewById(R.id.profileHeader);
        usernameView = (TextView) findViewById(R.id.name);
        quoteView = (TextView) findViewById(R.id.quoteView);
        bioView = (TextView) findViewById(R.id.textView27);

        currentUser = mainApp.getCurrentUser();

        // Overrides all fonts of TextView children
        overrideFonts(getBaseContext(), profileView);
        overrideFonts(getBaseContext(), profileHeader);
        editButton.setTypeface(myTypeface);
        usernameView.setTypeface(myTypeface);
        quoteView.setTypeface(myTypeface);
        bioView.setTypeface(myTypeface);

        //When the user clicks the edit button, it takes them to the edit profile page
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, EditProfileUpdateActivity.class);
                startActivity(next);
            }
        });

        // Autofills current user's username and quote
        usernameView.setText(currentUser.getName());
        quoteView.setText(currentUser.getQuote());
        bioView.setText(currentUser.getBio());

        // Adds existing support skills list and onclick listener
        supportSkillList = (LinearLayout) findViewById(R.id.supportSkillListView);
        supportAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, supportSkillsList);

        List<String> currentSupportSkills = currentUser.getSupportSkills();
        final int supportAdapterCount = currentSupportSkills.size();

        for (int i = 0; i < supportAdapterCount; i++) {
            TextView tv = (TextView) newItem(currentSupportSkills.get(i));
            supportSkillList.addView(tv);
        }

        supportSkillList.setScrollContainer(false);

        // Adds existing conversation topics list and onclick listener
        conversationTopicList = (LinearLayout) findViewById(R.id.conversationTopicsListView);
        conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, conversationTopicsList);

        List<String> currentConversationTopics = currentUser.getConversationTopics();
        final int conversationAdapterCount = currentConversationTopics.size();

        for (int i = 0; i < conversationAdapterCount; i++) {
            TextView tv = (TextView) newItem(currentConversationTopics.get(i));
            conversationTopicList.addView(tv);
        }
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
}




















    /*
    ListView supportSkillList;
    ListView conversationTopicList;
    MainApp mainApp;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mainApp = (MainApp) getApplication();
        mainApp.changeStatusBarColor(this);

        LinearLayout profileLayoutIcon = (LinearLayout) findViewById(R.id.profileLayoutIcon);
        profileLayoutIcon.setBackgroundColor(getResources().getColor(R.color.light_gray));

        ImageView icon = (ImageView) profileLayoutIcon.findViewById(R.id.profileLayoutIcon_img);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.settings01));



        Button editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(EditProfileActivity.this, EditProfileUpdateActivity.class);
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
        //quoteView.setText(currentUser.getQuote());

        List<String> conversationTopicsList = currentUser.getConversationTopics();

        MyArrayAdapter<String> conversationTopicsAdapter = new MyArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, conversationTopicsList);

        conversationTopicList.setAdapter(conversationTopicsAdapter);


        mainApp.setupToolBar(this);

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
            view.setTextSize(15);
            view.setPadding(10, 0, 10, 0);
            return view;
        }

    }

}
*/