package de.rwu.group_up.data.model;

import java.util.HashMap;

public interface IUserModifiable {
    static final String NONE = "None";
    static final String MALE = "Male";
    static final String FEMALE = "Female";
    static final String NON_BINARY = "Non-binary";
    static final String OTHER = "Other gender identity";
    static final String[] GENDERS = {NONE, MALE, FEMALE, NON_BINARY, OTHER};

    HashMap<String, Boolean> getInterestsMap();

    void setProfileImageUrl(String profileImageUrl);

    void setUid(String uid);

    void setEmail(String email);

    void setName(String name);

    void setAge(int age);

    void setGender(String gender);

    void setInterestsMap(HashMap<String, Boolean> interestsMap);

    void setInterestsMapItem(String key, Boolean value);

    void setOtherInfo(String otherInfo);
}
