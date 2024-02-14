package de.rwu.group_up.data.model;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.utils.UserManager;

public class User implements IUserModifiable, IUserReadable {

    public User() {
        UserManager userManager = UserManager.getInstance();
        uid = userManager.getUid();
        email = userManager.getEmail();
        initInterestsMap();
        initMyGroups();
    }

    private void initMyGroups(){
        if(this.myGroups == null) {
            this.myGroups = new HashMap<>();
        }
    }

    private void initInterestsMap() {
        this.interestsMap = new HashMap<>();

        // Sport Activities
        this.interestsMap.put("Soccer", false);
        this.interestsMap.put("Basketball", false);
        this.interestsMap.put("Tennis", false);
        this.interestsMap.put("Swimming", false);
        this.interestsMap.put("Running", false);
        this.interestsMap.put("Cycling", false);
        this.interestsMap.put("Yoga", false);
        this.interestsMap.put("Martial Arts", false);

        // Outdoor Activities
        this.interestsMap.put("Hiking", false);
        this.interestsMap.put("Camping", false);
        this.interestsMap.put("Fishing", false);
        this.interestsMap.put("Hunting", false);
        this.interestsMap.put("Birdwatching", false);
        this.interestsMap.put("Gardening", false);

        // Creative Hobbies
        this.interestsMap.put("Drawing", false);
        this.interestsMap.put("Painting", false);
        this.interestsMap.put("Photography", false);
        this.interestsMap.put("Writing", false);
        this.interestsMap.put("Music", false);
        this.interestsMap.put("Dance", false);
        this.interestsMap.put("Acting", false);

        // Technology and Gaming
        this.interestsMap.put("Video Games", false);
        this.interestsMap.put("Programming", false);
        this.interestsMap.put("Web Development", false);
        this.interestsMap.put("App Development", false);
        this.interestsMap.put("Electronics", false);
        this.interestsMap.put("Robotics", false);

        // Culinary Arts
        this.interestsMap.put("Cooking", false);
        this.interestsMap.put("Baking", false);
        this.interestsMap.put("Food Tasting", false);
        this.interestsMap.put("Wine Tasting", false);
        this.interestsMap.put("Mixology", false);

        // Travel
        this.interestsMap.put("Backpacking", false);
        this.interestsMap.put("Adventure Travel", false);
        this.interestsMap.put("Cultural Exploration", false);
        this.interestsMap.put("Sightseeing", false);
        this.interestsMap.put("Road Trips", false);

        // Academic Interests
        this.interestsMap.put("Science", false);
        this.interestsMap.put("Mathematics", false);
        this.interestsMap.put("History", false);
        this.interestsMap.put("Literature", false);
        this.interestsMap.put("Philosophy", false);
        this.interestsMap.put("Psychology", false);

        // Health and Fitness
        this.interestsMap.put("Gym", false);
        this.interestsMap.put("Weightlifting", false);
        this.interestsMap.put("CrossFit", false);
        this.interestsMap.put("Pilates", false);
        this.interestsMap.put("Nutrition", false);
        this.interestsMap.put("Meditation", false);

        // Social Interests
        this.interestsMap.put("Volunteering", false);
        this.interestsMap.put("Community Service", false);
        this.interestsMap.put("Networking", false);
        this.interestsMap.put("Socializing", false);
        this.interestsMap.put("Event Planning", false);

        // Miscellaneous
        this.interestsMap.put("Collecting", false);
        this.interestsMap.put("DIY Projects", false);
        this.interestsMap.put("Board Games", false);
        this.interestsMap.put("Pet Care", false);
        this.interestsMap.put("Fashion", false);
        this.interestsMap.put("Movies and TV Series", false);
    }

    private String uid;
    private String profileImageUrl;
    private String email;
    private String name;
    private int age;
    private String gender;
    private HashMap<String, Boolean> interestsMap;
    private String otherInfo;
    private HashMap<String, Boolean> myGroups;


    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getNumericAge() {
        return age;
    }

    public String getStringAge() {
        return String.valueOf(this.age);
    }

    public String getGender() {
        return gender;
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return interestsMap;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public HashMap<String, Boolean> getMyGroups() {
        return this.myGroups;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        try {
            // Attempt to parse the input string to an integer
            this.age = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            // Handle the case where parsing fails
            // For example, return a special value to indicate parsing failure
            this.age = Integer.MIN_VALUE;
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterestsMap(HashMap<String, Boolean> interestsMap) {
        this.interestsMap = interestsMap;
    }

    public void setInterestsMapItem(String key, Boolean value) {
        interestsMap.put(key, value);
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public void setMyGroups(HashMap<String, Boolean> myGroups) {
        this.myGroups = myGroups;
    }

    public void setMyGroupsItem(String groupName, Boolean isOwner) {
        this.initMyGroups();
        this.myGroups.put(groupName, isOwner);
    }

    public void deleteMyGroupsItem(String groupName) {
        this.myGroups.remove(groupName);
    }

    public boolean myGroupsContains(String groupName) {
        return this.myGroups.containsKey(groupName);
    }

    // Saving the User instance as a Hash map format for storing in the database
    public static HashMap<String, Object> toHashMap(User user) {
        HashMap<String, Object> userHashMap = new HashMap<>();
        userHashMap.put("userId", user.getUid());
        userHashMap.put("name", user.getName());
        userHashMap.put("email", user.getEmail());
        userHashMap.put("age", user.getNumericAge());
        userHashMap.put("gender", user.getGender());
        userHashMap.put("interestsMap", new HashMap<>(user.getInterestsMap()));
        userHashMap.put("otherInfo", user.getOtherInfo());
        userHashMap.put("myGroups", user.getMyGroups());
        return userHashMap;
    }

    // Retrieving the user instance from the hashmap
    public static User fromHashMap(Map<String, Object> userHashMap) {
        User user = new User();
        user.setUid((String) userHashMap.get("userId"));
        user.setName((String) userHashMap.get("name"));
        user.setEmail((String) userHashMap.get("email"));
        user.setAge((userHashMap.get("age").toString()));
        user.setGender((String) userHashMap.get("gender"));
        user.setInterestsMap((HashMap<String, Boolean>) userHashMap.get("interestsMap"));
        user.setOtherInfo((String) userHashMap.get("otherInfo"));
        user.setMyGroups((HashMap<String, Boolean>) userHashMap.get("myGroups"));
        return user;
    }
}
