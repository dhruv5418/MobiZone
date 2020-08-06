package com.example.mobizone.view;

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

public class ForgotpassFragment extends Fragment implements View.OnClickListener {

Button btn_send;
TextView txt_login;

    public ForgotpassFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_send=view.findViewById(R.id.btn_frgtPass);
        txt_login=view.findViewById(R.id.txt_frgtLogin);
        btn_send.setOnClickListener(this);
        txt_login.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgotpass, container, false);
    }

    @Override
    public void onClick(View view) {
        NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
        switch (view.getId()){
            case R.id.btn_frgtPass:navController.navigate(R.id.loginFragment);
                                   break;
            case R.id.txt_frgtLogin:navController.navigate(R.id.loginFragment);
                                    break;

        }

    }
}