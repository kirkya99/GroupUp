package de.rwu.group_up.ui.start_screen.user_profile_creation;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.User;


public class UserProfileCreationViewModel extends ViewModel{

    private IUserModifiable newUser;
    private HashMap<String, Boolean> interestsMap;

    public UserProfileCreationViewModel() {
        newUser = new User();
        // set uid and email for newUser here?
        interestsMap = newUser.getInterestsMap();
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return interestsMap;
    }

    public void setProfileImageUrl(String profileImageUrl) {

    }

    public void setEmail(String email) {
        newUser.setEmail(email);
    }

    public void setName(String name) {
        newUser.setName(name);
    }

    public void setAge(int age) {
        newUser.setAge(age);
    }

    public void setGender(String gender) {
        newUser.setGender(gender);
    }

    public void setInterestsMapItem(String key, Boolean value) {
        interestsMap.put(key, value);
    }

    public void setOtherInfo(String otherInfo) {
        newUser.setOtherInfo(otherInfo);
    }

    public void saveUserToFirestore() {
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.createUserEntry();
    }
}
