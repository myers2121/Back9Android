package com.astral.back9;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    // Creating variables up top need to be private to keep access only to this class.

    private Toolbar toolbar;
    private TextView toolbarText;

    String TITLES[] = {"Overview", "Profile", "Social", "Ranking", "About", "Help"};

    String NAME = "Connor Myers";
    String LOCATION = "Fresno, CA";

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(((Back9Application) getApplication()).getFirstRun()){
            //This is first run
            ((Back9Application) getApplication()).setRunned();

            // your code for first run goes here
            Intent intent = new Intent(MainActivity.this, FirstLoadActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else{
            // this is the case other than first run
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser == null) {
                // do stuff with the user
                Intent intent = new Intent(MainActivity.this, FirstScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                setContentView(R.layout.activity_main);


                // Creates and sets up the action bar at the top of the overview screen. This is the same of every screen.

                toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
                toolbar.setTitle("");

                toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

                toolbarText.setText("Overview");
                setSupportActionBar(toolbar);

                mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

                mRecyclerView.setHasFixedSize(true);  // Letting the system know that the list objects are of fixed size

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
        }

        // This is checking to see if the user is logged in or not. If they user isnt logged in then they are going to be sent
        // to the sign in screen.



    }          // Finally we set the drawer toggle sync State


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            ParseUser.logOutInBackground();
            Intent intent = new Intent(MainActivity.this, FirstScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_start_round) {
            Intent intent = new Intent(MainActivity.this, StartRound.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
