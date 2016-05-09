package edu.uw.ischool.trellis;

import android.app.Application;
import android.util.Log;

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

    // reads given InputStream of JSON file and returns it in string format
    private String readJSONFile(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }
}
