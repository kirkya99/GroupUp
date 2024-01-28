package de.rwu.group_up.ui.user_profile.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentUserProfileEditBinding;

public class UserProfileEditFragment extends Fragment {

    private FragmentUserProfileEditBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        binding = FragmentUserProfileEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnSave = root.findViewById(R.id.btnSaveUserProfile);
        btnSave.setOnClickListener(v -> navigateToUserDetailsFragment());

        return root;
    }

    private void navigateToUserDetailsFragment() {
        Navigation.findNavController(requireView()).navigate(R.id.action_userProfileEditFragment_to_navigation_home);
    }
}
