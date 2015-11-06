/*
ZooKeeper class houses all basic  functionality for the zookeeper, includes methods to get food from the zoo enclosure into the enclosure store, remove waste from
the enclosure and a 'aMonthPasses' method. This is the base class for the Physio and Play ZooKeeper classes. As certain treats can only be administered by certain
types of zookeepers, the base class has a 'keeperLabel' that all the other types will use. Each different keeper has a certain 'value' as follows:

0 - default zookeeper
1 - play keeper
2 - physio keeper

These will be used when deciding if a certain zookeeper can perform a given treat

*/

package com.company;

import java.util.Random;

public class ZooKeeper {

    protected Zoo zoo;
    protected Enclosure enclosureKeeperAssignedTo;
    protected FoodStore foodStoreKeeperAssignedTo;
    protected FoodStore zooFoodStore;
    protected Random randomGen = new Random();
    protected Integer keeperLabel;

    public ZooKeeper()
    {
        // Default constructor for zookeeper
    }

    public ZooKeeper(Zoo zoo, Enclosure enclosureToSet, FoodStore zooFoodStore) {
        this.enclosureKeeperAssignedTo = enclosureToSet;
        this.foodStoreKeeperAssignedTo = enclosureKeeperAssignedTo.getFoodStore();
        this.zooFoodStore = zooFoodStore;
        this.zoo = zoo;
        this.keeperLabel = 0;
    }

    public Boolean aMonthPasses() {
        return true;
    }

    public void getFoodFromZooStore() // Gets 2 items from the zooFoodStore and puts it into the enclosure foodstore.
    {
        for(String s : this.zooFoodStore.foodStorage.keySet())
        {
            if(this.zooFoodStore.takeFood(s, 2)) {
                this.foodStoreKeeperAssignedTo.addFood(s, 2);
                System.out.format("\n2 lots of %s has been added to the enclosure!\n", s);
            }else{
                System.out.format("\nNot enough of %s in the zoo store to add to enclosure!\n", s);
            }
        }
    }

    public void removedWasteFromEnclosure()
    {
        if(this.enclosureKeeperAssignedTo.getWasteSize() >= 20) {
            this.enclosureKeeperAssignedTo.removeAnimalWaste(20);
        }else{
            this.enclosureKeeperAssignedTo.removeAnimalWaste(this.enclosureKeeperAssignedTo.getWasteSize());
        }
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