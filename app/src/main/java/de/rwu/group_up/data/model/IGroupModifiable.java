package de.rwu.group_up.data.model;

import java.util.HashMap;

public interface IGroupModifiable extends IGroupReadable{

    void setGroupName(String groupName);

    void setOwnerId(String adminId);

    void setOwnerName(String adminName);

    void setGroupDescription(String groupDescription);

    void setInterestsMap(HashMap<String, Boolean> interestsMap) ;

    void setInterestsMapItem(String key, Boolean value);

    void setLocation(String location);
}
