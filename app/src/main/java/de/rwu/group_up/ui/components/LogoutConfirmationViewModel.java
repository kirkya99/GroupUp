package de.rwu.group_up.ui.components;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutConfirmationViewModel extends ViewModel {

    public void handleLogout() {
        FirebaseAuth.getInstance().signOut();
    }
}
