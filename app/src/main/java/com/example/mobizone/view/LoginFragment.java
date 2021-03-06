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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Sarbjit Kaur
 * @author Kamaljit Kaur
 * @author Akshay Varma
 * This java class is for logging in the user using firebase authentication.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    /**
     * object of FirebaseAuth
     */
    private FirebaseAuth auth;
    /**
     * object of FirebaseUser
     */
    private FirebaseUser curUser;
    /**
     * object NavController
     */
    public NavController navController;
    /**
     * all variable of required edit texts
     */
    private EditText edt_email,edt_pass;
    /**
     * variable of Button
     */
    Button btn_log;
    /**
     * variables of textViews
     */
    TextView txt_reg,txt_forgot;
    /**
     * String TAG
     */
    private static final String TAG="LoginFragment";

    /**
     * Default constructor for the class
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * On create method
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
    }

    /**
     * On view created method
     * @param view
     * @param savedInstanceState
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_log=view.findViewById(R.id.btn_log);
        txt_reg=view.findViewById(R.id.txt_logR);
        txt_forgot=view.findViewById(R.id.txt_forgot);
        edt_email=view.findViewById(R.id.edit_loginEmail);
        edt_pass=view.findViewById(R.id.edit_loginPass);;
        txt_reg.setOnClickListener(this);
        btn_log.setOnClickListener(this);
        txt_forgot.setOnClickListener(this);
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /**
     * onClick listener for the fragment
     * @param view
     */
    @Override
    public void onClick(View view) {
        NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
        switch (view.getId()){
            case R.id.btn_log:
                login();
                Intent intent =new Intent(getActivity().getApplicationContext(),DashActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_logR:
                navController.navigate(R.id.registerFragment);
                break;
            case R.id.txt_forgot:
                navController.navigate(R.id.forgotpassFragment);
                break;


        }
    }

    /**
     * this method checks for the basic validations
     */
    private void login() {
        String email = edt_email.getText().toString();
        String pass = edt_pass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edt_email.setError("Email cannot be blank!");
            edt_email.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            edt_pass.setError("Password cannot be blank!");
            edt_pass.requestFocus();
        }else{
            loginUser(email, pass);
        }
    }

    /**
     * This method check for registered user using email and pass with firebase auth
     * @param email
     * @param pass
     */
    private void loginUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    curUser=auth.getCurrentUser();
                                Toast.makeText(getActivity().getApplicationContext(),"Login Success!",Toast.LENGTH_LONG).show();
                                updateUI(curUser);
                }else{
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        Toast.makeText(getActivity().getApplicationContext(),"Email not exist!",Toast.LENGTH_LONG).show();
                        edt_email.getText().clear();
                        edt_pass.getText().clear();
                        edt_email.setError("Email not exist!");
                        edt_email.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        Toast.makeText(getActivity().getApplicationContext(),"Wrong Password!",Toast.LENGTH_LONG).show();
                        edt_pass.getText().clear();
                        edt_pass.setError("Wrong Password!");
                        edt_pass.requestFocus();
                    }catch (Exception e ){
                        Toast.makeText(getActivity().getApplicationContext(),"Login Failed!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    /**
     * @param fUser
     */
    public void updateUI(FirebaseUser fUser){

        Intent intent = new Intent(getActivity(), DashActivity.class);
        intent.putExtra("User",fUser);
        startActivity(intent);
    }

    /**
     * onStart method for checking current logged in user
     */
    @Override
    public void onStart() {
        super.onStart();
        curUser=auth.getCurrentUser();
        if(curUser!=null){
            updateUI(curUser);
            Toast.makeText(getActivity().getApplicationContext(),"User Already Login",Toast.LENGTH_LONG).show();
        }
    }
}
