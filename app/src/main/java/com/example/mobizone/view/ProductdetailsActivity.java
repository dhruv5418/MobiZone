package com.example.mobizone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobizone.R;

import java.util.ArrayList;
import java.util.List;

public class ProductdetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_addCart,btn_orderNow;
    Spinner spinner;
    ImageView img_backDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        btn_addCart=findViewById(R.id.btn_addCart);
        btn_orderNow=findViewById(R.id.btn_orderNow);
        img_backDetails=findViewById(R.id.img_backDetails);
        img_backDetails.setOnClickListener(this);
        spinner=findViewById(R.id.spinner);
        setSpinner();
        btn_addCart.setOnClickListener(this);
        btn_orderNow.setOnClickListener(this);
    }

    private void setSpinner() {
        List<Integer> categories = new ArrayList<>();
        for (int i=1;i<=10;i++){
            categories.add(i);
        }
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_addCart:
                Toast.makeText(getApplicationContext(),"Item Added to cart!",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_orderNow:
                Intent intent=new Intent(ProductdetailsActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.img_backDetails:onBackPressed();
                    break;

        }

    }
}