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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import de.rwu.group_up.ui.main_screen.group.create.CreateGroupFragment;
import de.rwu.group_up.ui.main_screen.group.details.DetailsGroupFragment;
import de.rwu.group_up.ui.main_screen.group.details.DetailsGroupViewModel;
import de.rwu.group_up.utils.GroupManager;

public class AllGroupsFragment extends Fragment {
    private FragmentAllGroupsBinding binding;
    private AllGroupsViewModel allGroupsViewModel;
    private ArrayList<Group> allGroups = new ArrayList<>();
    private GroupAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        Log.d("AllGroupsList", "onResume wurde aufgerufen");

        // Call database here
        this.readGroupEntries();
        this.displayAllGroups();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Call database here
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AllGroupsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(AllGroupsViewModel.class);

        binding = FragmentAllGroupsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GroupManager.getInstance().setName("test4");

        binding.button.setOnClickListener(v -> navigateToDetailsView());

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

    private void navigateToDetailsView(){
        DetailsGroupFragment detailsGroupFragment = new DetailsGroupFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, detailsGroupFragment, "detailsGroupFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void readGroupEntries() {
        GroupDatabaseController groupDatabaseController = new DatabaseController();
        groupDatabaseController.readGroupEntries(new GroupDatabaseController.GroupsReadListener() {
            @Override
            public void onSuccess(ArrayList<Group> groupsList) {
                allGroups = groupsList;
                Log.d("AllGroupsList", "onSuccess wurde aufgerufen mit Daten: " + allGroups.toString());
                adapter.updateData(allGroups);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void displayAllGroups(){
        RecyclerView recyclerView = binding.rvAllGroups;
        adapter = new GroupAdapter(allGroups);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
