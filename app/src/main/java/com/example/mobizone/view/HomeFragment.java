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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        generteView();
    }

    private void generteView() {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Apple"));
        productCategoryList.add(new ProductCategory(3, "Samsung"));
        productCategoryList.add(new ProductCategory(4, "LG"));
        productCategoryList.add(new ProductCategory(5, "Google"));
        productCategoryList.add(new ProductCategory(6, "Huawei"));
        productCategoryList.add(new ProductCategory(7, "Motorola"));

        setProductRecycler(productCategoryList);

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Iphone 11 Pro Max", "64GB", "$ 1100.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "128GB", "$ 1300.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "64GB", "$ 1100.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "128GB", "$ 1300.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "64GB", "$ 1100.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "128GB", "$ 1300.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "64GB", "$ 1100.00", R.drawable.prod2));
        productsList.add(new Products(1, "Iphone 11 Pro Max", "128GB", "$ 1300.00", R.drawable.prod2));
        setProdItemRecycler(productsList);
    }
    private void setProductRecycler(List<ProductCategory> productCategoryList){


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