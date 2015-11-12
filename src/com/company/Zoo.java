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
    protected Simulation mySim;

    public Zoo(Simulation mySim)
    {
        this.mySim = mySim;
        zooFoodStore = new FoodStore();
        for(String s : this.zooFoodStore.getAvailableFoodInZoo())
        {
            zooFoodStore.foodStorage.put(s , 10);
        }
    }

    // This method will call the 'aMonthPasses' on each enclosure, it does this by iterating over the array of enclosures, which will in turn
    // call the 'aMonthPasses' method on each animal in the enclosure.
    public void aMonthPasses()
    {
        mySim.displayStats();

        for(Enclosure e : enclosures)
        {
            e.aMonthPasses();
        }

        System.out.println();
        System.out.println("\n--- ZOO KEEPER DUTY!!! ---");

        for(ZooKeeper k : zooKeepers)
        {
            k.aMonthPasses();
        }

        System.out.println();
        System.out.println("\n--- ZOO ADMIN ---");

        orderAdditionalFood();

        mySim.incrementMonth();

    }

    public void createEnclosuresAndZooKeepersTEST()
    {
        enclosures = new Enclosure[1];
        enclosures[0] = new Enclosure();
        zooKeepers.add(new ZooKeeper(this, "default"));
        zooKeepers.add(new PlayZooKeeper(this));
        zooKeepers.add(new PhysioZooKeeper(this));

        for(ZooKeeper k : zooKeepers)
        {
            k.assignEnclosure(enclosures[0]);
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
            System.out.format("1 lots of %s has been ordered for the zoo store, there are now %s of %s in the store!\n", s , this.zooFoodStore.getFoodQuantity(s), s);
        }
    }

}