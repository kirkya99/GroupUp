package de.rwu.group_up.ui.start_screen.launch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentLaunchBinding;
import de.rwu.group_up.ui.main_screen.MainActivity;
import de.rwu.group_up.ui.start_screen.login.LoginFragment;
import de.rwu.group_up.ui.start_screen.login.LoginViewModel;
import de.rwu.group_up.ui.start_screen.register.RegisterFragment;

public class LaunchFragment extends Fragment {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    private FragmentLaunchBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        this.binding = FragmentLaunchBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();

        requireActivity().setTitle("GroupUP");

        this.binding.buttonLoginLaunch.setOnClickListener(v -> showLoginFragment());
        this.binding.buttonRegisterLaunch.setOnClickListener(v -> showRegisterFragment());

        return root;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.start_container, fragment);
        transaction.addToBackStack(null); // Add the transaction to the back stack
        transaction.commit();
    }

    public void showLoginFragment() {
        replaceFragment(new LoginFragment());
    }

    public void showRegisterFragment() {
        replaceFragment(new RegisterFragment());
    }
}
