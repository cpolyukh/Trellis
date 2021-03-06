package edu.uw.ischool.trellis;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ConversationStarterActivity extends AppCompatActivity {
    MainApp app;
    Spinner dropdown;
    ListView categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_starter);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);

        app = (MainApp) getApplication();

        app.changeStatusBarColor(this);

        LinearLayout conversationStartersLayoutIcon = (LinearLayout) findViewById(R.id.conversationStartersLayoutIcon);
        conversationStartersLayoutIcon.setBackgroundColor(getResources().getColor(R.color.light_gray));

        ImageView icon = (ImageView) conversationStartersLayoutIcon.findViewById(R.id.conversationStartersLayoutIcon_img);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.conversation01));
        app.setupToolBar(ConversationStarterActivity.this);



        dropdown = (Spinner) findViewById(R.id.spinner);
        final String[] categories = ConversationCategory.getConversationStarterTitlesArray();
        final List<String> categoriesList = ConversationCategory.getAllConversationStarterTitles();

        MySpinnerAdapter<String> adapter = new MySpinnerAdapter(app.getBaseContext(),
                android.R.layout.simple_expandable_list_item_1, categoriesList);
        dropdown.setAdapter(adapter);

        final ConversationStarterActivity current = this;

        dropdown.setAdapter(adapter);

        categoryList = (ListView) findViewById(R.id.listView);

        String title = dropdown.getSelectedItem().toString();
        final String[] currentItems = ConversationCategory.getConversationStartersArray(title);

        final ArrayAdapter<String> categoryAdapter = new CustomArrayAdapter(getBaseContext(), currentItems);
        categoryList.setAdapter(categoryAdapter);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String s = dropdown.getItemAtPosition(arg2).toString();
                String[] items;
                if (s.equals("TAP TO SELECT CATEGORY")) {
                    items = ConversationCategory.getRandomConversationStarters(20);
                } else {
                    items = ConversationCategory.getConversationStartersArray(s);
                }
                ArrayAdapter<String> newCategoryAdapter = new CustomArrayAdapter(getBaseContext(), items);
                categoryList.setAdapter(newCategoryAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        final Context currentContext = this.getBaseContext();


        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,int position, long id)
        {
            String selectedFromList = (categoryList.getItemAtPosition(position).toString());

            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("copied text", selectedFromList);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(ConversationStarterActivity.this,
                    "Copied to clipboard", Toast.LENGTH_LONG).show();
        }});




    }

    public class CustomArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public CustomArrayAdapter(Context context, String[] values) {
            super(context, android.R.layout.simple_list_item_1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView rowView = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
            rowView.setText(values[position]);
            rowView.setTypeface(myTypeface);

            return rowView;

        }
    }



    private static class MySpinnerAdapter<String> extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "Futura.ttc");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            view.setTextColor(Color.parseColor("#fafafa"));
            view.setBackgroundColor(Color.parseColor("#647753"));
            view.setTextSize(15);
            view.setPadding(100, 0, 20, 0);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            view.setBackgroundColor(Color.parseColor("#647753"));
            view.setTextColor(Color.parseColor("#fafafa"));
            view.setTextSize(15);
            view.setPadding(100, 0, 20, 0);
            return view;
        }
    }
}
