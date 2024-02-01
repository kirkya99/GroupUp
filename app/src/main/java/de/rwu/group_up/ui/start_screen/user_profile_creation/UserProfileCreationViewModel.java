package de.rwu.group_up.ui.start_screen.user_profile_creation;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import de.rwu.group_up.User;
import de.rwu.group_up.ui.user_profile.view.UserProfileDetailsViewModel;

public class UserProfileCreationViewModel extends ViewModel {

    private User newUser;
    private HashMap<String, Boolean> interestsMap;

    public UserProfileCreationViewModel() {
        newUser = new User();
        interestsMap = newUser.getInterestsMap();
    }
}
