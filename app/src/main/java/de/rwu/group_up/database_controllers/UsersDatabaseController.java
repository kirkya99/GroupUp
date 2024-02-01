package de.rwu.group_up.database_controllers;

import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UsersDatabaseController {
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public UsersDatabaseController() {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }
}
