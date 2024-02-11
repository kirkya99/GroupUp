package de.rwu.group_up.ui.start_screen.user_profile_creation;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.User;


public class UserProfileCreationViewModel extends ViewModel {

    private IUserModifiable newUser;

    public UserProfileCreationViewModel() {
        this.newUser = new User();
        // set uid and email for newUser here?
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return this.newUser.getInterestsMap();
    }

    public void setProfileImageUrl(String profileImageUrl) {

    }

    public void setEmail(String email) {
        this.newUser.setEmail(email);
    }

    public void setName(String name) {
        this.newUser.setName(name);
    }

    public void setAge(String age) {
        this.newUser.setAge(age);
    }

    public void setGender(String gender) {
        this.newUser.setGender(gender);
    }

    public void setInterestsMapItem(String key, Boolean value) {
        this.newUser.getInterestsMap().put(key, value);
    }

    public void setOtherInfo(String otherInfo) {
        this.newUser.setOtherInfo(otherInfo);
    }

    public void saveUserToFirestore() {
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.createUserEntry(User.toHashMap((User) this.newUser));
    }
}
