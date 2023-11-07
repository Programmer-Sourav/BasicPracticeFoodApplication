package com.practice.portfolio.basicpracticefoodapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.portfolio.basicpracticefoodapplication.adapter.CartAdapter;
import com.practice.portfolio.basicpracticefoodapplication.model.CartDetails;
import com.practice.portfolio.basicpracticefoodapplication.model.CartItem;

import java.util.ArrayList;

public class ViewCart extends AppCompatActivity {

    private CartAdapter cartAdapter;
    private ArrayList<CartItem> cartItemList;
    private RecyclerView cartItemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        cartItemListView = findViewById(R.id.cartList);

        TextView cartMsg = findViewById(R.id.cart_msg);


        CartDetails cartDetails = new CartDetails();
        cartItemList = cartDetails.getCartItem();
        Log.i("Snath, CartItem On View ", "Length"+cartItemList.size());
        cartAdapter = new CartAdapter(cartItemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartItemListView.setLayoutManager(layoutManager);
        cartItemListView.setAdapter(cartAdapter);

        if(cartItemList.size()<=0){
            cartMsg.setVisibility(View.VISIBLE);
        }
        else{
            cartMsg.setVisibility(View.GONE);
        }

    }
}
