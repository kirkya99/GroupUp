package de.rwu.group_up.data.model;

import java.util.HashMap;

import de.rwu.group_up.utils.UserManager;

public class User implements IUserModifiable, IUserReadable {

    public User() {
        // set uid and email for newUser here?
        UserManager userManager = UserManager.getInstance();
        uid = userManager.getUid();
        email = userManager.getUid();
        initInterestsMap();
    }

    private void initInterestsMap() {
        interestsMap = new HashMap<>();

        // Sport Activities
        interestsMap.put("Soccer", false);
        interestsMap.put("Basketball", false);
        interestsMap.put("Tennis", false);
        interestsMap.put("Swimming", false);
        interestsMap.put("Running", false);
        interestsMap.put("Cycling", false);
        interestsMap.put("Yoga", false);
        interestsMap.put("Martial Arts", false);

        // Outdoor Activities
        interestsMap.put("Hiking", false);
        interestsMap.put("Camping", false);
        interestsMap.put("Fishing", false);
        interestsMap.put("Hunting", false);
        interestsMap.put("Birdwatching", false);
        interestsMap.put("Gardening", false);

        // Creative Hobbies
        interestsMap.put("Drawing", false);
        interestsMap.put("Painting", false);
        interestsMap.put("Photography", false);
        interestsMap.put("Writing", false);
        interestsMap.put("Music", false);
        interestsMap.put("Dance", false);
        interestsMap.put("Acting", false);

        // Technology and Gaming
        interestsMap.put("Video Games", false);
        interestsMap.put("Programming", false);
        interestsMap.put("Web Development", false);
        interestsMap.put("App Development", false);
        interestsMap.put("Electronics", false);
        interestsMap.put("Robotics", false);

        // Culinary Arts
        interestsMap.put("Cooking", false);
        interestsMap.put("Baking", false);
        interestsMap.put("Food Tasting", false);
        interestsMap.put("Wine Tasting", false);
        interestsMap.put("Mixology", false);

        // Travel
        interestsMap.put("Backpacking", false);
        interestsMap.put("Adventure Travel", false);
        interestsMap.put("Cultural Exploration", false);
        interestsMap.put("Sightseeing", false);
        interestsMap.put("Road Trips", false);

        // Academic Interests
        interestsMap.put("Science", false);
        interestsMap.put("Mathematics", false);
        interestsMap.put("History", false);
        interestsMap.put("Literature", false);
        interestsMap.put("Philosophy", false);
        interestsMap.put("Psychology", false);

        // Health and Fitness
        interestsMap.put("Gym", false);
        interestsMap.put("Weightlifting", false);
        interestsMap.put("CrossFit", false);
        interestsMap.put("Pilates", false);
        interestsMap.put("Nutrition", false);
        interestsMap.put("Meditation", false);

        // Social Interests
        interestsMap.put("Volunteering", false);
        interestsMap.put("Community Service", false);
        interestsMap.put("Networking", false);
        interestsMap.put("Socializing", false);
        interestsMap.put("Event Planning", false);

        // Miscellaneous
        interestsMap.put("Collecting", false);
        interestsMap.put("DIY Projects", false);
        interestsMap.put("Board Games", false);
        interestsMap.put("Pet Care", false);
        interestsMap.put("Fashion", false);
        interestsMap.put("Movies and TV Series", false);
    }

    private String uid;
    private String profileImageUrl;
    private String email;
    private String name;
    private int age;
    private String gender;
    private HashMap<String, Boolean> interestsMap;
    private String otherInfo;

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

    public int getAge() {
        return age;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterestsMap(HashMap<String, Boolean> interestsMap) {
        this.interestsMap = interestsMap;
    }

    public void setInterestsMapItem(String key, Boolean value){
        interestsMap.put(key, value);
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public static HashMap<String, Object> toHashMap(User user) {
        HashMap<String, Object> userHashMap = new HashMap<>();
        userHashMap.put("userId", user.getUid());
        userHashMap.put("name", user.getName());
        userHashMap.put("age", user.getAge());
        userHashMap.put("gender", user.getGender());
        userHashMap.put("interestsMap", user.getInterestsMap());
        userHashMap.put("otherInfo", user.getOtherInfo());
        return userHashMap;
    }

    public static IUserModifiable fromHashMap(HashMap<String, Object> userHashMap) {
        IUserModifiable user = new User();
        user.setUid((String) userHashMap.get("userId"));
        user.setName((String) userHashMap.get("name"));
        user.setAge((int) userHashMap.get("age"));
        user.setGender((String) userHashMap.get("gender"));
        user.setInterestsMap((HashMap<String, Boolean>) userHashMap.get("interestsMap"));
        user.setOtherInfo((String) userHashMap.get("otherInfo"));
        return user;
    }
}
