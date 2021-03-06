/**
ZooKeeper class houses all basic  functionality for the zookeeper, includes methods to get food from the zoo enclosure into the enclosure store, remove waste from
the enclosure and a 'aMonthPasses' method. This is the base class for the Physio and Play ZooKeeper classes. As certain treats can only be administered by certain
types of zookeepers, the base class has a 'keeperLabel' that all the other types will use. Each different keeper has a certain 'value' as follows:

"default" - default zookeeper
"play" - play keeper
"physio" - physio keeper

These will be used when deciding if a certain zookeeper can perform a given treat
*/

package com.company;

import java.util.ArrayList;
import java.util.Random;

public class ZooKeeper {

    protected Zoo zoo;
    protected Enclosure enclosureKeeperAssignedTo;
    protected FoodStore foodStoreKeeperAssignedTo;
    protected FoodStore zooFoodStore;
    private String keeperLabel;
    private Random generator = new Random();

    public ZooKeeper()
    {
        // Default constructor for zookeeper
    }

    public ZooKeeper(Zoo zoo, String keeperLabel) {
        this.zooFoodStore = zoo.zooFoodStore;
        this.zoo = zoo;
        this.keeperLabel = keeperLabel;
        assignEnclosure();
    }

    /**
    Assigns the zooKeeper randomly to an enclosure.
    */
    public void assignEnclosure()
    {
        ArrayList<Enclosure> validEnclosures = new ArrayList<>();
        validEnclosures = zoo.getValidEnclosures();
        this.enclosureKeeperAssignedTo = validEnclosures.get(generator.nextInt(validEnclosures.size()));
        this.foodStoreKeeperAssignedTo = this.enclosureKeeperAssignedTo.getFoodStore();
    }

    /**
    This method makes the zoo keeper do its duties every month.
    */
    public Boolean aMonthPasses()
    {
        getFoodFromZooStore();
        treatTwoAnimals();
        removedWasteFromEnclosure();
        return true;
    }

    /**
    Gets 2 items from the zooFoodStore and puts it into the enclosure foodStore.
    */
    public void getFoodFromZooStore()
    {
        Integer foodTaken = 0;
        Integer foodRand = generator.nextInt(3);
        for (String foodInHashMap : this.zooFoodStore.getFoodStorage().keySet())
        {
            while(foodRand.equals(0)){
                foodRand = generator.nextInt(6);
            }
            if (this.zooFoodStore.takeFood(foodInHashMap, foodRand)) {
                this.foodStoreKeeperAssignedTo.addFood(foodInHashMap, foodRand);
                System.out.format("\n%s lots of %s from the zoo store has been added to enclosure %s!",foodRand, foodInHashMap, zoo.enclosures.indexOf(this.enclosureKeeperAssignedTo));
                foodTaken += foodRand;
            }else{
                System.out.format("\nNot enough of %s in the zoo store to add to enclosure!", foodInHashMap);
            }
            if(foodTaken.equals(20)){
                System.out.format("\n20 items of food has been moved by this keeper! (%s)", this.getKeeperLabel());
                break;
            }
        }
    }

    public void removedWasteFromEnclosure()
    {
        if(this.enclosureKeeperAssignedTo.getWasteSize() >= 20) {
            this.enclosureKeeperAssignedTo.removeWaste(20);
            System.out.format("20 waste has been removed from the enclosure! There is now %s waste in the enclosure!", enclosureKeeperAssignedTo.getWasteSize());
        }else{
            System.out.format("%s waste has been removed from the enclosure! There is now %s waste in the enclosure!\n", this.enclosureKeeperAssignedTo.getWasteSize() ,this.enclosureKeeperAssignedTo.getWasteSize() - enclosureKeeperAssignedTo.getWasteSize());
            this.enclosureKeeperAssignedTo.removeWaste(this.enclosureKeeperAssignedTo.getWasteSize());
        }
    }

    /**
    This method allows the zookeeper to treat up to two animals, it does this by generating two random numbers between 0 and the Size of the enclosure (to avoid
    out of bounds exceptions). The keeper then treats this animal. (Twice)
    */
    private void treatTwoAnimals()
    {
        if(this.enclosureKeeperAssignedTo.checkIfAllDead()) {
            System.out.format("\n%S Can't treat as all animals in my enclosure are dead!\n", this.getKeeperLabel());
        }else{
            System.out.format("\n--- %s NOW TREATING IN ENCLOSURE %s ---\n", this.keeperLabel, zoo.enclosures.indexOf(this.enclosureKeeperAssignedTo));
            for(int i = 0; i < 2; i ++ )
            {
                //Loop that executes twice (the keeper can only treat 2 animals)
                System.out.format("Treating animal #%s: " , i + 1);
                this.enclosureKeeperAssignedTo.getAnimal(generator.nextInt(this.enclosureKeeperAssignedTo.getSize())).treat(this.getKeeperLabel());
                System.out.println();
            }
        }
    }

    protected String getKeeperLabel()
    {
        return this.keeperLabel;
    }

    protected void setKeeperLabel(String labelToSet)
    {
        this.keeperLabel = labelToSet;
    }

}