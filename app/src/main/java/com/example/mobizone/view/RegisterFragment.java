package com.example.mobizone.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    EditText edt_fName,edt_lName,edt_email,edt_pass,edt_cPass;
    Button btn_reg;
    TextView txt_log;
    String fName,lName,email,pass,cPass;
    NavController navController;
    private FirebaseAuth auth;

    public RegisterFragment() {
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
        btn_reg=view.findViewById(R.id.btn_reg);
        txt_log=view.findViewById(R.id.txt_regL);
        btn_reg=view.findViewById(R.id.btn_reg);
        edt_fName=view.findViewById(R.id.FirstName);
        edt_lName=view.findViewById(R.id.LastName);
        edt_email=view.findViewById(R.id.reg_email);
        edt_pass=view.findViewById(R.id.reg_pass);
        edt_cPass=view.findViewById(R.id.reg_cPass);
        txt_log.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (view.getId()){
            case R.id.btn_reg:
                fName=edt_fName.getText().toString();
                lName=edt_lName.getText().toString();
                email=edt_email.getText().toString();
                pass=edt_pass.getText().toString();
                cPass=edt_cPass.getText().toString();
                if(!checkEmptyField()){
                    if(pass.length()<6){
                        edt_pass.setError("Invalid Password,minimum 6 characters");
                        edt_pass.requestFocus();
                        return;
                    }else{
                        if(!pass.equals(cPass)){
                            edt_cPass.setError("Password not match!");
                            edt_cPass.requestFocus();
                            return;
                        }else{
                            register();
                        }
                    }
                }else{
                    return;
                }
                break;
            case R.id.txt_regL:
                NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
                navController.navigate(R.id.loginFragment);
                break;

        }
    }

    public  boolean checkEmptyField(){
        if(TextUtils.isEmpty(email)){
            edt_email.setError("Email cannot be blank!");
            edt_email.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(pass)){
            edt_pass.setError("Password cannot be blank!");
            edt_pass.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(cPass)){
            edt_cPass.setError("Confirm Password cannot be blank!");
            edt_cPass.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(fName)){
            edt_fName.setError("Name cannot be blank!");
            edt_fName.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(lName)){
            edt_lName.setError("Name cannot be blank!");
            edt_lName.requestFocus();
            return true;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Wrong Email Format!");
            edt_email.getText().clear();
            edt_email.requestFocus();
            return true;
        }
        else{
            return false;
        }
    }

    public void register(){

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthUserCollisionException already) {
                        Toast.makeText(getActivity().getApplicationContext(),"User Already Exist!",Toast.LENGTH_LONG).show();
                        navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
                        navController.navigate(R.id.loginFragment);
                    }
                    catch (Exception e){
                        Log.d("Exception", "onComplete: " + e.getMessage());
                        Toast.makeText(getActivity().getApplicationContext(),"Register Failed!",Toast.LENGTH_LONG).show();
                    }
                    edt_email.getText().clear();
                    edt_pass.getText().clear();
                    edt_cPass.getText().clear();
                    edt_fName.getText().clear();
                    edt_lName.getText().clear();
                    edt_email.requestFocus();
                    return;
                }else{

                }
            }
        });

    }

}