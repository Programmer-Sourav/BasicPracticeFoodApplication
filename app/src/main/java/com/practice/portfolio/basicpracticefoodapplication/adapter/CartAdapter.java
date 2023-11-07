package com.practice.portfolio.basicpracticefoodapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.portfolio.basicpracticefoodapplication.R;
import com.practice.portfolio.basicpracticefoodapplication.model.CartItem;
import com.practice.portfolio.basicpracticefoodapplication.model.Price;


import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<CartItem> cartItemList;

    public CartAdapter(ArrayList<CartItem> cartList) {
        this.cartItemList = cartList;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_row, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
         CartItem currentCartItemDetails = cartItemList.get(position);
         holder.menuFoodName.setText(currentCartItemDetails.getFoodName());
         holder.menuFoodCuisine.setText(currentCartItemDetails.getFoodCuisine());
         holder.cartFoodQuantity.setText(String.valueOf(currentCartItemDetails.getQuantityOfItem()));
         holder.menuFoodType.setText(currentCartItemDetails.getFoodType());
        Price price = currentCartItemDetails.getPrice();
        double totalPrice = price.calculateTotalPrice();
        holder.menuFoodPrice.setText("Rs. "+String.valueOf(totalPrice));
         holder.deleteItemFromCart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                 cartItemList.remove(currentCartItemDetails);
                 notifyDataSetChanged();
             }
         });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView menuFoodName;
        TextView menuFoodPrice;
        TextView menuFoodType;
        TextView menuFoodCuisine;
        TextView cartFoodQuantity;
        TextView deleteItemFromCart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            menuFoodName = itemView.findViewById(R.id.foodname);
            menuFoodPrice = itemView.findViewById(R.id.foodprice);
            menuFoodType = itemView.findViewById(R.id.foodtype);
            menuFoodCuisine = itemView.findViewById(R.id.foodcuisine);
            cartFoodQuantity = itemView.findViewById(R.id.quantity);
            deleteItemFromCart = itemView.findViewById(R.id.delete);
        }
    }
}
