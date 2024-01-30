package de.rwu.group_up.ui.user_profile.view;

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