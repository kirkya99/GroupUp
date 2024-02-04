package de.rwu.group_up.data.local;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import de.rwu.group_up.data.model.User;

public interface UserDatabaseController {

    void createUserEntry(User user);

    User readUserEntry();

    void updateUserEntry(User user);

    void deleteUserEntry();
}
