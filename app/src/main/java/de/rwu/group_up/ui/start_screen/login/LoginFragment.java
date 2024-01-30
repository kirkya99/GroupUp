package de.rwu.group_up.ui.start_screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import de.rwu.group_up.MainActivity;
import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.BaseForm;

public class LoginFragment extends BaseForm {

    private LoginViewModel loginViewModel;


    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        requireActivity().setTitle("Login");

        editTextUsername = root.findViewById(R.id.editTextUsername);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        buttonGo = root.findViewById(R.id.buttonLogin);

        buttonGo.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            if (isValidCredentials(username, password)) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Snackbar.make(requireView(), "Invalid username or password", Snackbar.LENGTH_SHORT).show();
            }
        });

        buttonCancel = root.findViewById(R.id.buttonCancelLogin);
        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    protected boolean isValidCredentials(String username, String password) {
        // Replace this with your actual authentication logic (e.g., API call to server, Firebase Authentication, etc.)
        // For demo purposes, let's assume any non-empty username/password is valid
        return !username.isEmpty() && !password.isEmpty();
    }
}
