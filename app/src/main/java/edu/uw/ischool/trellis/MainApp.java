package edu.uw.ischool.trellis;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by iguest on 4/17/16.
 */
public class MainApp extends Application {
    private static MainApp instance = null;
    private static User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public MainApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create more than one MainApp");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            InputStream conversationStarterStream = getAssets().open("ConversationStarters.json");

            JSONArray ingredientsJSON = new JSONArray(readJSONFile(conversationStarterStream));

            // adds all ingredients to a list
            for (int i = 0; i < ingredientsJSON.length(); i++) {
                JSONObject categoryObj = ingredientsJSON.getJSONObject(i);
                ConversationCategory category = new ConversationCategory(categoryObj);
            }
        } catch (Exception er) {
            Log.e("Access Assets", "Error Message: " + er.toString());
        }
    }

    @TargetApi(21)
    public void changeStatusBarColor(Activity activity) {
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.status_bar));

    }

    // reads given InputStream of JSON file and returns it in string format
    private String readJSONFile(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }

    public void setupToolBar(final Activity activity) {
        LinearLayout friendsIcon = (LinearLayout) activity.findViewById(R.id.friendsLayoutIcon);
        LinearLayout messagesIcon = (LinearLayout) activity.findViewById(R.id.messagesLayoutIcon);
        LinearLayout conversationIcon = (LinearLayout) activity.findViewById(R.id.conversationStartersLayoutIcon);
        LinearLayout learnMoreIcon = (LinearLayout) activity.findViewById(R.id.learnMoreLayoutIcon);
        LinearLayout profileIcon = (LinearLayout) activity.findViewById(R.id.profileLayoutIcon);


        friendsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(activity, SupportActivity.class);
                next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(next);
            }
        });

        messagesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(activity, MessagesActivity.class);
                next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(activity, ConversationStarterActivity.class);
                next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(next);
            }
        });

        learnMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(activity, LearnMoreActivity.class);
                next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(next);
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(activity, EditProfileActivity.class);
                next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(next);
            }
        });
    }
}
