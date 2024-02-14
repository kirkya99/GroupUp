package de.rwu.group_up.ui.main_screen.user_profile.edit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.IGroupReadable;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.databinding.FragmentUserProfileEditBinding;

public class UserProfileEditFragment extends Fragment {

    private FragmentUserProfileEditBinding binding;
    private UserProfileEditViewModel userProfileEditViewModel;
    private RadioButton currentlySelectedGenderOption;

    private void readCurrentUserEntry() {
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.readUserEntry(new UserDatabaseController.OnReadUserEntryListener() {
            @Override
            public void onSuccess(User user) {
                userProfileEditViewModel.setUser(user);
            }

            @Override
            public void onFailure(String errorMessage) {
                Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.readCurrentUserEntry();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        this.binding = FragmentUserProfileEditBinding.inflate(inflater, container, false);
        this.userProfileEditViewModel = new ViewModelProvider(this).get(UserProfileEditViewModel.class);

        View root = this.binding.getRoot();
        requireActivity().setTitle("User Profile Edit");


        this.userProfileEditViewModel.getUserModifiableLiveData().observe(getViewLifecycleOwner(), iUserModifiable -> {
            this.updateUserName(iUserModifiable);
            this.updateUserEmail(iUserModifiable);
            this.updateUserAge(iUserModifiable);
            this.updateUserGender(iUserModifiable);
            this.updateUserInterests(iUserModifiable);
            this.updateUserOtherInfo(iUserModifiable);
        });


        this.binding.buttonSaveUserEdit.setOnClickListener(v -> save());
        this.binding.buttonCancelUserEdit.setOnClickListener(v -> cancel());
        return root;
    }

    private void updateUserName(IUserModifiable modifiableUser) {
        this.binding.inputNameUserEdit.setText(modifiableUser.getName());

        this.binding.inputNameUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileEditViewModel.setName(s.toString());
            }
        });
    }

    private void updateUserEmail(IUserModifiable modifiableUser) {
        this.binding.inputEmailUserEdit.setText(modifiableUser.getEmail());

        this.binding.inputEmailUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileEditViewModel.setNewEmail(s.toString());
            }
        });
    }

    private void updateUserAge(IUserModifiable modifiableUser) {
        this.binding.inputAgeUserEdit.setText(modifiableUser.getStringAge());

        binding.inputAgeUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileEditViewModel.setAge(s.toString());
            }
        });
    }

    private void updateUserGender(IUserModifiable modifiableUser) {
        String selectedGender = modifiableUser.getGender();

        HashMap<String, Integer> identifiers = new HashMap<>();
        for (String gender : IUserModifiable.GENDERS) {
            identifiers.put(gender, this.createGenderRadioButton(gender));
        }

        this.setGenderSelection(identifiers, selectedGender);
        this.setGenderChangeListener(identifiers);
    }

    private int createGenderRadioButton(String gender) {
        RadioButton genderRadioButton = new RadioButton(getActivity());
        int numericId = View.generateViewId();
        genderRadioButton.setId(numericId);
        genderRadioButton.setText(gender);

        binding.radioGroupGenderUserEdit.addView(genderRadioButton);
        return numericId;
    }

    private void setGenderSelection(HashMap<String, Integer> identifiers, String selectedGender) {
        if (identifiers.containsKey(selectedGender)) {
            this.binding.radioGroupGenderUserEdit.check(identifiers.get(selectedGender));
            this.binding.inputOtherGenderIdentityUserEdit.setEnabled(false);
        } else {
            this.binding.radioGroupGenderUserEdit.check(identifiers.get(IUserModifiable.OTHER));
            this.binding.inputOtherGenderIdentityUserEdit.setEnabled(true);
            this.binding.inputOtherGenderIdentityUserEdit.setText(selectedGender);
        }
    }

    private void setGenderChangeListener(HashMap<String, Integer> identifiers) {
        binding.radioGroupGenderUserEdit.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == identifiers.get(IUserModifiable.NONE)) {
                binding.inputOtherGenderIdentityUserEdit.setEnabled(false);
                userProfileEditViewModel.setGender(IUserModifiable.NONE);
            } else if (checkedId == identifiers.get(IUserModifiable.MALE)) {
                binding.inputOtherGenderIdentityUserEdit.setEnabled(false);
                userProfileEditViewModel.setGender(IUserModifiable.MALE);
            } else if (checkedId == identifiers.get(IUserModifiable.FEMALE)) {
                binding.inputOtherGenderIdentityUserEdit.setEnabled(false);
                userProfileEditViewModel.setGender(IUserModifiable.FEMALE);
            } else if (checkedId == identifiers.get(IUserModifiable.NON_BINARY)) {
                binding.inputOtherGenderIdentityUserEdit.setEnabled(false);
                userProfileEditViewModel.setGender(IUserModifiable.NON_BINARY);
            } else {
                binding.inputOtherGenderIdentityUserEdit.setEnabled(true);
                getOtherGenderIdentity();
            }
        });
    }

    private void getOtherGenderIdentity() {
        binding.inputOtherGenderIdentityUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileEditViewModel.setGender(s.toString());
            }
        });
    }

    private void callDetailsView(String text) {
        // Call the details view fragment
    }

    private void updateUserInterests(IUserModifiable modifiableUser) {
        HashMap<String, Boolean> interestsMap = modifiableUser.getInterestsMap();
        for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
            Chip interestChip = new Chip(getActivity());
            interestChip.setText(interest.getKey());
            interestChip.setCheckable(true);
            interestChip.setChecked(interest.getValue());

            interestChip.setOnCheckedChangeListener((buttonView, isChecked) -> userProfileEditViewModel.setInterestsMapItem(interest.getKey(), isChecked));

            binding.chipGroupInterestsUserEdit.addView(interestChip);
        }
    }

    private void updateUserOtherInfo(IUserModifiable modifiableUser) {
        binding.inputOtherInformationUserEdit.setText(modifiableUser.getOtherInfo());

        binding.inputOtherInformationUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileEditViewModel.setOtherInfo(s.toString());
            }
        });
    }


    public void save() {
        this.userProfileEditViewModel.saveUserToFirestore();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    public void cancel() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

}
