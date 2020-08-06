package com.example.mobizone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.mobizone.R;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        btn_add=findViewById(R.id.btn_addNxt);
        toolbar=findViewById(R.id.toolbar_address);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.btn_addNxt:intent=new Intent(AddressActivity.this,PaymentActivity.class);
                                 startActivity(intent);
                                 break;
        }
    }
}