package com.example.mobizone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobizone.view.DashActivity;

public class ThankyouActivity extends AppCompatActivity {

    Button btn_con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        btn_con=findViewById(R.id.btn_continue);
        btn_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThankyouActivity.this, DashActivity.class);
                startActivity(intent);
            }
        });
    }
}