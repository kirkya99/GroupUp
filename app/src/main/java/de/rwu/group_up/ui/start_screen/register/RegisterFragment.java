package de.rwu.group_up.ui.start_screen.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.BaseForm;
import de.rwu.group_up.ui.start_screen.user_profile_creation.UserProfileCreationFragment;

public class RegisterFragment extends BaseForm {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        requireActivity().setTitle("Register");

        editTextEmail = root.findViewById(R.id.editTextUsername);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        buttonGo = root.findViewById(R.id.buttonLogin);
        buttonCancel = root.findViewById(R.id.buttonCancelRegister);

        buttonGo.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            handleRegistration(email, password);
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

    public void handleRegistration(String email, String password) {
        if(isValidCredentials(email, password))
        {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            navigateToUserProfileCreation();
                        } else {
                            Snackbar.make(requireView(), "Registration failed: " + task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            Snackbar.make(requireView(), "Registration failed: Input fields must be filled", Snackbar.LENGTH_SHORT).show();
        }
    }
}
