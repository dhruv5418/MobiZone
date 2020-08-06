package com.example.mobizone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobizone.R;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        btn_add=findViewById(R.id.btn_addNxt);
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