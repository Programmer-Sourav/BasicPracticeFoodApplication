package com.practice.portfolio.basicpracticefoodapplication.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.portfolio.basicpracticefoodapplication.R;
import com.practice.portfolio.basicpracticefoodapplication.model.CartDetails;
import com.practice.portfolio.basicpracticefoodapplication.model.CartItem;
import com.practice.portfolio.basicpracticefoodapplication.model.FoodItem;
import com.practice.portfolio.basicpracticefoodapplication.model.Price;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    private ArrayList<FoodItem> myFoodList;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    public FoodListAdapter(ArrayList<FoodItem> myFoodsList) {
        Log.i("Snath, MyFoodList ", "List "+myFoodsList.size());
        this.myFoodList = myFoodsList;
    }

    public void updateFoodList(List<FoodItem> filteredList) {
        myFoodList.clear();
        myFoodList.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create a new viewHolder for each item to populate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_row, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        //get current item details
        FoodItem foodItemAtPosition = myFoodList.get(position);
      //bind to view
        holder.menuFoodName.setText(foodItemAtPosition.getFoodName());
        holder.menuFoodType.setText(foodItemAtPosition.getFoodType());
        holder.menuFoodCuisine.setText(foodItemAtPosition.getFoodCuisine());
        Price price = foodItemAtPosition.getPrice();
        double totalPrice = price.calculateTotalPrice();
        holder.menuFoodPrice.setText("Rs. "+String.valueOf(totalPrice));
        Log.i("Snath, Price", "Price "+foodItemAtPosition.getPrice());
        holder.foodImage.setImageResource(foodItemAtPosition.getImageResource());
    }

    @Override
    public int getItemCount() {
        //return length of the current list
        return myFoodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView menuFoodName;
        TextView menuFoodPrice;
        TextView menuFoodType;
        TextView menuFoodCuisine;
        ImageView foodImage;
        Button menuAddToCart;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
             menuFoodName = itemView.findViewById(R.id.foodname);
             menuFoodPrice = itemView.findViewById(R.id.foodprice);
             menuFoodType = itemView.findViewById(R.id.foodtype);
             menuFoodCuisine = itemView.findViewById(R.id.foodcuisine);
             menuAddToCart = itemView.findViewById(R.id.addToCart);
             foodImage = itemView.findViewById(R.id.imageOfTheItem);

             menuAddToCart.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Toast.makeText(itemView.getContext(), "Clicked"+getAdapterPosition()+1, Toast.LENGTH_SHORT).show();
                     //get the item on button click and add to cart
                     CartDetails cart = new CartDetails();
                     FoodItem food = myFoodList.get(getAdapterPosition());
                     CartItem cartItem = new CartItem(food.getId(), food.getFoodName(), food.getFoodCuisine(), food.getFoodType(), food.getPrice(), food.getImageResource());
                     cartItem.setQuantityOfItem(1);
                     cartItems.add(cartItem);
                     cart.setCartItem(cartItems);
                     Log.i("Snath, CartItem on add ", "Length"+cartItems.size());
                     for (FoodItem foodItem : cartItems) {
                         Log.i("CartItem", "ID: " + foodItem.getId());
                         Log.i("CartItem", "Food Name: " + foodItem.getFoodName());
                         Log.i("CartItem", "Cuisine: " + foodItem.getFoodCuisine());
                         Log.i("CartItem", "Food Type: " + foodItem.getFoodType());
                       ;

                         Price price = foodItem.getPrice();
                         Log.i("CartItem", "Price - MRP: " + price.getMrp());
                         Log.i("CartItem", "Price - Discount Percentage: " + price.getDiscountPercentage());
                         Log.i("CartItem", "Price - GST: " + price.getGst());
                         Log.i("CartItem", "Price - Service Tax: " + price.getServiceTax());
                     }
                 }
             });
        }
    }
}
