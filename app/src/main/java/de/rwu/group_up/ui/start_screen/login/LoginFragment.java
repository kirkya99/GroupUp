package de.rwu.group_up.ui.start_screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        editTextEmail = root.findViewById(R.id.editTextUsername);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        buttonGo = root.findViewById(R.id.buttonLogin);
        buttonCancel = root.findViewById(R.id.buttonCancelLogin);

        buttonGo.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            handleLogin(email, password);
        });

        buttonCancel.setOnClickListener(v -> cancel());

        return root;
    }

    private void handleLogin(String email, String password) {
        if (isValidCredentials(email, password)) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Snackbar.make(requireView(), "Login failed: " + task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Snackbar.make(requireView(), "Login failed: Input fields must be filled", Snackbar.LENGTH_SHORT).show();
        }
    }
}
