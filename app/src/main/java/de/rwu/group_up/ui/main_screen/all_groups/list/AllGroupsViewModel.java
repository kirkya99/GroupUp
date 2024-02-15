package de.rwu.group_up.ui.main_screen.all_groups.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.IGroupReadable;

public class AllGroupsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Group>> mAllGroups;

    public AllGroupsViewModel(){
        this.mAllGroups = new MutableLiveData<>();
    }

    public void setMAllGroups(ArrayList<Group> allGroups){
        this.mAllGroups.setValue(allGroups);
    }

    public LiveData<ArrayList<Group>> getMAllGroups(){
        return this.mAllGroups;
    }
}