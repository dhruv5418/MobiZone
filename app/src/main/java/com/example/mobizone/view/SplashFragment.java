package com.example.mobizone.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobizone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashFragment extends Fragment implements View.OnClickListener {

    Button btn_reg,btn_log;
    NavController navController;
    FirebaseAuth auth;
    FirebaseUser curUser;
    public SplashFragment() {
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
        btn_log=view.findViewById(R.id.btn_logS);
        btn_reg=view.findViewById(R.id.btn_regS);
        btn_reg.setOnClickListener(this);
        btn_log.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onClick(View view) {
        navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
        switch (view.getId()){
            case R.id.btn_logS: navController.navigate(R.id.loginFragment);
                                    break;

            case R.id.btn_regS: navController.navigate(R.id.registerFragment);
                                    break;

        }

    }
    @Override
    public void onStart() {
        super.onStart();
        curUser=auth.getCurrentUser();
        if(curUser!=null){
            updateUI(curUser);
            Toast.makeText(getActivity().getApplicationContext(),"User Already Login",Toast.LENGTH_LONG).show();
        }
    }

    public void updateUI(FirebaseUser fUser){
        Intent intent = new Intent(getActivity(), DashActivity.class);
        intent.putExtra("User",fUser);
        startActivity(intent);
    }

}