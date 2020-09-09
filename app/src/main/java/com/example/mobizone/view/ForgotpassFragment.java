package com.example.mobizone.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobizone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author Patel Dhruv
        * @author Gakhar Tanvi
        * @author Sarbjit Kaur
        * @author Kamaljit Kaur
        * @author Akshay Varma
        * this java class is for reset the password using firebase.
        */
public class ForgotpassFragment extends Fragment implements View.OnClickListener {

    /**
     * variable of button
     */
    Button btn_send;

    /**
     * variable of Text View
     */
    TextView txt_login;

    /**
     * object of FirebaseAuth
     */
    private FirebaseAuth auth;

    /**
     * variable of edit Text
     */
    private EditText edt_email;

    /**
     * object of navController
     */
    NavController navController;

    /**
     * variable of String
     */
    String email;

    /**
     * default constructor for the class
     */
    public ForgotpassFragment() {
        // Required empty public constructor
    }

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
    }

    /**
     * onViewCreated method
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_send=view.findViewById(R.id.btn_frgtPass);
        txt_login=view.findViewById(R.id.txt_frgtLogin);
        edt_email=view.findViewById(R.id.edit_restPassEmail);
        btn_send.setOnClickListener(this);
        txt_login.setOnClickListener(this);
    }

    /**
     * onCreateView method
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgotpass, container, false);
    }

    /**
     * onClickListener for fragment
     * @param view
     */
    @Override
    public void onClick(View view) {
        NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
        switch (view.getId()){
            case R.id.btn_frgtPass:
                email=edt_email.getText().toString();
                if(TextUtils.isEmpty(email)){
                    edt_email.setError("Please enter email!");
                    edt_email.requestFocus();
                }else{
                    reset(email);
                }
                                   break;
            case R.id.txt_frgtLogin:navController.navigate(R.id.loginFragment);
                                    break;

        }

    }

    /**
     * to send password reset email using firebase
     * @param email
     */
    private void reset(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity().getApplicationContext(),"Email sent successfully!",Toast.LENGTH_LONG).show();
                    navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
                    navController.navigate(R.id.loginFragment);
                }
            }
        });
    }
}