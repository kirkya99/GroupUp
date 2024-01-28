package de.rwu.group_up.ui.user_profile.view;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import de.rwu.group_up.ui.user_profile.edit.UserProfileEditFragment;

public class UserProfileDetailsFragment extends Fragment {

    private FragmentUserProfileDetailsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserProfileDetailsViewModel homeViewModel =
                new ViewModelProvider(this).get(UserProfileDetailsViewModel.class);

        binding = FragmentUserProfileDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        FloatingActionButton editButton = root.findViewById(R.id.editUserProfileBtn);
        editButton.setOnClickListener(v -> navigateToUserEditFragment());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToUserEditFragment() {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new UserProfileEditFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}