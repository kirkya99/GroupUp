package de.rwu.group_up.ui.main_screen.group.create;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import de.rwu.group_up.data.local.DatabaseController;
import de.rwu.group_up.data.local.GroupDatabaseController;
import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.IGroupModifiable;
import de.rwu.group_up.utils.UserManager;

public class CreateGroupViewModel extends ViewModel {

    private IGroupModifiable newGroup;

    public CreateGroupViewModel(){
        this.newGroup = new Group();
        this.newGroup.setOwnerId(UserManager.getInstance().getUid());
        this.newGroup.setOwnerName(UserManager.getInstance().getName());

    }

    public IGroupModifiable getGroup(){
        return this.newGroup;
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
}
