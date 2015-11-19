package com.company;

public class PhysioZooKeeper extends ZooKeeper
{
    public PhysioZooKeeper(Zoo zoo)
    {
        this.zoo = zoo;
        this.zooFoodStore = zoo.zooFoodStore;
        this.setKeeperLabel("physio");
        this.assignEnclosure();
    }
}
