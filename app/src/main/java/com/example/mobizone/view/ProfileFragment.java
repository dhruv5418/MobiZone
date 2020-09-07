package com.example.mobizone.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import static android.app.Activity.RESULT_OK;

import com.bumptech.glide.Glide;
import com.example.mobizone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rel_pro,rel_pass,rel_signout,rel_feed,rel_order;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db;
    String fName,lName,nEmail,email;
    CircleImageView imageView;
    TextView txt_name,txt_email;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rel_feed=view.findViewById(R.id.rel_feedBack);
        rel_pro=view.findViewById(R.id.rel_pro);
        rel_pass=view.findViewById(R.id.rel_changePass);
        rel_order=view.findViewById(R.id.rel_orderHistory);
        txt_email=view.findViewById(R.id.txt_email);
        txt_name=view.findViewById(R.id.txt_name);
        rel_signout=view.findViewById(R.id.rel_signOut);
        imageView=view.findViewById(R.id.img_p);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        user=auth.getCurrentUser();
        rel_feed.setOnClickListener(this);
        rel_pro.setOnClickListener(this);
        rel_order.setOnClickListener(this);
        rel_signout.setOnClickListener(this);
        rel_pass.setOnClickListener(this);
        imageView.setOnClickListener(changeProfile);
        loadData();
    }

    private void loadData() {
        user=auth.getCurrentUser();
        if(user!=null){
            if(user.getPhotoUrl()!= null){
                Glide.with(getActivity().getApplicationContext())
                        .load(user.getPhotoUrl())
                        .into(imageView);
            }
        }
        readData(new FirestoreCallback() {
            @Override
            public void onClickback(DocumentSnapshot documentSnapshot) {
                txt_name.setText((String)documentSnapshot.get("First Name")+" "+(String)documentSnapshot.get("Last Name"));

                email=(String) documentSnapshot.get("Email").toString();
                txt_email.setText(email);
                Toast.makeText(getActivity().getApplicationContext(),"Email="+email,Toast.LENGTH_LONG).show();
            }
        });

    }
    private interface FirestoreCallback{
        void onClickback(DocumentSnapshot documentSnapshot);
    }
    private void readData(final FirestoreCallback firestoreCallback){
        DocumentReference docref=db.collection("Users").document(user.getUid());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        NavController navController;
        switch (v.getId()){

            case R.id.rel_pro: intent=new Intent(getActivity().getApplicationContext(),ProfileActivity.class);
                                startActivity(intent);
                                break;
            case R.id.rel_orderHistory: intent=new Intent(getActivity().getApplicationContext(), OrderhistoryActivity.class);
                                         startActivity(intent);
                                         break;
            case R.id.rel_feedBack: intent=new Intent(getActivity().getApplicationContext(),FeedbackActivity.class);
                                    startActivity(intent);
                                    break;
            case R.id.rel_signOut: auth.signOut();
                                   intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                   startActivity(intent);
                                   break;
            case R.id.rel_changePass:
                                        resetPass(email);
                                        break;

        }
    }

    private void resetPass(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity().getApplicationContext(),"Password reset link send successfully!",Toast.LENGTH_LONG).show();
                    auth.signOut();
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    View.OnClickListener changeProfile=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                        imageView.setImageBitmap(bitmap);
                        handleUpload(bitmap);
                    } catch (IOException e) {
                        Log.d("Image Gallary","exception"+e.getMessage());
                        e.printStackTrace();
                    }
            }
        }
    }

    private void handleUpload(Bitmap bitmap) {
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        user=auth.getCurrentUser();
        String uId=user.getUid();
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

    private void setUserProfile(Uri uri){
        UserProfileChangeRequest request=new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();
        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity().getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(),"Profile Update Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        user=auth.getCurrentUser();
        if(user!=null){
            if(user.getPhotoUrl()!= null){
                Glide.with(getActivity().getApplicationContext())
                        .load(user.getPhotoUrl())
                        .into(imageView);
            }
        }
        super.onResume();
    }


}