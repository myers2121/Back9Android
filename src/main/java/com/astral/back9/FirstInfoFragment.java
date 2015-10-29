package com.astral.back9;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * A simple {@link Fragment} subclass.
 */
public class FirstInfoFragment extends Fragment {

    private TextView title, description;

    private ImageView icon, circles;

    private String titleString, descriptionString;
    private int iconTemp;
    private int position;

    public FirstInfoFragment(int icon, String title, String description, int _position) {

        // Required empty public constructor

        titleString = title;
        descriptionString = description;
        iconTemp = icon;
        position = _position;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_info, container, false);

        icon = (ImageView) v.findViewById(R.id.first_fragment_image);
        title = (TextView) v.findViewById(R.id.first_fragment_title);
        description = (TextView) v.findViewById(R.id.first_fragment_description);
        circles = (ImageView) v.findViewById(R.id.circles);

        icon.setImageResource(iconTemp);
        title.setText(titleString);
        description.setText(descriptionString);

        if (position == 0) {
            circles.setImageResource(R.mipmap.circles1);
        } else if (position == 1) {
            circles.setImageResource(R.mipmap.circles2);
        } else if (position == 2) {
            circles.setImageResource(R.mipmap.circles3);
        } else if (position == 3) {
            circles.setImageResource(R.mipmap.circles4);
        } else if (position == 4) {
            circles.setImageResource(R.mipmap.circles5);
        } else if (position == 5) {
            circles.setImageResource(R.mipmap.circles6);
        }

        return v;
    }


}
