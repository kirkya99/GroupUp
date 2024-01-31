package de.rwu.group_up.ui.start_screen.user_profile_creation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.rwu.group_up.MainActivity;
import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentUserProfileCreationBinding;

public class UserProfileCreationFragment extends Fragment {
    private FragmentUserProfileCreationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_profile_creation, container, false);
        requireActivity().setTitle("User Profile Edit");

        Button buttonSave = root.findViewById(R.id.buttonSave);
        Button buttonCancel = root.findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(v -> save());
        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    public void save() {
        // TODO: Save user data input in class User

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void cancel() {
        // TODO: Implement a delete user for the cancellation
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
