package com.practice.portfolio.basicpracticefoodapplication.model;

import com.practice.portfolio.basicpracticefoodapplication.interfaces.FoodType;

public class FoodItem implements FoodType {
    public FoodItem(int id, String foodName, String foodCusine, String foodType, Price price, int imageUri) {
        this.id = id;
        this.foodName = foodName;
        this.foodCuisine = foodCusine;
        this.foodType = foodType;
        this.price = price;
        this.imageResource = imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    private String foodName;
    private String foodCuisine;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCuisine() {
        return foodCuisine;
    }

    public void setFoodCuisine(String foodCuisine) {
        this.foodCuisine = foodCuisine;
    }


    @Override
    public String getFoodType() {
        return foodType;
    }

    @Override
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    private String foodType;
    private Price price;



    public double calculateTotalPrice() {
        // Assuming price is a Price object with properties like mrp, discountPercentage, gst, and serviceTax
        double discountedPrice = price.getMrp() - price.getMrp() * price.getDiscountPercentage();
        return discountedPrice + price.getGst() + price.getServiceTax();
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    int imageResource;
}
