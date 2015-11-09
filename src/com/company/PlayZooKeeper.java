package com.company;

/**
 * Created by Anton on 30/10/2015.
 */
public class PlayZooKeeper extends ZooKeeper
{
    public PlayZooKeeper(Zoo zoo, Enclosure enclosureToSet, FoodStore zooFoodStore)
    {
        this.enclosureKeeperAssignedTo = enclosureToSet;
        this.foodStoreKeeperAssignedTo = enclosureKeeperAssignedTo.getFoodStore();
        this.zooFoodStore = zooFoodStore;
        this.zoo = zoo;
        this.keeperLabel = 1;
    }
}
