package edu.uw.ischool.trellis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class EditProfileActivity extends AppCompatActivity {
    ListView supportSkillList;
    ListView conversationTopicList;
    MainApp mainApp;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
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
