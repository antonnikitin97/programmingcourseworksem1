/*
Zoo class that models the actual zoo, contains fields that represent the enclosures, and the zookeepers
*/

package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Zoo
{
    protected Enclosure[] enclosures;
    protected ArrayList<ZooKeeper> zooKeepers;
    protected FoodStore zooFoodStore;
    protected Simulation mySim;
    protected Random ran = new Random();

    public Zoo(Simulation mySim, FoodStore zooFoodStore, Enclosure[] enclosureList, ArrayList<ZooKeeper> keeperArrayList)
    {
        this.mySim = mySim;
        this.zooFoodStore = zooFoodStore;
        this.enclosures = enclosureList;
        this.zooKeepers = keeperArrayList;
        for(String s : this.zooFoodStore.getAvailableFoodInZoo())
        {
            zooFoodStore.foodStorage.put(s , 10);
        }
    }

    /*
    This method first displays the statistics for the whole zoo, which includes the number of months that have passed, the number of enclosures in the zoo,
    the animals in those enclosure and their health and age. It then proceeds to check through the list of enclosures, and returns the ones that have animals
    in them. It then calls the 'aMonthPasses' method on these enclosures. After this has finished. It then checks to see whether these enclosures STILL
    has animals in them, if they don't, all animals have died and the simulation terminates. If they do then it then proceeds to iterate over the list of
    ZooKeepers and calls their 'aMonthPasses' method. After this it orders additional food for the zoo and then increments the number of months passed
    by one.
    */
    public void aMonthPasses()
    {
        displayStats();
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

    /*
    Iterates over the list of enclosures and checks to see whether they are empty,
    returns the ones which are not. If the method returns a list containing no enclosures, then this means
    all the animals are dead.
    */
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

    //This method will order additional food for the zoo food store
    public void orderAdditionalFood()
    {
        for(String s : this.zooFoodStore.foodStorage.keySet())
        {
            this.zooFoodStore.addFood(s, 2);
            System.out.format("2 lots of %s has been ordered for the zoo store, there are now %s of %s in the store!\n", s , this.zooFoodStore.getFoodQuantity(s), s);
        }
    }

    /*
    Displays statistics such as the months passed, and the number of enclosures in the zoo
    It then proceeds to call a method which displays information about the animals in the
    enclosures.
    */
    public void displayStats()
    {
        System.out.format("#### ZOO STATUS ###\nMonths Passed: %s\nNumber of Enclosures: %s\n", mySim.getMonthsPassed() , this.enclosures.length);
        getEnclosureStatus();
    }

    /*
    This method iterates over all the enclosures in the zoo and prints out information about them including
    the types of animal in the enclosure, their age and their health.
    */
    private void getEnclosureStatus()
    {
        Integer currentEnclosure = 0;

        for(Enclosure e : this.enclosures)
        {
            System.out.format("### Enclosure %s ###\nAnimals in Enclosure: %s\nWaste size: %s\n", currentEnclosure, e.size(), e.getWasteSize());
            e.printTypesOfAnimalInEnclosure();
            System.out.println("#######################################");
            currentEnclosure += 1;
        }
    }
}