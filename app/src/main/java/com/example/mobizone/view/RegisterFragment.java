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


public class RegisterFragment extends Fragment implements View.OnClickListener {

    Button btn_reg;
    TextView txt_log;
    public RegisterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_reg=view.findViewById(R.id.btn_reg);
        txt_log=view.findViewById(R.id.txt_regL);
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
        switch (view.getId()){
            case R.id.btn_reg:
                Intent intent =new Intent(getActivity().getApplicationContext(),DashActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_regL:
                NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
                navController.navigate(R.id.loginFragment);
                break;

        }
    }
}