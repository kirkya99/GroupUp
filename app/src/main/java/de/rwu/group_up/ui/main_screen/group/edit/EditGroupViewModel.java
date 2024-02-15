package de.rwu.group_up.ui.main_screen.group.edit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.IGroupModifiable;
import de.rwu.group_up.utils.GroupManager;

public class EditGroupViewModel extends ViewModel {

    private String groupName;
    private MutableLiveData<IGroupModifiable> mGroup;
    private IGroupModifiable group;


    public EditGroupViewModel(){
        this.groupName = GroupManager.getInstance().getName();
        this.mGroup = new MutableLiveData<>();
    }

    public String getGroupName(){
        return this.groupName;
    }

    public LiveData<IGroupModifiable> getGroupModifiableLiveData(){
        return this.mGroup;
    }

    public IGroupModifiable getGroup(){
        return this.group;
    }

    public void setGroup(Group group){
        this.group = group;
        this.mGroup.setValue(group);
    }

    public void setGroupName(String groupName){
        this.group.setGroupName(groupName);
    }

    public void setGroupDescription(String groupDescription){
        this.group.setGroupDescription(groupDescription);
    }

    public void setGroupLocation(String groupLocation) {
        this.group.setLocation(groupLocation);
    }

    public void setInterestsMapItem(String key, Boolean value) {
        this.group.getInterestsMap().put(key, value);
    }


}
