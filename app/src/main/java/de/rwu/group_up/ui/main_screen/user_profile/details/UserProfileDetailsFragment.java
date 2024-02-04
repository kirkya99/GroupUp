package de.rwu.group_up.ui.main_screen.user_profile.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentUserProfileDetailsBinding;
import de.rwu.group_up.ui.components.dialogs.logout.LogoutConfirmationDialogFragment;
import de.rwu.group_up.ui.main_screen.user_profile.edit.UserProfileEditFragment;

public class UserProfileDetailsFragment extends Fragment {

    private FragmentUserProfileDetailsBinding binding;
    private UserProfileDetailsViewModel userProfileDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileDetailsViewModel =
                new ViewModelProvider(this).get(UserProfileDetailsViewModel.class);

        binding = FragmentUserProfileDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        requireActivity().setTitle("User Profile Details");

        final TextView textView = binding.textHome;
        userProfileDetailsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        FloatingActionButton editButton = root.findViewById(R.id.editUserProfileBtn);
        editButton.setOnClickListener(v -> navigateToUserProfileEditFragment());

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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

    private void showLogoutConfirmationDialog(){
        LogoutConfirmationDialogFragment logoutConfirmationDialogFragment = new LogoutConfirmationDialogFragment();
        logoutConfirmationDialogFragment.setArguments(new Bundle());
        logoutConfirmationDialogFragment.show(getParentFragmentManager(), "LogoutConfirmationDialog");
    }
}