package com.astral.back9;

import android.app.Application;

import com.parse.Parse;


/**
 * Created by connormyers on 9/24/15.
 */
public class Back9Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "WscP7f2mNuWe6tvVP2as4wz8nlsklFm6FTmABYCw", "NZTB7sOOJp6kYwGKlmQ9xad9hQdxTcoIuwsCR7dW");

    }

}
