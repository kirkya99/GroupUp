package de.rwu.group_up;

import java.util.HashMap;

public interface IUserModifiable {
    HashMap<String, Boolean> getInterestsMap();

    void setProfileImageUrl(String profileImageUrl);

    void setEmail(String email);

    void setName(String name);

    void setAge(int age);

    void setGender(String gender);

    void setInterestsMapItem(String key, Boolean value);

    void setOtherInfo(String otherInfo);
}
