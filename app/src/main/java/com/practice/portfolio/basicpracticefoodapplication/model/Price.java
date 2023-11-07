package com.practice.portfolio.basicpracticefoodapplication.model;

public class Price {
    private double mrp;
    private double discountPercentage;
    private double gst;

    public Price(double v, int discount, double gst, double sTax) {
        this.mrp = v;
        this.discountPercentage = discount;
        this.gst = gst;
        this.serviceTax = sTax;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    private int foodId;

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }

    private double serviceTax;

    public double calculateDiscountedPrice() {
        return mrp - (mrp * discountPercentage / 100);
    }

    public double calculateTotalPrice() {
        double discountedPrice = calculateDiscountedPrice();
        return discountedPrice + gst + serviceTax;
    }
}
