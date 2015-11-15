package com.company;

import java.util.HashMap;

public class FoodStore
{
    protected HashMap<String, Integer> foodStorage;

    // FoodStore constructor creates a new HashMap instance and assigns it to 'foodStorage'
    public FoodStore()
    {
    	foodStorage = new HashMap<>();
        populateZeroFood();
    }

    public void populateZeroFood()
    {
        for(String s : getAvailableFoodInZoo())
        {
            this.foodStorage.put(s, 0);
        }
    }

    //Here we are adding food to the foodstore.
    public void addFood(String name, Integer quantityToAdd)
    {
        this.foodStorage.put(name, foodStorage.get(name) + quantityToAdd);
        //System.out.format("\n%s of %s has been added to the store! There are now %s of this food in the store!\n", quantityToAdd, name, foodStorage.get(name));
    }
    
    public Boolean takeFood(String name)
    {
    	return takeFood(name, 1);
    }

    public Boolean takeFood(String name, Integer quantity)
    {
        if(checkIsEnoughFood(name, quantity)) {
            this.foodStorage.put(name, this.getFoodQuantity(name) - quantity);
            return true;
        }else{
            System.out.println("Not enough of the specified food in foodstore!");
            return false;
        }
    }

    // Here we are checking if there is enough food in the foodstore, we'll return true if there is, and false if there isn't.
    public Boolean checkIsEnoughFood(String nameOfFood, Integer quantity)
    {
        return (getFoodQuantity(nameOfFood) == null || getFoodQuantity(nameOfFood) >= quantity);
    }

    public Integer getFoodQuantity(String name)
    {
        return this.foodStorage.get(name);
    }

    // Here we are looking to see what type health increase and waste adding is required based on the food eaten.
    // We are comparing the value in 'food' against multiple cases to decide on the best action.

    public String[] getAvailableFoodInZoo()
    {
        return new String[]{"hay" , "steak" , "fruit" , "celery" , "fish" , "ice cream"};
    }

}
