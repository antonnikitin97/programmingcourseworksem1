/*
ZooKeeper class houses all basic  functionality for the zookeeper, includes methods to get food from the zoo enclosure into the enclosure store, remove waste from
the enclosure and a 'aMonthPasses' method. This is the base class for the Physio and Play ZooKeeper classes. As certain treats can only be administered by certain
types of zookeepers, the base class has a 'keeperLabel' that all the other types will use. Each different keeper has a certain 'value' as follows:

"default" - default zookeeper
"play" - play keeper
"physio" - physio keeper

These will be used when deciding if a certain zookeeper can perform a given treat

*/

package com.company;

public class ZooKeeper {

    protected Zoo zoo;
    protected Enclosure enclosureKeeperAssignedTo;
    protected FoodStore foodStoreKeeperAssignedTo;
    protected FoodStore zooFoodStore;
    protected String keeperLabel;

    public ZooKeeper()
    {
        // Default constructor for zookeeper
    }

    public ZooKeeper(Zoo zoo, Enclosure enclosureToSet, String keeperLabel) {
        this.enclosureKeeperAssignedTo = enclosureToSet;
        this.foodStoreKeeperAssignedTo = enclosureKeeperAssignedTo.getFoodStore();
        this.zooFoodStore = zoo.zooFoodStore;
        this.zoo = zoo;
        this.keeperLabel = keeperLabel;
        //this.addToListOfZooKeepers();
    }

    /*private void addToListOfZooKeepers()
    {
        this.zoo.zooKeepers.add(this);
    }*/

    public Boolean aMonthPasses()
    {
        getFoodFromZooStore();
        removedWasteFromEnclosure();
        this.zoo.orderAdditionalFood();
        for(Animal a : enclosureKeeperAssignedTo.animalsInEnclosure)
        {
            a.treat(this.keeperLabel);
        }
        return true;
    }

    // Gets 2 items from the zooFoodStore and puts it into the enclosure foodStore.
    public void getFoodFromZooStore()
    {
        for(String foodInHashMap : this.zooFoodStore.foodStorage.keySet())
        {
            if(this.zooFoodStore.takeFood(foodInHashMap, 2)) {
                this.foodStoreKeeperAssignedTo.addFood(foodInHashMap, 2);
                System.out.format("\n2 lots of %s has been added to the enclosure!\n", foodInHashMap);
            }else{
                System.out.format("\nNot enough of %s in the zoo store to add to enclosure!\n", foodInHashMap);
            }
        }
    }

    public void removedWasteFromEnclosure()
    {
        if(this.enclosureKeeperAssignedTo.getWasteSize() >= 20) {
            this.enclosureKeeperAssignedTo.removeWaste(20);
        }else{
            this.enclosureKeeperAssignedTo.removeWaste(this.enclosureKeeperAssignedTo.getWasteSize());
        }
    }

    public void treatAnimalsInEnclosre()
    {

    }

}