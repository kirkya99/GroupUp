package de.rwu.group_up.data.local;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.data.model.Group;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.utils.UserManager;

public class DatabaseController implements UserDatabaseController, GroupDatabaseController {
    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore firebaseFirestore;
    private String userId;

    private static final String TAG = "DatabaseController";
    private static final String USERS = "users";
    private static final String GROUPS = "groups";

    public DatabaseController() {
        this.firebaseStorage = FirebaseStorage.getInstance();
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.userId = UserManager.getInstance().getUid();
    }

    @Override
    public void createUserEntry(HashMap<String, Object> userEntryHashMap) {
        this.firebaseFirestore.collection(this.USERS)
                .document(this.userId)
                .set(userEntryHashMap)
                .addOnSuccessListener(aVoid -> Log.d(this.TAG, "User data added with UID: " + this.userId))
                .addOnFailureListener(e -> Log.w(this.TAG, "Error adding user data", e));
    }

    @Override
    public void readUserEntry(OnReadUserEntryListener listener) {
        DocumentReference userEntryReference = firebaseFirestore.collection(USERS).document(this.userId);

        userEntryReference.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> userData = documentSnapshot.getData();
                        User user = User.fromHashMap(userData);
                        listener.onSuccess(user);
                    } else {
                        listener.onFailure("User entry does not exist");
                    }
                })
                .addOnFailureListener(e -> {
                    listener.onFailure("Error reading user entry: " + e.getMessage());
                });
    }

    @Override
    public void updateUserEntry(HashMap<String, Object> userEntryHashMap) {
        DocumentReference userDocumentReference = firebaseFirestore.collection(this.USERS).document(this.userId);

        userDocumentReference.update(userEntryHashMap)
                .addOnSuccessListener(aVoid -> Log.d(this.TAG, "DocumentSnapshot successfully updated!"))
                .addOnFailureListener(e -> Log.e(this.TAG, "Error updating document", e));
    }

    @Override
    public void deleteUserEntry() {
        DocumentReference userDocumentReference = firebaseFirestore.collection(this.USERS).document(this.userId);

        userDocumentReference.delete()
                .addOnSuccessListener(aVoid -> Log.d(this.TAG, "DocumentSnapshot successfully deleted!"))
                .addOnFailureListener(e -> Log.e(this.TAG, "Error deleting document", e));
    }


    // Create CRUD functions for the list of groups and the interface for limiting the access to only these functions. e.g. like the UserDataBaseController

    @Override
    public void createGroupEntry(HashMap<String, Object> groupEntryHashMap, String groupName, GroupWriteListener listener) {
        DocumentReference groupEntryReference = firebaseFirestore.collection(GROUPS).document(groupName);

        groupEntryReference.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if(documentSnapshot.exists()){
                        listener.onFailure("Error: Group name already exists!");
                    }
                    else {
                        firebaseFirestore.collection(GROUPS)
                                .document(groupName)
                                .set(groupEntryHashMap)
                                .addOnSuccessListener(aVoid -> listener.onSuccess("Group entry saved"))
                                .addOnFailureListener(e -> listener.onFailure("Error: " + e.getMessage()));
                    }
                })
                .addOnFailureListener(e -> listener.onFailure("Error: " + e.getMessage()));
    }

    @Override
    public void readGroupEntry(String groupName, GroupReadListener listener) {
        DocumentReference groupEntryReference = firebaseFirestore.collection(GROUPS).document(groupName);

        groupEntryReference.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> groupData = documentSnapshot.getData();
                        Group group = Group.fromHashMap(groupData);
                        listener.onSuccess(group);
                    } else {
                        listener.onFailure("Group entry does not exist");
                    }
                })
                .addOnFailureListener(e -> {
                    listener.onFailure("Error reading user entry: " + e.getMessage());
                });
    }

    @Override
    public void readGroupEntries(GroupsReadListener listener) {
        CollectionReference groupsReference = firebaseFirestore.collection(GROUPS);

        groupsReference.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<Group> groups = new ArrayList<>();

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Map<String, Object> groupData = document.getData();
                        Group group = Group.fromHashMap(groupData);
                        groups.add(group);
                    }

                    listener.onSuccess(groups);
                })
                .addOnFailureListener(e -> listener.onFailure("Error: " + e.getMessage()));
    }

    @Override
    public void updateGroupEntry(HashMap<String, Object> groupEntryHashMap, String groupName, GroupWriteListener listener) {
        this.firebaseFirestore.collection(GROUPS)
                .document(groupName)
                .set(groupEntryHashMap)
                .addOnSuccessListener(aVoid -> listener.onSuccess("Group entry saved"))
                .addOnFailureListener(e -> listener.onFailure("Error: " + e.getMessage()));
    }

    @Override
    public void deleteGroupEntry(String groupName, GroupWriteListener listener) {
        DocumentReference groupEntryReference = firebaseFirestore.collection(GROUPS).document(groupName);

        groupEntryReference.delete()
                .addOnSuccessListener(aVoid -> listener.onSuccess("Group deleted"))
                .addOnFailureListener(e -> listener.onFailure("Error: " + e.getMessage()));
    }
}


