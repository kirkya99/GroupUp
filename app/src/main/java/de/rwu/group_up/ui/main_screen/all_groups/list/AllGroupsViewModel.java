package de.rwu.group_up.ui.main_screen.all_groups.list;

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