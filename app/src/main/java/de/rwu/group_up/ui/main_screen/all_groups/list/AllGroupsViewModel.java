package de.rwu.group_up.ui.main_screen.all_groups.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.rwu.group_up.data.model.IGroupReadable;

public class AllGroupsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<ArrayList<IGroupReadable>> mGroupReadable;

    public AllGroupsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is all groups fragment");
        mGroupReadable = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}