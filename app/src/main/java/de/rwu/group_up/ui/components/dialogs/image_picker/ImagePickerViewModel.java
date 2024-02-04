package de.rwu.group_up.ui.components.dialogs.image_picker;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImagePickerViewModel extends ViewModel {

    private final MutableLiveData<Uri> selectedImageUri = new MutableLiveData<>();

    public LiveData<Uri> getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setImageUri(Uri uri) {
        selectedImageUri.setValue(uri);
    }

//    public Uri GetImageUri(Context context, Bitmap bitmap) {
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.DISPLAY_NAME, "image.jpg");
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
//        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
//
//        ContentResolver resolver = context.getContentResolver();
//        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//        try {
//            OutputStream fos = resolver.openOutputStream(uri);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            assert fos != null;
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return uri;
//    }

}
