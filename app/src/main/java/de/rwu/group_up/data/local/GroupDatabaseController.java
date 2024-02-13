package de.rwu.group_up.data.local;

import java.util.ArrayList;
import java.util.HashMap;

import de.rwu.group_up.data.model.Group;

public interface GroupDatabaseController {

    void createGroupEntry(HashMap<String, Object> groupEntryHashMap, String groupName, GroupWriteListener listener);
    void readGroupEntry(String groupName, GroupReadListener listener);
    void readGroupEntries(GroupsReadListener listener);
    void updateGroupEntry(HashMap<String, Object> groupEntryHashMap, String groupName, GroupWriteListener listener);
    void deleteGroupEntry(String groupName, GroupWriteListener listener);

    interface GroupWriteListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    interface GroupReadListener {
        void onSuccess(Group group);

        void onFailure(String message);
    }

    interface GroupsReadListener {
        void onSuccess(ArrayList<Group> groupsList);

        void onFailure(String message);
    }
}
