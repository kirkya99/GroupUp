package de.rwu.group_up.ui.start_screen.user_profile_creation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.MainActivity;
import de.rwu.group_up.R;
import de.rwu.group_up.User;
import de.rwu.group_up.databinding.FragmentUserProfileCreationBinding;
import de.rwu.group_up.ui.components.cancel_registration.AbortRegistrationConfirmationDialog;

public class UserProfileCreationFragment extends Fragment {
    private FragmentUserProfileCreationBinding binding;
    private UserProfileCreationViewModel userProfileCreationViewModel;
    private RadioGroup genderRadioGroup;
    private RadioButton genderDefaultRadioButton;
    private EditText otherGenderIdentityEditText;
    private ChipGroup interestsChipGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_profile_creation, container, false);
        userProfileCreationViewModel = new ViewModelProvider(this).get(UserProfileCreationViewModel.class);

        requireActivity().setTitle("User Profile Creation");
        genderRadioGroup = root.findViewById(R.id.radioGroupGender);
        interestsChipGroup = root.findViewById(R.id.chipGroupInterests);
        otherGenderIdentityEditText = root.findViewById(R.id.otherGenderIdentityEditText);
        createGenderRadioGroup(root);
        createInterestsChipList();

        // Extract content from creation fragment
        getProfileImage(root);
        getUserName(root);
        getAge(root);
        getOtherInfo(root);

        Button buttonSave = root.findViewById(R.id.buttonSave);
        Button buttonCancel = root.findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(v -> save());
        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    // TODO: Implement profile image extraction
    private void getProfileImage(View root) {
        ImageView profileImage = root.findViewById(R.id.profileImage);
    }

    private void getUserName(View root) {
        EditText editTextUsername = root.findViewById(R.id.editTextUsername);
        editTextUsername.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String name = editTextUsername.getText().toString();
                userProfileCreationViewModel.setName(name);
                return true;
            } else {
                return false;
            }
        });
    }

    private void getAge(View root) {
        EditText editTextAge = root.findViewById(R.id.editTextAge);
        editTextAge.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String ageString = editTextAge.getText().toString();
                if (!ageString.isEmpty()) {
                    int age = Integer.parseInt(ageString);
                    userProfileCreationViewModel.setAge(age);
                    return true;
                } else {
                    // Handle empty age input
                    return false;
                }
            } else {
                return false;
            }
        });
    }


    private void getOtherInfo(View root) {
        EditText editTextOtherInfo = root.findViewById(R.id.editTextOtherInfo);
        editTextOtherInfo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String otherInfo = editTextOtherInfo.getText().toString();
                userProfileCreationViewModel.setOtherInfo(otherInfo);
                return true;
            } else {
                return false;
            }
        });
    }

    private void createGenderRadioGroup(View root) {
        genderDefaultRadioButton = null;
        HashMap<String, Integer> identifiers = new HashMap<>();
        for (String gender : User.GENDERS) {
            RadioButton genderRadioButton = new RadioButton(getActivity());
            int numericId = root.generateViewId();
            String alphabeticId = gender;
            identifiers.put(alphabeticId, numericId);
            genderRadioButton.setId(numericId);
            genderRadioButton.setText(gender);

            if (!gender.equals(User.OTHER)) {
                genderRadioButton.setOnClickListener(v -> userProfileCreationViewModel.setGender(gender));
            }

            if (gender.equals(User.NONE)) {
                genderDefaultRadioButton = genderRadioButton;
            }

            genderRadioGroup.addView(genderRadioButton);
        }

        genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == identifiers.get(User.OTHER)) {
                otherGenderIdentityEditText.setEnabled(true);
                getOtherGenderIdentity();
            } else {
                otherGenderIdentityEditText.setEnabled(false);
            }
        });

        if (genderDefaultRadioButton != null) {
            genderDefaultRadioButton.setChecked(true);
        }

        RadioButton otherGenderIdentityRadioButton = new RadioButton(getActivity());
        otherGenderIdentityRadioButton.setText("Other gender identity");
    }

    private void getOtherGenderIdentity() {
        otherGenderIdentityEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String otherGenderIdentity = otherGenderIdentityEditText.getText().toString();
                userProfileCreationViewModel.setGender(otherGenderIdentity);
                return true;
            } else {
                return false;
            }
        });
    }

    private void createInterestsChipList() {

        HashMap<String, Boolean> interestsMap = userProfileCreationViewModel.getInterestsMap();
        for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
            Chip interestChip = new Chip(getActivity());
            interestChip.setText(interest.getKey());
            interestChip.setCheckable(true);
            interestChip.setChecked(interest.getValue());

            interestChip.setOnCheckedChangeListener((buttonView, isChecked) -> userProfileCreationViewModel.setInterestsMapItem(interest.getKey(), isChecked));

            interestsChipGroup.addView(interestChip);
        }
    }

    public void save() {
        // TODO: Save user data input in class User

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void cancel() {
        AbortRegistrationConfirmationDialog abortRegistrationConfirmationDialog = new AbortRegistrationConfirmationDialog(this);
        abortRegistrationConfirmationDialog.setArguments(new Bundle());
        abortRegistrationConfirmationDialog.show(getParentFragmentManager(), "AbortRegistrationConfirmationDialog");
    }

    public void onRegistrationAborted() {
        String message = "Registration process aborted!";
        getParentFragmentManager().popBackStack();
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
    }
}
