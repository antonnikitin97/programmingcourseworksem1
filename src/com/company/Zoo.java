/*
Zoo class that models the actual zoo, contains fields that represent the enclosures
*/

package com.company;

import java.util.ArrayList;

public class Zoo
{
    protected Enclosure[] enclosures;
    protected ArrayList<ZooKeeper> zooKeepers = new ArrayList<>();
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

    public void createEnclosuresAndZooKeepersTEST()
    {
        enclosures = new Enclosure[1];
        enclosures[0] = new Enclosure();
        zooKeepers.add(new ZooKeeper());
        zooKeepers.add(new PlayZooKeeper());
        zooKeepers.add(new PhysioZooKeeper());

        for(ZooKeeper k : zooKeepers)
        {
            k.enclosureKeeperAssignedTo = enclosures[0];
        }
    }

    public void populateZooTEST()
    {
        for(Enclosure e : enclosures)
        {
            e.addAnimal(new Bear());
            e.addAnimal(new Chimpanzee());
            e.addAnimal(new Elephant());
            e.addAnimal(new Giraffe());
            e.addAnimal(new Gorilla());
            e.addAnimal(new Lion());
            e.addAnimal(new Penguin());
            e.addAnimal(new Tiger());
        }

        for(Animal a : enclosures[0].animalsInEnclosure)
        {
            a.enclosureAnimalResidesIn = enclosures[0];
        }


    }

    // This method will order additional food for the zoo food store
    public void orderAdditionalFood()
    {
        for(String s : this.zooFoodStore.foodStorage.keySet())
        {
            this.zooFoodStore.addFood(s, 1);
            System.out.format("10 lots of %s has been ordered for the zoo store, there are now %s of %s in the store!", s , this.zooFoodStore.getFoodQuantity(s), s);
        }
    }

}