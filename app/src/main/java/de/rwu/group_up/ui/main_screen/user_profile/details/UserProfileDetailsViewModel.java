package de.rwu.group_up.ui.main_screen.user_profile.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.rwu.group_up.data.model.IUserReadable;
import de.rwu.group_up.data.model.User;

public class UserProfileDetailsViewModel extends ViewModel {



    private MutableLiveData<IUserReadable> mUser;

    public UserProfileDetailsViewModel() {
        this.mUser = new MutableLiveData<>();
    }

    public LiveData<IUserReadable> getUser() {
        return this.mUser;
    }

    public void setUser(User user) {
        this.mUser.setValue(user);
    }

}