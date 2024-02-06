package de.rwu.group_up.ui.main_screen.user_profile.edit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.User;

public class UserProfileEditViewModel extends ViewModel {


    private MutableLiveData<IUserModifiable> mUser;
    private IUserModifiable user;
    private String newEmail;

    // TODO: Adapt the methods for the use with user for the use with mUser

    public UserProfileEditViewModel() {
        mUser = new MutableLiveData<>();
    }

    public String getName(){
        return this.user.getName();
    }

    public String getEmail() {
        this.newEmail = getEmail();
        return this.user.getEmail();
    }

    public int getAge() {
        return this.user.getAge();
    }

    public String getGender(){
        return this.user.getGender();
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return this.user.getInterestsMap();
    }

    public String getOtherInfo() {
        return this.user.getOtherInfo();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setName(String name) {
        user.setName(name);
    }

    public void setAge(int age) {
        user.setAge(age);
    }

    public void setGender(String gender) {
        user.setGender(gender);
    }

    public void setInterestsMapItem(String key, Boolean value) {
        this.user.getInterestsMap().put(key, value);
    }

    public void setOtherInfo(String otherInfo) {
        user.setOtherInfo(otherInfo);
    }

    public void saveUserToFirestore() {
        this.manageEmailUpdate();
        UserDatabaseController userDatabaseController = new DatabaseController();
        userDatabaseController.updateUserEntry(User.toHashMap((User) user));
    }

    public int parseInteger(String input) {
        try {
            // Attempt to parse the input string to an integer
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Handle the case where parsing fails
            // For example, return a special value to indicate parsing failure
            return Integer.MIN_VALUE;
        }
    }

    private void manageEmailUpdate() {
        // TODO: Implement Email update
    }
}
