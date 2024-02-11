package de.rwu.group_up.ui.start_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public abstract class BaseForm extends Fragment {

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState);

    protected boolean isValidCredentials(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    protected void cancel() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    protected void enableButton(Button button) {
        button.setEnabled(true);
    }

    protected void disableButton(Button button) {
        button.setEnabled(false);
    }

}
