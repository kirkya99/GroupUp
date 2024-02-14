package de.rwu.group_up.ui.start_screen.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.FragmentRegisterBinding;
import de.rwu.group_up.ui.start_screen.BaseForm;
import de.rwu.group_up.ui.start_screen.user_profile_creation.UserProfileCreationFragment;

public class RegisterFragment extends BaseForm {

    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        this.binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();
        requireActivity().setTitle("Register");
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        this.binding.inputEmailRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.setEmail(s.toString());
            }
        });

        this.binding.inputPasswordRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                RegisterFragment.this.registerViewModel.setPassword(s.toString());
            }
        });

        this.binding.buttonRegisterRegister.setOnClickListener(view -> {
            this.disableButton(this.binding.buttonRegisterRegister);
            registerViewModel.handleRegistration(new RegisterViewModel.OnRegistrationListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    navigateToUserProfileCreation();
                    enableButton(binding.buttonRegisterRegister);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Snackbar.make(requireView(), "Registration failed: " + errorMessage, Snackbar.LENGTH_SHORT).show();
                    enableButton(binding.buttonRegisterRegister);
                }
            });
        });

        this.binding.buttonCancelRegister.setOnClickListener(v -> cancel());

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
