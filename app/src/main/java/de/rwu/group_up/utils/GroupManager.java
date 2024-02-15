package de.rwu.group_up.utils;

public class GroupManager {
    private static GroupManager instance;
    private String name;

    private GroupManager() {
    }

    public static synchronized GroupManager getInstance() {
        if (instance == null) {
            instance = new GroupManager();
        }
        return instance;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clearUserData() {
        this.name = null;
    }
}
