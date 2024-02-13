package de.rwu.group_up.ui.main_screen.user_profile.edit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.utils.UserManager;

public class UserProfileEditViewModel extends ViewModel {


    private MutableLiveData<IUserModifiable> mUser;
    private IUserModifiable user;
    private String newEmail;

    // TODO: Adapt the methods for the use with user for the use with mUser

    public UserProfileEditViewModel() {
        mUser = new MutableLiveData<>();
    }

    public LiveData<IUserModifiable> getUserModifiableLiveData(){
        return this.mUser;
    }

    public String getName(){
        return this.user.getName();
    }

    public String getEmail() {
        this.newEmail = getEmail();
        return this.user.getEmail();
    }

    public void setUser(User user) {
        this.user = user;
        this.mUser.setValue(user);
    }

    public void setName(String name) {
        UserManager.getInstance().setName(name);
        this.user.setName(name);
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }


    public void setAge(String age) {
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

    private void manageEmailUpdate() {
        // TODO: Implement Email update
    }
}
