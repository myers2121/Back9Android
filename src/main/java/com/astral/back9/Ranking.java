package com.astral.back9;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Ranking extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText;

    //NavDrawer List Values (to be populated):
    String TITLES[] = {"Overview", "Profile", "Ranking", "About", "Help"};
    String NAME = "Connor Myers";
    String LOCATION = "Fresno, CA";

    //Initialization of RecyclerView for NavDrawer:
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;

    //Rankings List Values (to be populated):
    String[] names = {"Rahul Nunna", "Connor Myers", "Ernesto Perez", "Trevor Munday", "Cory Nelson"};
    String[] locations = {"Fresno, CA", "Clovis, CA", "San Francisco, CA", "Visalia, CA", "New York, NY"};
    int[] profilePics = {R.drawable.redranger, R.drawable.blueranger, R.drawable.greenranger,
            R.drawable.blackranger, R.drawable.yellowranger};

    //Initialization of RecyclerView for Ranking:
    RecyclerView rankRecyclerView;
    RecyclerView.Adapter rankAdapter;
    RecyclerView.LayoutManager rankLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setNavigationIcon(R.mipmap.back_button_nav);
        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);
        toolbarText.setText("RANKING");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NavDrawerAdapter(TITLES,NAME,LOCATION,this);
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setAdapter(mAdapter);                             // Setting the adapter to RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager

        rankRecyclerView = (RecyclerView) findViewById(R.id.rankRecyclerView);
        rankRecyclerView.setHasFixedSize(true);
        rankAdapter = new RankAdapter(names, locations, profilePics);
        rankLayoutManager = new LinearLayoutManager(this);
        rankRecyclerView.setAdapter(rankAdapter);
        rankRecyclerView.setLayoutManager(rankLayoutManager);
        rankRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

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
