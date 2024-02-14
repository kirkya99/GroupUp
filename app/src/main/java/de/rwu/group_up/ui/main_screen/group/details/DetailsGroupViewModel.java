package de.rwu.group_up.ui.main_screen.group.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.IGroupReadable;
import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.User;

public class DetailsGroupViewModel extends ViewModel {

    private MutableLiveData<IGroupReadable> mGroup;
    private MutableLiveData<IUserModifiable> mUser;
    private IUserModifiable user;

    public DetailsGroupViewModel(){
        this.mGroup = new MutableLiveData<>();
        this.mUser = new MutableLiveData<>();
    }

    public void setUser(User user){
        this.mUser.setValue(user);
        this.user = user;
    }

    public void setGroup(Group group){
        this.mGroup.setValue(group);
    }

    public LiveData<IGroupReadable> getGroup(){
        return this.mGroup;
    }

    public LiveData<IUserModifiable> getUser() {
        return this.mUser;
    }

    public void addGroupReference(String groupName) {
        this.user.setMyGroupsItem(groupName, false);
    }

    public void removeGroupReference(String groupName) {
        this.user.deleteMyGroupsItem(groupName);
    }
}
