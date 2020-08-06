package com.example.mobizone.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobizone.R;
import com.example.mobizone.adapter.CartAdapter;
import com.example.mobizone.adapter.ProductCategoryAdapter;
import com.example.mobizone.model.Cart;
import com.example.mobizone.model.ProductCategory;
import com.example.mobizone.model.Products;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    Button btn_order;

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
        btn_order.setOnClickListener(onClick);
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

        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(1, "Iphone 11 Pro Max 64GB", "1", "$ 1100.00", R.drawable.prod4));
        cartList.add(new Cart(2, "Iphone 11 Pro Max 128GB", "1", "$ 1300.00", R.drawable.prod4));
        setCartItemRecycler(cartList);
    }

    private void setCartItemRecycler(List<Cart> cartList) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(getActivity(), cartList);
        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}