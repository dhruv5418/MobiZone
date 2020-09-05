package com.example.mobizone.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobizone.R;
import com.example.mobizone.adapter.CartAdapter;
import com.example.mobizone.adapter.ProductCategoryAdapter;
import com.example.mobizone.model.Cart;
import com.example.mobizone.model.ProductCategory;
import com.example.mobizone.model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    Button btn_order;
    FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseUser curUser;
    final ArrayList<Cart> productsList = new ArrayList<>();
    TextView total_price;
    int total=0;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerview_cart);
        btn_order=view.findViewById(R.id.cart_continue_btn);
        total_price=view.findViewById(R.id.total_cart_amount);
        btn_order.setOnClickListener(onClick);
        auth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        curUser=auth.getCurrentUser();
        generteView();
    }

    View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getActivity().getApplicationContext(),AddressActivity.class);
            startActivity(intent);
        }
    };

    private void generteView() {
        total=0;
        db.collection("Users").document(curUser.getUid()).collection("Cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Cart=", document.getId() + " => " + document.getData());
                        String name= (String) document.getData().get("Name");
                        String quantity= String.valueOf(document.getData().get("Quantity"));
                        long id= (long) document.getData().get("Productid");
                        int cid=(int)id;
                        Log.d("ProductId", ""+id);

                        String image= (String) document.getData().get("Image");
                        String price= String.valueOf(document.getData().get("Price"));
                        productsList.add(new Cart(cid,  name, quantity, price,image));
                        total=total+(Integer.parseInt(price)*Integer.parseInt(quantity));
                    }
                    setCartItemRecycler(productsList);
                    total_price.setText("$ "+total);
                }
                else {
                    Log.d("", "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void setCartItemRecycler(List<Cart> cartList) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(getActivity(), cartList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.setOnClickListner(removeItem);
    }
    View.OnClickListener removeItem=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder=(RecyclerView.ViewHolder) v.getTag();
            final int position = viewHolder.getAdapterPosition();
            Query productIdRef=db.collection("Users").document(curUser.getUid()).collection("Cart").whereEqualTo("Productid",productsList.get(position).getProductid());
            productIdRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        document.getReference().delete();
                        removeitem(productsList);
                        generteView();
                    }
                }
            });
        }
    };
    private void removeitem(List<Cart> productsList) {
        int size = productsList.size();
        if (size > 0) {
            productsList.removeAll(productsList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}