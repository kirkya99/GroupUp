package de.rwu.group_up.ui.components.cancel_registration;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AbortRegistrationConfirmationViewModel extends ViewModel {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public void handleAbortRegistration(OnCompleteListener onCompleteListener) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null) {
            firebaseUser.delete().addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    onCompleteListener.onComplete("User creation aborted!");
                }
            });
        }
    }

    public interface OnCompleteListener{
        void onComplete(String message);
    }
}
