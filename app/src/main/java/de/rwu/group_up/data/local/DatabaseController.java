package de.rwu.group_up.data.local;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import de.rwu.group_up.data.model.IUserModifiable;
import de.rwu.group_up.data.model.IUserReadable;
import de.rwu.group_up.data.model.User;
import de.rwu.group_up.utils.UserManager;

public class DatabaseController implements UserDatabaseController {
    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore firebaseFirestore;
    private String userId;

    private static final String TAG = "DatabaseController";
    private static final String USERS = "users";

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
}


