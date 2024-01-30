package de.rwu.group_up.ui.start_screen.launch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.login.LoginFragment;
import de.rwu.group_up.ui.start_screen.login.LoginViewModel;
import de.rwu.group_up.ui.start_screen.register.RegisterFragment;

public class LaunchFragment extends Fragment {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View root = inflater.inflate(R.layout.fragment_launch, container, false);
        requireActivity().setTitle("GroupUp");

        Button loginButton = (Button) root.findViewById(R.id.buttonLogin);
        Button registerButton = (Button) root.findViewById(R.id.buttonRegister);

        loginButton.setOnClickListener(v -> showLoginFragment());
        registerButton.setOnClickListener(v -> showRegisterFragment());


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
