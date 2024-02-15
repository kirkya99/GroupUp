package de.rwu.group_up.ui.main_screen.my_groups.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import de.rwu.group_up.data.model.Group;

public class MyGroupsViewModel extends ViewModel {

    private ArrayList<Group> myGroups;
    private HashMap<String, Boolean> userGroups;

    public MyGroupsViewModel() {
        this.myGroups = new ArrayList<>();
        this.userGroups = new HashMap<>();
    }

    public void setUserGroups(HashMap<String, Boolean> userGroups) {
        this.userGroups = userGroups;
    }

    public void setMyGroups(ArrayList<Group> myGroups){
        for (Group group : myGroups) {
            if(userGroups.containsKey(group.getGroupName())) {
                this.myGroups.add(group);
            }
        }
    }

    public ArrayList<Group> getMyGroups() {
        return this.myGroups;
    }
}