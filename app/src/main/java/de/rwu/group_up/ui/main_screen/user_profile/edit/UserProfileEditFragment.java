package de.rwu.group_up.ui.main_screen.user_profile.edit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        this.binding = FragmentUserProfileEditBinding.inflate(inflater, container, false);
        this.userProfileEditViewModel = new ViewModelProvider(this).get(UserProfileEditViewModel.class);

        View root = this.binding.getRoot();
        requireActivity().setTitle("User Profile Edit");

        this.readCurrentUserEntry();

        this.updateUserName();
        this.updateUserEmail();
        this.updateUserAge();
        this.updateUserGender();
        this.updateUserInterests();
        this.updateUserOtherInfo();


        this.binding.buttonSave.setOnClickListener(v -> save());
        this.binding.buttonCancel.setOnClickListener(v -> cancel());
        return root;
    }

    private void updateUserName() {
        this.binding.editTextUsername.setText(this.userProfileEditViewModel.getName());

        this.binding.editTextUsername.addTextChangedListener(new TextWatcher() {
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

    private void updateUserEmail() {
        this.binding.editTextEmail.setText(this.userProfileEditViewModel.getEmail());

        this.binding.editTextEmail.addTextChangedListener(new TextWatcher() {
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

    private void updateUserAge() {
        this.binding.editTextAge.setText(this.userProfileEditViewModel.getAge());

        binding.editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int age = userProfileEditViewModel.parseInteger(s.toString());
                if (age != Integer.MIN_VALUE) {
                    // Integer parsed successfully, you can save it or use it as needed
                    userProfileEditViewModel.setAge(age);
                } else {
                    Snackbar.make(requireView(), "Error: Invalid input!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUserGender() {
        String selectedGender = this.userProfileEditViewModel.getGender();

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

        binding.radioGroupGender.addView(genderRadioButton);
        return numericId;
    }

    private void setGenderSelection(HashMap<String, Integer> identifiers, String selectedGender) {
        if (identifiers.containsKey(selectedGender)) {
            this.binding.radioGroupGender.check(identifiers.get(selectedGender));
            this.binding.otherGenderIdentityEditText.setEnabled(false);
        } else {
            this.binding.radioGroupGender.check(identifiers.get(IUserModifiable.OTHER));
            this.binding.otherGenderIdentityEditText.setEnabled(true);
            this.binding.otherGenderIdentityEditText.setText(selectedGender);
        }
    }

    private void setGenderChangeListener(HashMap<String, Integer> identifiers){
        binding.radioGroupGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == identifiers.get(IUserModifiable.OTHER)) {
                binding.otherGenderIdentityEditText.setEnabled(true);
                this.updateUserOtherGenderIdentity();
            } else {
                binding.otherGenderIdentityEditText.setEnabled(false);
            }
        });
    }

    private void updateUserOtherGenderIdentity() {
        binding.otherGenderIdentityEditText.addTextChangedListener(new TextWatcher() {
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

    private void updateUserInterests(){
        HashMap<String, Boolean> interestsMap = userProfileEditViewModel.getInterestsMap();
        for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
            Chip interestChip = new Chip(getActivity());
            interestChip.setText(interest.getKey());
            interestChip.setCheckable(true);
            interestChip.setChecked(interest.getValue());

            interestChip.setOnCheckedChangeListener((buttonView, isChecked) -> userProfileEditViewModel.setInterestsMapItem(interest.getKey(), isChecked));

            binding.chipGroupInterests.addView(interestChip);
        }
    }

    private void updateUserOtherInfo() {
        binding.editTextOtherInfo.setText(this.userProfileEditViewModel.getOtherInfo());

        binding.editTextOtherInfo.addTextChangedListener(new TextWatcher() {
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
