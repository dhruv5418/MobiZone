package com.example.mobizone.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobizone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * Variable of edit text
     */
    EditText edt_fName,edt_lName;
    /**
     * button for update email
     */
    Button btn_updateEmail;
    /**
     * Textview
     */
    TextView txt_email;

    /**
     * Strings
     */
    String fName,lName,nEmail,email;

    /**
     * oncreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar=findViewById(R.id.toolbar_profile);
        edt_fName=findViewById(R.id.txt_fName);
        edt_lName=findViewById(R.id.txt_lName);
        txt_email=findViewById(R.id.txt_email);
        btn_updateEmail=findViewById(R.id.update_email);
        btn_updateEmail.setOnClickListener(updateEmail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        auth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName=edt_fName.getText().toString();
                lName=edt_lName.getText().toString();
                //email=edt_email.getText().toString();

                if(!checkEmptyField()){
                    updateData();
                }

                onBackPressed();
            }
        });
        imageView=findViewById(R.id.img_p);
        imageView.setOnClickListener(changeProfile);
        loadData();
    }



    /**
     * for checking empty fields
     * @return
     */
    public  boolean checkEmptyField(){
        if(TextUtils.isEmpty(fName)){
            edt_fName.setError("Name cannot be blank!");
            edt_fName.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(lName)){
            edt_lName.setError("Name cannot be blank!");
            edt_lName.requestFocus();
            return true;
        } /*else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Wrong Email Format!");
            edt_email.getText().clear();
            edt_email.requestFocus();
            return true;
        }*/
        else{
            return false;
        }
    }

    /**
     * updating user
     */
    public void updateData(){
        curUser=auth.getCurrentUser();
        Map<String,Object> usermap=new HashMap<>();
        usermap.put("First Name",fName);
        usermap.put("Last Name",lName);
        //usermap.put("Email",email);
        //int a=0;
        //usermap.put("Level",a);
        db.collection("Users").document(curUser.getUid()).update(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Updated Successfully!",Toast.LENGTH_LONG).show();
                    loadData();
                    //Toast.makeText(getActivity().getApplicationContext(),"Please verify your Email address!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * For loading data from firestore
     */
    void loadData() {
        curUser = auth.getCurrentUser();
        if (curUser != null) {
            if (curUser.getPhotoUrl() != null) {
                Glide.with(getApplicationContext())
                        .load(curUser.getPhotoUrl())
                        .into(imageView);
            }
        }
        readData(new FirestoreCallback() {
            @Override
            public void onClickback(DocumentSnapshot documentSnapshot) {
                edt_fName.setText((CharSequence) documentSnapshot.get("First Name"));
                edt_lName.setText((CharSequence) documentSnapshot.get("Last Name"));
                txt_email.setText((CharSequence) documentSnapshot.get("Email"));
                email=txt_email.getText().toString();

            }
        });
    }

    /**
     *
     * @param firestoreCallback
     */
    private void readData(final ProfileActivity.FirestoreCallback firestoreCallback){
        DocumentReference docref=db.collection("Users").document(curUser.getUid());
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    documentSnapshot.getData();
                    firestoreCallback.onClickback(documentSnapshot);
                }else{
                    Log.d("Else=","Doc not exist");
                }
            }
        });
    }

    /**
     *
     */
    private interface FirestoreCallback{
        void onClickback(DocumentSnapshot documentSnapshot);
    }

    /**
     * onClicklk listener for update email button
     */
    View.OnClickListener updateEmail=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateemail(v);
        }
    };

    /**
     *
     * @param v
     */
    private void updateemail(View v) {

        View popupview=getLayoutInflater().inflate(R.layout.popupview,null);
        final PopupWindow popupWindow=new PopupWindow(popupview, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(5.0f);
        }
        final EditText edtPEmail=popupview.findViewById(R.id.edt_popemail);
        final EditText edtPPass=popupview.findViewById(R.id.edt_poppassword);
        final EditText edtNEmail=popupview.findViewById(R.id.edt_newEmail);

        //Log.d("Setting","email="+nEmail);
        Button btnSubmit=popupview.findViewById(R.id.btn_poplogin);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orenge_button)));
        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtNEmail.getText().toString())){
                    edtNEmail.setError("Email cannot be blank!");
                    edtNEmail.requestFocus();
                }else if(TextUtils.isEmpty(edtPEmail.getText().toString())) {
                    edtPEmail.setError("New Email cannot be blank!");
                    edtPEmail.requestFocus();
                }else if(TextUtils.isEmpty(edtPPass.getText().toString())) {
                    edtPPass.setError("Password cannot be blank!");
                    edtPPass.requestFocus();
                }else{
                    if(edtPPass.getText().toString().length()<6){
                        edtPPass.setError("Invalid password,Should be at least 6 characters");
                        edtPPass.requestFocus();
                    } else {
                        AuthCredential credential= EmailAuthProvider.getCredential(edtPEmail.getText().toString(),edtPPass.getText().toString());
                        curUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    nEmail=edtNEmail.getText().toString();
                                    Log.d("Setting","email="+nEmail);
                                    curUser.updateEmail(nEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Log.d("Exception", "log Succ: ");
                                                curUser=auth.getCurrentUser();
                                                Map<String,Object> usermap=new HashMap<>();
                                                usermap.put("Email",nEmail);
                                                //int a=0;
                                                //usermap.put("Level",a);
                                                db.collection("Users").document(curUser.getUid()).update(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(getApplicationContext(),"Email Updated Successfully!",Toast.LENGTH_LONG).show();
                                                            popupWindow.dismiss();
                                                            loadData();
                                                            //Toast.makeText(getActivity().getApplicationContext(),"Please verify your Email address!",Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                            }else{
                                                try{
                                                    throw task.getException();
                                                } catch (FirebaseAuthUserCollisionException already) {
                                                    Toast.makeText(getApplicationContext(),"Email Already Exist!",Toast.LENGTH_LONG).show();
                                                    edtNEmail.getText().clear();
                                                    edtNEmail.requestFocus();
                                                    edtNEmail.setError("Enter Other Emil");
                                                    edtPEmail.getText().clear();
                                                    edtPPass.getText().clear();

                                                }
                                                catch (Exception e){
                                                    Log.d("Exception", "onComplete: " + e.getMessage());
                                                    Toast.makeText(getApplicationContext(),"Register Failed!",Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        }
                                    });
                                }else {
                                    Log.d("Setting Activity","onFailure: Authentication"+task.getException().getMessage());
                                    edtPEmail.getText().clear();
                                    edtPPass.getText().clear();
                                    edtPEmail.setError("Please enter correct details");
                                }
                            }
                        });
                    }
                }
            }
        });
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