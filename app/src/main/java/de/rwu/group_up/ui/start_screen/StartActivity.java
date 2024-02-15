package de.rwu.group_up.ui.start_screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import de.rwu.group_up.R;
import de.rwu.group_up.ui.start_screen.launch.LaunchFragment;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        LaunchFragment launchFragment = new LaunchFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.start_container, launchFragment, "launchFragment");
        transaction.commit();
    }
}
