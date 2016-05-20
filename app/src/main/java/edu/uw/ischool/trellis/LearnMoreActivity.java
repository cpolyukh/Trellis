package edu.uw.ischool.trellis;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LearnMoreActivity extends AppCompatActivity {
    MainApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
        TextView[] textViews = {(TextView) findViewById(R.id.textView1), (TextView) findViewById(R.id.textView2),
                                (TextView) findViewById(R.id.textView3), (TextView) findViewById(R.id.textView4),
                                (TextView) findViewById(R.id.textView5)};
        for (TextView current : textViews)
        {
            current.setTypeface(myTypeface);
        }


        app = (MainApp) getApplication();
        app.changeStatusBarColor(this);

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
                Intent next = new Intent(LearnMoreActivity.this, SupportActivity.class);
                startActivity(next);
            }
        });

        messagesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(LearnMoreActivity.this, MessagesActivity.class);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(LearnMoreActivity.this, ConversationStarterActivity.class);
                startActivity(next);
            }
        });

        learnMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(LearnMoreActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(LearnMoreActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });
        /********************************************************/
        /********************************************************/
        /********************************************************/





        ImageView article1 = (ImageView) findViewById(R.id.support1);
        ImageView article2 = (ImageView) findViewById(R.id.support2);
        ImageView article3 = (ImageView) findViewById(R.id.support3);
        ImageView article4 = (ImageView) findViewById(R.id.support4);
        ImageView article5 = (ImageView) findViewById(R.id.support5);

        TextView text1 = (TextView) findViewById(R.id.textView1);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        TextView text4 = (TextView) findViewById(R.id.textView4);
        TextView text5 = (TextView) findViewById(R.id.textView5);

        article1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.everydayhealth.com/columns/therese-borchard-sanity-break/ways-to-help-a-friend-or-family-member-with-depression/"));
                startActivity(browserIntent);
            }
        });

        article2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mentalhealthamerica.net/mental-health-screen/patient-health"));
                startActivity(browserIntent);
            }
        });

        article3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.depressiontoolkit.org/news/tips-for-talking-to-your-doctor-about-depression.asp"));
                startActivity(browserIntent);
            }
        });

        article4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/thicken-your-skin/201105/when-your-friend-is-depresseddont-and-dos"));
                startActivity(browserIntent);
            }
        });

        article5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.healthline.com/health-slideshow/9-myths-depression"));
                startActivity(browserIntent);
            }
        });


    }

}
