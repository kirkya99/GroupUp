package de.rwu.group_up.ui.start_screen.user_profile_creation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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
        this.createGenderRadioGroup();
        this.createInterestsChipList();

        // Extract content from creation fragment
        this.callImagePickerDialog();
//        getProfileImage();
        this.getUserName();
        this.getAge();
        this.getOtherInfo();

        binding.buttonSaveUserCreation.setOnClickListener(v -> save());
        binding.buttonSaveUserCreation.setOnClickListener(v -> cancel());

        return root;
    }

    private void getProfileImage() {
        imagePickerViewModel.getSelectedImageUri().observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                try {
                    ImageDecoder.Source source = ImageDecoder.createSource(requireContext().getContentResolver(), uri);
                    Drawable drawable = ImageDecoder.decodeDrawable(source);
                    binding.profileImageUserCreation.setImageDrawable(drawable);
                    userProfileCreationViewModel.setProfileImageUrl(uri.toString());
                    String uriString = uri.toString();
                    uri = Uri.parse(uriString);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                binding.profileImageUserCreation.setImageResource(R.drawable.default_profile_image);
            }
        });
    }

    private void callImagePickerDialog() {
        binding.profileImageUserCreation.setImageResource(R.drawable.default_profile_image);

        binding.profileImageUserCreation.setOnClickListener(
                v -> imagePickerDialogFragment.show(getChildFragmentManager(), "ImagePickerDialogFragment"));
    }

    private void getUserName() {
        binding.inputNameUserCreation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileCreationViewModel.setName(s.toString());
            }
        });
    }

    private void getAge() {
        binding.inputAgeUserCreation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileCreationViewModel.setAge(s.toString());
            }
        });
    }


    private void getOtherInfo() {
        binding.inputOtherInformationUserCreation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileCreationViewModel.setOtherInfo(s.toString());
            }
        });
    }

    private void createGenderRadioGroup() {
        genderDefaultRadioButton = null;
        HashMap<String, Integer> identifiers = new HashMap<>();
        for (String gender : IUserModifiable.GENDERS) {
            identifiers.put(gender, this.createGenderRadioButton(gender));
        }

        this.setGenderChangeListener(identifiers);

        binding.radioGroupGenderUserCreation.check(identifiers.get(IUserModifiable.NONE));
    }

    private int createGenderRadioButton(String gender) {
        RadioButton genderRadioButton = new RadioButton(getActivity());
        int numericId = View.generateViewId();
        genderRadioButton.setId(numericId);
        genderRadioButton.setText(gender);

        binding.radioGroupGenderUserCreation.addView(genderRadioButton);
        return numericId;
    }

    private void setGenderChangeListener(HashMap<String, Integer> identifiers) {
        this.binding.radioGroupGenderUserCreation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == identifiers.get(IUserModifiable.OTHER)) {
                this.binding.radioGroupGenderUserCreation.setEnabled(true);
                this.getOtherGenderIdentity();
            } else {
                this.binding.radioGroupGenderUserCreation.setEnabled(false);
            }
        });
    }

    private void getOtherGenderIdentity() {
        this.binding.inputOtherGenderIdentityUserCreation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userProfileCreationViewModel.setGender(s.toString());
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

            binding.chipGroupInterestsUserCreation.addView(interestChip);
        }
    }

    public void save() {
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
        if (imageUri != null) {
            try {
                ImageDecoder.Source source = ImageDecoder.createSource(requireContext().getContentResolver(), imageUri);
                Drawable drawable = ImageDecoder.decodeDrawable(source);
                this.binding.profileImageUserCreation.setImageDrawable(drawable);
                this.userProfileCreationViewModel.setProfileImageUrl(imageUri.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.binding.profileImageUserCreation.setImageResource(R.drawable.default_profile_image);
        }
    }
}
