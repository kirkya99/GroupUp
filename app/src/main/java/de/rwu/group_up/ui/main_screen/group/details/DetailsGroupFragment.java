package de.rwu.group_up.ui.main_screen.group.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.R;
import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.GroupDatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.databinding.FragmentGroupDetailsBinding;
import de.rwu.group_up.ui.main_screen.group.edit.EditGroupFragment;
import de.rwu.group_up.utils.GroupManager;
import de.rwu.group_up.utils.UserManager;

public class DetailsGroupFragment extends Fragment {

    private FragmentGroupDetailsBinding binding;
    private DetailsGroupViewModel detailsGroupViewModel;

    private void readCurrentUserAndGroupEntry() {
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.readUserEntry(new UserDatabaseController.OnReadUserEntryListener() {
            @Override
            public void onSuccess(User user) {
                detailsGroupViewModel.setUser(user);
            }

            @Override
            public void onFailure(String errorMessage) {
                Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_SHORT).show();
            }
        });

        GroupDatabaseController groupDatabaseController = new DatabaseController();
        groupDatabaseController.readGroupEntry(GroupManager.getInstance().getName(), new GroupDatabaseController.GroupReadListener() {
            @Override
            public void onSuccess(Group group) {
                detailsGroupViewModel.setGroup(group);
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentGroupDetailsBinding.inflate(inflater, container, false);
        this.detailsGroupViewModel = new ViewModelProvider(this).get(DetailsGroupViewModel.class);
        View root = this.binding.getRoot();
        this.readCurrentUserAndGroupEntry();

        this.detailsGroupViewModel.getGroup().observe(getViewLifecycleOwner(), iGroupReadable -> {
            binding.displayOwnerNameContent.setText(iGroupReadable.getOwnerName());
            binding.displayGroupNameContent.setText(iGroupReadable.getGroupName());
            binding.displayGroupDescriptionContent.setText(iGroupReadable.getGroupDescription());
            binding.displayGroupLocationContent.setText(iGroupReadable.getLocation());

            this.binding.chipGroupGroupInterestsContent.removeAllViews();
            HashMap<String, Boolean> interestsMap = iGroupReadable.getInterestsMap();
            for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
                if(interest.getValue()) {
                    Chip interestChip = new Chip(getActivity());
                    interestChip.setText(interest.getKey());
                    interestChip.setChecked(interest.getValue());
                    binding.chipGroupGroupInterestsContent.addView(interestChip);
                }
            }

            handleButtonActions();
        });


        return root;
    }


    private void handleButtonActions() {
        this.manageDeleteAddButton();
        this.handleEditGroup();
        this.handleReturnToList();
    }

    private void manageDeleteAddButton(){
        detailsGroupViewModel.getUser().observe(getViewLifecycleOwner(), iUserModifiable -> {
            if(iUserModifiable.getMyGroups().containsKey(GroupManager.getInstance().getName())) {
                binding.buttonRemoveAddGroup.setText("Delete");
            }
            else {
                binding.buttonRemoveAddGroup.setText("Add");
            }

            binding.buttonRemoveAddGroup.setOnClickListener(v -> {
                if(iUserModifiable.getMyGroups().containsKey(GroupManager.getInstance().getName())) {
                    if(iUserModifiable.getMyGroups().get(GroupManager.getInstance().getName()) == true){
                        GroupDatabaseController groupDatabaseController = new DatabaseController();
                        groupDatabaseController.deleteGroupEntry(GroupManager.getInstance().getName(), new GroupDatabaseController.GroupWriteListener() {
                            @Override
                            public void onSuccess(String message) {
                                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(String message) {
                                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }

                    iUserModifiable.deleteMyGroupsItem(GroupManager.getInstance().getName());
                    handleReturnToList();
                } else {
                    iUserModifiable.setMyGroupsItem(GroupManager.getInstance().getName(), false);
                }
            });
        });
    }

    private void handleEditGroup(){
        if(UserManager.getInstance().getUid().equals(GroupManager.getInstance().getName()))
        {
            this.binding.fabEditGroup.setOnClickListener(v -> navigateToGroupEdit());
        } else {
            this.binding.fabEditGroup.setVisibility(View.GONE);
        }
    }

    private void navigateToGroupEdit() {
        EditGroupFragment editGroupFragment = new EditGroupFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, editGroupFragment, "editGroupFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void handleReturnToList(){
        this.binding.buttonReturnGroupDetails.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}
