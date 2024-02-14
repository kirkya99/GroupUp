package de.rwu.group_up.ui.main_screen.my_groups.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentMyGroupsBinding;
import de.rwu.group_up.ui.main_screen.group.create.CreateGroupFragment;

public class MyGroupsFragment extends Fragment {

    private FragmentMyGroupsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyGroupsViewModel dashboardViewModel =
                new ViewModelProvider(this).get(MyGroupsViewModel.class);

        binding = FragmentMyGroupsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        this.binding.fabNewGroup.setOnClickListener(v ->navigateToGroupCreation());

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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}