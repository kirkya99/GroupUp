package de.rwu.group_up.ui.components.dialogs.logout;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import de.rwu.group_up.utils.UserManager;

public class LogoutConfirmationViewModel extends ViewModel {

    public void handleLogout() {
        UserManager.getInstance().clearUserData();
        FirebaseAuth.getInstance().signOut();
    }
}
