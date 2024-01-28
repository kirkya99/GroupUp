package de.rwu.group_up.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.rwu.group_up.R;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;

    public LoginFragment(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
//        setContentView(R.layout.fragment_login);
//        Button btnLogin = (Button) findViewById(R.id.buttonLogin);
//        btnLogin.setOnClickListener(view -> {
//            loginViewModel.setLoggedIn(true);
//        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }
}
