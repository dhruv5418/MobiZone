package com.example.mobizone.view;

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
import android.widget.ImageView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Sarbjit Kaur
 * @author Kamaljit Kaur
 * @author Akshay Varma
 * this java class is for Home Fragment
 */
public class HomeFragment extends Fragment {


    /**
     * object of ProductCategoryAdapter
     */
    ProductCategoryAdapter productCategoryAdapter;
    /**
     * Variables for recyclerView
     */
    RecyclerView productCatRecycler, prodItemRecycler;
    /**
     * object of ProductAdapter
     */
    ProductAdapter productAdapter;
    /**
     * Object of Firestore
     */
    FirebaseFirestore db;
    /**
     * Object of FirebaseAuth
     */
    FirebaseAuth auth;
    /**
     * Arraylist of Products type
     */
    List<Products> productsList = new ArrayList<>();
    /**
     * Arraylist of ProductCategory
     */
    List<ProductCategory> productCategoryList = new ArrayList<>();
    /**
     * ImageView Variable
     */
    ImageView imageView;

    /**
     * default constructor for the class
     */
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * onCreate Method
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
    }

    /**
     * onCreateView Method
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    /**
     * onViewCreated method
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productCatRecycler = view.findViewById(R.id.cat_recycler);
        prodItemRecycler = view.findViewById(R.id.product_recycler);
        imageView=view.findViewById(R.id.imageView);
        imageView.setOnClickListener(removeFilter);
        getCategory();
        getAllProducts();
    }



    View.OnClickListener removeFilter=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeitem(productsList);
            getAllProducts();
        }
    };

    /**
     * For getting product details
     */
    private void getAllProducts() {
        db.collection("Products")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name= (String) document.getData().get("Name");
                        String company= (String) document.getData().get("Company");
                        String memory= (String) document.getData().get("Memory");
                        String price= (String) document.getData().get("Price");
                        int id= Integer.parseInt(document.getId());
                        String image= (String) document.getData().get("image");
                        String image_detail= (String) document.getData().get("image_detail");
                        String processor= (String) document.getData().get("Processor");
                        String battery= (String) document.getData().get("Battery");
                        String frntCam= (String) document.getData().get("Front Cam");
                        String bckCam= (String) document.getData().get("Back Cam");
                        String os=(String) document.getData().get("os");
                        getItem(id,name,company,memory,price,image,image_detail,processor,battery,frntCam,bckCam,os);

                    }

                }
            }
        });
    }

    /**
     * for adding product into list
     * @param id
     * @param name
     * @param company
     * @param memory
     * @param price
     * @param image
     * @param image_detail
     * @param processor
     * @param battery
     * @param frntCam
     * @param bckCam
     * @param os
     */
    private void getItem(final int id, final String name, final String company, final String memory, final String price, final String image, final String image_detail, final String processor, final String battery, final String frntCam, final String bckCam, final String os) {


        productsList.add(new Products(id,name,price,image,image_detail,company,battery,frntCam,bckCam,memory,processor,os));
        setProdItemRecycler(productsList);
    }

    /**
     * For getting Category details
     */
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

    /**
     *
     * @param id
     * @param company
     */
    private void addCategory(int id, String company) {
        productCategoryList.add(new ProductCategory(id, company));
        setProductCategoryRecycler(productCategoryList);
    }

    /**
     * @param productCategoryList
     */
    private void setProductCategoryRecycler(List<ProductCategory> productCategoryList){
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext()
                , RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(getActivity(), productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);
        productCategoryAdapter.setOnClickListner(onClickCategory);
    }

    /**
     *
     */
    View.OnClickListener onClickCategory=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder=(RecyclerView.ViewHolder) v.getTag();
            // viewHolder.itemView.setBackgroundColor(Color.parseColor("#A9A9A9"));
            int position = viewHolder.getAdapterPosition();
            Toast.makeText(getActivity().getApplicationContext(),productCategoryList.get(position).getProductCategory(),Toast.LENGTH_SHORT).show();
            removeitem(productsList);

            setFilterData(productCategoryList.get(position).getProductCategory());
        }
    };

    /**
     * Filter for showing product property
     * @param company
     */
    private void setFilterData(final String company){
        db.collection("Products")
                .whereEqualTo("Company", company)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("", document.getId() + " => " + document.getData());
                    System.out.println(document.getId() + " => " + document.getData());
                    String name= (String) document.getData().get("Name");
                    String company= (String) document.getData().get("Company");
                    String memory= (String) document.getData().get("Memory");
                    String price= (String) document.getData().get("Price");
                    int id= Integer.parseInt(document.getId());
                    String image= (String) document.getData().get("image");
                    String image_detail= (String) document.getData().get("image_detail");
                    String processor= (String) document.getData().get("Processor");
                    String battery= (String) document.getData().get("Battery");
                    String frntCam= (String) document.getData().get("Front Cam");
                    String bckCam= (String) document.getData().get("Back Cam");
                    String os=(String) document.getData().get("os");
                    getItem(id,name,company,memory,price,image,image_detail,processor,battery,frntCam,bckCam,os);

                }

            }
        });
    }


    /**
     *
     * @param productsList
     */
    private void setProdItemRecycler(List<Products> productsList){


        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(getActivity(), productsList);
        prodItemRecycler.setAdapter(productAdapter);

    }

    /**
     *
     * @param productsList
     */
    private void removeitem(List<Products> productsList) {
        int size = productsList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                productsList.removeAll(productsList);
            }
        }
    }




}