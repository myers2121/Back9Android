package com.astral.back9;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hp1 on 28-12-2014.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the viaew under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[] = {R.mipmap.profile, R.mipmap.social, R.mipmap.ranking, R.mipmap.about, R.mipmap.help};       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email
    Context context;




    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;

        TextView textView;
        ImageView imageView;
        CircleImageView profile;
        TextView Name;
        TextView email;
        Context contxt;


        public ViewHolder(View itemView,int ViewType,Context c) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            contxt = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created



            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            } else {

                profile = (CircleImageView) itemView.findViewById(R.id.profile_image);
                Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }



        }


        @Override
        public void onClick(View v) {

            int position = getPosition();

            if (position == 1) {

                Intent helpIntent = new Intent((Activity) contxt, Profile.class);
                contxt.startActivity(helpIntent);

            } else if (position == 2) {

                Intent settingsIntent = new Intent((Activity) contxt, Social.class);
                contxt.startActivity(settingsIntent);

            } else if (position == 3) {
                Intent helpIntent = new Intent((Activity) contxt, Ranking.class);
                contxt.startActivity(helpIntent);

            } else if (position == 4) {

                Intent settingsIntent = new Intent((Activity) contxt, About.class);
                contxt.startActivity(settingsIntent);

            } else if (position == 5) {

                Intent settingsIntent = new Intent((Activity) contxt, Help.class);
                contxt.startActivity(settingsIntent);

            }

        }
    }



    NavDrawerAdapter(String Titles[],String Name,String Email,Context passedContext){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mNavTitles = Titles;                //have seen earlier

        name = Name;
        email = Email;
        //here we assign those passed values to the values we declared here
        this.context = passedContext;

        //in adapter



    }



    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public NavDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v,viewType,context); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v,viewType,context); //Creating ViewHolder and passing the object of type view

            return vhHeader; //returning the object created


        }
        return null;

    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(NavDrawerAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {
//            Typeface robotoCondLight = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");// as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position - 1]);
            //holder.textView.setTypeface(robotoCondLight);
        }
        else{
            // Similarly we set the resources for header view


            holder.Name.setText(name);
            holder.email.setText(email);
            holder.profile.setImageResource(R.mipmap.fresnostate);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
    }


    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
