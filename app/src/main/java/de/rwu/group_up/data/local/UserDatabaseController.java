package de.rwu.group_up.data.local;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

import de.rwu.group_up.data.model.User;
import de.rwu.group_up.ui.components.dialogs.cancel_registration.AbortRegistrationConfirmationViewModel;

public interface UserDatabaseController {

    void createUserEntry(HashMap<String, Object> userEntryHashMap);

    void readUserEntry(OnReadUserEntryListener listener);

    void updateUserEntry(HashMap<String, Object> userEntryHashMap);

    void deleteUserEntry();

    interface OnReadUserEntryListener {
        void onSuccess(User user);

        void onFailure(String errorMessage);
    }
}
