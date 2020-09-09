package com.example.mobizone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.mobizone.R;


/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Sarbjit Kaur
 * @author Kamaljit Kaur
 * @author Akshay Varma
 * this java class is for Address Activity
 */
public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Variable of toolbar
     */
    Toolbar toolbar;

    /**
     * variable of Button
     */
    Button btn_add;
    /**
     * variables of all required Edit Text
     */
    EditText uname,uapt,uaddress,ucity,uprovince,umob,upostal;

    /**
     * on Create method
     * @param savedInstanceState
     */
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
        uname = findViewById(R.id.name);
        uapt = findViewById(R.id.aptId);
        uaddress = findViewById(R.id.addressId);
        ucity = findViewById(R.id.city);
        upostal = findViewById(R.id.postalID);
        umob=findViewById(R.id.mobileId);
        uprovince=findViewById(R.id.provinceId);
        btn_add.setOnClickListener(this);
    }

    /**
     * on Click method
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.btn_addNxt:   String name = uname.getText().toString();
                                    String apt = uapt.getText().toString();
                                    String address = uaddress.getText().toString();
                                    String city = ucity.getText().toString();
                                    String province=uprovince.getText().toString();
                                    String postal = upostal.getText().toString();
                                    String mob=umob.getText().toString();
                                    final Bundle b = getIntent().getExtras();
                                    b.putString("Name",name);
                                    b.putString("apt",apt);
                                    b.putString("address",address);
                                    b.putString("city",city);
                                    b.putString("province",province);
                                    b.putString("postal",postal);
                                    b.putString("mobile",mob);
                                    intent=new Intent(AddressActivity.this,PaymentActivity.class);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                    break;
        }
    }
}