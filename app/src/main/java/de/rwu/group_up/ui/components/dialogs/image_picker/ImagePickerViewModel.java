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

import com.google.type.DateTime;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import de.rwu.group_up.utils.UserManager;

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
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = localDateTime.format(formatter);
        this.imageUri = FileProvider.getUriForFile(
                context, "de.rwu.group_up.fileprovider",
                new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        "IMG_" + formattedDate + ".jpg"));
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

}
