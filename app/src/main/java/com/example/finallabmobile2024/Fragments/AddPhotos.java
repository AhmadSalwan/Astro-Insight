package com.example.finallabmobile2024.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.finallabmobile2024.Database.DatabaseContract;
import com.example.finallabmobile2024.Database.DatabaseHelper;
import com.example.finallabmobile2024.Database.ImageDatabaseManager;
import com.example.finallabmobile2024.Models.Images;
import com.example.finallabmobile2024.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddPhotos extends Fragment {
    static Uri imageUri;
    View view;
    ImageButton imgButton;
    EditText etTitle,etDesc;
    Button submit;
    Images images;
    ActivityResultLauncher<Intent> resultLauncher;
    ImageDatabaseManager dbManager;
    boolean cekGambar=false;
    private ImageDatabaseManager imageHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_add_photos,container,false);
        imgButton=view.findViewById(R.id.iv_edit);
        etTitle=view.findViewById(R.id.et_title);
        etDesc=view.findViewById(R.id.et_desc);
        submit=view.findViewById(R.id.button_submit);
        imageHelper=ImageDatabaseManager.getInstance(getContext());
        imageHelper.open();
        images=new Images();
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGallery=new Intent(Intent.ACTION_PICK);
                openGallery.setType("image/*");
                resultLauncher.launch(openGallery);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cekGambar){
                    String title=etTitle.getText().toString();
                    String description=etDesc.getText().toString();
                    String Uri=String.valueOf(imageUri);
                    images.setTitle(title);
                    images.setDescription(description);
                    images.setImage(Uri);
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(DatabaseContract.ImagesColumns.TITLE,title);
                    contentValues.put(DatabaseContract.ImagesColumns.DESCRIPTION,description);
                    contentValues.put(DatabaseContract.ImagesColumns.IMAGE,Uri);
                    long result=imageHelper.insert(contentValues);
                    if(result>0){
                        images.setId((int)result);
                    }else {
                        Toast.makeText(requireContext(), "Failed to Add", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(requireContext(),"Image Saved Succesfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(requireContext(),"Select an Image",Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerResult();
        return view;
    }
    private void registerResult(){
        resultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            Intent data = result.getData();
                            if (data != null) {
                                imageUri = data.getData();
                                imgButton.setImageURI(imageUri);
                                cekGambar=true;
                            } else {
                                Toast.makeText(requireContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }
}