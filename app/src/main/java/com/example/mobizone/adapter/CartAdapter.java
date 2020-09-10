package com.example.mobizone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobizone.R;
import com.example.mobizone.model.Cart;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Kaur Sarbjit
 * @author Kaur Kamaljit
 * @author Varma Akshay
 * This adapter class is used for Cart.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder>  {

    /**
     * Context
     */
    Context context;
    /**
     * Arraylist of Cart type
     */
    List<Cart> cartList;
    /**
     * onClickListener
     */
    private View.OnClickListener clickListener;

    /**
     * constructor
     * @param context
     * @param cartList
     */
    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    /**
     * onCreateViewHolder of adapter
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart,parent,false);
        return new ProductViewHolder(view);
    }

    /**
     * onBindViewHolder of adapter
     * @param holder
     * @param position
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {

        Picasso.get().load(cartList.get(position).getImageUrl()).into(holder.prodImage);
        holder.prodName.setText(cartList.get(position).getProductName());
        holder.prodQty.setText("Qty:- "+cartList.get(position).getProductQty());
        holder.prodPrice.setText("$ "+cartList.get(position).getProductPrice());

    }

    /**
     *
     * @param onClickListner
     */
    public void setOnClickListner(View.OnClickListener onClickListner)
    {
        clickListener = onClickListner;
    }

    /**
     * return size of arrayList
     * @return
     */
    @Override
    public int getItemCount() {
        return cartList.size();
    }

    /**
     * item class
     */
    public final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImage;
        TextView prodName, prodQty,prodPrice,prod_removeItem;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.cart_item_img);
            prodName = itemView.findViewById(R.id.cart_item_txt);
            prodPrice = itemView.findViewById(R.id.cart_product_price);
            prodQty = itemView.findViewById(R.id.cart_product_quantity);
            prod_removeItem=itemView.findViewById(R.id.txt_removeItem);
            prod_removeItem.setTag(this);
            prod_removeItem.setOnClickListener(clickListener);

        }
    }

}
