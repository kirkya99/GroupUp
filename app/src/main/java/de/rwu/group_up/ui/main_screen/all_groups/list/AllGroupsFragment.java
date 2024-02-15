package de.rwu.group_up.ui.main_screen.all_groups.list;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.rwu.group_up.R;

import java.util.ArrayList;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.GroupDatabaseController;
import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.GroupAdapter;
import de.rwu.group_up.databinding.FragmentAllGroupsBinding;

public class AllGroupsFragment extends Fragment {
    private FragmentAllGroupsBinding binding;
    private AllGroupsViewModel allGroupsViewModel;
    private GroupAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        Log.d("AllGroupsList", "onResume wurde aufgerufen");

        // Call database here
        this.readGroupEntries();
        this.allGroupsViewModel.getMAllGroups().observe(getViewLifecycleOwner(), groups -> adapter.updateData(groups));
        this.displayAllGroups();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllGroupsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.allGroupsViewModel = new ViewModelProvider(this).get(AllGroupsViewModel.class);


        this.readGroupEntries();
        this.allGroupsViewModel.getMAllGroups().observe(getViewLifecycleOwner(), groups -> adapter.updateData(groups));
        this.displayAllGroups();

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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

    private void readGroupEntries() {
        GroupDatabaseController groupDatabaseController = new DatabaseController();
        groupDatabaseController.readGroupEntries(new GroupDatabaseController.GroupsReadListener() {
            @Override
            public void onSuccess(ArrayList<Group> groupsList) {
                allGroupsViewModel.setMAllGroups(groupsList);
                Log.d("AllGroupsList", "onSuccess was called with the following data: " + groupsList.toString());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void displayAllGroups() {
        RecyclerView recyclerView = binding.rvAllGroups;
        ArrayList<Group> myGroups = allGroupsViewModel.getMAllGroups().getValue();
        if (myGroups != null) {
            adapter = new GroupAdapter(myGroups, getParentFragmentManager());
        } else {
            adapter = new GroupAdapter(new ArrayList<>(), getParentFragmentManager());
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
