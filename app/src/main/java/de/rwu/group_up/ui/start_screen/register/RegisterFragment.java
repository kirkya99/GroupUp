package de.rwu.group_up.ui.start_screen.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import de.rwu.group_up.MainActivity;
import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.BaseForm;
import de.rwu.group_up.ui.start_screen.login.LoginViewModel;
import de.rwu.group_up.ui.start_screen.user_profile_creation.UserProfileCreationFragment;

public class RegisterFragment extends BaseForm {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        requireActivity().setTitle("Register");

        editTextUsername = root.findViewById(R.id.editTextUsername);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        buttonGo = root.findViewById(R.id.buttonLogin);
        buttonCancel = root.findViewById(R.id.buttonCancelRegister);

        buttonGo.setOnClickListener(view -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();


            if (isValidCredentials(username, password)) {
                UserProfileCreationFragment userProfileCreationFragment = new UserProfileCreationFragment();
                FragmentTransaction fragmentTransaction =  getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.start_container, userProfileCreationFragment, "userProfileCreationFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                Snackbar.make(requireView(), "Invalid username or password", Snackbar.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    protected boolean isValidCredentials(String username, String password) {
        // TODO: Implement checks for user creation:
        // - new user must have a new unique name
        // - a password must be given
        // If these checks fail --> error message
        return !username.isEmpty() && !password.isEmpty();
    }
}
