package de.rwu.group_up.data.model;

import java.util.HashMap;

public interface IGroupReadable {

    String getGroupName();

    String getAdminId();

    String getAdminName();

    String getGroupDescription();

    HashMap<String, Boolean> getInterestsMap();
    String getLocation();
}
