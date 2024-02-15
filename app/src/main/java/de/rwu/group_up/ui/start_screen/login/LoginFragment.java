package de.rwu.group_up.ui.start_screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.databinding.FragmentLoginBinding;
import de.rwu.group_up.databinding.FragmentRegisterBinding;
import de.rwu.group_up.ui.main_screen.MainActivity;
import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.BaseForm;

public class LoginFragment extends BaseForm {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel = null;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        if (loginViewModel == null) {
            loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        }
        this.binding = FragmentLoginBinding.inflate(inflater, container, false);

        View root = this.binding.getRoot();
        requireActivity().setTitle("Login");

        debugLogin();
        this.loginViewModel.setEmail(this.binding.inputEmailLogin.getText().toString());
        this.loginViewModel.setPassword(this.binding.inputPasswordLogin.getText().toString());

        this.binding.inputEmailLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.setEmail(s.toString());
            }
        });

        this.binding.inputPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.setPassword(s.toString());
            }
        });

        this.binding.buttonLoginLogin.setOnClickListener(v -> {
            this.disableButton(this.binding.buttonLoginLogin);
            loginViewModel.handleLogin(new LoginViewModel.OnLoginListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    navigateToMainActivity();
                    enableButton(binding.buttonLoginLogin);
                    Snackbar.make(requireView(), "Login successful", Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Snackbar.make(requireView(), "Login failed: " + errorMessage, Snackbar.LENGTH_SHORT).show();
                    enableButton(binding.buttonLoginLogin);
                }
            });
        });

        this.binding.buttonResetPassword.setOnClickListener(v -> {
            disableButton(this.binding.buttonResetPassword);
            loginViewModel.handlePasswordReset(new LoginViewModel.OnPasswordResetListener() {
                @Override
                public void onSuccess(String successMessage) {
                    Snackbar.make(requireView(), "Password reset success: " + successMessage, Snackbar.LENGTH_SHORT).show();
                    enableButton(binding.buttonResetPassword);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Snackbar.make(requireView(), "Passwor reset failed: " + errorMessage, Snackbar.LENGTH_SHORT).show();
                    enableButton(binding.buttonResetPassword);
                }
            });
        });

        binding.buttonCancelLogin.setOnClickListener(v -> cancel());

        return root;
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void debugLogin(){
        this.binding.inputEmailLogin.setText("test@fire.net");
        this.binding.inputPasswordLogin.setText("123456");
    }
}
