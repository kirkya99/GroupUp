package de.rwu.group_up.ui.start_screen.register;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {
    public void handleRegistration(String email, String password, OnRegistrationListener listener) {
        if (!email.isEmpty() && !password.isEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Registration successful
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.onSuccess(user);
                        } else {
                            // Registration failed
                            listener.onFailure(task.getException().getMessage());
                        }
                    });
        } else {
            listener.onFailure("Input fields must be filled!");
        }
    }

    public interface OnRegistrationListener {
        void onSuccess(FirebaseUser user);

        void onFailure(String errorMessage);
    }
}
