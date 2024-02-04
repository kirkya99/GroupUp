package de.rwu.group_up.data.model;

import java.util.HashMap;

public interface IUserReadable {

    static final String NONE = "None";
    static final String MALE = "Male";
    static final String FEMALE = "Female";
    static final String NON_BINARY = "Non-binary";
    static final String OTHER = "Other gender identity";
    static final String[] GENDERS = {NONE, MALE, FEMALE, NON_BINARY, OTHER};

    String getUid();

    String getProfileImageUrl();

    String getEmail();

    String getName();

    int getAge();

    String getGender();

    HashMap<String, Boolean> getInterestsMap();

    String getOtherInfo();
}
