package de.rwu.group_up;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.databinding.ActivityMainBinding;
import de.rwu.group_up.ui.all_groups.AllGroupsFragment;
import de.rwu.group_up.ui.login.LoginFragment;
import de.rwu.group_up.ui.login.LoginViewModel;
import de.rwu.group_up.ui.my_groups.MyGroupsFragment;
import de.rwu.group_up.ui.user_profile.edit.UserProfileEditFragment;
import de.rwu.group_up.ui.user_profile.view.UserProfileDetailsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private ActivityMainBinding binding;

    // ViewModels
    private LoginViewModel loginViewModel;

    // Fragments
    private LoginFragment loginFragment;
    private UserProfileDetailsFragment userProfileDetailsFragment;
    private UserProfileEditFragment userProfileEditFragment;
    private MyGroupsFragment myGroupsFragment;
    private AllGroupsFragment allGroupsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnItemSelectedListener(this); // Set the listener

        // Initially selecting the first item
//        navView.setSelectedItemId(R.id.navigation_user_profile_details);
    }

    private void checkLoginStatus() {
        // Check if user is logged in
        if (loginViewModel.isLoggedIn()) {
            showNavigationMenu();
        } else {
            showLoginFragment();
        }
    }

    private void showLoginFragment() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);

        if (loginFragment == null) {
            loginFragment = new LoginFragment();
            loginFragment.setLoginViewModel(loginViewModel);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, loginFragment, "loginFragment");
        fragmentTransaction.commit();
    }

    private void showNavigationMenu() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnItemSelectedListener(this);
        navView.setVisibility(View.VISIBLE);

        if (userProfileDetailsFragment == null) {
            userProfileDetailsFragment = new UserProfileDetailsFragment();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, userProfileDetailsFragment, "userProfileDetailsFragment");
        fragmentTransaction.commit();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_user_profile_details) {
            setTitle("User Profile Details");
            if (userProfileDetailsFragment == null) {
                userProfileDetailsFragment = new UserProfileDetailsFragment();
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, userProfileDetailsFragment, "userProfileDetailsFragment");
            fragmentTransaction.commit();
            return true;
        } else if (itemId == R.id.navigation_my_groups) {
            setTitle("My Groups");
            if (myGroupsFragment == null) {
                myGroupsFragment = new MyGroupsFragment();
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, myGroupsFragment, "myGroupsFragment");
            fragmentTransaction.commit();
            return true;
        } else if (itemId == R.id.navigation_all_groups) {
            setTitle("All Groups");
            if (allGroupsFragment == null) {
                allGroupsFragment = new AllGroupsFragment();
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, allGroupsFragment, "allGroupsFragment");
            fragmentTransaction.commit();
            return true;
        } else if (itemId == R.id.logout) {
            // TODO: Implement logout logic
            return true;
        }
        return false;
    }
}