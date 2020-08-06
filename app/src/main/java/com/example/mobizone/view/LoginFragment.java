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
import android.widget.TextView;

import com.example.mobizone.R;


public class LoginFragment extends Fragment implements View.OnClickListener {


    Button btn_log;
    TextView txt_reg,txt_forgot;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_log=view.findViewById(R.id.btn_log);
        txt_reg=view.findViewById(R.id.txt_logR);
        txt_forgot=view.findViewById(R.id.txt_forgot);
        txt_reg.setOnClickListener(this);
        btn_log.setOnClickListener(this);
        txt_forgot.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onClick(View view) {
        NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
        switch (view.getId()){
            case R.id.btn_log:
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
}