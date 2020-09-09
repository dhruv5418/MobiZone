package com.example.mobizone.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobizone.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 *  @author Dhruv Patel
 *   @author Gakhar Tanvi
 *  @author Sarbjit Kaur
 *  @author Kamaljit Kaur
 *  @author Akshay Varma
 *  This java class is for profile activity
 */
public class ProfileActivity extends AppCompatActivity {
    /**
     * object of FirebaseAuth
     */
    private FirebaseAuth auth;
    /**
     * object of FirebaseUser
     */
    private FirebaseUser curUser;
    /**
     * object of CircleImageView
     */
    CircleImageView imageView;
    /**
     * object of toolbar
     */
    Toolbar toolbar;
    /**
     * object of FirebaseFirestore
     */
    private FirebaseFirestore db;

    /**
     * oncreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar=findViewById(R.id.toolbar_profile);
        auth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        imageView=findViewById(R.id.img_p);
        imageView.setOnClickListener(changeProfile);
        loadData();
    }
    void loadData() {
        curUser = auth.getCurrentUser();
        if (curUser != null) {
            if (curUser.getPhotoUrl() != null) {
                Glide.with(getApplicationContext())
                        .load(curUser.getPhotoUrl())
                        .into(imageView);
            }
        }
    }

    /**
     * onclicklistener for circle view
     */
    CircleImageView.OnClickListener changeProfile=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
            builder.setTitle("Add Photo!");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if(options[item].equals("Take Photo")){
                        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(openCamera, 1000);
                    }else if(options[item].equals("Choose from Gallery")){
                        Intent openGalary=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(openGalary,1001);
                    }else if (options[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }
    };

    /**
     * onActivityResult method
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            switch (resultCode){
                case RESULT_OK:
                    Bitmap bitmap=(Bitmap)data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                    handleUpload(bitmap);
            }
        }else if(requestCode==1001){

            switch (resultCode){
                case RESULT_OK:
                    Uri imageUri=data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                        imageView.setImageBitmap(bitmap);
                        handleUpload(bitmap);
                    } catch (IOException e) {
                        Log.d("Image Gallary","exception"+e.getMessage());
                        e.printStackTrace();
                    }
            }
        }
    }

    /**
     *
     * @param bitmap
     */
    private void handleUpload(Bitmap bitmap) {
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        curUser=auth.getCurrentUser();
        String uId=curUser.getUid();
        final StorageReference reference= FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uId+".jpeg");
        reference.putBytes(baos.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                getDownloadUrl(reference);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Profile Fragment","onFailure",e.getCause());
            }
        });
    }

    /**
     * getter method for download url
     * @param reference
     */
    private void getDownloadUrl(StorageReference reference){
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("Profile Fragment","onSuccess"+uri);
                        setUserProfile(uri);
                    }
                });
    }

    /**
     * setter method for user profile
     * @param uri
     */
    private void setUserProfile(Uri uri){
        UserProfileChangeRequest request=new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();
        curUser.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            /**
             * onFailure method
             * @param e
             */
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Profile Update Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
    }


}