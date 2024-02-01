package de.rwu.group_up;

import java.util.HashMap;



public class User {

    public User() {
        initInterestsMap();
    }

    private void initInterestsMap() {
        interestsMap = new HashMap<>();
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

    public enum Gender {
        Male,
        Female,
        NonBinary,
        None
    }

    private String profileImageUrl;
    private String email;
    private String name;
    private int age;
    private Gender gender;
    private HashMap<String, Boolean> interestsMap;
    private String otherInfo;

    public String getProfileImageUrl() {
        return profileImageUrl;
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

    public Gender getGender() {
        return gender;
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return interestsMap;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setUser(String profileImageUrl, String email, String name, int age, Gender gender, HashMap<String, Boolean> interestsMap, String otherInfo) {
        this.profileImageUrl = profileImageUrl;
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.interestsMap = interestsMap;
        this.otherInfo = otherInfo;
    }
}
