package de.rwu.group_up.ui.start_screen.login;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.utils.UserManager;

public class LoginViewModel extends ViewModel {
    public void handleLogin(String email, String password, OnLoginListener listener) {
        if (!email.isEmpty() && !password.isEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserManager userManager = UserManager.getInstance();
                    userManager.setUid(user.getUid());
                    userManager.setEmail(user.getEmail());
                    listener.onSuccess(user);
                } else {
                    listener.onFailure(task.getException().getMessage());
                }
            });
        } else {
            listener.onFailure("Input fields must be filled!");
        }
    }

    public void handlePasswordReset(String email, OnPasswordResetListener listener) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (!email.isEmpty()) {
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            listener.onSuccess("Password reset email sent successfully");
                        } else {
                            listener.onFailure(task.getException().getMessage());
                        }
                    });
        } else {
            listener.onFailure("Email input field must be filled");
        }
    }

    public interface OnLoginListener {
        void onSuccess(FirebaseUser user);

        void onFailure(String errorMessage);
    }

    public interface OnPasswordResetListener {
        void onSuccess(String successMessage);

        void onFailure(String errorMessage);
    }
}
