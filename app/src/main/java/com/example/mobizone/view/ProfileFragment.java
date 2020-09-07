package com.example.mobizone.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mobizone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rel_pro,rel_pass,rel_signout,rel_feed,rel_order;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db;
    String fName,lName,nEmail,email;
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
        rel_signout=view.findViewById(R.id.rel_signOut);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        user=auth.getCurrentUser();
        rel_feed.setOnClickListener(this);
        rel_pro.setOnClickListener(this);
        rel_order.setOnClickListener(this);
        rel_signout.setOnClickListener(this);
        rel_pass.setOnClickListener(this);
        loadData();
    }

    private void loadData() {
        readData(new FirestoreCallback() {
            @Override
            public void onClickback(DocumentSnapshot documentSnapshot) {

                email=(String) documentSnapshot.get("Email").toString();
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
}