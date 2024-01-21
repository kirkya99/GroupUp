package de.rwu.group_up.ui.all_groups;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllGroupsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AllGroupsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is all groups fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}