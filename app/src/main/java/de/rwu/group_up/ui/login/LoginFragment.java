package de.rwu.group_up.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.rwu.group_up.R;

public class LoginFragment extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        loginViewModel = new LoginViewModel();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_login);
        Button btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(view -> {
//            loginViewModel.setLoggedIn(true);
            setContentView(R.layout.activity_main);
        });
    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_login, container, false);
//
//        return view;
//    }
}
