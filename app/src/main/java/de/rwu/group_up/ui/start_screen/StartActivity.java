package de.rwu.group_up.ui.start_screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.rwu.group_up.R;
import de.rwu.group_up.ui.main_screen.MainActivity;
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

    private void checkLoginState() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null ) {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            // Do nothing
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace this with your actual authentication logic (e.g., API call to server, Firebase Authentication, etc.)
        // For demo purposes, let's assume any non-empty username/password is valid
        return !username.isEmpty() && !password.isEmpty();
    }
}