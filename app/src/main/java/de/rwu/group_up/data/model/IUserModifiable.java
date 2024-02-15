package de.rwu.group_up.data.model;

import java.util.HashMap;

public interface IUserModifiable extends IUserReadable{

    void setProfileImageUrl(String profileImageUrl);

    void setUid(String uid);

    void setEmail(String email);

    void setName(String name);

    void setAge(String age);

    void setGender(String gender);

    void setInterestsMap(HashMap<String, Boolean> interestsMap);

    void setInterestsMapItem(String key, Boolean value);

    void setOtherInfo(String otherInfo);

    void setMyGroups(HashMap<String, Boolean> myGroups);

    void setMyGroupsItem(String groupName, Boolean isOwner);

    void deleteMyGroupsItem(String groupName);
}
