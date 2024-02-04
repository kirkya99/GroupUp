package de.rwu.group_up.ui.start_screen.user_profile_creation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.R;
import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.ui.components.dialogs.image_picker.IImagePickerListener;
import de.rwu.group_up.ui.components.dialogs.image_picker.ImagePickerDialogFragment;
import de.rwu.group_up.ui.components.dialogs.image_picker.ImagePickerViewModel;
import de.rwu.group_up.ui.main_screen.MainActivity;
import de.rwu.group_up.databinding.FragmentUserProfileCreationBinding;
import de.rwu.group_up.ui.components.dialogs.cancel_registration.AbortRegistrationConfirmationDialogFragment;

public class UserProfileCreationFragment extends Fragment implements IImagePickerListener {
    private FragmentUserProfileCreationBinding binding;
    private UserProfileCreationViewModel userProfileCreationViewModel;
    private ImagePickerViewModel imagePickerViewModel;
    private ImagePickerDialogFragment imagePickerDialogFragment;
    private RadioButton genderDefaultRadioButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserProfileCreationBinding.inflate(inflater, container, false);
        imagePickerDialogFragment = new ImagePickerDialogFragment();
        View root = binding.getRoot();
        userProfileCreationViewModel = new ViewModelProvider(this).get(UserProfileCreationViewModel.class);
        imagePickerViewModel = new ViewModelProvider(requireActivity()).get(ImagePickerViewModel.class);

        requireActivity().setTitle("User Profile Creation");
        createGenderRadioGroup();
        createInterestsChipList();

        // Extract content from creation fragment
        callImagePickerDialog();
//        getProfileImage();
        getUserName();
        getAge();
        getOtherInfo();

        binding.buttonSave.setOnClickListener(v -> save());
        binding.buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    private void getProfileImage() {

        imagePickerViewModel.getSelectedImageUri().observe(getViewLifecycleOwner(), uri -> {
            if(uri != null) {
                try {
                    ImageDecoder.Source source = ImageDecoder.createSource(requireContext().getContentResolver(), uri);
                    Drawable drawable = ImageDecoder.decodeDrawable(source);
                    binding.profileImage.setImageDrawable(drawable);
                    userProfileCreationViewModel.setProfileImageUrl(uri.toString());
                    String uriString = uri.toString();
                    uri = Uri.parse(uriString);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                binding.profileImage.setImageResource(R.drawable.default_profile_image);
            }
        });
    }

    private void callImagePickerDialog() {
        binding.profileImage.setImageResource(R.drawable.default_profile_image);

        binding.buttonProfileImageInteraction.setOnClickListener(
                v -> imagePickerDialogFragment.show(getChildFragmentManager(), "ImagePickerDialogFragment"));
    }

    private void getUserName() {
        binding.editTextUsername.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String name = binding.editTextUsername.getText().toString();
                userProfileCreationViewModel.setName(name);
                return true;
            } else {
                return false;
            }
        });
    }

    private void getAge() {
        binding.editTextAge.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String ageString = binding.editTextAge.getText().toString();
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


    private void getOtherInfo() {
        binding.editTextOtherInfo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String otherInfo = binding.editTextOtherInfo.getText().toString();
                userProfileCreationViewModel.setOtherInfo(otherInfo);
                return true;
            } else {
                return false;
            }
        });
    }

    private void createGenderRadioGroup() {
        genderDefaultRadioButton = null;
        HashMap<String, Integer> identifiers = new HashMap<>();
        for (String gender : IUserModifiable.GENDERS) {
            RadioButton genderRadioButton = new RadioButton(getActivity());
            int numericId = View.generateViewId();
            String alphabeticId = gender;
            identifiers.put(alphabeticId, numericId);
            genderRadioButton.setId(numericId);
            genderRadioButton.setText(gender);

            if (!gender.equals(IUserModifiable.OTHER)) {
                genderRadioButton.setOnClickListener(v -> userProfileCreationViewModel.setGender(gender));
            }

            if (gender.equals(IUserModifiable.NONE)) {
                genderDefaultRadioButton = genderRadioButton;
            }

            binding.radioGroupGender.addView(genderRadioButton);
        }

        binding.radioGroupGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == identifiers.get(IUserModifiable.OTHER)) {
                binding.otherGenderIdentityEditText.setEnabled(true);
                getOtherGenderIdentity();
            } else {
                binding.otherGenderIdentityEditText.setEnabled(false);
            }
        });

        if (genderDefaultRadioButton != null) {
            genderDefaultRadioButton.setChecked(true);
        }

        RadioButton otherGenderIdentityRadioButton = new RadioButton(getActivity());
        otherGenderIdentityRadioButton.setText("Other gender identity");
    }

    private void getOtherGenderIdentity() {
        binding.otherGenderIdentityEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String otherGenderIdentity = binding.otherGenderIdentityEditText.getText().toString();
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

            binding.chipGroupInterests.addView(interestChip);
        }
    }

    public void save() {
        // TODO: Save user data input in class User
        userProfileCreationViewModel.saveUserToFirestore();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void cancel() {
        AbortRegistrationConfirmationDialogFragment abortRegistrationConfirmationDialogFragment = new AbortRegistrationConfirmationDialogFragment(this);
        abortRegistrationConfirmationDialogFragment.setArguments(new Bundle());
        abortRegistrationConfirmationDialogFragment.show(getParentFragmentManager(), "AbortRegistrationConfirmationDialog");
    }

    public void onRegistrationAborted() {
        String message = "Registration process aborted!";
        getParentFragmentManager().popBackStack();
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onImagePicked(Uri imageUri) {
        if(imageUri != null) {
            try {
                ImageDecoder.Source source = ImageDecoder.createSource(requireContext().getContentResolver(), imageUri);
                Drawable drawable = ImageDecoder.decodeDrawable(source);
                this.binding.profileImage.setImageDrawable(drawable);
                this.userProfileCreationViewModel.setProfileImageUrl(imageUri.toString());
                String uriString = imageUri.toString();
                imageUri = Uri.parse(uriString);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.binding.profileImage.setImageResource(R.drawable.default_profile_image);
        }    }
}
