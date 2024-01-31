package de.rwu.group_up.ui.start_screen.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.BaseForm;
import de.rwu.group_up.ui.start_screen.user_profile_creation.UserProfileCreationFragment;

public class RegisterFragment extends BaseForm {

    private RegisterViewModel registerViewModel;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        requireActivity().setTitle("Register");
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        editTextEmail = root.findViewById(R.id.editTextUsername);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        buttonGo = root.findViewById(R.id.buttonLogin);
        buttonCancel = root.findViewById(R.id.buttonCancelRegister);

        buttonGo.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            registerViewModel.handleRegistration(email, password, new RegisterViewModel.OnRegistrationListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    navigateToUserProfileCreation();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Snackbar.make(requireView(), "Registration failed: " + errorMessage, Snackbar.LENGTH_SHORT).show();
                }
            });
        });

        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    private void navigateToUserProfileCreation() {
        UserProfileCreationFragment userProfileCreationFragment = new UserProfileCreationFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.start_container, userProfileCreationFragment, "userProfileCreationFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
