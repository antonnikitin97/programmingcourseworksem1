package com.company;

public class PlayZooKeeper extends ZooKeeper
{
    public PlayZooKeeper (Zoo zoo)
    {
        this.zoo = zoo;
        this.zooFoodStore = zoo.zooFoodStore;
        this.keeperLabel = "play";
    }
}
