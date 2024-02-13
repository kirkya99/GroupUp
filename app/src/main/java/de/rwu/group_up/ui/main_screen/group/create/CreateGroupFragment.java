package de.rwu.group_up.ui.main_screen.group.create;

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
import de.rwu.group_up.databinding.FragmentGroupCreationBinding;

public class CreateGroupFragment extends Fragment {

    private FragmentGroupCreationBinding binding;
    private CreateGroupViewModel createGroupViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentGroupCreationBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();
        this.createGroupViewModel = new ViewModelProvider(this).get(CreateGroupViewModel.class);

        requireActivity().setTitle("Group Creation");

        this.setGroupName();
        this.setGroupDescription();
        this.setGroupLocation();
        this.setGroupInterests();
        this.buttonInteractions();

        return root;
    }

    private void buttonInteractions() {
        this.binding.buttonSaveCreateGroup.setOnClickListener(v -> {
            binding.buttonSaveCreateGroup.setEnabled(false);
            GroupDatabaseController groupDatabaseController = new DatabaseController();
            groupDatabaseController.createGroupEntry(Group.toHashMap((Group) createGroupViewModel.getGroup()), createGroupViewModel.getGroup().getGroupName(), new GroupDatabaseController.GroupWriteListener() {
                @Override
                public void onSuccess(String message) {
                    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                    binding.buttonSaveCreateGroup.setEnabled(true);
                }

                @Override
                public void onFailure(String message) {
                    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
                    binding.buttonSaveCreateGroup.setEnabled(true);
                }
            });
        });

        this.binding.buttonCancelCreateGroup.setOnClickListener(v -> {
            Snackbar.make(requireView(), "Cancelled group creation!", Snackbar.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void setGroupLocation() {
        this.binding.inputLocationCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                createGroupViewModel.setLocation(s.toString());
            }
        });
    }

    private void setGroupDescription() {
        this.binding.inputDescriptionCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                createGroupViewModel.setDescription(s.toString());
            }
        });
    }

    private void setGroupName() {
        this.binding.inputNameGroupCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                createGroupViewModel.setGroupName(s.toString());
            }
        });
    }

    private void setGroupInterests() {
        HashMap<String, Boolean> interestsMap = createGroupViewModel.getInterestsMap();
        for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
            Chip interestChip = new Chip(getActivity());
            interestChip.setText(interest.getKey());
            interestChip.setCheckable(true);
            interestChip.setChecked(interest.getValue());

            interestChip.setOnCheckedChangeListener((buttonView, isChecked) -> createGroupViewModel.setInterestsMapItem(interest.getKey(), isChecked));

            binding.chipInterestsGroupCreate.addView(interestChip);
        }
    }
}
