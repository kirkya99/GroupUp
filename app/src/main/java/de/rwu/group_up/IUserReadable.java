package de.rwu.group_up;

import java.util.HashMap;

public interface IUserReadable {
    String getProfileImageUrl();

    String getEmail();

    String getName();

    int getAge();

    String getGender();

    HashMap<String, Boolean> getInterestsMap();

    String getOtherInfo();
}
