package com.astral.back9;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrentRound extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView toolbarText, currentRoundScore, frontOfGreen, middleOfGreen, backOfGreen;

    private Course currentCourse;

    private String courseJsonObject;

    private Button score1, score2, score3, score4, score5, score6, score7, score8, score9, score10, nextHoleButton;

    private Integer totalStrokes = 0, currentHole = 1, holeLocationsIterator = 6, currentStrokeForHoleDistance = 0, currentStroke;

    private Boolean isScoreEntered = false;

    private JSONArray holeLocations;

    private Double[] userLocations = {36.851304, -119.838853, 36.852525, -119.839386, 36.853198, -119.839549
            ,36.853566, -119.840673,	36.854878, -119.840343,	36.856193, -119.840223
            ,36.856919, -119.839834,	36.856810, -119.840667,	36.857055, -119.841077
            ,36.856021, -119.842084,	36.854570, -119.842647,	36.853054, -119.843124
            ,36.852925, -119.842598,	36.854928, -119.841870,	36.855780, -119.841696
            ,36.855934, -119.840821,	36.853660, -119.841384,	36.852507, -119.842279
            ,36.852124, -119.843769,	36.851422, -119.843339,	36.850422, -119.842775
            ,36.850422, -119.842775,	36.851745, -119.841675,	36.852795, -119.841068
            ,36.853463, -119.840379,	36.852397, -119.840149,	36.851704, -119.839916
            ,36.851139, -119.838204,	36.852451, -119.838268,	36.853460, -119.838981
            ,36.854078, -119.839796,	36.854704, -119.839640,	36.855043, -119.839441
            ,36.855522, -119.839041,	36.854583, -119.838801,	36.853831, -119.837883
            ,36.853467, -119.837076,	36.851961, -119.837709,	36.851471, -119.836995
            ,36.851724, -119.836556,	36.851235, -119.835899,	36.850434, -119.834431
            ,36.850518, -119.833455,	36.851254, -119.834954,	36.851950, -119.836422
            ,36.853180, -119.836769,	36.852454, -119.835765,	36.851388, -119.834241
            ,36.850793, -119.831862,	36.850413, -119.832146,	36.849788, -119.832420
            ,36.849586, -119.833517,	36.850033, -119.834747,	36.850872, -119.837024
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_round);

        //Bundle extras = getIntent().getExtras();
            courseJsonObject = getIntent().getStringExtra("courseObject");
            Log.e("Tag", courseJsonObject);
        try {
            JSONObject jObject = new JSONObject(courseJsonObject);
            String courseName = jObject.getString("courseName");
            String courseLocation = jObject.getString("courseLocation");
            String coursePar = jObject.getString("coursePar");
            String courseAverage = jObject.getString("courseAverage");
            String courseHoleCount = jObject.getString("holeCount");
            holeLocations = jObject.getJSONArray("holeLocations");

            for (int i = 0; i < holeLocations.length(); i++) {
                Log.e("TAG", String.valueOf(holeLocations.getDouble(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

        toolbarText.setText(getActionBarText());

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        currentRoundScore = (TextView) findViewById(R.id.current_round_score);
        currentRoundScore.setText("0");

        frontOfGreen = (TextView) findViewById(R.id.front_of_green);
        middleOfGreen = (TextView) findViewById(R.id.middle_of_green);
        backOfGreen = (TextView) findViewById(R.id.back_of_green);

        score1 = (Button) findViewById(R.id.score_1_button);
        score1.setOnClickListener(this);
        score2 = (Button) findViewById(R.id.score_2_button);
        score2.setOnClickListener(this);
        score3 = (Button) findViewById(R.id.score_3_button);
        score3.setOnClickListener(this);
        score4 = (Button) findViewById(R.id.score_4_button);
        score4.setOnClickListener(this);
        score5 = (Button) findViewById(R.id.score_5_button);
        score5.setOnClickListener(this);
        score6 = (Button) findViewById(R.id.score_6_button);
        score6.setOnClickListener(this);
        score7 = (Button) findViewById(R.id.score_7_button);
        score7.setOnClickListener(this);
        score8 = (Button) findViewById(R.id.score_8_button);
        score8.setOnClickListener(this);
        score9 = (Button) findViewById(R.id.score_9_button);
        score9.setOnClickListener(this);
        score10 = (Button) findViewById(R.id.score_10_button);
        score10.setOnClickListener(this);

        nextHoleButton = (Button) findViewById(R.id.next_hole_button);
        nextHoleButton.setOnClickListener(this);

        getHoleDistances();

    }

    private void getHoleDistances() {

        Location userLocation = new Location("Current Location");
        userLocation.setLatitude(userLocations[currentStrokeForHoleDistance]);
        userLocation.setLongitude(userLocations[currentStrokeForHoleDistance + 1]);

        Location frontOfHole = new Location("Hole One");
        try {
            frontOfHole.setLatitude(holeLocations.getDouble(holeLocationsIterator - 6));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            frontOfHole.setLongitude(holeLocations.getDouble(holeLocationsIterator - 5));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double distanceToFront = userLocation.distanceTo(frontOfHole);

        Log.e("Tag", String.valueOf(distanceToFront));

        Location middleOfHole = new Location("Hole One");
        try {
            middleOfHole.setLatitude(holeLocations.getDouble(holeLocationsIterator - 4));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            middleOfHole.setLongitude(holeLocations.getDouble(holeLocationsIterator - 3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double distanceToMiddle = userLocation.distanceTo(middleOfHole);


        Log.e("Tag", String.valueOf(distanceToMiddle));

        Location backOfHole = new Location("Hole One");
        try {
            backOfHole.setLatitude(holeLocations.getDouble(holeLocationsIterator - 2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            backOfHole.setLongitude(holeLocations.getDouble(holeLocationsIterator - 1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double distanceToBack = userLocation.distanceTo(backOfHole);


        Log.e("Tag", String.valueOf(distanceToBack));

        frontOfGreen.setText(String.valueOf((int) distanceToFront));
        middleOfGreen.setText(String.valueOf((int) distanceToMiddle));
        backOfGreen.setText(String.valueOf((int) distanceToBack));
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub


        if (v.getId() == score1.getId()) {
            currentStroke = 1;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score2.getId()) {
            currentStroke = 2;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score3.getId()) {
            currentStroke = 3;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score4.getId()) {
            currentStroke = 4;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score5.getId()) {
            currentStroke = 5;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score6.getId()) {
            currentStroke = 6;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score7.getId()) {
            currentStroke = 7;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score8.getId()) {
            currentStroke = 8;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score9.getId()) {
            currentStroke = 9;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button));
        } else if (v.getId() == score10.getId()) {
            currentStroke = 10;
            isScoreEntered = true;

            score1.setBackground(getResources().getDrawable(R.drawable.register_button));
            score2.setBackground(getResources().getDrawable(R.drawable.register_button));
            score3.setBackground(getResources().getDrawable(R.drawable.register_button));
            score4.setBackground(getResources().getDrawable(R.drawable.register_button));
            score5.setBackground(getResources().getDrawable(R.drawable.register_button));
            score6.setBackground(getResources().getDrawable(R.drawable.register_button));
            score7.setBackground(getResources().getDrawable(R.drawable.register_button));
            score8.setBackground(getResources().getDrawable(R.drawable.register_button));
            score9.setBackground(getResources().getDrawable(R.drawable.register_button));
            score10.setBackground(getResources().getDrawable(R.drawable.register_button_clicked));
        } else if (v.getId() == nextHoleButton.getId()) {

            if (currentHole < 18){
                currentHole++;
                toolbarText.setText(getActionBarText());
                totalStrokes = totalStrokes + currentStroke;
                currentRoundScore.setText(String.valueOf(totalStrokes));
                isScoreEntered = false;
                score1.setBackground(getResources().getDrawable(R.drawable.register_button));
                score2.setBackground(getResources().getDrawable(R.drawable.register_button));
                score3.setBackground(getResources().getDrawable(R.drawable.register_button));
                score4.setBackground(getResources().getDrawable(R.drawable.register_button));
                score5.setBackground(getResources().getDrawable(R.drawable.register_button));
                score6.setBackground(getResources().getDrawable(R.drawable.register_button));
                score7.setBackground(getResources().getDrawable(R.drawable.register_button));
                score8.setBackground(getResources().getDrawable(R.drawable.register_button));
                score9.setBackground(getResources().getDrawable(R.drawable.register_button));
                score10.setBackground(getResources().getDrawable(R.drawable.register_button));
                currentStrokeForHoleDistance = holeLocationsIterator;
                holeLocationsIterator = holeLocationsIterator + 6;
                getHoleDistances();

                Toast.makeText(getApplicationContext(), String.valueOf(currentHole),Toast.LENGTH_LONG).show();


            } else {
                ParseUser currentUser = ParseUser.getCurrentUser();
                currentUser.put("averageScore", (currentUser.getInt("averageScore") + totalStrokes) / currentUser.getInt("roundsPlayed"));
                currentUser.put("holesPlayed", currentUser.getInt("holesPlayed") + currentHole);
                currentUser.put("totalStrokes", currentUser.getInt("totalStrokes") + totalStrokes);
                currentUser.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                           // myObjectSavedSuccessfully();
                            Toast.makeText(getApplicationContext(), "Everything was saved successfully", Toast.LENGTH_LONG).show();
                        } else {
                            //myObjectSaveDidNotSucceed();
                        }
                    }
                });

                Intent intent = new Intent(CurrentRound.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_current_round, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cancel_round) {
            Intent intent = new Intent(CurrentRound.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Round has been cancelled", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_refresh_locations) {
            if (currentStrokeForHoleDistance < holeLocationsIterator - 2) {
                currentStrokeForHoleDistance = currentStrokeForHoleDistance + 2;
                Toast.makeText(getApplicationContext(), String.valueOf(currentStrokeForHoleDistance), Toast.LENGTH_LONG).show();
                getHoleDistances();
            } else {
                Toast.makeText(getApplicationContext(), "Please enter score for the hole", Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private String getActionBarText() {
        if (currentHole == 1) {
            return "1ST HOLE";
        } else if (currentHole == 2) {
            return "2ND HOLE";
        } else if (currentHole == 3) {
            return "3RD HOLE";
        } else {
            return String.valueOf(currentHole) + "TH HOLE";
        }
    }
}
