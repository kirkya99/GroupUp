package de.rwu.group_up.data.model;

import java.util.HashMap;

public interface IUserReadable {

    String NONE = "None";
    String MALE = "Male";
    String FEMALE = "Female";
    String NON_BINARY = "Non-binary";
    String OTHER = "Other gender identity";
    String[] GENDERS = {NONE, MALE, FEMALE, NON_BINARY, OTHER};

    String getUid();

    String getProfileImageUrl();

    String getEmail();

    String getName();

    int getAge();

    String getGender();

    HashMap<String, Boolean> getInterestsMap();

    String getOtherInfo();
}
