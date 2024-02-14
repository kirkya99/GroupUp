package de.rwu.group_up.data.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Map;

import de.rwu.group_up.R;
import de.rwu.group_up.data.model.Group;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    private ArrayList<Group> groupsList;

    public GroupAdapter(ArrayList<Group> groupsList) {
        this.groupsList = groupsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Group group = groupsList.get(position);
        String groupName = group.getGroupName();
        holder.groupName.setText(groupName);

        // Clear the ChipGroup before adding new Chips
        holder.chipGroup.removeAllViews();

        // Create a Chip for each interest that is true
        for (Map.Entry<String, Boolean> entry : group.getInterestsMap().entrySet()) {
            if (entry.getValue()) {
                Chip chip = new Chip(holder.chipGroup.getContext());
                chip.setText(entry.getKey());
                holder.chipGroup.addView(chip);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GroupAdapter", "Gruppe angeklickt: " + groupName);
            }
        });
    }


    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    public void updateData(ArrayList<Group> newGroupsList) {
        this.groupsList = newGroupsList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView groupName;
        public ChipGroup chipGroup;

        public MyViewHolder(View view) {
            super(view);
            groupName = view.findViewById(R.id.tvGroupName);
            chipGroup = view.findViewById(R.id.chip_group);
        }
    }
}
