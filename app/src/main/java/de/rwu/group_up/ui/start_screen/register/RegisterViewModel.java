package de.rwu.group_up.ui.start_screen.register;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.data.model.User;
import de.rwu.group_up.utils.UserManager;

public class RegisterViewModel extends ViewModel {

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void handleRegistration(OnRegistrationListener listener) {
        if (!email.isEmpty() && !password.isEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Registration successful
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserManager userManager = UserManager.getInstance();
                            userManager.setUid(user.getUid());
                            userManager.setEmail(user.getEmail());
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
