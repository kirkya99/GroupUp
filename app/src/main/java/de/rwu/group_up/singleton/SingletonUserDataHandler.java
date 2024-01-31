package de.rwu.group_up.singleton;

import java.util.ArrayList;

public class SingletonUserDataHandler {
    private static SingletonUserDataHandler singletonUserDataHandler = null;

    public static SingletonUserDataHandler getInstance() {
        if(singletonUserDataHandler == null) {
            singletonUserDataHandler = new SingletonUserDataHandler();
        }
        return singletonUserDataHandler;
    }

    private static String base64Image = "";
    private static String username = "";
    private static String name = "";
    private static String email = "";
    private static Integer age = 0;
    private static ArrayList<String> interests = new ArrayList<>();

    public static String getBase64Image() {
        return base64Image;
    }

    public static String getUsername() {
        return username;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static Integer getAge() {
        return age;
    }

    public static ArrayList<String> getInterests() {
        return interests;
    }

    public static void setUserData(String base64Image, String username, String name, String email, Integer age, ArrayList<String> interests) {
        SingletonUserDataHandler.base64Image = base64Image;
        SingletonUserDataHandler.username = username;
        SingletonUserDataHandler.name = name;
        SingletonUserDataHandler.email = email;
        SingletonUserDataHandler.age = age;
        SingletonUserDataHandler.interests = interests;
    }
}
