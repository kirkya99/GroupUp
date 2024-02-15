package de.rwu.group_up.ui.main_screen.user_profile.details;

import android.os.Bundle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.R;
import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.databinding.FragmentUserProfileDetailsBinding;
import de.rwu.group_up.ui.components.dialogs.logout.LogoutConfirmationDialogFragment;
import de.rwu.group_up.ui.main_screen.user_profile.edit.UserProfileEditFragment;

public class UserProfileDetailsFragment extends Fragment {

    private FragmentUserProfileDetailsBinding binding;
    private UserProfileDetailsViewModel userProfileDetailsViewModel;

    private void readCurrentUserEntry() {
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.readUserEntry(new UserDatabaseController.OnReadUserEntryListener() {
            @Override
            public void onSuccess(User user) {
                userProfileDetailsViewModel.setUser(user);
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileDetailsViewModel =
                new ViewModelProvider(this).get(UserProfileDetailsViewModel.class);

        this.binding = FragmentUserProfileDetailsBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();
        this.requireActivity().setTitle("User Profile Details");

        this.readCurrentUserEntry();

        this.userProfileDetailsViewModel.getUser().observe(getViewLifecycleOwner(), iUserReadable -> {

            this.binding.displayNameContent.setText(iUserReadable.getName());

            this.binding.displayEmailContent.setText(iUserReadable.getEmail());

            this.binding.displayAgeContent.setText(String.valueOf(iUserReadable.getStringAge()));

            this.binding.displayGenderContent.setText(iUserReadable.getGender());

            this.binding.chipGroupInterestsContent.removeAllViews();
            HashMap<String, Boolean> interestsMap = iUserReadable.getInterestsMap();
            for (Map.Entry<String, Boolean> interest : interestsMap.entrySet()) {
                if(interest.getValue()) {
                    Chip interestChip = new Chip(getActivity());
                    interestChip.setText(interest.getKey());
                    interestChip.setChecked(interest.getValue());
                    binding.chipGroupInterestsContent.addView(interestChip);
                }
            }

            this.binding.displayOtherInfoContent.setText(iUserReadable.getOtherInfo());
        });

        this.binding.fabEditUserProfile.setOnClickListener(v -> navigateToUserProfileEditFragment());

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.user_profile_details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            showLogoutConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void navigateToUserProfileEditFragment() {
        UserProfileEditFragment userProfileEditFragment = new UserProfileEditFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, userProfileEditFragment, "userProfileEditFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showLogoutConfirmationDialog() {
        LogoutConfirmationDialogFragment logoutConfirmationDialogFragment = new LogoutConfirmationDialogFragment();
        logoutConfirmationDialogFragment.setArguments(new Bundle());
        logoutConfirmationDialogFragment.show(getParentFragmentManager(), "LogoutConfirmationDialog");
    }
}