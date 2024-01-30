package de.rwu.group_up;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import de.rwu.group_up.ui.start_screen.launch.LaunchFragment;
import de.rwu.group_up.ui.start_screen.login.LoginFragment;
import de.rwu.group_up.ui.start_screen.register.RegisterFragment;

public class StartActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        LaunchFragment launchFragment = new LaunchFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.start_container, launchFragment, "launchFragment");
        transaction.commit();

//        Toolbar toolbar = findViewById(R.id.toolbar);


    }

    private boolean isValidCredentials(String username, String password) {
        // Replace this with your actual authentication logic (e.g., API call to server, Firebase Authentication, etc.)
        // For demo purposes, let's assume any non-empty username/password is valid
        return !username.isEmpty() && !password.isEmpty();
    }
}
