package com.practice.portfolio.basicpracticefoodapplication.model;

import java.util.ArrayList;

public class CartDetails {
    public ArrayList<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(ArrayList<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    //static, so that it can be shared among activities, otherwise, will need to pass it through intent or some other way
    public static ArrayList<CartItem> cartItem = new ArrayList<>();
}
