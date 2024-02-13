package de.rwu.group_up.utils;

public class UserManager {
    private static UserManager instance;
    private String uid;
    private String email;
    private String name;

    private UserManager() {
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clearUserData() {
        this.uid = null;
        this.email = null;
        this.name = null;
    }
}


