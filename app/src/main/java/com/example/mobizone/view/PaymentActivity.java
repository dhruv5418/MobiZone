package com.example.mobizone.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobizone.R;
import com.example.mobizone.model.Cart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  @author Dhruv Patel
 *   @author Gakhar Tanvi
 *  @author Sarbjit Kaur
 *  @author Kamaljit Kaur
 *  @author Akshay Varma
 *  This java class is for Payment activity
 */
public class PaymentActivity extends AppCompatActivity {

    /**
     * object of Firestore
     */
    FirebaseFirestore db;
    /**
     * object of FirebaseAuth
     */
    FirebaseAuth auth;
    /**
     * object of FirebaseUser
     */
    FirebaseUser user;
    /**
     * variable of button
     */
    Button btn_pay;
    /**
     * Variables of all edit texts
     */
    EditText nameCard,cardNo,exp,cvv;
    /**
     * Strings
     */
    String name,apt,add,city,mob,province,postal;

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btn_pay=findViewById(R.id.btn_pay);
        final Bundle b = getIntent().getExtras();
        final ArrayList<Cart> list = b.getParcelableArrayList("list");
        name=b.getString("Name");
        apt=b.getString("apt");
        city=b.getString("city");
        add=b.getString("address");
        mob=b.getString("mobile");
        province=b.getString("province");
        postal=b.getString("postal");
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cname=nameCard.getText().toString();
                String Cno=cardNo.getText().toString();
                String expiry=exp.getText().toString();
                String cv=cvv.getText().toString();

                for (int i = 0; i < list.size(); i++) {
                    savedata(Cname,Cno,expiry,cv,name,apt,add,city,province,postal,mob,i,list);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent=new Intent(PaymentActivity.this, ThankyouActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nameCard=findViewById(R.id.Cname);
        cardNo=findViewById(R.id.cardNumberId);
        exp=findViewById(R.id.expiryId);
        cvv=findViewById(R.id.cvvId);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

    }

    /**
     * Method for saving order details in firestore
     * @param cname
     * @param cno
     * @param expiry
     * @param cv
     * @param name
     * @param apt
     * @param add
     * @param city
     * @param province
     * @param postal
     * @param mob
     * @param i
     * @param list
     */
    private void savedata(String cname, String cno, String expiry, String cv, String name, String apt, String add, String city, String province, String postal, String mob, int i, ArrayList<Cart> list) {
        final String userid= String.valueOf(user.getUid());
        Date date = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("Name", name);
        data.put("Apt", apt);
        data.put("Address", add);
        data.put("City", city);
        data.put("Postal", postal);
        data.put("Province",province);
        data.put("Mobile",mob);
        data.put("CardName",cname);
        data.put("CardNumber",cno);
        data.put("CardExpiry",expiry);
        data.put("CVV",cv);
        data.put("Image",String.valueOf(list.get(i).getImageUrl()));
        data.put("Productid",list.get(i).getProductid());
        data.put("ProductName",list.get(i).getProductName());
        data.put("productQty",list.get(i).getProductQty());
        data.put("price",list.get(i).getProductPrice());
        data.put("Userid",userid);
        db.collection("Users").document(userid).collection("Orders").document(String.valueOf(date)).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("", "DocumentSnapshot successfully written!");
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Added",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("", "Error writing document", e);
            }
        });

        Query productIdRef=db.collection("Users").document(userid).collection("Cart").whereEqualTo("Productid", list.get(i).getProductid());
        productIdRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    document.getReference().delete();
                }
            }
        });
    }
}