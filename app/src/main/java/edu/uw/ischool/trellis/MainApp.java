package edu.uw.ischool.trellis;

import android.app.Application;

/**
 * Created by iguest on 4/17/16.
 */
public class MainApp extends Application {
    private static MainApp instance = null;

    public MainApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create more than one MainApp");
        }
    }
}
