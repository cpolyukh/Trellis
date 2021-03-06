package edu.uw.ischool.trellis;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import edu.uw.ischool.trellis.UI.ToolbarView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;

public class SupportSelectionActivity extends AppCompatActivity implements Serializable{

    Button seekSupportBtn;
    Button giveSupportBtn;
    MainApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_selection);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);

        seekSupportBtn = (Button) findViewById(R.id.seekSupportBtn);
        giveSupportBtn = (Button) findViewById(R.id.giveSupportBtn);

        app = (MainApp) getApplication();
        app.changeStatusBarColor(this);

        seekSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportSelectionActivity.this, LearnMoreActivity.class);
                startActivity(next);

            }
        });

        giveSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportSelectionActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
        Typeface myTypefaceBold = Typeface.createFromAsset(getAssets(), "Futura_Bold.ttf");

        TextView textView1 = (TextView) findViewById(R.id.welcome);
        TextView textView2 = (TextView) findViewById(R.id.welcomemessage);

        textView1.setTypeface(myTypeface);
        textView2.setTypeface(myTypeface);
        seekSupportBtn.setTypeface(myTypefaceBold);
        giveSupportBtn.setTypeface(myTypefaceBold);

    }

}
