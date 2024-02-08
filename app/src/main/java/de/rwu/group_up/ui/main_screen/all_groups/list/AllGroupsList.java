package de.rwu.group_up.ui.main_screen.all_groups.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import de.rwu.group_up.utils.GroupAdapter;
import de.rwu.group_up.utils.GroupManager;

public class AllGroupsList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    GroupAdapter groupAdapter;
    ArrayList<GroupManager> list;
}
