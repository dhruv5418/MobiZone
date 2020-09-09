package com.example.mobizone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobizone.R;
import com.example.mobizone.model.ProductCategory;
import com.example.mobizone.model.ProductCategory;

import java.util.List;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Kaur Sarbjit
 * @author Kaur Kamaljit
 * @author Varma Akshay
 * This Adapter is used for showing different product categories.
 */
public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder> {

    /**
     * Variable of a context
      */
    Context context;
    /**
     * ArrayList of ProductCategory type.
     */
    List<ProductCategory> productCategoryList;
    private View.OnClickListener clickListener;

    /**
     * Constructor
     * @param context
     * @param productCategoryList
     */
    public ProductCategoryAdapter(Context context, List<ProductCategory> productCategoryList) {
        this.context = context;
        this.productCategoryList = productCategoryList;
    }

    /**
     * onCreate ViewHolder method for different categories.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_category, parent, false);
        // lets create a recyclerview row item layout file
        return new ProductViewHolder(view);
    }

    /**
     * onBindViewHolder method for product categories.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.catagoryName.setText(productCategoryList.get(position).getProductCategory());

    }

    /**
     * Method for getting the List size.
     * @return
     */
    @Override
    public int getItemCount() {
        return productCategoryList.size();
    }

    /**
     * onClickListener method for category adapter.
     * @param onClickListner
     */
    public void setOnClickListner(View.OnClickListener onClickListner)
    {
        clickListener = onClickListner;
    }

    /**
     * Item Class
     */
    public final class ProductViewHolder extends RecyclerView.ViewHolder{


        TextView catagoryName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryName = itemView.findViewById(R.id.cat_name);
            itemView.setTag(this);
            itemView.setOnClickListener(clickListener);
        }
    }

}
