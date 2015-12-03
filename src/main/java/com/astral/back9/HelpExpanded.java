/*package com.astral.back9;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HelpExpanded extends AppCompatActivity {


    private String[] helpData, helpFinal;
    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_expanded);

        extras = getIntent().getExtras();

        helpData = extras.getStringArray("accountData");
        helpFinal = extras.getStringArray("accountFinal");

        RecyclerView helpExpandedLayoutList;
        RecyclerView.Adapter helpAdapter;
        RecyclerView.LayoutManager helpExpandedLayoutManager;

        helpExpandedLayoutList = (RecyclerView) findViewById(R.id.help_recycler);
        helpExpandedLayoutList.setHasFixedSize(true);

        helpAdapter= new HelpExpandedAdapter (helpData, helpFinal, this);
        helpExpandedLayoutList.setAdapter(helpAdapter);


        helpExpandedLayoutManager = new LinearLayoutManager(this);

        helpExpandedLayoutList.setLayoutManager(helpExpandedLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help_expanded, menu);
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


}*/

package com.astral.back9;

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
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.content.Intent;

        import java.util.ArrayList;

public class HelpExpanded extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText;
    private RelativeLayout accountHelp, scoringHelp, whereHelp, rankingHelp;

    String TITLES[] = {"Overview", "Profile", "Social", "Ranking", "About", "Help"};

    String NAME = "Connor Myers";
    String LOCATION = "Fresno, CA";

    RecyclerView helpExpandedLayoutList;
    RecyclerView.Adapter helpAdapter;
    RecyclerView.LayoutManager helpExpandedLayoutManager;

    ActionBarDrawerToggle mDrawerToggle;

    private String[] helpData, helpFinal;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_expanded);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);
        toolbar.setNavigationIcon(R.mipmap.back_button_nav);
        toolbarText.setText("HOW CAN WE HELP?");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        extras = getIntent().getExtras();

        helpData = extras.getStringArray("accountData");
        helpFinal = extras.getStringArray("accountFinal");

        helpExpandedLayoutList = (RecyclerView) findViewById(R.id.help_recycler);
        helpExpandedLayoutList.setHasFixedSize(true);

        helpAdapter= new HelpExpandedAdapter (helpData, helpFinal, this);
        helpExpandedLayoutList.setAdapter(helpAdapter);


        helpExpandedLayoutManager = new LinearLayoutManager(this);

        helpExpandedLayoutList.setLayoutManager(helpExpandedLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
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