package de.rwu.group_up.ui.components.dialogs.logout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.ui.start_screen.StartActivity;

public class LogoutConfirmationDialogFragment extends DialogFragment {
    private LogoutConfirmationViewModel logoutConfirmationViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        logoutConfirmationViewModel = new ViewModelProvider(this).get(LogoutConfirmationViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> handleLogout())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

    private void handleLogout() {
        logoutConfirmationViewModel.handleLogout();
        Intent intent = new Intent(getActivity(), StartActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
