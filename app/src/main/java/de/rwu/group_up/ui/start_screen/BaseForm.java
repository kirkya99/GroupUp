package de.rwu.group_up.ui.start_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public abstract class BaseForm extends Fragment {

    protected EditText editTextEmail;
    protected EditText editTextPassword;
    protected Button buttonGo;
    protected Button buttonCancel;

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState);

    protected boolean isValidCredentials(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    protected void cancel() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

}
