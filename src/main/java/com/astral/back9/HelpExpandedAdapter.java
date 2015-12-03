package com.astral.back9;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class HelpExpandedAdapter extends RecyclerView.Adapter<HelpExpandedAdapter.HelpViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private static String expandedQuestions[];
    private static String finalAnswers[];
    Context context;


    public static class HelpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;

        TextView textView;
        Context contxt;

        public HelpViewHolder(View itemView,int ViewType,Context c) {
            super(itemView);
            contxt = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.help_expanded_rowText);
                Holderid = 1;
            }


        }

       @Override
        public void onClick(View v) {
            int position = getPosition();
            int textPosition;
            int titlePosition;

           if (position==0) {
               titlePosition = position;
               textPosition=titlePosition+1;
           }
           else if(position==1) {
               textPosition=position+2;
               titlePosition=position+1;
           }
           else {
               textPosition=position+3;
               titlePosition = position+2;
           }



            Intent intent = new Intent (contxt, HelpFinal.class);
            intent.putExtra("accountData",expandedQuestions);
            intent.putExtra("accountFinal", finalAnswers);
            intent.putExtra("accountAnswerTitle",finalAnswers[titlePosition]);
            intent.putExtra("accountAnswer",finalAnswers[textPosition]);
            contxt.startActivity(intent);


        }
    }

    HelpExpandedAdapter(String[] ExpandedQuestions,String[] FinalAnswers, Context passedContext){
        expandedQuestions = ExpandedQuestions;
        finalAnswers = FinalAnswers;

        this.context = passedContext;

    }


    @Override
    public HelpExpandedAdapter.HelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_help_item_row, parent, false);

        HelpViewHolder vhItem = new HelpViewHolder(v, viewType, context);

        return vhItem;

    }

    @Override
    public void onBindViewHolder(HelpExpandedAdapter.HelpViewHolder holder, int position) {
        if(holder.Holderid == 1) {

            holder.textView.setText(expandedQuestions[position]);

        }

    }

    @Override
    public int getItemCount() {
        return expandedQuestions.length;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

}