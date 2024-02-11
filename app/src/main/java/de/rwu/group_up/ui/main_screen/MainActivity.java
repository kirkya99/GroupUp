package de.rwu.group_up.ui.main_screen;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import de.rwu.group_up.R;
import de.rwu.group_up.databinding.ActivityMainBinding;
import de.rwu.group_up.ui.main_screen.all_groups.list.AllGroupsFragment;
import de.rwu.group_up.ui.main_screen.my_groups.list.MyGroupsFragment;
import de.rwu.group_up.ui.main_screen.user_profile.edit.UserProfileEditFragment;
import de.rwu.group_up.ui.main_screen.user_profile.details.UserProfileDetailsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private ActivityMainBinding binding;


    private UserProfileDetailsFragment userProfileDetailsFragment;
    private UserProfileEditFragment userProfileEditFragment;
    private MyGroupsFragment myGroupsFragment;
    private AllGroupsFragment allGroupsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnItemSelectedListener(this); // Set the listener

        // Initially selecting the first item
        navView.setSelectedItemId(R.id.navigation_user_profile_details);
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_user_profile_details) {
            setTitle("User Profile Details");
            if (userProfileDetailsFragment == null) {
                userProfileDetailsFragment = new UserProfileDetailsFragment();
            }
            fragmentNavigation(userProfileDetailsFragment, "userProfileDetailsFragment");
            return true;
        } else if (itemId == R.id.navigation_my_groups) {
            setTitle("My Groups");
            if (myGroupsFragment == null) {
                myGroupsFragment = new MyGroupsFragment();
            }
            fragmentNavigation(myGroupsFragment, "myGroupsFragment");
            return true;
        } else if (itemId == R.id.navigation_all_groups) {
            setTitle("All Groups");
            if (allGroupsFragment == null) {
                allGroupsFragment = new AllGroupsFragment();
            }
            fragmentNavigation(allGroupsFragment, "allGroupsFragment");
            return true;
        }
        return false;
    }

    private void fragmentNavigation(@NonNull Fragment fragmentInstance, @Nullable String fragmentName) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragmentInstance, fragmentName);
        fragmentTransaction.commit();
    }
}