package com.example.mobizone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobizone.R;

/**
 *  @author Dhruv Patel
 *  @author Gakhar Tanvi
 *  @author Sarbjit Kaur
 *  @author Kamaljit Kaur
 *  @author Akshay Varma
 *  This java class is for feedback activity
 */
public class FeedbackActivity extends AppCompatActivity {

    /**
     * Variable of button
     */
    Button btn_send;
    /**
     * Variable of EditText
     */
    EditText txt_message;
    /**
     * String for storing feedback message
     */
    String message;
    /**
     * Toolbar variable
     */
    Toolbar toolbar;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn_send=findViewById(R.id.btn_feedBack);
        txt_message=findViewById(R.id.edt_feedBack);
        toolbar=findViewById(R.id.toolbar_feedback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message=txt_message.getText().toString();
                sendFeedback();
            }
        });
    }

    /**
     * Method for opening Email App
     */
    private void sendFeedback() {
        String[] TO = {"dhruvj5418@gmail.com","gakhartanvi@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Mobizone");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
            //Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            //Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}