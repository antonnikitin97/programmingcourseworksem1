package com.company;

import java.util.HashMap;

public class FoodStore
{
    protected HashMap<String, Integer> foodStorage;

    // FoodStore constructor creates a new HashMap instance and assigns it to 'foodStorage'
    public FoodStore()
    {
    	foodStorage = new HashMap<>();
    }

    //Here we are adding food to the foodstore.
    public void addFood(String name, Integer quantityToAdd)
    {
        this.foodStorage.put(name, foodStorage.get(name) + quantityToAdd);
        System.out.format("%s of %s has been added to the store! There are now %s of this food in the store!", quantityToAdd, name, foodStorage.get(name));
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
    public void giveHealthAndAddWasteBasedOnFood(String food, Enclosure enclosure, Animal animal)
    {
        switch(food)
        {
            case "hay":
                animal.health += 1;
                enclosure.addWaste(4);
                break;
            case "steak":
                animal.health += 3;
                enclosure.addWaste(4);
                break;
            case "fruit":
                animal.health += 2;
                enclosure.addWaste(3);
                break;
            case "celery":
                animal.health += 0;
                enclosure.addWaste(1);
                break;
            case "fish":
                animal.health += 3;
                enclosure.addWaste(2);
                break;
            case "ice cream":
                animal.health += 1;
                enclosure.addWaste(3);
                break;
        }
    }

    public String[] getAvailableFoodInZoo()
    {
        return new String[]{"hay" , "steak" , "fruit" , "celery" , "fish" , "ice cream"};
    }
}
