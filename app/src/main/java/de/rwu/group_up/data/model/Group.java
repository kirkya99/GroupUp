package de.rwu.group_up.data.model;

import java.util.HashMap;
import java.util.Map;

public class Group implements IGroupModifiable, IGroupReadable{

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

    private String groupName;
    private String adminId;
    private String adminName;
    private String groupDescription;
    private HashMap<String, Boolean> interestsMap;
    private String location;

    public String getGroupName() {
        return groupName;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminName(){
        return this.adminName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public HashMap<String, Boolean> getInterestsMap() {
        return interestsMap;
    }

    public String getLocation(){
        return this.location;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public void setInterestsMap(HashMap<String, Boolean> interestsMap) {
        this.interestsMap = interestsMap;
    }

    public void setInterestsMapItem(String key, Boolean value){
        this.interestsMap.put(key, value);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // saving the group instance as a hash map
    public static HashMap<String, Object> toHashMap(Group group) {
        HashMap<String, Object> groupHashMap = new HashMap<>();
        groupHashMap.put("name", group.getGroupName());
        groupHashMap.put("adminId", group.getAdminId());
        groupHashMap.put("adminName", group.getAdminName());
        groupHashMap.put("description", group.getGroupDescription());
        groupHashMap.put("interestsMap", group.getInterestsMap());
        groupHashMap.put("location", group.getLocation());
        return groupHashMap;
    }

    // retrieving the group instance from the hashmap
    public static Group fromHashMap(Map<String, Object> groupHashMap) {
        Group group = new Group();
        group.setAdminId((String) groupHashMap.get("adminId"));
        group.setAdminName((String) groupHashMap.get("adminName"));
        group.setGroupName((String) groupHashMap.get(("name")));
        group.setGroupDescription((String) groupHashMap.get("description"));
        group.setInterestsMap((HashMap<String, Boolean>) groupHashMap.get("interestsMap"));
        group.setLocation((String) groupHashMap.get("location"));
        return group;
    }
}
