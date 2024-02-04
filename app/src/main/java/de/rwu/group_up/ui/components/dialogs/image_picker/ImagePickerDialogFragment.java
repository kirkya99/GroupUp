package de.rwu.group_up.ui.components.dialogs.image_picker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;


public class ImagePickerDialogFragment extends DialogFragment {
    private ImagePickerViewModel imagePickerViewModel;
    private IImagePickerListener imagePickerListener;
    private static final String TAG = "ImagePickerDialogFragment";

    private ActivityResultLauncher<Uri> takePicture;

//    private ActivityResultLauncher<Intent> mGetContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.imagePickerViewModel = new ViewModelProvider(this).get(ImagePickerViewModel.class);
//        this.mGetContent = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    Log.i(TAG, "Result code: " + result.getResultCode());
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        if (data != null && data.getData() != null) {
//                            Uri imageUri = data.getData();
//
//                            Log.e(TAG, "Result code: " + imageUri.toString());
//
//                            this.imagePickerListener.onImagePicked(imageUri);
//                        } else {
//                            this.imagePickerListener.onImagePicked(null);
//                        }
//                    }
//                }
//        );

        this.takePicture = registerForActivityResult(
                new ActivityResultContracts.TakePicture(), result -> {
                    if(result) {
                        Log.d(TAG, "Image was saved");
                    } else {
                        Log.e(TAG, "Image was not saved");
                    }
                });

        this.imagePickerViewModel.setImageUri(getContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        this.setImagePickerListener((IImagePickerListener) getParentFragment());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Choose an option").setItems(new String[]{"Open Gallery", "Open Camera", "Remove Image", "Cancel"}, (dialog, which) -> {
            switch (which) {
                case 0:
//                    this.openGallery(mGetContent);
                    break;
                case 1:
                    this.takePicture.launch(this.imagePickerViewModel.getImageUri());
                    this.imagePickerListener.onImagePicked(this.imagePickerViewModel.getImageUri());
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
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
