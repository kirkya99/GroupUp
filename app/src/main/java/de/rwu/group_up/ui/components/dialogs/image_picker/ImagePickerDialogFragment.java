package de.rwu.group_up.ui.components.dialogs.image_picker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImagePickerDialogFragment extends DialogFragment {
    private ImagePickerViewModel imagePickerViewModel;
    private IImagePickerListener imagePickerListener;
    private static final String TAG = "ImagePickerDialogFragment";

    private Uri imageUri;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if(isGranted) {
                    this.openCamera();
                }
            });

    private ActivityResultLauncher<Intent> takeImageResultLauncher;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    dialog.dismiss();
            }
        });
        return builder.create();
    }


    private void openCamera(){

    }

    public void setImagePickerListener(IImagePickerListener listener) {
        this.imagePickerListener = listener;
    }
}
