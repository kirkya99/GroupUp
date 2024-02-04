package de.rwu.group_up.ui.components.dialogs.image_picker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import de.rwu.group_up.R;

public class ImagePickerDialogFragment extends DialogFragment {
    private IImagePickerListener imagePickerListener;
    private static final String TAG = "ImagePickerDialogFragment";
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        this.setImagePickerListener((IImagePickerListener) getParentFragment());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.i(this.TAG, "Result code: " + result.getResultCode());
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data != null && data.getData() != null) {
                            Uri imageUri = data.getData();
                            this.imagePickerListener.onImagePicked(imageUri);
                        }
                        else {
                            this.imagePickerListener.onImagePicked(null);
                        }
                    }
                }
        );

        builder.setTitle("Choose an option").setItems(new String[]{"Open Gallery", "Open Camera", "Remove Image", "Cancel"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    this.openGallery(mGetContent);
                    break;
                case 1:
                    this.dispatchTakePictureIntent(mGetContent);
                    break;
                case 2:
                    this.imagePickerListener.onImagePicked(null);
                    break;
                case 3:
                    dialog.dismiss();
            }
        });
        return builder.create();
    }

    public void openGallery(ActivityResultLauncher<Intent> mGetContent) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        mGetContent.launch(gallery);
    }

    public void dispatchTakePictureIntent(ActivityResultLauncher<Intent> mGetContent) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mGetContent.launch(takePictureIntent);
    }

    public void setImagePickerListener(IImagePickerListener listener) {
        this.imagePickerListener = listener;
    }
}
