/*
Class that represents a foodStore. Contains a HashMap to store the food, and various methods to access the store, put food
into the store, take food out and enquire about the food in the store.
*/

package com.company;

import java.util.HashMap;

public class FoodStore
{
    private HashMap<String, Integer> foodStorage;

    /**
    FoodStore constructor creates a new HashMap instance and assigns it to 'foodStorage'.
    Also populates the foodStore with 0 of each item.
    */
    public FoodStore()
    {
    	foodStorage = new HashMap<>();
        populateZeroFood();
    }

    /**
    Populates the foodStore with 0 of each item when it gets initially created.
    */
    private void populateZeroFood()
    {
        for(String s : getAvailableFoodInZoo())
        {
            this.foodStorage.put(s, 0);
        }
    }

    /**
    Method to add food to the food store. Takes two parameters, one for the name, one for the quantity.
    */
    public void addFood(String name, Integer quantityToAdd)
    {
        this.foodStorage.put(name, foodStorage.get(name) + quantityToAdd);
    }

    /**
    Method to remove food from the foodstore. Returns true if the operation was successful, and false
    if it failed. Method has been overloaded. One that only takes one item of food, and the other which takes how
    ever many is passed in.
    */
    public Boolean takeFood(String name)
    {
    	return takeFood(name, 1);
    }
    /**
    Overloaded method...
    */
    public Boolean takeFood(String name, Integer quantity)
    {
        if(checkIsEnoughFood(name, quantity)) {
            this.foodStorage.put(name, this.getFoodQuantity(name) - quantity);
            return true;
        }else{
            return false;
        }
    }

    /**
    Here we are checking if there is enough food of the given type in the foodstore,
    we'll return true if there is, and false if there isn't.
    */
    private Boolean checkIsEnoughFood(String nameOfFood, Integer quantity)
    {
        return (getFoodQuantity(nameOfFood) == null || getFoodQuantity(nameOfFood) >= quantity);
    }

    public Integer getFoodQuantity(String name)
    {
        return this.foodStorage.get(name);
    }

    /**
    Returns a string array of all the possible types of food in the zoo.
    */
    public String[] getAvailableFoodInZoo()
    {
        return new String[]{"hay" , "steak" , "fruit" , "celery" , "fish" , "ice cream"};
    }

    public HashMap<String, Integer> getFoodStorage()
    {
        return this.foodStorage;
    }
}
