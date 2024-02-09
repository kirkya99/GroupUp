package de.rwu.group_up.ui.start_screen.login;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.utils.UserManager;

public class LoginViewModel extends ViewModel {

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void handleLogin(OnLoginListener listener) {
        if (!this.email.isEmpty() && !this.password.isEmpty() || this.email == null || this.password == null) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(this.email, this.password).addOnCompleteListener(task -> {
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

    public void handlePasswordReset(OnPasswordResetListener listener) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (!this.email.isEmpty() || this.email == null) {
            auth.sendPasswordResetEmail(this.email)
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
