package de.rwu.group_up.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.rwu.group_up.R;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    Context context;

    ArrayList<GroupManager> list;


    public GroupAdapter(Context context, ArrayList<GroupManager> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new GroupViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        GroupManager group = list.get(position);
        holder.groupName.setText(group.getGroupName());
        holder.tags.setText(group.getTags());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder{

        TextView groupName, tags;


        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.tvGroupName);
            tags = itemView.findViewById(R.id.tvTags);

        }
    }
}
