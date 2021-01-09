package com.example.activitymanager.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activitymanager.Entities.Activity;
import com.example.activitymanager.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter<ActivityRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Activity> mActivityDescriptions;
    private Context mContext;

    public ActivityRecyclerViewAdapter(ArrayList<Activity> mActivityDescriptions, Context mContext) {
        this.mActivityDescriptions = mActivityDescriptions;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.activityDescriptionTextView.setText(mActivityDescriptions.get(position).activityText);
        holder.activityTimeTextView.setText(mActivityDescriptions.get(position).activityTime);
    }

    @Override
    public int getItemCount() {
        return mActivityDescriptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView activityDescriptionTextView;
        TextView activityTimeTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            activityDescriptionTextView = itemView.findViewById(R.id.activityDescription_TextView);
            activityTimeTextView = itemView.findViewById(R.id.activityTime_TextView);
        }
    }
}
