package com.astral.back9;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import de.hdodenhof.circleimageview.CircleImageView;
import android.widget.TextView;

/**
 * Created by Rahul on 11/29/15.
 */
public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankingsViewHolder> {

    private String[] names;
    private String[] locations;
    private int[] profilePics;

    public RankAdapter(String[] mNames, String[] mLocations, int[] mProfilePics) {
        names = mNames;
        locations = mLocations;
        profilePics = mProfilePics;
    }

    public class RankingsViewHolder extends RecyclerView.ViewHolder {

        public TextView mRankLabel;
        public TextView mUserLabel;
        public TextView mLocationLabel;
        public ImageView mProfilePic;

        public RankingsViewHolder(View itemView) {
            super(itemView);
            mRankLabel = (TextView) itemView.findViewById(R.id.rankLabel);
            mUserLabel = (TextView) itemView.findViewById(R.id.userLabel);
            mLocationLabel = (TextView) itemView.findViewById(R.id.locationLabel);
            mProfilePic = (ImageView) itemView.findViewById(R.id.profilePic);

        }

    }

    @Override
    public RankingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank_row, parent, false);
        RankingsViewHolder vh = new RankingsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RankingsViewHolder holder, int position) {
        holder.mRankLabel.setText(String.valueOf(position + 1));
        holder.mUserLabel.setText(names[position]);
        holder.mLocationLabel.setText(locations[position]);
        holder.mProfilePic.setImageResource(profilePics[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }
}
