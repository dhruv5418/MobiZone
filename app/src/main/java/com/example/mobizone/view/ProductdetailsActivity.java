package com.example.mobizone.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobizone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductdetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_addCart,btn_orderNow;
    Spinner spinner;
    ImageView img_backDetails,img_product;
    TextView name,company,price,memory,battery,processor,frnt,bck,os;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String Pname,Pprice,Pqunt,Pimage;
    int Pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        btn_addCart=findViewById(R.id.btn_addCart);
        btn_orderNow=findViewById(R.id.btn_orderNow);
        img_backDetails=findViewById(R.id.img_backDetails);
        img_backDetails.setOnClickListener(this);
        name=findViewById(R.id.txt_name);
        company=findViewById(R.id.txt_company);
        os=findViewById(R.id.txt_os);
        battery=findViewById(R.id.txt_battery);
        memory=findViewById(R.id.txt_memory);
        frnt=findViewById(R.id.txt_frntCam);
        bck=findViewById(R.id.txt_bckCam);
        processor=findViewById(R.id.txt_processor);
        price=findViewById(R.id.txt_price);
        img_product=findViewById(R.id.img_prod);
        showData();
        spinner=findViewById(R.id.spinner);
        setSpinner();
        btn_addCart.setOnClickListener(this);
        btn_orderNow.setOnClickListener(this);
    }
    private void showData() {

        final Bundle b = getIntent().getExtras();
        Pid=b.getInt("id");
        Pname=b.getString("Name");
        Pprice=b.getString("Price");
        Pimage=b.getString("Image");
        final int id = b.getInt("id");
        name.setText(Pname);
        company.setText(b.getString("Company"));
        battery.setText("Battery");
        memory.setText(b.getString("Memory"));
        processor.setText(b.getString("Processor"));
        frnt.setText(b.getString("Front"));
        bck.setText(b.getString("Back"));
        os.setText(b.getString("Os"));
        price.setText(Pprice);
        Picasso.get().load(Pimage).into(img_product);
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
                addCart();
                break;
            case R.id.btn_orderNow:
                Intent intent=new Intent(ProductdetailsActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.img_backDetails:onBackPressed();
                    break;

        }

    }

    private void addCart() {
        FirebaseUser user=auth.getCurrentUser();
        Date date = new Date();
        Map<String, Object> data = new HashMap<>();
        Pqunt=spinner.getSelectedItem().toString();
        data.put("Userid", user.getUid());
        data.put("Quantity", Pqunt);
        data.put("Productid", Pid);
        data.put("Name", Pname);
        data.put("Price", Pprice);
        data.put("Image",Pimage);
        db.collection("Users").document(user.getUid()).collection("Cart").document(String.valueOf(date)).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"Item added to cart!!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}