package de.rwu.group_up.ui.user_profile.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
        requireActivity().setTitle("User Profile Edit");


        Button buttonSave = root.findViewById(R.id.buttonSave);
        Button buttonCancel = root.findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(v -> save());
        buttonCancel.setOnClickListener(v -> cancel());
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
