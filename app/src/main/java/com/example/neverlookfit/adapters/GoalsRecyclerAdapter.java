package com.example.neverlookfit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.TextView;

import com.example.neverlookfit.R;
import com.example.neverlookfit.model.Goal;

import java.util.List;

public class GoalsRecyclerAdapter extends RecyclerView.Adapter<GoalsRecyclerAdapter.UserViewHolder> {

    private List<Goal> listGoals;

    public GoalsRecyclerAdapter(List<Goal> listGoals) {
        this.listGoals = listGoals;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goal_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewDate.setText(listGoals.get(position).getDate());
        holder.textViewWeight.setText(String.valueOf(listGoals.get(position).getWeight()));
        holder.textViewBodyFat.setText(String.valueOf(listGoals.get(position).getBodyFat()));
        holder.textViewThigh.setText(String.valueOf(listGoals.get(position).getThigh()));
        holder.textViewChest.setText(String.valueOf(listGoals.get(position).getChest()));
        holder.textViewBiceps.setText(String.valueOf(listGoals.get(position).getBicep()));
        holder.textViewHip.setText(String.valueOf(listGoals.get(position).getHip()));

    }

    @Override
    public int getItemCount() {
        Log.v(GoalsRecyclerAdapter.class.getSimpleName(),""+listGoals.size());
        return listGoals.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewDate;
        public TextView textViewWeight;
        public TextView textViewBodyFat;
        public TextView textViewThigh;
        public TextView textViewChest;
        public TextView textViewBiceps;
        public TextView textViewHip;

        public UserViewHolder(View view) {
            super(view);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewWeight = view.findViewById(R.id.textViewWeight);
            textViewBodyFat = view.findViewById(R.id.textViewBodyFat);
            textViewThigh = view.findViewById(R.id.textViewThigh);
            textViewChest = view.findViewById(R.id.textViewChest);
            textViewBiceps = view.findViewById(R.id.textViewBiceps);
            textViewHip = view.findViewById(R.id.textViewHip);

        }
    }

}
