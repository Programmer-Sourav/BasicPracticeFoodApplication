package com.practice.portfolio.basicpracticefoodapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.portfolio.basicpracticefoodapplication.adapter.FoodListAdapter;
import com.practice.portfolio.basicpracticefoodapplication.model.FoodItem;
import com.practice.portfolio.basicpracticefoodapplication.model.Price;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView myFoodsList;
    private FoodListAdapter myFoodListAdapter;

    //list of food
    private ArrayList<FoodItem> foodList = new ArrayList<>();
    private ArrayList<FoodItem> originalList;
    private TextView viewCart;
    private TextView foodMenu;
    private RadioGroup cuisineRadioButtonGroup;
    private ArrayList<FoodItem> sortedList;
    CheckBox sortData;
    @Override
    protected void onCreate(Bundle savedInstanceSate){
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.activity_main);
         //step1: set Reference to the xml item
        myFoodsList = findViewById(R.id.foodsList);
        viewCart = findViewById(R.id.cart);
        foodMenu = findViewById(R.id.menu);
        sortData = (CheckBox) findViewById(R.id.sortByPrice);
        cuisineRadioButtonGroup = (RadioGroup) findViewById(R.id.cusineRadioGroup);

         //step2: get and set data to the arraylist from data source
        foodList.add(new FoodItem(1, "Caprese Salad", "Italian", "Starter", new Price(8.99, 0, 7, 10), R.drawable.food1));
        foodList.add(new FoodItem(2, "Mushroom Bruschetta", "Italian", "Starter", new Price(7.99, 0, 7, 10),R.drawable.food2));
        foodList.add(new FoodItem(3, "Chicken Wings", "American", "Starter", new Price(9.99, 15, 7, 10),R.drawable.food3));
        foodList.add(new FoodItem(4, "Nachos", "Mexican", "Starter", new Price(6.99, 10, 8, 10),R.drawable.food1));
        foodList.add(new FoodItem(5, "Caesar Salad", "American", "Starter", new Price(7.99, 0, 7, 10),R.drawable.food2));
        foodList.add(new FoodItem(6, "Margherita Pizza", "Italian", "Main Course", new Price(12.99, 0, 7, 10),R.drawable.food3));
        foodList.add(new FoodItem(7, "Chicken Fettuccine Alfredo", "Italian", "Main Course", new Price(14.99, 10, 7, 10),R.drawable.food1));
        foodList.add(new FoodItem(8, "Taco Salad", "Mexican", "Main Course", new Price(10.99, 0, 8, 10),R.drawable.food2));
        foodList.add(new FoodItem(9, "Sushi Platter", "Japanese", "Main Course", new Price(18.99, 5, 7, 10),R.drawable.food3));
        foodList.add(new FoodItem(10, "BBQ Ribs", "American", "Main Course", new Price(16.99, 10, 7, 10),R.drawable.food1));
        foodList.add(new FoodItem(11, "Chocolate Brownie", "Dessert", "Dessert", new Price(5.99, 0, 7, 10),R.drawable.food2));
        foodList.add(new FoodItem(12, "Tiramisu", "Italian", "Dessert", new Price(6.99, 0, 7, 10),R.drawable.food3));
        foodList.add(new FoodItem(13, "Apple Pie", "American", "Dessert", new Price(4.99, 0, 7, 10),R.drawable.food1));
        foodList.add(new FoodItem(14, "Mango Sorbet", "Dessert", "Dessert", new Price(3.99, 0, 7, 10),R.drawable.food2));
        foodList.add(new FoodItem(15, "Strawberry Cheesecake", "Dessert", "Dessert", new Price(6.99, 0, 7, 10),R.drawable.food3));
        foodList.add(new FoodItem(16, "Pizza Margherita", "Italian", "Main", new Price(109.99, 0, 7, 10),R.drawable.food1));
        foodList.add(new FoodItem(17, "Chicken Alfredo", "Italian", "Pasta", new Price(12.99, 15, 7, 10),R.drawable.food2));
        foodList.add(new FoodItem(18, "Chicken Masala", "Indian", "Main", new Price(109.99, 0, 7, 10),R.drawable.food3));
        foodList.add(new FoodItem(19, "Paneer Tikka", "Indian", "Starter", new Price(12.99, 15, 7, 10),R.drawable.food1));
        //step3: send list o items to adapter
        myFoodListAdapter = new FoodListAdapter(foodList);

        originalList = new ArrayList<>(foodList); //copy of original list
        //step4: specify layout for the recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myFoodsList.setLayoutManager(layoutManager);

        //step5: set adapter to the list
        myFoodsList.setAdapter(myFoodListAdapter);

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCart = new Intent(MainActivity.this, ViewCart.class);
                startActivity(goToCart);
            }
        });

        foodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cuisineRadioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedItemId) {
                RadioButton selectedRadioButton = findViewById(checkedItemId);
                String selectedRadioButtonValue = selectedRadioButton.getText().toString();
                Log.i("Snath", "Value "+selectedRadioButtonValue);
                if (selectedRadioButtonValue.equals("All")) {
                    resetRecyclerViewToOriginalList();
                }else {
                    showFilteredListByCuisine(selectedRadioButtonValue);
                }
            }
        });

        sortData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.i("Snath, ", "Checked "+b);
                    Collections.sort(foodList, new Comparator<FoodItem>() {
                        @Override
                        public int compare(FoodItem food1, FoodItem food2) {
                            Log.i("Snath ", "Food "+food1.calculateTotalPrice()+ food2.calculateTotalPrice());
                            double totalPrice1 = food1.calculateTotalPrice();
                            double totalPrice2 = food2.calculateTotalPrice();

                            // Round to 2 decimal places
                            double roundedTotalPrice1 = Math.round(totalPrice1 * 100.0) / 100.0;
                            double roundedTotalPrice2 = Math.round(totalPrice2 * 100.0) / 100.0;

                          return Double.compare(roundedTotalPrice1,roundedTotalPrice2);
                        }
                    });
                 sortedList = new ArrayList<>(foodList);
                }
                else{
                    sortedList = new ArrayList<FoodItem>(originalList);
                }
//                for(FoodItem food : foodList){
//                    Log.i("Snath", "food price"+food.calculateTotalPrice() + food.getFoodName() + foodList.size());
//                }

                myFoodListAdapter.updateFoodList(sortedList);
            }
        });
    }

    private void resetRecyclerViewToOriginalList() {
        myFoodListAdapter.updateFoodList(originalList);
    }

    private void showFilteredListByCuisine(String selectedRadioButtonValueForCuisine) {
        List<FoodItem> filteredList = new ArrayList<>();

        for(FoodItem foodItem : originalList){
            if(foodItem.getFoodCuisine().equalsIgnoreCase(selectedRadioButtonValueForCuisine))
                filteredList.add(foodItem);
        }
        foodList = (ArrayList<FoodItem>) filteredList;
        Log.i("Snath, Size", "size"+foodList.size());
        //notify the adapter about changes
        myFoodListAdapter.updateFoodList(filteredList);
    }

}



