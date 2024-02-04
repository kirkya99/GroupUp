package de.rwu.group_up.ui.components.dialogs.image_picker;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ImagePickerViewModel extends ViewModel {
    private Uri imageUri;
    private final MutableLiveData<Uri> selectedImageUri = new MutableLiveData<>();

    public LiveData<Uri> getSelectedImageUri() {
        return selectedImageUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Context context) {
        this.imageUri = FileProvider.getUriForFile(
                context, "de.rwu.group_up.fileprovider",
                new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        "profile_image.jpg"));
    }
}
