package de.rwu.group_up.ui.main_screen.user_profile.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentUserProfileEditBinding;

public class UserProfileEditFragment extends Fragment {

    private FragmentUserProfileEditBinding binding;
    private UserProfileEditViewModel userProfileEditViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        this.binding = FragmentUserProfileEditBinding.inflate(inflater, container, false);
        this.userProfileEditViewModel = new ViewModelProvider(this).get(UserProfileEditViewModel.class);

        View root = this.binding.getRoot();
        requireActivity().setTitle("User Profile Edit");

        this.binding.buttonSave.setOnClickListener(v -> save());
        this.binding.buttonCancel.setOnClickListener(v -> cancel());
        return root;
    }

    public void save() {
        // TODO: save the new data
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    public void cancel() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

}
