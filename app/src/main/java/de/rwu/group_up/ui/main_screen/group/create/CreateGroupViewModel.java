package de.rwu.group_up.ui.main_screen.group.create;

import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.GroupDatabaseController;
import de.rwu.group_up.data.local.UserDatabaseController;
import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.IGroupModifiable;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.IUserReadable;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.utils.UserManager;

public class CreateGroupViewModel extends ViewModel {

    private MutableLiveData<IUserModifiable> mUser;
    private IUserModifiable user;
    private IGroupModifiable newGroup;

    public CreateGroupViewModel(){
        this.newGroup = new Group();
        this.mUser = new MutableLiveData<>();
    }

    public IGroupModifiable getGroup(){
        return this.newGroup;
    }

    public String getGroupName(){
        return this.newGroup.getGroupName();
    }

    public LiveData<IUserModifiable> getUser(){
        return this.mUser;
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return this.newGroup.getInterestsMap();
    }

    public void setGroupName(String groupName){
        this.newGroup.setGroupName(groupName);
    }

    public void setDescription(String description){
        this.newGroup.setGroupDescription(description);
    }

    public void setLocation(String location) {
        this.newGroup.setLocation(location);
    }

    public void setInterestsMapItem(String key, Boolean value) {
        this.newGroup.getInterestsMap().put(key, value);
    }

    public void setUser(User user){
        this.mUser.setValue(user);
        this.user = user;
    }

    public void setOwnerId(String userId){
        this.newGroup.setOwnerId(userId);
    }

    public void setOwnerName(String userName) {
        this.newGroup.setOwnerName(userName);
    }
}
