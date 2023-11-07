package com.practice.portfolio.basicpracticefoodapplication.model;

public class CartItem extends FoodItem{
    public CartItem(int id, String foodName, String foodCuisine, String foodType, Price price, int imageUrl) {
        super(id, foodName, foodCuisine, foodType, price, 0);
    }

    public int getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(int quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    private int quantityOfItem;

    public double getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    private double totalCartPrice;

}
