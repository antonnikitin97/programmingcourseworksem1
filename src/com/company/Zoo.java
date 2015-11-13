/*
Zoo class that models the actual zoo, contains fields that represent the enclosures, and the zookeepers
*/

package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Zoo
{
    protected Enclosure[] enclosures;
    protected ArrayList<ZooKeeper> zooKeepers = new ArrayList<>();
    protected FoodStore zooFoodStore;
    protected Simulation mySim;
    protected Random ran = new Random();

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
        for(Enclosure e : getValidEnclosures())
        {
            System.out.format("\nEnclosure %s animals eating!!!\n\n", getValidEnclosures().indexOf(e));
            e.aMonthPasses();
        }
        if(getValidEnclosures().size() == 0)
        {
            System.out.println("\n###All animals have died! Simulation ending...###\n");
            System.exit(0);
        }
        System.out.println("\n--- ZOO KEEPER DUTY!!! ---");
        for(ZooKeeper k : zooKeepers)
        {
            k.aMonthPasses();
        }
        System.out.println("\n--- ZOO ADMIN ---");
        orderAdditionalFood();
        mySim.incrementMonth();
    }

    public void createEnclosuresAndZooKeepersTEST()
    {
        enclosures = new Enclosure[2];
        enclosures[0] = new Enclosure();
        enclosures[1] = new Enclosure();
        zooKeepers.add(new ZooKeeper(this, "default"));
        zooKeepers.add(new PlayZooKeeper(this));
        zooKeepers.add(new PhysioZooKeeper(this));
        zooKeepers.add(new ZooKeeper(this, "default"));
        zooKeepers.add(new PlayZooKeeper(this));
        zooKeepers.add(new PhysioZooKeeper(this));
        zooKeepers.add(new ZooKeeper(this, "default"));
        zooKeepers.add(new PlayZooKeeper(this));
        zooKeepers.add(new PhysioZooKeeper(this));

        for(ZooKeeper k : zooKeepers)
        {
            if(ran.nextInt(10) % 2 == 0) {
                k.assignEnclosure(enclosures[0]);
            }else{
                k.assignEnclosure(enclosures[1]);
            }
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
        for(Animal a : enclosures[1].animalsInEnclosure)
        {
            a.enclosureAnimalResidesIn = enclosures[1];
        }
    }

    public ArrayList<Enclosure> getValidEnclosures()
    {
        ArrayList<Enclosure> listOfValid = new ArrayList<>();

        for(Enclosure e : enclosures)
        {
            if(e.animalsInEnclosure.size() == 0) {
                continue;
            }else{
                listOfValid.add(e);
            }
        }

        return listOfValid;
    }

    // This method will order additional food for the zoo food store
    public void orderAdditionalFood()
    {
        for(String s : this.zooFoodStore.foodStorage.keySet())
        {
            this.zooFoodStore.addFood(s, 2);
            System.out.format("2 lots of %s has been ordered for the zoo store, there are now %s of %s in the store!\n", s , this.zooFoodStore.getFoodQuantity(s), s);
        }
    }
}