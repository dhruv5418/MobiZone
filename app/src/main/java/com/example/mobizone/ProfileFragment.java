package com.example.mobizone;

import android.app.Activity;
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
import android.widget.RelativeLayout;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rel_pro,rel_pass,rel_signout,rel_feed,rel_order;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rel_feed=view.findViewById(R.id.rel_feedBack);
        rel_pro=view.findViewById(R.id.rel_pro);
        rel_pass=view.findViewById(R.id.rel_changePass);
        rel_order=view.findViewById(R.id.rel_orderHistory);
        rel_signout=view.findViewById(R.id.rel_signOut);
        rel_feed.setOnClickListener(this);
        rel_pro.setOnClickListener(this);
        rel_order.setOnClickListener(this);
        rel_signout.setOnClickListener(this);
        rel_pass.setOnClickListener(this);
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
            case R.id.rel_orderHistory: //intent=new Intent(getActivity().getApplicationContext(),ProfileActivity.class);
                                         //startActivity(intent);
                                         break;
            case R.id.rel_feedBack: intent=new Intent(getActivity().getApplicationContext(),FeedbackActivity.class);
                                    startActivity(intent);
                                    break;
            case R.id.rel_signOut: intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        break;
            case R.id.rel_changePass: navController= Navigation.findNavController(getActivity(),R.id.nav_host_login);
                                        navController.navigate(R.id.forgotpassFragment);
                                        break;

        }
    }
}