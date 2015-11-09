/*
Zoo class that models the actual zoo, contains fields that represent the enclosures
*/

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
        for(String s : this.zooFoodStore.getAvailableFoodInZoo())
        {
            zooFoodStore.foodStorage.put(s , 200);
        }
    }

    // This method will call the 'aMonthPasses' on each enclosure, it does this by iterating over the array of enclosures, which will in turn
    // call the 'aMonthPasses' method on each animal in the enclosure.
    public void aMonthPasses()
    {
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

    // This method will order additional food for the zoo food store
    public void orderAdditionalFood()
    {
        for(String s : this.zooFoodStore.foodStorage.keySet())
        {
            this.zooFoodStore.addFood(s, 10);
            System.out.format("10 lots of %s has been ordered for the zoo store, there are now %s of %s in the store!", s , this.zooFoodStore.getFoodQuantity(s), s);
        }
    }


}