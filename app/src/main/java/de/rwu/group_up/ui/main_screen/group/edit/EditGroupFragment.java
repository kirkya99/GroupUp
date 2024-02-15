package de.rwu.group_up.ui.main_screen.group.edit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.GroupDatabaseController;
import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.IGroupModifiable;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.databinding.FragmentGroupEditBinding;
import de.rwu.group_up.databinding.FragmentUserProfileEditBinding;
import de.rwu.group_up.ui.main_screen.user_profile.edit.UserProfileEditViewModel;

public class EditGroupFragment extends Fragment {

    private FragmentGroupEditBinding binding;
    private EditGroupViewModel editGroupViewModel;

    private void readCurrentGroupEntry() {
        GroupDatabaseController groupDatabaseController = new DatabaseController();

        groupDatabaseController.readGroupEntry(editGroupViewModel.getGroupName(), new GroupDatabaseController.GroupReadListener() {
            @Override
            public void onSuccess(Group group) {
                editGroupViewModel.setGroup(group);
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.readCurrentGroupEntry();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentGroupEditBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();
        this.editGroupViewModel = new ViewModelProvider(this).get(EditGroupViewModel.class);

        requireActivity().setTitle("Group Edit");

        this.editGroupViewModel.getGroupModifiableLiveData().observe(getViewLifecycleOwner(), iGroupModifiable -> {

            updateGroupName(iGroupModifiable);
            updateGroupDescription(iGroupModifiable);
            updateGroupLocation(iGroupModifiable);
            updateGroupInterests(iGroupModifiable);
            handleButtons(iGroupModifiable);
        });

        return root;
    }

    private void updateGroupName(IGroupModifiable groupModifiable) {
        binding.inputNameGroupEdit.setText(groupModifiable.getGroupName());

        binding.inputNameGroupEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editGroupViewModel.setGroupName(s.toString());
            }
        });
    }

    private void updateGroupDescription(IGroupModifiable groupModifiable) {
        binding.inputDescriptionCreate.setText(groupModifiable.getGroupDescription());

        binding.inputDescriptionCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editGroupViewModel.setGroupDescription(s.toString());
            }
        });
    }

    private void updateGroupLocation(IGroupModifiable groupModifiable) {
        binding.inputLocationEdit.setText(groupModifiable.getGroupDescription());

        binding.inputLocationEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editGroupViewModel.setGroupLocation(s.toString());
            }
        });
    }

    private void updateGroupInterests(IGroupModifiable GroupModifiable) {
        HashMap<String, Boolean> interestsMap = GroupModifiable.getInterestsMap();
        for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
            Chip interestChip = new Chip(getActivity());
            interestChip.setText(interest.getKey());
            interestChip.setCheckable(true);
            interestChip.setChecked(interest.getValue());

            interestChip.setOnCheckedChangeListener((buttonView, isChecked) -> GroupModifiable.setInterestsMapItem(interest.getKey(), isChecked));

            binding.chipInterestsGroupEdit.addView(interestChip);
        }
    }

    private void handleButtons(IGroupModifiable iGroupModifiable) {
        this.binding.buttonSaveEditGroup.setOnClickListener(v -> {
            binding.buttonSaveEditGroup.setEnabled(false);
            GroupDatabaseController groupDatabaseController = new DatabaseController();
            if (iGroupModifiable.getGroupName().equals(editGroupViewModel.getGroup().getGroupName())) {
                updateOnlyGroupContent(groupDatabaseController);
            } else {
                updateGroupWithIdentifier(iGroupModifiable, groupDatabaseController);
            }


        });

        this.binding.buttonCancelEditGroup.setOnClickListener(v -> {
            Snackbar.make(requireView(), "Cancelled group creation!", Snackbar.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void updateOnlyGroupContent(GroupDatabaseController groupDatabaseController) {
        groupDatabaseController.updateGroupEntry(Group.toHashMap((Group) editGroupViewModel.getGroup()), editGroupViewModel.getGroupName(), new GroupDatabaseController.GroupWriteListener() {
            @Override
            public void onSuccess(String message) {
                Snackbar.make(requireView(), "Group successfully updated!", Snackbar.LENGTH_SHORT).show();
                binding.buttonSaveEditGroup.setEnabled(true);
                requireActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
                binding.buttonSaveEditGroup.setEnabled(true);
            }
        });
    }

    private void updateGroupWithIdentifier(IGroupModifiable iGroupModifiable, GroupDatabaseController groupDatabaseController) {
        groupDatabaseController.deleteGroupEntry(iGroupModifiable.getGroupName(), new GroupDatabaseController.GroupWriteListener() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
            }
        });

        groupDatabaseController.createGroupEntry(Group.toHashMap((Group) editGroupViewModel.getGroup()), editGroupViewModel.getGroup().getGroupName(), new GroupDatabaseController.GroupWriteListener() {
            @Override
            public void onSuccess(String message) {
                Snackbar.make(requireView(), "Group successfully updated with identifier!", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
