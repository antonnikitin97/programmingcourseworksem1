package com.company;

public class PhysioZooKeeper extends ZooKeeper
{
    public PhysioZooKeeper(Zoo zoo, Enclosure enclosureToSet, FoodStore zooFoodStore)
    {
        this.enclosureKeeperAssignedTo = enclosureToSet;
        this.foodStoreKeeperAssignedTo = enclosureKeeperAssignedTo.getFoodStore();
        this.zooFoodStore = zooFoodStore;
        this.zoo = zoo;
        this.keeperLabel = 2;
    }
}
