/**
This class defines a 'play' keeper, contains a no fields but one constructor to initialize values for the keeper.
*/
package com.company;

public class PlayZooKeeper extends ZooKeeper
{
    public PlayZooKeeper (Zoo zoo)
    {
        this.zoo = zoo;
        this.zooFoodStore = zoo.zooFoodStore;
        this.setKeeperLabel("play");
        this.assignEnclosure();
    }
}
