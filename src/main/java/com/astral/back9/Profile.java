package com.astral.back9;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText;

    String TITLES[] = {"Overview", "Profile", "Ranking", "About", "Help"};

    String NAME = "Connor Myers";
    String LOCATION = "Fresno, CA";

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;



        Person ernesto = new Person("Ernesto","Fresno");
        Person connor = new Person("Connor","Fresno");
        Person rahul = new  Person("Rahul","Fresno");
        Person trevor = new Person("Trevor","Fresno");
        Person cory = new Person("Cory","Fresno");
        Person drake = new Person("Drake","Canada");
        Person meekmill = new Person("Meek Mill","The Hood");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RelativeLayout averageScoreLayout = (RelativeLayout) findViewById(R.id.average_score_layout);
        RelativeLayout friendsLayout = (RelativeLayout) findViewById(R.id.friends_layout);

        averageScoreLayout.setOnClickListener(
                new LinearLayout.OnClickListener() {
                    public void onClick(View v){
                        ArrayList<Person> arrayOfUsers =  new ArrayList<Person>();
                        AverageScoreAdapter adapter = new AverageScoreAdapter(arrayOfUsers,getBaseContext());

                        adapter.add(ernesto);
                        adapter.add(connor);
                        adapter.add(rahul);
                        adapter.add(cory);
                        adapter.add(trevor);
                        adapter.add(drake);
                        adapter.add(meekmill);

                        ListView bottomLayoutLV = (ListView) findViewById(R.id.bottomLayoutListView);
                        bottomLayoutLV.setAdapter(adapter);
                    }
                }
        );

        friendsLayout.setOnClickListener(
                new LinearLayout.OnClickListener(){
                    public void onClick(View view){
                        ArrayList<Person> arrayOfUsers = new ArrayList<Person>();
                        FriendsAdapter fadapter = new FriendsAdapter(arrayOfUsers,getBaseContext());
                        fadapter.add(ernesto);
                        fadapter.add(connor);
                        fadapter.add(rahul);
                        fadapter.add(cory);
                        fadapter.add(trevor);
                        fadapter.add(drake);
                        fadapter.add(meekmill);

                        ListView BottomLayoutLV = (ListView) findViewById(R.id.bottomLayoutListView);
                        BottomLayoutLV.setAdapter(fadapter);

                    }
                }
        );







        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setNavigationIcon(R.mipmap.back_button_nav);

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

        toolbarText.setText("Profile");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);  // Letting the system know that the list objects are of fixed size

        //NAME = currentUser.getString("firstName") + " " + currentUser.getString("lastName");
        //LOCATION = currentUser.getEmail();


        mAdapter = new NavDrawerAdapter(TITLES,NAME,LOCATION,this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture


        mAdapter = new NavDrawerAdapter(TITLES,NAME,LOCATION,this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
}
