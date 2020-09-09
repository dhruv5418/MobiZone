package com.example.mobizone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobizone.R;
import com.example.mobizone.model.Order;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Kaur Sarbjit
 * @author Kaur Kamaljit
 * @author Varma Akshay
 * This Adapter is used for showing different categories of products
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    /**
     * Variable of context
     */
    Context context;

    /**
     * ArrayList of Order type
     */
    List<Order> productsList;

    /**
     * Constructor
     * @param context
     * @param productsList
     */
    public OrderAdapter(Context context, List<Order> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    /**
     * onCreate method for different product categories
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_orderhistory, parent, false);
        return new OrderViewHolder(view);
    }

    /**
     * onBind method for product categories
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Picasso.get().load(productsList.get(position).getDetail_image()).into(holder.prodImage);
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodQty.setText("Quantity "+productsList.get(position).getProductQty());
        holder.prodPrice.setText("$ "+productsList.get(position).getProductPrice());
        holder.province.setText("Price "+productsList.get(position).getProvince());
        holder.apt.setText("Apt:- "+productsList.get(position).getApt());
        holder.address.setText("Address:- "+productsList.get(position).getAddress());
        holder.city.setText("City:- "+productsList.get(position).getCity());
        holder.postal.setText("Postal:- "+productsList.get(position).getPostal());
    }

    /**
     * Method for getting List size
     * @return
     */
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    /**
     * Item class
     */
    public static final class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImage;
        TextView prodName, prodPrice,prodQty, province,apt,address,city,postal;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage = itemView.findViewById(R.id.order_img);
            prodName = itemView.findViewById(R.id.order_name);
            prodQty = itemView.findViewById(R.id.order_quantity);
            prodPrice=itemView.findViewById(R.id.order_price);
            apt = itemView.findViewById(R.id.txt_apt);
            address = itemView.findViewById(R.id.txt_add);
            city = itemView.findViewById(R.id.txt_city);
            postal = itemView.findViewById(R.id.txt_postal);
            province = itemView.findViewById(R.id.txt_province);

        }
    }
}
