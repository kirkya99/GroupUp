package de.rwu.group_up;

import java.util.HashMap;



public class User {

    public User() {
        interestsMap = new HashMap<>();
        interestsMap.put("Soccer", R.id.chip_Soccer);
        interestsMap.put("Basketball", R.id.chip_Basketball);
        interestsMap.put("Tennis", R.id.chip_Tennis);
        interestsMap.put("Swimming", R.id.chip_Swimming);
        interestsMap.put("Running", R.id.chip_Running);
        interestsMap.put("Cycling", R.id.chip_Cycling);
        interestsMap.put("Yoga", R.id.chip_Yoga);
        interestsMap.put("Martial Arts", R.id.chip_Martial_Arts);

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
        interestsMap.put("Gym", R.id.chip_Gym);
        interestsMap.put("Weightlifting", R.id.chip_Weightlifting);
        interestsMap.put("CrossFit", R.id.chip_CrossFit);
        interestsMap.put("Pilates", R.id.chip_Pilates);
        interestsMap.put("Nutrition", R.id.chip_Nutrition);
        interestsMap.put("Meditation", R.id.chip_Meditation);

// Social Interests
        interestsMap.put("Volunteering", R.id.chip_Volunteering);
        interestsMap.put("Community Service", R.id.chip_Community_Service);
        interestsMap.put("Networking", R.id.chip_Networking);
        interestsMap.put("Socializing", R.id.chip_Socializing);
        interestsMap.put("Event Planning", R.id.chip_Event_Planning);

// Miscellaneous
        interestsMap.put("Collecting", R.id.chip_Collecting);
        interestsMap.put("DIY Projects", R.id.chip_DIY_Projects);
        interestsMap.put("Board Games", R.id.chip_Board_Games);
        interestsMap.put("Pet Care", R.id.chip_Pet_Care);
        interestsMap.put("Fashion", R.id.chip_Fashion);
        interestsMap.put("Movies and TV Series", R.id.chip_Movies_and_TV_Series);
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

    public HashMap<String, Boolean> getInterests() {
        return interests;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setUser(String profileImageUrl, String email, String name, int age, Gender gender, HashMap<String, Boolean> interests, String otherInfo) {
        this.profileImageUrl = profileImageUrl;
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
        this.otherInfo = otherInfo;
    }
}
