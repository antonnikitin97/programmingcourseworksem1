package com.company;

import java.util.ArrayList;
import java.util.Random;

public class ZooKeeper {

    protected Zoo zoo;
    protected Enclosure enclosureKeeperAssignedTo;
    protected FoodStore foodStoreKeeperAssignedTo;
    protected FoodStore zooFoodStore;
    protected Random randomGen = new Random();

    public ZooKeeper()
    {
        // Default constructor for zookeeper
    }

    public ZooKeeper(Zoo zoo, Enclosure enclosureToSet, FoodStore zooFoodStore) {
        this.enclosureKeeperAssignedTo = enclosureToSet;
        this.foodStoreKeeperAssignedTo = enclosureKeeperAssignedTo.getFoodStore();
        this.zooFoodStore = zooFoodStore;
        this.zoo = zoo;
    }

    public Boolean aMonthPasses() {
        return true;
    }

    public void getFoodFromZooStore() // Gets 2 items from the zooFoodStore and puts it into the enclosure foodstore.
    {
        for(String s : this.zoo.)
    }
}


/*
protected void getFoodFromZoo()
    {
        Integer numberOfItemsTaken = 0;

        while(numberOfItemsTaken <= 20)
        {
            for(String s : this.zoo.getAvailableFoodInZoo())
            {
                Integer randomNumber;

                if(this.enclosureKeeperAssignedTo.foodStore.foodStorage.get(s).equals(0))
                {
                    System.out.format("We have run out of %s, moving items from from zoo store...", s);
                    randomNumber = randomGen.nextInt(5);
                    if(zooFoodStore.takeFood(s, randomNumber))
                    {
                        this.enclosureKeeperAssignedTo.foodStore.addFood(s, randomNumber);
                        System.out.format("Quantity %s of %s has been added to the enclosure foodstore!", randomNumber, s);
                        numberOfItemsTaken += randomNumber;
                    }
                }
            }
        }
    }
 */