package com.company;

import java.util.ArrayList;

public class Zoo
{
    protected Enclosure[] enclosures;
    protected ArrayList<ZooKeeper> zooKeepers;
    protected FoodStore zooFoodStore;

    public Zoo()
    {
        zooFoodStore = new FoodStore();
        foodAvailable = this.zooFoodStore.getAvailableFoodInZoo();
        for(String s : foodAvailable)
        {
            zooFoodStore.foodStorage.put(s , 200);
        }
    }

    public void aMonthPasses() // This method will call the 'aMonthPasses' on each enclosure, it does this by iterating over the array of enclosures, which will in turn
    {                          // call the 'aMonthPasses' method on each animal in the enclosure.
        for(Enclosure e : enclosures)
        {
            e.aMonthPasses();
        }
    }

    public void populateZoo()
    {
        enclosures = new Enclosure[1];
        enclosures[1].addAnimal(new Lion());
        enclosures[1].addAnimal(new Lion());
        enclosures[1].addAnimal(new Lion());
        enclosures[1].addAnimal(new Lion());
    }

    public String[] getFoodAvailable()
    {
        return new String[]{"celery" , "ice cream" , "steak", "fish", "fruit", "hay"};
    }

}