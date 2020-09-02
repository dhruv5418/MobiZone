package com.example.mobizone.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobizone.R;
import com.example.mobizone.adapter.ProductAdapter;
import com.example.mobizone.adapter.ProductCategoryAdapter;
import com.example.mobizone.model.ProductCategory;
import com.example.mobizone.model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;
    FirebaseFirestore db;
    FirebaseAuth auth;
    List<Products> productsList = new ArrayList<>();
    List<ProductCategory> productCategoryList = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productCatRecycler = view.findViewById(R.id.cat_recycler);
        prodItemRecycler = view.findViewById(R.id.product_recycler);
        getCategory();
       // generteView();
    }

    private void getCategory() {
        db.collection("Category")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        int id = Integer.parseInt(document.getId());
                        String company= (String) document.getData().get("Company");
                        addCategory(id,company);
                    }
                }
            }
        });
    }

    private void addCategory(int id, String company) {
        productCategoryList.add(new ProductCategory(id, company));
        setProductCategoryRecycler(productCategoryList);
    }
    private void setProductCategoryRecycler(List<ProductCategory> productCategoryList){
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext()
                , RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(getActivity(), productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }



    private void setProdItemRecycler(List<Products> productsList){


        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(getActivity(), productsList);
        prodItemRecycler.setAdapter(productAdapter);

    }
}