package de.rwu.group_up.ui.start_screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.MainActivity;
import de.rwu.group_up.R;
import de.rwu.group_up.ui.my_groups.MyGroupsViewModel;
import de.rwu.group_up.ui.start_screen.BaseForm;

public class LoginFragment extends BaseForm {

    private LoginViewModel loginViewModel = null;
    private Button buttonResetPassword;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        if (loginViewModel == null) {
            loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        }

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        requireActivity().setTitle("Login");

        editTextEmail = root.findViewById(R.id.editTextUsername);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        buttonGo = root.findViewById(R.id.buttonLogin);
        buttonResetPassword = root.findViewById(R.id.buttonResetPassword);
        buttonCancel = root.findViewById(R.id.buttonCancelLogin);

        buttonGo.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            loginViewModel.handleLogin(email, password, new LoginViewModel.OnLoginListener() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    navigateToMainActivity();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Snackbar.make(requireView(), "Login failed: " + errorMessage, Snackbar.LENGTH_SHORT).show();

                }
            });
        });

        buttonResetPassword.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            loginViewModel.handlePasswordReset(email, new LoginViewModel.OnPasswordResetListener() {
                @Override
                public void onSuccess(String successMessage) {
                    Snackbar.make(requireView(), "Password reset success: " + successMessage, Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Snackbar.make(requireView(), "Passwor reset failed: " + errorMessage, Snackbar.LENGTH_SHORT).show();
                }
            });
        });

        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
