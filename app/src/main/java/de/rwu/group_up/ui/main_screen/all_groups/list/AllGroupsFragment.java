package de.rwu.group_up.ui.main_screen.all_groups.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.databinding.FragmentAllGroupsBinding;

public class AllGroupsFragment extends Fragment {

    private FragmentAllGroupsBinding binding;

    @Override
    public void onResume() {
        super.onResume();

        // Call database here
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AllGroupsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(AllGroupsViewModel.class);

        binding = FragmentAllGroupsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}