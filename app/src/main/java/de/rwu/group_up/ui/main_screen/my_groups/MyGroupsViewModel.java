package de.rwu.group_up.ui.main_screen.my_groups;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import de.rwu.group_up.data.model.Group;

public class MyGroupsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Group>> mMyGroups;
    private HashMap<String, Boolean> userGroups;

    public MyGroupsViewModel() {
        this.mMyGroups = new MutableLiveData<>();
    }

    public void setMMyGroups(ArrayList<Group> allGroups){
        this.mMyGroups.setValue(allGroups);
    }

    public LiveData<ArrayList<Group>> getMMyGroups(){
        return this.mMyGroups;
    }

    public void setUserGroups(HashMap<String, Boolean> userGroups){
        this.userGroups = userGroups;
    }

    public HashMap<String, Boolean> getUserGroups() {
        return this.userGroups;
    }
}