package edu.uw.ischool.trellis;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ConversationStarterActivity extends AppCompatActivity {
    MainApp app;
    Spinner dropdown;
    ListView categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_starter);

        //app = (MainApp) getApplication();

        dropdown = (Spinner) findViewById(R.id.spinner);

        final String[] categories = {"Most Frequently Used", "Most Popular", "Recommended", "Academics", "Family", "Friends", "Romantic Relationships", "Current Events"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, categories);

        dropdown.setAdapter(adapter);

        categoryList = (ListView) findViewById(R.id.listView);

        final String[] currentItems = {"How are your classes going?", "What have you done today?",
                "Tell me about how this past week has been. What has been the most rewarding part?",
                "What's your favorite food?", "What's your favorite kind of animal?",
                "Have you been watching the recent campaign elections? What are your thoughs?",
                "What do you do for fun?", "Do you have any pets?"};

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currentItems);
        categoryList.setAdapter(categoryAdapter);
    }

}
