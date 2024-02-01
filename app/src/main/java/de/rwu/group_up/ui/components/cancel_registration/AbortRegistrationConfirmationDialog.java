package de.rwu.group_up.ui.components.cancel_registration;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import de.rwu.group_up.StartActivity;
import de.rwu.group_up.ui.start_screen.user_profile_creation.UserProfileCreationFragment;

public class AbortRegistrationConfirmationDialog extends DialogFragment {
    private AbortRegistrationConfirmationViewModel abortRegistrationConfirmationViewModel;
    private UserProfileCreationFragment userProfileCreationFragment;

    public AbortRegistrationConfirmationDialog(UserProfileCreationFragment userProfileCreationFragment){
        this.userProfileCreationFragment = userProfileCreationFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        abortRegistrationConfirmationViewModel = new ViewModelProvider(this).get(AbortRegistrationConfirmationViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to abort the registration?")
                .setPositiveButton("Yes", (dialog, which) -> handleRegistrationAbortion())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

    private void handleRegistrationAbortion() {
        abortRegistrationConfirmationViewModel.handleAbortRegistration(message -> {
            userProfileCreationFragment.onRegistrationAborted();
        });
        dismiss();
    }
}
