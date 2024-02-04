package de.rwu.group_up.ui.main_screen.user_profile.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileDetailsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UserProfileDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is user profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}