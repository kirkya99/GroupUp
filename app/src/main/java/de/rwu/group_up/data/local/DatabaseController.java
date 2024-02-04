package de.rwu.group_up.data.local;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    public void createUserEntry(User user) {
        this.firebaseFirestore.collection(this.USERS)
                .document(this.userId)
                .set(user)
                .addOnSuccessListener(aVoid -> Log.d(this.TAG, "User data added with UID: " + this.userId))
                .addOnFailureListener(e -> Log.w(this.TAG, "Error adding user data", e));
    }

    @Override
    public User readUserEntry() {
        DocumentReference userDocumentReference = firebaseFirestore.collection(this.USERS).document(this.userId);
        final User[] user = new User[1];
        userDocumentReference.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                user[0] = documentSnapshot.toObject(User.class);
                Log.d(this.TAG, "User retrieved: " + user[0].toString());
            } else {
                Log.d(this.TAG, "No such document");
            }
        }).addOnFailureListener(e -> {
            Log.e(this.TAG, "Error fetching user: ", e);
        });
        return user[0];
    }

    @Override
    public void updateUserEntry(User user) {
        DocumentReference userDocumentReference = firebaseFirestore.collection(this.USERS).document(this.userId);

        userDocumentReference.set(user)
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


