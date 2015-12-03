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

public class HelpFinal extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText;

    String TITLES[] = {"Overview", "Profile", "Social", "Ranking", "About", "Help"};

    String NAME = "Connor Myers";
    String LOCATION = "Fresno, CA";


    private String[] helpData, helpFinal;
    private String helpAnswer, helpTitle;
    private TextView textView, titleView;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_final);


        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setNavigationIcon(R.mipmap.back_button_nav);

        toolbarText = (TextView) toolbar.findViewById(R.id.toolbar_text);

        toolbarText.setText("LEARN MORE");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        extras = getIntent().getExtras();

        helpTitle = extras.getString("accountAnswerTitle");
        helpData = extras.getStringArray("accountData");
        helpFinal = extras.getStringArray("accountFinal");
        helpAnswer = extras.getString("accountAnswer");

        textView = (TextView) findViewById(R.id.help_final_title);
        textView.setText(helpTitle);

        titleView = (TextView) findViewById(R.id.help_final_text);
        titleView.setText(helpAnswer);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpFinal.this, HelpExpanded.class);
                intent.putExtra("accountData", helpData);
                intent.putExtra("accountFinal", helpFinal);
                finish();
            }
        });

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