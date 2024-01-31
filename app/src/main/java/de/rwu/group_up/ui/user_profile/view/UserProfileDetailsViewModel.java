package de.rwu.group_up.ui.user_profile.view;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import de.rwu.group_up.MainActivity;
import de.rwu.group_up.StartActivity;

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