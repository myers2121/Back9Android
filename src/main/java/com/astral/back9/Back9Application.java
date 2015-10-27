package com.astral.back9;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.parse.Parse;


/**
 * Created by connormyers on 9/24/15.
 */
public class Back9Application extends Application {

    SharedPreferences mPrefs;

    @Override
    public void onCreate() {
        super.onCreate();


        Context mContext = this.getApplicationContext();
        //0 = mode private. only this app can read these preferences
        mPrefs = mContext.getSharedPreferences("myAppPrefs", 0);


        // the rest of your app initialization code goes here

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "WscP7f2mNuWe6tvVP2as4wz8nlsklFm6FTmABYCw", "NZTB7sOOJp6kYwGKlmQ9xad9hQdxTcoIuwsCR7dW");

    }

    public boolean getFirstRun() {
        return mPrefs.getBoolean("firstRun", true);
    }

    public void setRunned() {
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putBoolean("firstRun", false);
        edit.commit();
    }

}
