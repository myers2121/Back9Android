package com.astral.back9;

import android.content.Intent;
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
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class StartRound extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText, courseParTextView, courseHoleNumber, courseAverageScore, courseNameTextView, courseLocationTextView;

    private String coursePar, courseTotalHoles, courseUserAverage, tempLongitude = "36.8538519", courseName, courseLocation;
    List<Double> holeLocations;

    private Button startRoundButton;

    private Course totalCurrentCourse = new Course();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_round);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setTitle("");

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

        toolbar.setNavigationIcon(R.mipmap.back_button_nav);

        toolbarText.setText("COURSE");
        setSupportActionBar(toolbar);

        startRoundButton = (Button) findViewById(R.id.start_round);

        courseNameTextView = (TextView) findViewById(R.id.course_name);
        courseLocationTextView = (TextView) findViewById(R.id.course_location);
        courseParTextView = (TextView) findViewById(R.id.course_par);
        courseHoleNumber = (TextView) findViewById(R.id.course_total_holes);
        courseAverageScore = (TextView) findViewById(R.id.course_average_score);

        getCurrentCourse(tempLongitude);

        startRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartRound.this, CurrentRound.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("courseObject", new Gson().toJson(totalCurrentCourse));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_round, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getCurrentCourse(String longitude) {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Courses");
        query.whereEqualTo("longitude", longitude);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                } else {

                    coursePar = object.getString("parScore");
                    courseTotalHoles = object.getString("totalHoles");
                    courseName = object.getString("courseName");
                    courseLocation = object.getString("location");

                    holeLocations = object.getList("holeLocations");

                    Course currentCourse = new Course(courseName, courseLocation, coursePar, "72", courseTotalHoles, holeLocations);
                    totalCurrentCourse = currentCourse;

                    courseNameTextView.setText(currentCourse.getCourseName());
                    courseLocationTextView.setText(currentCourse.getCourseLocation());
                    courseParTextView.setText(currentCourse.getCoursePar());
                    courseHoleNumber.setText(currentCourse.getHoleCount());
                    courseAverageScore.setText(currentCourse.getCourseAverage());
                }
            }
        });
    }
}
