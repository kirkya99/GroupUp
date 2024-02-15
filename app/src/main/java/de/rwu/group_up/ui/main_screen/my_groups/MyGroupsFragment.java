package de.rwu.group_up.ui.main_screen.my_groups;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.rwu.group_up.R;
import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.GroupDatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.GroupAdapter;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.databinding.FragmentMyGroupsBinding;
import de.rwu.group_up.ui.main_screen.group.create.CreateGroupFragment;

public class MyGroupsFragment extends Fragment {

    private FragmentMyGroupsBinding binding;
    private MyGroupsViewModel myGroupsViewModel;
    private GroupAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        Log.d("AllGroupsList", "onResume wurde aufgerufen");

        // Call database here

        this.readGroupEntries();
        this.myGroupsViewModel.getMMyGroups().observe(getViewLifecycleOwner(), groups -> adapter.updateData(groups));
        this.displayMyGroups();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.myGroupsViewModel = new ViewModelProvider(this).get(MyGroupsViewModel.class);

        binding = FragmentMyGroupsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        this.binding.fabNewGroup.setOnClickListener(v -> navigateToGroupCreation());


        this.readGroupEntries();
        this.myGroupsViewModel.getMMyGroups().observe(getViewLifecycleOwner(), groups -> adapter.updateData(groups));
        this.displayMyGroups();

        setHasOptionsMenu(true);

        return root;
    }

    private void navigateToGroupCreation() {
        CreateGroupFragment createGroupFragment = new CreateGroupFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, createGroupFragment, "createGroupFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.groups_menu, menu);

        MenuItem searchItem = menu.findItem((R.id.search_group));
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void readGroupEntries() {
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.readUserEntry(new UserDatabaseController.OnReadUserEntryListener() {
            @Override
            public void onSuccess(User user) {
                myGroupsViewModel.setUserGroups(user.getMyGroups());
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });

        GroupDatabaseController groupDatabaseController = new DatabaseController();
        groupDatabaseController.readGroupEntries(new GroupDatabaseController.GroupsReadListener() {
            @Override
            public void onSuccess(ArrayList<Group> groupsList) {
                ArrayList<Group> filteredGroup = new ArrayList<>();
                for (Group group : groupsList) {
                    if (myGroupsViewModel.getUserGroups().containsKey(group.getGroupName())) {
                        filteredGroup.add(group);
                    }
                }

                myGroupsViewModel.setMMyGroups(filteredGroup);
                Log.d("AllGroupsList", "onSuccess was called with the following data: " + filteredGroup);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void displayMyGroups() {
        RecyclerView recyclerView = binding.rvMyGroups;
        ArrayList<Group> myGroups = myGroupsViewModel.getMMyGroups().getValue();
        if (myGroups != null) {
            adapter = new GroupAdapter(myGroups, getParentFragmentManager());
        } else {
            adapter = new GroupAdapter(new ArrayList<>(), getParentFragmentManager());
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}